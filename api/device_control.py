from flask import json
from flask import jsonify
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Response
from flask import abort
from typing import List
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy

from rpi_rf import RFDevice
from rf_rpi_command import RfRpiCommand
#from models import device

class DeviceManager(Resource):

    def __init__(self):{
    }

    #Control RF devices
    def get(self):

            state = request.args.get('state', default= None, type = str)
            value = request.args.get('value', default= None, type = int)
    #TODO handle exceptions / errors and return error data

            if 'value' not in request.args:
                abort(400, 'Missing data value')
            if 'state' not in request.args:
                abort(400, 'Missing state value')

            if(state == 'true'):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(value)
                return jsonify(
                    status = 200,
                    state = 'device on',
                    code_sent = value)

            elif(state == 'false'):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(value)
                return jsonify(
                    status = 200,
                    state = 'device off',
                    code_sent = value)

            else:
                return jsonify(
                    status = 400,
                    state = 'Error',
                    code_sent = 0)

    #Add device
    def post(self):
           if not request.data:
              abort(400, 'json required')
           data = request.get_json()
           device_name = data['device_name']
           on_value = data['on_value']
           off_value = data['off_value']
           type = data['type']
           print(device_name)
           print(on_value)
           print(type)

           return jsonify(data)

    def delete(self):
           return jsonify('in progress')


 #   def save_device():
 #          device = Device(uuid, name, on_value, off_value, type)
 #          session.add(device)
 #          session.commit()
 #          return jsonify(Device = device.serialize)
