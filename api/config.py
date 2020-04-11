import os

# You need to replace the next values with the appropriate values for your configuration

basedir = os.path.abspath(os.path.dirname(__file__))
SQLALCHEMY_ECHO = False
SQLALCHEMY_TRACK_MODIFICATIONS = True
#SQLALCHEMY_DATABASE_URI = "postgresql://pi:anything@0.0.0.0:5050/smart_iot"
SQLALCHEMY_DATABASE_URI = "postgresql:///smart_iot_db"
