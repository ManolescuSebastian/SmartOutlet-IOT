from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Blueprint

#Route
from device_control import DeviceManager
from gateway import Gateway

#app.config.from_pyfile('config.py')
#db = SQLAlchemy(app)
#migrate = Migrate(app, db)
api_bp = Blueprint('api', __name__)
api = Api(api_bp)

#Web pages
@api_bp.route('/')
def index():
    return render_template('/index.html')

@api_bp.route('/setup')
def setup():
    return render_template('/gateway_setup.html') 

#API requests mapping
# Manage devices | Control | Add | Edit | Delete
api.add_resource(DeviceManager, '/device')
# Gateway information
api.add_resource(Gateway, '/gateway/devices')
