void setup()
{
    pinMode(LED_BUILTIN, OUTPUT);
}

void loop()
{
    //Acende o led
    digitalWrite(LED_BUILTIN, HIGH);

    //Aguarda intervalo de tempo em milissegundos
    delay(1000);

    //Apaga o led
    digitalWrite(LED_BUILTIN, LOW);

    //Aguarda intervalo de tempo em milissegundos
    delay(1000);
}
