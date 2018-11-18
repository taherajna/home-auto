#include <SoftwareSerial.h>
#define RX 10
#define TX 11
SoftwareSerial esp8266(RX, TX);

void setup() {
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }
  Serial.print("Started\r\n");
  // set the data rate for the SoftwareSerial port
  esp8266.begin(115200);
  //executeCommandAndVerify("AT\r\n");
  //executeCommandAndPrint("AT\r\n");
  execAndPrint("AT\r\n");

}
void execAndPrint(String command) {
  esp8266.print(command);
  delay(1000);
  Serial.print("Second Started\r\n");
  Serial.print("Size is: ");
  Serial.print(esp8266.available());
  Serial.print("\r\n");
  while (esp8266.available()) {
    char temp = esp8266.read();
    Serial.print(temp);
  }
}

void loop()
{
}
void executeCommandAndPrint(String command) {
  esp8266.print(command);
  int outputSize = 50;
  char output[outputSize];
  Serial.print(esp8266.available());
  for (int i = 0; i < outputSize; i++) {
    Serial.print(esp8266.available());
    if (esp8266.available() <= 0) {
      break;
    }
    output[i] = esp8266.read();
  }
  for (int i = 0; i < outputSize; i++) {
    Serial.print(esp8266.available());
    Serial.print(output[i]);
  }
  delay(2000);
}


void executeCommandAndVerify(String command) {
  esp8266.print(command);
  char ok[] = "OK";
  if (esp8266.find(ok)) {
    Serial.print("Successfully Executed!");
  }
  Serial.print("\r\n");
}
