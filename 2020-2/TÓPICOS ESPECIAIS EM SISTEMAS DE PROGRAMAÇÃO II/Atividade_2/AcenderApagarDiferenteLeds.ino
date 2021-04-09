#define LED_EXTERNO_PIN 11

int ledExternoAceso=0;
int ledInternoAceso=0;
int ciclosPassados=0;

int toogleLed(uint8_t pin, int aceso = 1)
{
    aceso=(aceso == 1 ? 0 : 1);
    //se led tiver aceso => apaga | se tiver apagado => acendw
    digitalWrite(pin, (aceso == 1 ? HIGH : LOW));
    return aceso;
}

void setup() {
     //Define a porta do led 1 como saida
    pinMode(LED_EXTERNO_PIN, OUTPUT);

    //Define a porta do led builtin como saida
    pinMode(LED_BUILTIN, OUTPUT);
}

void loop() {
  //muda estado do led interno
  ledInternoAceso=toogleLed(LED_BUILTIN, ledInternoAceso);

  //p led externo tem q aguardar 2 segundos p fazer a alteracao
  if(ciclosPassados==2){
      ledExternoAceso=toogleLed(LED_EXTERNO_PIN, ledExternoAceso);
      ciclosPassados=0;
  }

  //adiciona mais um ciclo passado
  ciclosPassados++;
  
  //faz o ciclo de 1 segundo
  delay(1000);
}