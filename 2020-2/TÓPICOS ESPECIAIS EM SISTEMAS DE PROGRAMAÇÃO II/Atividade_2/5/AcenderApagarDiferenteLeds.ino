#include "Thread.h"   // usando a biblioteca https://github.com/ivanseidel/ArduinoThread
#include "ThreadController.h"

#define LED_EXTERNO_SLEEP 2000
#define LED_EXTERNO_PIN 11

#define LED_INTERNO_SLEEP 1000

void acendeApagaLed(uint8_t pin, unsigned long millisecs, int aceso = 1)
{
    //Acende o led no pin
    digitalWrite(pin, (aceso == 1 ? HIGH : LOW));

    //Aguarda intervalo de tempo em milissegundos
    delay(millisecs);

    //Apaga o led no pin
    digitalWrite(pin, (aceso == 1 ? LOW : HIGH));
}

void piscaLedExterno(){
   acendeApagaLed(LED_EXTERNO_PIN,LED_EXTERNO_SLEEP);
}

void piscaLedInterno(){
  acendeApagaLed(LED_BUILTIN,LED_INTERNO_SLEEP);
}

ThreadController cpu;

Thread threadLedExterno;
Thread threadLedInterno;

void setup() {
     //Define a porta do led 1 como saida
    pinMode(LED_EXTERNO_PIN, OUTPUT);

    //Define a porta do led builtin como saida
    pinMode(LED_BUILTIN, OUTPUT);

    threadLedExterno.setInterval(LED_EXTERNO_SLEEP);
    threadLedExterno.onRun(piscaLedExterno);

    threadLedInterno.setInterval(LED_INTERNO_SLEEP);
    threadLedInterno.onRun(piscaLedInterno);

    cpu.add(&threadLedExterno);
    cpu.add(&threadLedInterno);
}

void loop() {
  cpu.run();
}