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

class DeviceManager(Resource):

    def __init__(self):{
       db.create_all()
    }

    # Get all available devices
    def get(self):
            device_data = Device.query.all()
            all_devices = devices_schema.dump(device_data)
            return ({'status' :'success', 'data' : all_devices})

    # Add device
    def post(self):
           if not request.data:
              abort(400, 'json required')
           data = request.get_json()
           uuid = data['uuid']
           device_name = data['device_name']
           on_value = data['on_value']
           off_value = data['off_value']
           type = data['type']

           device = Device(device_name, type, uuid,on_value, off_value)
           db.session.add(device)
           db.session.commit()

           result = device_schema.dump(device)

           return jsonify({'status':'succcess', 'data': result})

    def put(self):
           return jsonify('in progress...')

    # Remove device
    def delete(self):
           if not request.data:
             abort(400, 'json required')
           data = request.get_json()
           device = Device.query.filter_by(id=data['id']).delete()
           db.session.commit()
           return jsonify({'status':'succcess'})



