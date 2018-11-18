#include <ESP8266WiFi.h>

const char* ssid = "TNT";   //put your SSID of the router to connect with
const char* pass = "Taher0210";   //put your password of the router to connect with
String response;
uint8_t relayPins[] = {D7};
int pinState;
String pinRequestString = "pin=";


WiFiServer server(4000);
WiFiClient client;
void setup()
{
  Serial.begin(115200);
  while (!Serial) {
    //Waiting for Serial to initialize
  }
  for (int i = 0; i < (sizeof(relayPins) / sizeof(relayPins[0])); i++) {
    pinMode(relayPins[i], OUTPUT);
    digitalWrite(relayPins[i], 1);
  }

  Serial.println();
  Serial.print("Connecting to............");
  Serial.println(ssid);

  WiFi.begin(ssid, pass);

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");

  server.begin();
  Serial.println("Server started.....");

  Serial.println(WiFi.localIP());

}

void loop()
{

  if (!client) {
    Serial.print("Waiting for client to connect");
    while (!client)
    {

      client = server.available();
      delay(200);
      Serial.print(".");
    }
    Serial.println("\r\nConnected!");
  }

  if (!client.available())
  {
    delay(200);
    return;
  }

  String request = client.readStringUntil('\r');
  Serial.println(request);
  client.flush();

  int indexOfPinRequest = request.indexOf(pinRequestString);
  if (indexOfPinRequest != -1) {
    int requestPinNumber = request[indexOfPinRequest + pinRequestString.length()]-48;
    uint8_t outputPin = relayPins[requestPinNumber];
    response = "Turning ";
    if (request.indexOf("state=on") != -1)
    {
      pinState = 0;
      response += "on pin ";
    }
    else if (request.indexOf("state=off") != -1)
    {
      pinState = 1;
      response += "off pin ";
    }
    response += requestPinNumber;
    digitalWrite(outputPin, pinState);
  } else {
    Serial.println("invalid request");
    return;
  }

  client.flush();
  client.print(response);
}
