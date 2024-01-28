import unittest
from unittest.mock import patch, MagicMock
from flask import json
from dest_finder import app, process_gpt_response, generate_unique_id


class TravelPlanTestCase(unittest.TestCase):

    def setUp(self):
        self.app = app.test_client()
        self.app.testing = True


    @patch('dest_finder.client.chat.completions.create')
    @patch('dest_finder.process_gpt_response')  
    @patch('dest_finder.save_travel_plan')  
    def test_find_destination_success(self, mock_save_travel_plan, mock_process_gpt_response, mock_openai_create):

        mock_openai_create.return_value.choices = [{
            "message": {
                "content": "Mocked response content"
            }
        }]

        # Mock the processing of the response
        mock_process_gpt_response.return_value = {"processed": "data"}

        # Test data
        test_data = {
            "plan_name": "Test Plan",
            "question1": "Answer1",
            "question2": "Answer2"
        }

        # Make a POST request to the endpoint
        response = self.app.post('/submit-data', json=test_data)
        print(response.json)

        # Assertions
        self.assertEqual(response.status_code, 200)
        mock_openai_create.assert_called_once()
        mock_process_gpt_response.assert_called_once()
        mock_save_travel_plan.assert_called_once_with({"processed": "data"})

    def test_process_gpt_response(self):
        mock_response = {
            "choices": [{
                "message": {
                    "content": {
                        "name": "Paris",
                        "description": "The City of Lights",
                        "images": ["paris1.jpg"],
                        "videos": ["video_url"],
                        "likes": ["Eiffel Tower"],
                        "localWonders": [{"name": "Louvre Museum", "image": "louvre.jpg"}],
                        "routeDetails": [{"route_name": "Tokyo", "route_instructions": "Take a flight to Tokyo"}]
                    }
                }
            }]
        }
        plan_name = "Test Plan"
        result = process_gpt_response(mock_response, plan_name)

        self.assertIsInstance(result, dict)
        self.assertEqual(result["name"], plan_name)
        self.assertIn("destinations", result)
        self.assertTrue(len(result["destinations"]) > 0)

    def test_generate_unique_id(self):
        unique_id = generate_unique_id()
        self.assertIsInstance(unique_id, str)
        self.assertTrue(len(unique_id) > 0)


if __name__ == '__main__':
    unittest.main()
