from time import sleep
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from control_api import ControlAPI

import RPi.GPIO as GPIO       
GPIO.setmode(GPIO.BCM)


app = Flask(__name__)
api = Api(app)

controlAPI = ControlAPI

#default web page
@app.route('/')
def index():
    return render_template('/index.html')

#API requests mapping
api.add_resource(controlAPI, '/api/outlet')

                        
if __name__ == '__main__':
    app.run(debug=True, port=5051, host='192.168.1.39',threaded=True)
