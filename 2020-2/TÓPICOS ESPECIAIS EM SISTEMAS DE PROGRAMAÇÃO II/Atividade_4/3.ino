int outputPin = 16;
int inputPin = 34;

// pwm configs
const int freq = 500;
const int ledChannel = 0;
const int resolution = 12;

void setup(){
  ledcSetup(ledChannel, freq, resolution);
  ledcAttachPin(outputPin, ledChannel);

  Serial.begin(115200);
}
 
void loop(){
  ledcWrite(ledChannel, 2109);
  int potValue = analogRead(inputPin);
  Serial.println(regraTres(potValue));
}

float regraTres(int numero) {
  return (numero * 3.3) / 4095;
}
