const int ledPin = 26;

void setup() {
}

void loop(){
  for(int dutyCycle = 150; dutyCycle <= 255; dutyCycle++){   
    dacWrite(ledPin, dutyCycle);
    delay(10);
  }

  for(int dutyCycle = 255; dutyCycle >= 150; dutyCycle--){
    dacWrite(ledPin, dutyCycle);   
    delay(10);
  }
}
