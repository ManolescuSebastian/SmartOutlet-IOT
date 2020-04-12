from flask import json
from flask import jsonify
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Response
from typing import List

#RF data
from rpi_rf import RFDevice
from rf_rpi_command import RfRpiCommand

class DeviceControl(Resource):

    #TODO allow  set pin from gateawy endpoint
    rf_pin = 17

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

            state = request.args.get('state', default= None, type = str)

            if 'state' not in request.args:
                return {'message' : 'Missing state value'}, 400

            if(state == 'true'):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(on_value, 17)
                return jsonify(
                    status = True,
                    state = 'device on',
                    code_sent = on_value)

            elif(state == 'false'):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(off_value, 17)
                return jsonify(
                    status = False,
                    state = 'device off',
                    code_sent = off_value)
            else:
                return {
                    'status' : False,
                    'state' : 'Error',
                    'code_sent' : 0}, 400
