from flask import Flask
from marshmallow import Schema, fields, pre_load, validate
from flask_marshmallow import Marshmallow
from flask_sqlalchemy import SQLAlchemy

ma = Marshmallow()
db = SQLAlchemy()

class Gateway(db.Model):
      __tablename__ = 'gateway_data'
      id = db.Column(db.Integer, primary_key=True)
      rf_pin = db.Column(db.Integer)

      def __init__(self, rf_pin):
          self.rf_pin = rf_pin

class GatewaySchema(ma.Schema):
      id = fields.Integer(dump_only=True)
      rf_pin = fields.Integer(required=True,validate = validate.Length(1))
