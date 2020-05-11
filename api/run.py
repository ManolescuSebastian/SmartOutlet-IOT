from flask import Flask
from flask_swagger_ui import get_swaggerui_blueprint

def create_app(config_filename):
     app = Flask(__name__)
     app.config.from_object(config_filename)

     from app import api_bp
     app.register_blueprint(api_bp, url_prefix='/api')

     from app import swagger_bp, SWAGGER_URL
     app.register_blueprint(swagger_bp, url_prefix = SWAGGER_URL)

     from models.device import db
     db.init_app(app)

     return app

if __name__ == "__main__":
     app = create_app("config")
     app.run(debug=True, port=5050,host='0.0.0.0', threaded=True)
