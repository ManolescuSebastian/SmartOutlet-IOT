# SmartOutlet-IOT

## API

After everything was pieced together on the hardware side we need a node that receives input and then sends the commands to our outlet/devices.

For that as I mentioned in the Hardware section [here](https://github.com/ManolescuSebastian/SmartOutlet-IOT/tree/master/HW) we need a Raspberry Pi, that will contain our API.

In this project we used as FLASK (Flask is a lightweight WSGI web application framework), more info [here](https://palletsprojects.com/p/flask/)

### Project structure

```bash
├── templates
│   ├── index.html
│
├── app_main.py  
├── control_api.py 
├── rf_rpi_command.py  
└── .gitignore
```

### Endpoint

 **/api/outlet** 
 with parameters: **state = on** | **state = off**
 
 e.g. http://192.168.1.39:5051/api/outlet?state=off
 
