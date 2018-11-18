
int relaySwitch = 2;
boolean isOn = false;

void setup() {
  pinMode(relaySwitch, OUTPUT);
}

void loop() {

  if (isOn) {
    digitalWrite(relaySwitch, LOW);
    isOn = false;
  } else {
    digitalWrite(relaySwitch, HIGH);
    isOn = true;
  }
  delay(5000);

}
