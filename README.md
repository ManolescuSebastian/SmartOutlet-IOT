# SmartOutlet-IOT



## Demo 
Video file.   
     
    
    
## Mobile UI    

<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/develop_mobile/mobile/SmartOutletIOT/app%20screenshots/hm_sc_1.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/develop_mobile/mobile/SmartOutletIOT/app%20screenshots/hm_sc_2.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/develop_mobile/mobile/SmartOutletIOT/app%20screenshots/hm_sc_4.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/develop_mobile/mobile/SmartOutletIOT/app%20screenshots/hm_sc_3.jpg" width="24%"></img>
    
     
     
     
## API

In this project we used as FLASK (Flask is a lightweight WSGI web application framework), more info [here](https://palletsprojects.com/p/flask/)
     
##### Project structure

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

#### start server command    
sudo python3 run.py
    
    
    
## Hardware

This is a DIY project so in order to create it you would need the following components.
 * Outlet case (for this project I've used a Mechanical Outlet Timer)
 * Phone charger (I've used an IPhone charger), open the container and get the circuit
 * Solid state relay (5V)
 * Receiver H3V4F Module (433 MHz) <b>OR</b> LR45A Receiver Module (433 MHz)
 * ATTINY85 USB Development Board
 
 The main idea behind this project is that we make the outlet hardware using really cheap components.

 Also I've made a simple and very graphic schematic that explains how the connections are made.
![](https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/smart_outlet_design.jpg)
 

More details in the <b> hardware </b> module

License
------
         

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
