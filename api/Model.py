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
     on_value = db.Column(db.Integer)
     off_value = db.Column(db.Integer)
     type = db.Column(db.String(32), nullable=False)

     def __init__(self, name, type, uuid, on_value, off_value):
         self.name = name
         self.type = type
         self.uuid = uuid
         self.on_value = on_value
         self.off_value = off_value

class DeviceSchema(ma.Schema):
     id = fields.Integer(dump_only=True)
     uuid = fields.Integer(unique=True)
     on_value = fields.Integer()
     off_value = fields.Integer()
     name = fields.String(required=True, validate = validate.Length(1))
     type = fields.String(required=True, validate = validate.Length(1))

