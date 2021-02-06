void setup()
{
    //Define a porta do led 1 como saida
    pinMode(11, OUTPUT);

    //Define a porta do led 2 como saida
    pinMode(10, OUTPUT);
}

void loop()
{
    //Acende o led 1
    digitalWrite(11, HIGH);

    //Apaga o led 2
    digitalWrite(10, LOW);

    //Aguarda intervalo de tempo em milissegundos
    delay(1000);

    //Apaga o led 1
    digitalWrite(11, LOW);

    //Acende o led 2
    digitalWrite(10, HIGH);

    //Aguarda intervalo de tempo em milissegundos
    delay(1000);
}