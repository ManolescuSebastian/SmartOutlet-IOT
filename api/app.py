from flask import Flask, request, render_template
from flask_restful import Resource, Api
from flask import Blueprint

#Route
from device_manager import DeviceManager
from device_control import DeviceControl
from gateway_manager import GatewayManager

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
#Control device
api.add_resource(DeviceControl, '/control')
# Manage devices | Add | Edit | Delete
api.add_resource(DeviceManager, '/device')
# Gateway information
api.add_resource(GatewayManager, '/gateway')
