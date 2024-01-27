import unittest
from unittest.mock import patch
from flask import json
from dest_finder import app, process_gpt_response, generate_unique_id

class TravelPlanTestCase(unittest.TestCase):

    def setUp(self):
        self.app = app.test_client()
        self.app.testing = True

    def test_find_destination_success(self):
        with patch('app.client.chat.completions.create') as mock_create:
            mock_create.return_value = type(
                'obj', (object,), {
                    "choices": [type('obj', (object,), {"message": type('obj', (object,), {"content": "Test Response"})})]
                }
            )
            response = self.app.post('/submit-data', json={
                "plan_name": "Test Plan",
                "question1": "Answer1",
                "question2": "Answer2"
            })
            self.assertEqual(response.status_code, 200)
            self.assertIn("Test Response", response.get_data(as_text=True))

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

