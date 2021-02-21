// Potentiometer is connected to GPIO 34 (Analog ADC1_CH6)
const int potPin = 34;

// variable for storing the potentiometer value
int potValue = 0;

void setup() {
  Serial.begin(115200);
  Serial.println("Setup done");
  delay(1000);
}

void loop() {
  // Reading potentiometer value
  potValue = analogRead(potPin);
  Serial.println(regraTres(potValue));
  delay(500);
}


float regraTres(int numero) {
  return (numero * 3.3) / 4095;
}
