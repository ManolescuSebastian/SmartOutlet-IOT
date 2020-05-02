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
           if('rfpin' in request.url):
                return  {'status' : 'in progress...'}, 200

