int ledExterno1Aceso = 0;
int ledExterno2Aceso = 0;
int ledExterno3Aceso = 0;
int contador = 0;

int toogleLed(uint8_t pin, int aceso = 1) {
  aceso = (aceso == 1 ? 0 : 1);
  //se led estiver aceso => apaga | se estiver apagado => acende
  digitalWrite(pin, (aceso == 1 ? HIGH : LOW));
  return aceso;
}

void setup() {
  //Define a porta do led externo 1 como saída
  pinMode(4, OUTPUT);
  //Define a porta do led externo 2 como saída
  pinMode(18, OUTPUT);
  //Define a porta do led built in como saída
  pinMode(22, OUTPUT);
}

void loop() {
  if(contador % 50 == 0) {
    //muda o estado do led externo 1 a cada 100ms
    ledExterno1Aceso = toogleLed(4, ledExterno1Aceso);
  }
  if(contador % 300 == 0) {
    //muda o estado do led externo 2 a cada 250ms
    ledExterno2Aceso = toogleLed(18, ledExterno2Aceso);
  }
  if(contador % 2000 == 0) {
    //muda o estado do led externo 3 a cada 1s
    ledExterno3Aceso = toogleLed(22, ledExterno3Aceso);
    contador = 0;
  }

  //faz o ciclo de 50ms
  contador += 50;
  delay(50);
}
