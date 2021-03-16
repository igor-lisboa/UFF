// Load libraries
#include "BluetoothSerial.h"
#include "DHT.h"

// Check if Bluetooth configs are enabled
#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

// Bluetooth Serial object
BluetoothSerial SerialBT;
// DHT11
DHT dht(4, DHT11);

void setup() {
  Serial.begin(9600);
  // Bluetooth device name
  SerialBT.begin("ESP32");
  Serial.println("The device started, now you can pair it with bluetooth!");
  dht.begin();
}

void loop() {
  // Wait a few seconds between measurements.
  delay(10000);

  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  float h = dht.readHumidity();
  // Read temperature as Celsius (the default)
  float t = dht.readTemperature();
  // Read temperature as Fahrenheit (isFahrenheit = true)
  float f = dht.readTemperature(true);

  // Check if any reads failed and exit early (to try again).
  if (isnan(h) || isnan(t) || isnan(f)) {
    Serial.println(F("Failed to read from DHT sensor!"));
    return;
  }

  // Compute heat index in Fahrenheit (the default)
  float hif = dht.computeHeatIndex(f, h);
  // Compute heat index in Celsius (isFahreheit = false)
  float hic = dht.computeHeatIndex(t, h, false);

  SerialBT.print(F("Humidity: "));
  SerialBT.print(h);
  SerialBT.print(F("%  Temperature: "));
  SerialBT.print(t);
  SerialBT.print(F("°C "));
  SerialBT.print(f);
  SerialBT.print(F("°F  Heat index: "));
  SerialBT.print(hic);
  SerialBT.print(F("°C "));
  SerialBT.print(hif);
  SerialBT.println(F("°F"));
}
