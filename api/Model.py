from flask import Flask
from marshmallow import Schema, fields, pre_load, validate
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy
#from sqlalchemy.ext.declarative import declarative_base

ma = Marshmallow()
db = SQLAlchemy()

#db.create_all()

class Device(db.Model):
     __tablename__ = 'outlet_devices'
     id = db.Column(db.Integer, primary_key=True)
     name = db.Column(db.String(128), nullable=False)
     type = db.Column(db.String(32), nullable=False)

     def __init__(self, name, type):
         self.name = name
         self.type = type


class DeviceSchema(ma.Schema):
     id = fields.Integer(dump_only=True)
     name = fields.String(required=True, validate = validate.Length(1))
     type = fields.String(required=True, validate = validate.Length(1))



#https://www.codementor.io/@mide/how-to-build-restful-apis-with-python-and-flask-fh5x7zjrx
