#include <WiFi.h>
#include <HTTPClient.h>
#include "DHT.h"

const char* ssid = "ebola";
const char* password = "xingu236";

// Domain Name with full URL Path for HTTP POST Request
const char* serverName = "https://iot-guerra-alexa-trab-final.herokuapp.com/info";
// Service API Key
String apiKey = "";

// THE DEFAULT TIMER IS SET TO 10 SECONDS FOR TESTING PURPOSES
// For a final application, check the API call limits per hour/minute to avoid getting blocked/banned
unsigned long lastTime = 0;
// Set timer to 10 minutes (600000)
//unsigned long timerDelay = 600000;
// Timer set to 10 seconds (10000)
unsigned long timerDelay = 10000;

// DHT
DHT dht(4, DHT11);

void setup() {
  Serial.begin(115200);
  dht.begin();

  WiFi.begin(ssid, password);
  Serial.println("Connecting");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.print("Connected to WiFi network with IP Address: ");
  Serial.println(WiFi.localIP());

  Serial.println("Timer set to 10 seconds (timerDelay variable), it will take 10 seconds before publishing the first reading.");

  // Random seed is a number used to initialize a pseudorandom number generator
  randomSeed(analogRead(33));
}

void loop() {
  delay(10000);
  //Send an HTTP POST request every 10 seconds
  if ((millis() - lastTime) > timerDelay) {
    //Check WiFi connection status
    if (WiFi.status() == WL_CONNECTED) {
      HTTPClient http;

      // Your Domain name with URL path or IP address with path
      http.begin(serverName);

      // Reading sensors
      float h = dht.readHumidity();
      float t = dht.readTemperature();

      // Check if any reads failed and exit early (to try again)
      if (isnan(h) | isnan(t)) {
        Serial.println(F("Failed to read from DHT sensor!"));
        return;
      }

      // Compute heat index
      float hic = dht.computeHeatIndex(t, h, false);

      // Specify content-type header
      http.addHeader("Content-Type", "application/json");
      //http.addHeader("Cache-Control", "no-cache");
      //http.addHeader("Host", "iot-guerra-alexa-trab-final.herokuapp.com");
      // Converting to an integer so that Alexa can read it
      int intH = (int)h;
      int intT = (int)t;
      int intHIC = (int)hic;
      // JSON data to send with HTTP POST
      String httpRequestData = "{\"itens\" : [\"A umidade do quarto está em: " + String(intH) + "% .\" , \"A temperatura do quarto está em: " + String(intT) + "ºC .\" , \"O índice de calor do quarto está em: " + String(intHIC) + "ºC .\"]}";
      String size = String(httpRequestData.length());
      //http.addHeader("Content-Length", size);
      // Send HTTP POST request
      int httpResponseCode = http.POST(httpRequestData);

      Serial.print("HTTP Request data: ");
      Serial.println(httpRequestData);
      Serial.print("HTTP Response code: ");
      Serial.println(httpResponseCode);

      // Free resources
      http.end();
    }
    else {
      Serial.println("WiFi Disconnected");
    }
    lastTime = millis();
  }
}
