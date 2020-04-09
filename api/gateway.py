from flask import json
from flask import jsonify
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Response
from typing import List

class Gateway(Resource):

    def __init__(self):{
    }

    def get(self):
           if('devices' in request.url):
                device_list = [
                    {
                       'status' : 200,
                       'device_name' : 'Lamp Outlet',
                       'value_on' : 5000,
                       'value_off' : 10000
                    },
                    {
                       'status' : 200,
                       'device_name' : 'Demo Outlet',
                       'value_on' : 1000,
                       'value_off' : 2000
                     }
                 ]

                return jsonify(device_list)
                return response
