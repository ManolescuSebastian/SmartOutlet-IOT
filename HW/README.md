# SmartOutlet-IOT (HARDWARE)

### Outlet Hardware description

This is a DIY project so in order to create it you would need the following components.
 * Outlet case (for this project I've used a Mechanical Outlet Timer)
 * Phone charger (I've used an IPhone charger), open the container and get the circuit
 * Solid state relay (5V)
 * Receiver H3V4F Module (433 MHz) <b>OR</b> LR45A Receiver Module (433 MHz)
 * ATTINY85 USB Development Board
 
 So the main idea behind this project is that we make the outlet hardware using really cheap components.
 
 In order to get a overview on how the components communicate I've created this simple diagram 
![](https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/HW/images/smart_outlet_hw_overview.jpg)
 
 Also I've made a simple and very graphic schematic that explains how the connections are made.
![](https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/HW/images/smart_outlet_design.jpg)
 
 
 The final result after we've put all compoments together should look like this.

<p align="center"><img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/HW/images/outlet_result_01.jpg" width="600" height="500"></p>                                                                                                                                 
                                                                                                                                   
 
 ### Gateway hardware
 
 In order to connect, save and control multiple devices we need a gateway / server that will receive our input, process and convert it and then send it to the outlets/devices.
 For this purpose we used a <b>Raspberry Pi 3B+</b> (we can use any other raspberry Pi device versions)