#include <RCSwitch.h>

RCSwitch mySwitch = RCSwitch();

void setup() {
  Serial.begin(9600);
  Serial.println("Setup RF Receiver");
  delay(1000);
  mySwitch.enableReceive(2);  // Receiver on interrupt 0 => that is pin #2
  pinMode(1, OUTPUT);
}

void loop() {
  if (mySwitch.available()) {
    int value = mySwitch.getReceivedValue();
    Serial.println("Value: " + value);
    if (value == 10000) {
      digitalWrite(1, HIGH);
    }
    else if (value == 5000) {
      digitalWrite(1, LOW);
    }
    mySwitch.resetAvailable();
  }
}
