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
            return {'status' : 'success', 'data' : all_devices}, 200

    # Add device
    def post(self):
           if not request.data:
              abort(400, 'json required')
           data = request.get_json()

           uuid_check = Device.query.filter_by(uuid=data['uuid']).first()
           if uuid_check:
              return {'message':'UUID already in use'}, 400

           uuid = data['uuid']
           name = data['name']
           type = data['type']

           device = Device(name, type, uuid)
           db.session.add(device)
           db.session.commit()

           result = device_schema.dump(device)

           return {'status' : 'succcess', 'data' : result}, 201

    # Edit device
    def put(self):
           data = request.get_json()
           edit_device = Device.query.filter_by(id=data['id']).first()

           if not edit_device:
                 return {'message': 'Device does not exist'}, 400

           edit_device.uuid = data['uuid']
           edit_device.name = data['name']
           edit_device.type = data['type']

           db.session.commit()

           return {'status' : 'succcess'}, 200

    # Remove device
    def delete(self):
           # Delete all devices
           delete = request.args.get('delete', default= None, type = str)
           if(delete == 'all'):
              db.session.query(Device).delete()
              db.session.commit()
              return {'message' : 'All devices deleted'}, 400

           #delete device by id
           #if not request.data:
           #  abort(400, 'json required')
           #data = request.get_json()

           device_id = request.args.get('id', default= None, type = str)
           if 'id' not in request.args:
                abort(400, 'Missing id value')
           device = Device.query.filter_by(id=device_id).delete()
           db.session.commit()
           return {'status' : 'succcess'}, 200



