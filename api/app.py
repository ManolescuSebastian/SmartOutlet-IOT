from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Blueprint
from flask_swagger_ui import get_swaggerui_blueprint

#Route
from device_manager import DeviceManager
from device_control import DeviceControl
from gateway_manager import GatewayManager

SWAGGER_URL = '/swagger'
SWAGGER_API_PATH = '/static/swagger.json'

api_bp = Blueprint('api', __name__)
api = Api(api_bp)

# swagger ui
@api_bp.route('/static/<path:path>')
def send_static(path):
    return send_from_directory('static', path)

swagger_bp =  get_swaggerui_blueprint(SWAGGER_URL, SWAGGER_API_PATH, config={'app_name':"SmartOutlet-IOT"})

# Web pages
@api_bp.route('/')
def index():
    return render_template('/index.html')

@api_bp.route('/setup')
def setup():
    return render_template('/gateway_setup.html') 

# API requests mapping
# Control device
api.add_resource(DeviceControl, '/control')
# Manage devices | Add | Edit | Delete
api.add_resource(DeviceManager, '/device')
# Gateway information
api.add_resource(GatewayManager, '/gateway')
