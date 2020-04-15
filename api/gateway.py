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
           rfpin = request.args.get('rfpin', default= None, type = int)
           if('rfpin' in request.url):
           #todo save pin in database and set it to rf imlpementation
                return  {'status':'success',
                         'rf_pin' : rfpin}, 200

           return {'status':'success',
                   'message': 'Gateway available'}, 200


