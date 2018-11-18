const int relayPin = 2;
const int switchPin = 3;
int currentSwitchState = 0;
int previousSwitchState = 0;

void setup() {
  Serial.begin(9600);
  pinMode(relayPin, OUTPUT);
  digitalWrite(relayPin, HIGH);
  pinMode(switchPin, INPUT);
}

void loop() {
  currentSwitchState = digitalRead(switchPin);
  if (currentSwitchState != previousSwitchState) {
    digitalWrite(relayPin, resolveStateValueForRelay(currentSwitchState));
    previousSwitchState = currentSwitchState;
  }
}

int resolveStateValueForRelay(int stateValue) {
  return ((stateValue == 1) ? 0 : 1);
}
