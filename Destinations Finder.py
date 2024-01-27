from flask import Flask, render_template, request
from flask_cors import CORS
from openai import OpenAI
from dotenv import load_dotenv
load_dotenv()

app = Flask(__name__)
CORS(app)

client = OpenAI()
model="gpt-3.5-turbo"
@app.route('/submit-data', method=['POST'])
def receive_answer():
    data = request.json
    reply = client.chat.completions.create(
        messages=[
        {"role": "user","content": data + ".If answer unrelated to question reply no,else ignore"}
        ],
        max_tokens=3
    )
    return reply.choices[0].message.content == "No"
    
def find_destination():
    completion = client.chat.completions.create(
    model="gpt-3.5-turbo",
    messages=[
        {"role": "system", "content": "You are a friendly expert in tourism and travelling that lieks to keep it short"},
        {"role": "user", "content": "Travel guide based on: I like swimming and warm weather. I do not have a big budget, just around £500 and I wanna go away for 10 days."}
    ],
    max_tokens=60
    )

    return completion.choices[0].message.content

if __name__ == '__main__':
    app.run()