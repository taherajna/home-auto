void setup() {
  Serial.begin(9600);
  while (!Serial) {
    //Wait
  }
  Serial.println("Initialized Serial!");

  String request = "pin=6,state=on";
  Serial.println(request);
  String search = "pin=";
  int index = request.indexOf(search);
  Serial.println(index+search.length());
  Serial.println("Number = ");
  Serial.println(request[index+search.length()]);
}

void loop() {

}
