from flask import json
from flask import jsonify
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Response
from typing import List

#db
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy

from models.gateway import db, Gateway, GatewaySchema

gateway_schema = GatewaySchema()

#RF data
from rpi_rf import RFDevice
from rf_rpi_command import RfRpiCommand

class DeviceControl(Resource):

    def __init__(self):{
    }

    #Control RF devices
    def post(self):
            if not request.data:
               abort(400, 'json required')
            data = request.get_json()

            uuid = data['uuid']
            name = data['name']
            type = data['type']

            # Decided to make the ON / OFF values based on the device UUID
            # This would be a lot better with an algorithm that we could use to generate the values,
            # but for the sake of simplicity I've only divided the value (/2)
            # rpi_rf does not support codes that have decimals e.g. 1000.0
            on_value: int = uuid
            off_value: int = int(uuid / 2)

            print(off_value)

            gpin = Gateway.query.first()
            if gpin is not None:
               rf_pin = gpin.rf_pin
            else:
               rf_pin = 17 #default pin

            print('Gateway RF_pin: ', rf_pin)

            state = request.args.get('state', default= None, type = str)

            if 'state' not in request.args:
                return {'message' : 'Missing state value'}, 400

            if(state == 'true'):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(on_value, rf_pin)
                return {
                   'status' : 'succcess',
                   'data' : [{
                   'status' : True,
                   'state' : 'device on',
                   'code_sent' : on_value}]}, 200

            elif(state == 'false'):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(off_value, rf_pin)
                return {
                   'status' : 'succcess',
                   'data' :[{
                   'status' : True,
                   'state' : 'device on',
                   'code_sent' : on_value}]}, 200
            else:
                return {
                    'status' : False,
                    'state' : 'Error',
                    'code_sent' : 0}, 400
