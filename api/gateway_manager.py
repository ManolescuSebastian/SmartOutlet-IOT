from flask import json
from flask import jsonify
from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Response
from typing import List
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy

from models.gateway import db, Gateway, GatewaySchema

gateway_schema = GatewaySchema()

class GatewayManager(Resource):

    def __init__(self):{
       db.create_all()
    }

    def get(self):
           return {'status':'success',
                   'message': 'Gateway available'}, 200


    def post(self):
           rfpin = request.args.get('rfpin', default= None, type = int)
           if('rfpin' in request.url):
                 gateway_rf_pin = Gateway.query.first()

                 if not gateway_rf_pin:
                    gateway = Gateway(rfpin)
                    db.session.add(gateway)
                    db.session.commit()

                 else:
                    gateway_rf_pin.rf_pin = rfpin

                 return  {'status':'success', 'rf_pin' : rfpin}, 200

           return {'status':'failed'}, 400


    def delete(self):
           # Delete all devices
           delete = request.args.get('delete', default= None, type = str)
           if(delete == 'all'):
              db.session.query(Gateway).delete()
              db.session.commit()
              return {'message' : 'All gateway data deleted'}, 200
