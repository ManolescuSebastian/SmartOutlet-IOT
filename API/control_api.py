
from flask import json
from flask import jsonify
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Response
from typing import List

from rpi_rf import RFDevice
from rf_rpi_command import RfRpiCommand


class ControlAPI(Resource):

    def __init__(self):{
    }

    def get(self):
        if('state' in request.args):
            arg = request.args['state']
            if(arg == "on"):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(5000)

                return jsonify(
                    status = 200,
                    state = 'on',
                    code_sent = 5000)
                return response
            elif(arg == "off"):
                rfCommand = RfRpiCommand
                rfCommand.data_tx(10000)

                return jsonify(
                    status = 200,
                    state = 'off',
                    code_sent = 10000)
                return response
            else:
                return jsonify(
                    status = 400,
                    state = 'Error',
                    code_sent = 0)
