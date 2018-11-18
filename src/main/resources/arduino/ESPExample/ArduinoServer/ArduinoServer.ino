#include <SoftwareSerial.h>
#define RX 10
#define TX 11

String AP = "TNT2";
String PASS = "taher1052";

//String AP = "TNT";
//String PASS = "Taher0210";
String SERVER_PORT = "4000";
int relaySwitchPinNumber = 2;

SoftwareSerial esp8266(RX, TX);

void setup() {
  Serial.begin(9600);
  esp8266.begin(9600);
  sendCommand("AT\r\n", 5, "OK");
  sendCommand("AT+RST\r\n", 5, "OK");
  sendCommand("AT+CWMODE=1\r\n", 5, "OK");
  sendCommand("AT+CWJAP=\"" + AP + "\",\"" + PASS + "\"\r\n", 20, "OK");
  sendCommand("AT+CIPMUX=1\r\n", 5, "OK");
  sendCommand("AT+CIPSERVER=1," + SERVER_PORT + "\r\n", 5, "OK");

  initializePin(relaySwitchPinNumber);

  esp8266.flush();

}

void initializePin(int relaySwitchPinNumber) {
  pinMode(relaySwitchPinNumber, OUTPUT);
  digitalWrite(relaySwitchPinNumber, HIGH);
}

void loop() {
  if (esp8266.available()) {
    if (esp8266.find("+IPD,")) {
      Serial.print("Found IPD!\r\n");
      delay(1000);
      if (esp8266.find("pin=")) {
        int connectionId = esp8266.read() - 48;
        int originalPinNumber = esp8266.peek();
        Serial.print("Received request from: ");
        Serial.print(connectionId);
        Serial.print("\r\n");
        Serial.print("Received request to toggle pin(Without ASCII): ");
        Serial.print(originalPinNumber);
        Serial.print("\r\n");

        //      int pinNumber = (esp8266.read() - 48) * 10;
        //      pinNumber += (esp8266.read() - 48);
        int pinNumber = (esp8266.read() - 48);
        Serial.print("Received request to toggle pin: ");
        Serial.print(pinNumber);
        Serial.print("\r\n");

        digitalWrite(pinNumber, !digitalRead(pinNumber)); // toggle pin

        String closeCommand = "AT+CIPCLOSE=";
        closeCommand += connectionId; // append connection id
        closeCommand += "\r\n";
        sendCommand(closeCommand, 5, "OK"); // close connection
        sendCommand("AT+CIPCLOSE\r\n", 5, "OK"); // close connection
      }
    }
  }
}

void sendCommand(String command, int maxTime, char readReplay[]) {
  boolean success = false;
  Serial.print(command + "\r\n");
  for (int i = 0; i < maxTime; i++ ) {
    esp8266.println(command);
    if (esp8266.find(readReplay)) {
      Serial.println("Command Executed.\r\n");
      success = true;
      break;
    }
    delay(200);
  }
  if (!success) {
    Serial.println("Failed to execute command.\r\n");
  }
}
