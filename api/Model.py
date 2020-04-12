from flask import Flask
from marshmallow import Schema, fields, pre_load, validate
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy

ma = Marshmallow()
db = SQLAlchemy()

class Device(db.Model):
     __tablename__ = 'outlet_devices'
     id = db.Column(db.Integer, primary_key=True)
     uuid = db.Column(db.Integer, unique=True)
     name = db.Column(db.String(128), nullable=False)
     type = db.Column(db.String(32), nullable=False)

     def __init__(self, name, type, uuid):
         self.name = name
         self.type = type
         self.uuid = uuid

class DeviceSchema(ma.Schema):
     id = fields.Integer(dump_only=True)
     uuid = fields.Integer(unique=True)
     name = fields.String(required=True, validate = validate.Length(1))
     type = fields.String(required=True, validate = validate.Length(1))

