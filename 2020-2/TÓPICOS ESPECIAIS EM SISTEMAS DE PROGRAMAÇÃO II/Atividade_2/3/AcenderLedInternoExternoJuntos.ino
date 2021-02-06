void setup()
{
    //Define a porta do led 1 como saida
    pinMode(11, OUTPUT);

    //Define a porta do led builtin como saida
    pinMode(LED_BUILTIN, OUTPUT);
}

void loop()
{
    //Acende o led 1
    digitalWrite(11, HIGH);

    //Acende o led builtin
    digitalWrite(LED_BUILTIN, HIGH);

    //Aguarda intervalo de tempo em milissegundos
    delay(1000);

    //Apaga o led 1
    digitalWrite(11, LOW);

    //Acende o led builtin
    digitalWrite(LED_BUILTIN, LOW);

    //Aguarda intervalo de tempo em milissegundos
    delay(1000);
}