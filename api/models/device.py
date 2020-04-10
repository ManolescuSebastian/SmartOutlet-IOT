from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from app import db

class Device(db.Model):
     id = db.Column(db.Integer, primary_key=True)
     uuid = db.Column(db.Integer)
     name = db.Column(db.String(32))
     on_value = db.Column(db.Integer)
     off_value = db.Column(db.Integer)
     type = db.Column(db.String(16))

     def __init__(self, uuid, name, on_value, off_value, type):
        self.uuid = uuid
        self.name = name
        self.on_value = on_value
        self.off_value = off_value
        self.type = type

     def serialize(self):
        return {"id": self.id, "name": self.name}

     def __repr__(self):
        return '<Device %s>' % self.name
