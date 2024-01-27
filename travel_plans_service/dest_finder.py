import uuid
from flask import Flask, render_template, request, jsonify
from flask_cors import CORS
from openai import OpenAI
from dotenv import load_dotenv
import json
load_dotenv()

app = Flask(__name__)
CORS(app)

client = OpenAI()


def generate_unique_id():
    return str(uuid.uuid4())


@app.route('/submit-data', methods=['POST'])
def find_destination():
    data = request.json

    plan_name = data.get('plan_name')
    # Safely extract plan_name
    plan_name = data.pop('plan_name', 'Default Plan Name')

    # Prepare the messages for the OpenAI API
    messages = [{"role": "system", "content": "You are an expert in tourism and traveling and create amazing travel plans based on user preferences."}]
    question_number = 0
    for question, answer in data.items():
        question_number += 1
        messages.append({"role": "user", "content": question})
        messages.append({"role": "assistant", "content": answer})
        messages.append(
            {"role": "system", "content": "If the answer is unrelated to the question, reply 'unrelated', else ignore."})

        try:
            completion = client.chat.completions.create(
                model="gpt-3.5-turbo",
                messages=messages,
                max_tokens=60
            )
            if completion.choices[0].message.content.strip().lower() == 'unrelated':
                return jsonify({"error": f"Unrelated answer detected at question number {question_number}"}), 400
        except Exception as e:
            return jsonify({"error": str(e)}), 500

    travel_plan_prompt = """

    Please create a detailed travel plan based on the above questions and answers. Each travel plan consists of:
    A list of destinations, where each destination includes:
    Destination name (name)
    A brief description (description)
    A list of things the traveler likes about this destination
    Local wonders with their names (localWonders)
    Additionally, include an array of routes for each destination, with each route detailing (routeDetails):
    The name of the next destination (route_name)
    Instructions on what to do at the current destination to get to the next one (route_instructions)

  """

    messages.append({"role": "user", "content": travel_plan_prompt})
    try:
        final_completion = client.chat.completions.create(
            model="gpt-3.5-turbo",
            messages=messages,
            max_tokens=150
        )

        processed_travel_plan = process_gpt_response(
            final_completion, plan_name)

        save_travel_plan(processed_travel_plan)

        return jsonify({"response": final_completion.choices[0].message.content})
    except Exception as e:
        return jsonify({"error": str(e)}), 500


def connect_db():

    pass


def save_travel_plan(travel_plan):
    pass


def process_gpt_response(response, plan_name):

    plan_data = {

        "id": generate_unique_id(),
        "name": plan_name,
        "destinations": []
    }

    for item in response:

        destination = {
            "name": item['name'],
            "description": item['description'],
            "images": item['images'],
            "videos": item['videos'],
            "likes": item['likes'],
            "localWonders": item['localWonders'],
            "routes": []  # Initialize the routes array
        }

    for route_info in item['routeDetails']:
        route = {
            "routeName": route_info['route_name'],
            "instructions": route_info["route_instructions"]

        }

        destination["routes"].append(route)
        plan_data["destinations"].append(destination)


if __name__ == '__main__':
    app.run()
