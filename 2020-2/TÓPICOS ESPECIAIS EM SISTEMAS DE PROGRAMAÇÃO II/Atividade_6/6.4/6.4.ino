void setup() {
 
  pinMode(4, OUTPUT);
  pinMode(18, OUTPUT);
  pinMode(22, OUTPUT);
 
  xTaskCreate(
                    taskOne,          /* Task function. */
                    "TaskOne",        /* String with name of task. */
                    10000,            /* Stack size in bytes. */
                    NULL,             /* Parameter passed as input of the task */
                    1,                /* Priority of the task. */
                    NULL);            /* Task handle. */
 
  xTaskCreate(
                    taskTwo,          /* Task function. */
                    "TaskTwo",        /* String with name of task. */
                    10000,            /* Stack size in bytes. */
                    NULL,             /* Parameter passed as input of the task */
                    1,                /* Priority of the task. */
                    NULL);            /* Task handle. */
  
  xTaskCreate(
                    taskThree,        /* Task function. */
                    "TaskThree",      /* String with name of task. */
                    10000,            /* Stack size in bytes. */
                    NULL,             /* Parameter passed as input of the task */
                    1,                /* Priority of the task. */
                    NULL);            /* Task handle. */
 
}
 
void loop() {
  //delay(1000);
}
 
void taskOne( void * parameter )
{
 
    for( int i = 0; ;){
 
        digitalWrite(4, HIGH);
        delay(50);
        digitalWrite(4, LOW);
        delay(50);
    }
    vTaskDelete( NULL );
 
}
 
void taskTwo( void * parameter)
{
 
    for( int i = 0; ;){
 
        digitalWrite(18, HIGH);
        delay(300);
        digitalWrite(18, LOW);
        delay(300);
    }
    vTaskDelete( NULL );
 
}

void taskThree( void * parameter)
{
 
    for( int i = 0; ;){
 
        digitalWrite(22, HIGH);
        delay(2000);
        digitalWrite(22, LOW);
        delay(2000);
    }
    vTaskDelete( NULL );
 
}
