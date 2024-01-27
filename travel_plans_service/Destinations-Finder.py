from flask import Flask, render_template, request, jsonify
from flask_cors import CORS
from openai import OpenAI
from dotenv import load_dotenv
import json
load_dotenv()

app = Flask(__name__)
CORS(app)

client = OpenAI()

  


@app.route('/submit-data', methods=['POST'])
def find_destination():
    data = request.json

    # Prepare the messages for the OpenAI API
    messages = [{"role": "system", "content": "You are an expert in tourism and traveling and create amazing travel plans based on user preferences."}]
    question_number = 0
    for question, answer in data.items():
        question_number += 1
        messages.append({"role": "user", "content": question})
        messages.append({"role": "assistant", "content": answer})
        messages.append({"role": "system", "content": "If the answer is unrelated to the question, reply 'unrelated', else ignore."})
        
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

    final_prompt = "Please create a detailed travel plan based on the above questions and answers."
    messages.append({"role": "user", "content": final_prompt})
    try:
        final_completion = client.chat.completions.create(
            model="gpt-3.5-turbo",
            messages=messages,
            max_tokens=150  
        )
        return jsonify({"response": final_completion.choices[0].message.content})
    except Exception as e:
        return jsonify({"error": str(e)}), 500    

if __name__ == '__main__':
    app.run()
