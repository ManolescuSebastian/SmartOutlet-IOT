from time import sleep
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from flask_marshmallow import Marshmallow

from device_control import DeviceManager
from gateway import Gateway

import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)

#Configuration
app = Flask(__name__)
app.config.from_pyfile('config.py')
db = SQLAlchemy(app)
migrate = Migrate(app, db)
api = Api(app)

#Web pages
@app.route('/')
def index():
    return render_template('/index.html')

@app.route('/setup')
def setup():
    return render_template('/gateway_setup.html') 

#API requests mapping
# Manage devices | Control | Add | Edit | Delete
api.add_resource(DeviceManager, '/api/device')
# Gateway information
api.add_resource(Gateway, '/api/gateway/devices')

if __name__ == '__main__':
    app.run(debug=True, port=5050, host='0.0.0.0',threaded=True)
