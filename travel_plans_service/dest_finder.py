import uuid
from flask import Flask, render_template, request, jsonify
from flask_cors import CORS
from openai import OpenAI
from dotenv import load_dotenv
import json
from flask_wtf import CSRFProtect

load_dotenv()

app = Flask(__name__)


CORS(app)
csrf = CSRFProtect(app)


client = OpenAI()


def generate_unique_id():
    return str(uuid.uuid4())


def get_db_connection():
    pass


def save_travel_plan(planData):
    conn = get_db_connection()
    cursor = conn.cursor()

    # Insert into Plans table
    cursor.execute("INSERT INTO Plans (id, name) VALUES (%s, %s)",
                   (planData['id'], planData['name']))

    for destination in planData['destinations']:
        # Insert into Destinations table
        cursor.execute("INSERT INTO Destinations (plan_id, name, description) VALUES (%s, %s, %s) RETURNING id",
                       (planData['id'], destination['name'], destination['description']))
        destination_id = cursor.fetchone()[0]

        for like in destination['likes']:
            cursor.execute(
                "INSERT INTO Likes (destination_id, like_item) VALUES (%s, %s)", (destination_id, like))

        for wonder in destination['localWonders']:
            cursor.execute("INSERT INTO LocalWonders (destination_id, name, image) VALUES (%s, %s, %s)",
                           (destination_id, wonder['name'], wonder['image']))

    conn.commit()
    cursor.close()
    conn.close()


@app.route('/submit-data', methods=['POST'])
@csrf.exempt
def find_destination():
    data = request.json
    plan_name = data.pop('plan_name', 'Default Plan Name')
    print("Plan Name", plan_name)

    messages = [
        {"role": "system", "content": "You are an expert in tourism and traveling and you are going to plan the dream holiday based on the answers."}]
    for question, answer in data.items():
        messages.extend([
            {"role": "user", "content": question},
            {"role": "user", "content": answer},
        ])

    travel_plan_prompt = """
    Please create a detailed travel plan based on the above questions and answers. Each travel plan consists of:
    A list of destinations, where each destination includes:
    Destination name (name)
    A brief description (description)
    A list of things the traveler likes about this destination (likes)
    A plan for each day that the traveler will spend at this destination (plan)
    Local wonders with their names (localWonders)
    Additionally, include an array of routes for each destination, with each route detailing (routes):
    The name of the next destination (routeName)
    Instructions on what to do at the current destination to get to the next one (instructions)

    The key for all the values should be structured exactly as I inputted e.g. Instructions -> instructions, Description > description
    """

    messages.append({"role": "user", "content": travel_plan_prompt})

    try:
        completion = client.chat.completions.create(
            model="gpt-3.5-turbo", messages=messages, max_tokens=300)
    except Exception as e:
        return jsonify({"error": str(e)}), 500

    try:
        processed_travel_plan = process_gpt_response(completion, plan_name)

        return jsonify({"response": processed_travel_plan})
    except Exception as e:
        return jsonify({"error": str(e)}), 500


def process_gpt_response(response, plan_name):

    # Extract the content from the response
    response_content = response.choices[0].message.content
    parsed_content = json.loads(response_content)

    return {"plan_name": plan_name, "destinations": parsed_content}


if __name__ == '__main__':
    app.run()
