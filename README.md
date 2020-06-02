# SmartOutlet-IOT     
[![Thingiverse](https://img.shields.io/badge/profile-Thingiverse-blue)](https://www.thingiverse.com/softwareinclude/about)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Home automation POC (proof of concept) system that allows the user to control a device from any client connected to the wifi network.    
The main parts of this project are the `outlet device` that receives the instructions, the `gateway` that handles the data between the client and the outlet device and the `client` that sends the requests.

Demo 
------
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet_on_press.gif" width="80%"></img>

 **Full Youtube video:**
[SmartOutlet-IOT](https://www.youtube.com/watch?v=8GfmIdJ8pTU "SmartOutlet-IOT")      
    
Android Mobile application     
------

<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_1.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_2.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_4.jpg" width="24%"></img>
<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/android%20app%20screenshots/hm_sc_3.jpg" width="24%"></img>

API
------
For the API implementation I've used Flask `(Flask is a lightweight WSGI web application framework)` more info [here](https://palletsprojects.com/p/flask/)    
     
##### Available endpoints  

<img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/swagger_ui_endpoints_img.png" width="100%"></img>    

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

start server command    
`sudo python3 run.py`
    
Gateway (Raspberry pi)
------
    
<p align="center">
   <img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/rpi_H34A.jpg" width="80%"></img>
</p>

Raspberry pi | H34A (TX)
------------ | ---------
VCC | VCC
GND | GND
DAT | DAT
       
    
`ANT (Optional)` - provides improved range    
    
    
Outlet Hardware
------
This is a DIY project so in order to create it you would need the following components.
 * Outlet case (for this project I've used a Mechanical Outlet Timer)
 * Phone charger (I've used an IPhone charger), open the container and get the circuit
 * Solid state relay (5V)
 * Receiver H3V4F Module (433 MHz) <b>OR</b> LR45A Receiver Module (433 MHz)
 * ATTINY85 USB Development Board
 
 The main idea behind this project is that we make the outlet hardware using really cheap components.

 Also I've made a simple and very graphic schematic that explains how the connections are made.     
<img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/smart_outlet_hw_overview.jpg" width="40%"></img>
<img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/smart_outlet_design.jpg" width="50%">


And this is how the devices look after the 3D printed lid and IOT / WIFI design was added

<p align="center">
<img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/2.jpg" width="80%">
</p>
    
3D STL files are available on my Thingiverse profile [here](https://www.thingiverse.com/softwareinclude/about)


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
