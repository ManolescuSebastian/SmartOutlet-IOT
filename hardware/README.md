# SmartOutlet-IOT (HARDWARE)

Outlet Hardware
------

This project requires the following components:
 - Relay
 - Microcontroller 
 - 433Mhz RF receiver module
 - AC 220V to 5V DC Step Down Transformer Power Supply Module
 - Outlet case to hold all the components mentioned above
 
Data processing for this project was done using two kinds of controllers `ATTINY85` | `Arduino Nano`.    
The outlet case used for this project was taken from a mechanical outlet timer.     

RF receiver modules.   
 * H3V4F Module (433 MHz) 
 * LR45A Receiver Module (433 MHz)

Outlet Schematic Diagram
-----

<p align="center">
   <img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/smart_outlet_hw_overview.jpg" width="40%"></img>
</p>

Outlet types     
-----

___ATTINY85___ outlet main advantage is the small size and that it has the board already a full USB port, and to power it we can use any mobile phone charger (remove it from case and we get a 220v AC - 5V DC module)


<p align="center">
   <img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/smart_outlet_design.jpg" width="50%"></img>
   <img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/3_01.jpg" width="15%"></img>
      <img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/5_01.jpg" width="15%"></img>
</p>


ATTINY85 receive data RF implementation.       
Note: code requires the Receiver on interrupt 0 => that is pin #2 (P2)

```C
#include <RCSwitch.h>

RCSwitch rcs = RCSwitch();

// pins
// rcs_pin for arduino nano the value is 0 and the pin should be connected to D2
// rcs_pin for ATTINY85 the value is 2 and the pin should be connected to P2
int rcs_pin = 2;
int relay_pin = 1;

//received values
int on = 10000;
//for the sake of simplicity we will consider the off value as on value divided by 2
int off = on / 2;

void setup() {
  Serial.begin(9600);
  delay(1000);
  rcs.enableReceive(rcs_pin);  // Receiver on interrupt 0 => that is pin #2
  pinMode(relay_pin, OUTPUT);
}
void loop() {
  if (rcs.available()) {
    int value = rcs.getReceivedValue();
    if (value == on) {
      digitalWrite(relay_pin, HIGH);
    }
    else if (value == off) {
      digitalWrite(relay_pin, LOW);
    }
    rcs.resetAvailable();
  }
}   
```

More information regarding the ATTINY85 setup and use can be found [here](https://www.electromaker.io/blog/article/introduction-to-the-attiny85-19)      
       
       
___Arduino Nano___ outlet was really easy to upload the code and the benifit it that you can also use the serial to see what data you receive (this is not possible for ATtiny85 - DigiStump Digispark), but the downside to this is that it requires a bit more space to fit the component.

//todo replace with the correct images
<p align="center">
   <img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/smart_outlet_design.jpg" width="50%"></img>
   <img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/1.jpg" width="15%"></img>
    <img align="center" src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet%20images/4_01.jpg" width="15%"></img>
</p>


Arduino nano receive RF data implementation

```C
#include <RCSwitch.h>

RCSwitch rcs = RCSwitch();

// pins
// rcs_pin for arduino nano the value is 0 and the pin should be connected to D2
// rcs_pin for ATTINY85 the value is 2 and the pin should be connected to P2
int rcs_pin = 0;
int relay_pin = 4;

//received values
int on = 10002;
//for the sake of simplicity we will consider the off value as on value divided by 2
int off = on / 2;

void setup() {
  Serial.begin(9600);
  Serial.println("Setup RF Receiver");
  delay(1000);
  rcs.enableReceive();  // Receiver on interrupt 0 => that is pin #2
  pinMode(relay_pin, OUTPUT);
}

void loop() {
  if (rcs.available()) {
    int value = rcs.getReceivedValue();
    Serial.println("Value: " + value);
    if (value == on) {
      digitalWrite(relay_pin, HIGH);
    }
    else if (value == off) {
      digitalWrite(relay_pin, LOW);
    }
    rcs.resetAvailable();
  }
}

```
Code also available in `SmartOutlet-IOT/hardware/outlet_data_receiver/outlet_data_receiver.ino`     
Awesome tutorial [here](https://www.14core.com/wiring-h34a-h3v3f-h3v4f-315-433-mhz-wireless-rf-tx-rx/)

Gateway hardware
------
 In order to connect, save and control multiple devices we need a gateway / server that will receive our input, process and convert it and then send it to the outlets/devices.
 For this purpose we used a <b>Raspberry Pi 3B+</b> (we can use any other raspberry Pi device versions)
 
 <p align="center">
   <img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/GPIO-Pinout-Diagram-2.png" width="70%"></img>
</p>



 
