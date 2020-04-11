from flask import json
from flask import jsonify
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Response
from flask import abort
from typing import List
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy

from Model import db, Device, DeviceSchema

devices_schema = DeviceSchema(many=True)
device_schema = DeviceSchema()

#RF data
from rpi_rf import RFDevice
from rf_rpi_command import RfRpiCommand

class DeviceManager(Resource):

    def __init__(self):{
       db.create_all()
    }

    #Control RF devices
    def get(self):
            device_data = Device.query.all()
            all_devices = devices_schema.dump(device_data)
            return ({'status' :'success', 'data' : all_devices})

#            state = request.args.get('state', default= None, type = str)
#            value = request.args.get('value', default= None, type = int)
    #TODO handle exceptions / errors and return error data

#            if 'value' not in request.args:
#                abort(400, 'Missing data value')
#            if 'state' not in request.args:
#                abort(400, 'Missing state value')

#            if(state == 'true'):
#                rfCommand = RfRpiCommand
#                rfCommand.data_tx(value)
#                return jsonify(
#                    status = 200,
#                    state = 'device on',
#                    code_sent = value)

#            elif(state == 'false'):
#                rfCommand = RfRpiCommand
#                rfCommand.data_tx(value)
#                return jsonify(
#                    status = 200,
#                    state = 'device off',
#                    code_sent = value)

  #Add device
    def post(self):
           if not request.data:
              abort(400, 'json required')
           data = request.get_json()
           device_name = data['device_name']
           on_value = data['on_value']
           off_value = data['off_value']
           type = data['type']

           device = Device(data['device_name'], data['type'])
           db.session.add(device)
           db.session.commit()

           result = device_schema.dump(device)

           return jsonify({'status':'succcess', 'data': result})

    def delete(self):
           if not request.data:
             abort(400, 'json required')
           data = request.get_json()
           device = Device.query.filter_by(id=data['id']).delete()
           db.session.commit()
           return jsonify({'status':'succcess'})

