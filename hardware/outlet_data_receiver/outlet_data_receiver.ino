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
