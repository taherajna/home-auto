uint8_t relayPin = D7;
void setup() {
  pinMode(relayPin,OUTPUT);
}

void loop() {
  digitalWrite(relayPin,0);
  delay(500);
  digitalWrite(relayPin,1);
  delay(500);
}
