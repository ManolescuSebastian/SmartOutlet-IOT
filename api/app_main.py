
from time import sleep
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from control_api import ControlAPI
from gateway import Gateway

import RPi.GPIO as GPIO       

GPIO.setmode(GPIO.BCM)


app = Flask(__name__)
api = Api(app)

#default web page
@app.route('/')
def index():
    return render_template('/index.html')

@app.route('/setup')
def setup():
	return render_template('/gateway_setup.html') 

#API requests mapping

# Outlet control
api.add_resource(ControlAPI, '/api/outlet')
# Gateway available devices
api.add_resource(Gateway, '/api/gateway/devices')

#TODO
#api/gateway/add_device
#api/gateway/remove_device

#TODO add database implementation

                        
if __name__ == '__main__':
    app.run(debug=True, port=5050, host='0.0.0.0',threaded=True)
