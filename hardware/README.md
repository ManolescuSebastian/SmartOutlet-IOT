# SmartOutlet-IOT (HARDWARE)

### Outlet Hardware description

This is a DIY project so in order to create it you would need the following components.
 * Outlet case (for this project I've used a Mechanical Outlet Timer)
 * Phone charger (I've used an IPhone charger), open the container and get the circuit
 * Solid state relay (5V)
 * Receiver H3V4F Module (433 MHz) <b>OR</b> LR45A Receiver Module (433 MHz)
 * ATTINY85 USB Development Board
 
 The main idea behind this project is that we make the outlet hardware using really cheap components.
 
 In order to get a overview on how the components communicate I've created this simple diagram 
![](https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/smart_outlet_hw_overview.jpg)
 
 Also I've made a simple and very graphic schematic that explains how the connections are made.
![](https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/smart_outlet_design.jpg)
 
 
 The final result after we've put all compoments together should look like this.

<p align="center"><img src="https://github.com/ManolescuSebastian/SmartOutlet-IOT/blob/master/hardware/images/outlet_result_01.jpg" width="600" height="500"></p>                                                                                                                                 
<b>ATTINY85 outlet code</b></br>

```C
#include <RCSwitch.h>
RCSwitch mySwitch = RCSwitch();
void setup() {
  Serial.begin(9600);
  Serial.println(“Setup RF Receiver”);
  delay(1000);
  mySwitch.enableReceive(2);  // Receiver on interrupt 0 => that is pin #2
  pinMode(1, OUTPUT);
}
void loop() {
  if (mySwitch.available()) {
    int value = mySwitch.getReceivedValue();
    Serial.println(“Value: ” + value);
    if (value == 10000) {
      digitalWrite(1, HIGH);
    }
    else if (value == 5000) {
      digitalWrite(1, LOW);
    }
    mySwitch.resetAvailable();
  }
}   
```

You can also get the code from this hardware repo <b>outlet_data_receiver.ino</b>

 ### Gateway hardware
 
 In order to connect, save and control multiple devices we need a gateway / server that will receive our input, process and convert it and then send it to the outlets/devices.
 For this purpose we used a <b>Raspberry Pi 3B+</b> (we can use any other raspberry Pi device versions)
 
 Helpful resources:
 [ Wiring H34A / H3V3F / H3V4F 315/433 MHz RF Radio TX/RX]( https://www.14core.com/wiring-h34a-h3v3f-h3v4f-315-433-mhz-wireless-rf-tx-rx/)


 
