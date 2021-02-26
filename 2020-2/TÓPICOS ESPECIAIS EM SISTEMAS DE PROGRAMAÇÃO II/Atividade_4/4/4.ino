int outputPin = 25;
int inputPin = 34;

int tensao = 142;

void setup() {
  pinMode(outputPin, OUTPUT);
  Serial.begin(115200);
}

void loop(){
  dacWrite(outputPin, tensao); 
  int potValue = analogRead(inputPin);
  Serial.println(regraTres(potValue));
}

float regraTres(int numero) {
  return (numero * 3.3) / 4095;
}
