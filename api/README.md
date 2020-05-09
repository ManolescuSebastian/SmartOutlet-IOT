# SmartOutlet-IOT

## API

After everything was pieced together on the hardware side we need a node that receives input and then sends the commands to our outlet/devices.

For that as I mentioned in the Hardware section [here](https://github.com/ManolescuSebastian/SmartOutlet-IOT/tree/master/HW) we need a Raspberry Pi, that will contain our API.

In this project we used as FLASK (Flask is a lightweight WSGI web application framework), more info [here](https://palletsprojects.com/p/flask/)

### Setup    
Install required libraries and dependencies     
pip3 install -r requirements.txt     

pip3 install flask
pip3 install flask_restful
pip3 install flask_marshmallow
pip3 install marshmallow-sqlalchemy
pip3 install rpi_rf

### Project structure

```bash
├── models
│   ├── device.py
├── templates
│   ├── index.html
│   ├── gateway_setup.html
│
├── app.py  
├── config.py  
├── device_control.py
├── device_manager.py
├── gateway.py
├── rf_rpi_command.py  
├── run.py
└── .gitignore
```

### Endpoints

 **[GET] /api/control**    
 with parameters: **state = true** | **state = false**        
 Request    
```json
{
      "type": "RF",
      "id": 11,
      "name": "Device 5",
      "uuid": 10000
}
```
 
 Response
 ```json
{
  "status": "succcess",
  "data": [
    {
      "status": true,
      "state": "device on",
      "code_sent": 10000
    }
  ]
}
```

 **[GET] /api/device**    
 Returns all available devices       
 Response.   
 ```json
{
  "status": "success",
  "data": [
    {
      "uuid": 10002,
      "id": 2,
      "type": 0,
      "name": "Room device"
    },
    {
      "uuid": 10004,
      "id": 3,
      "type": 3,
      "name": "Other Room device"
    },
    {
      "uuid": 10006,
      "id": 4,
      "type": 2,
      "name": "Any room device"
    }
  ]
}
```

 **[POST] /api/device**     
 Add device     
 Request
```json
{
   "uuid" : 10008,
   "name": "Any room",
   "type":"0"
}
```

Response
 ```json
{
  "status": "succcess",
  "data": {
    "name": "Any room",
    "id": 5,
    "uuid": 10008,
    "type": 0
  }
}
```

 **[PUT] /api/device**    
 Edit device   
 Request
```json
{
   "id" : 8, 
   "uuid" : 10000,
   "device_name": "New Device Name",
   "type":"1"
}
```

**[DELETE] api/device?id=**   
**[DELETE] api/device?delete=all**   
Response
 ```json
{
  "status": "succcess"
}
```
