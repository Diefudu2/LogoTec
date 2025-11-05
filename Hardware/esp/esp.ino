#include <Arduino.h>
#include <ESP32Servo.h>
#include <WiFi.h>

/* ============================================================
   CONFIGURACIÓN DE HARDWARE
   ============================================================ */
const int A_H = 25;   // PNP izquierdo
const int A_L = 26;   // NPN izquierdo
const int B_H = 27;   // PNP derecho
const int B_L = 14;   // NPN derecho
const int SERVO_PIN = 32;
const int MOTOR_EN_PIN = 12;   // 🔧 Pin de acople de alimentación

// Servo lápiz
Servo penServo;

// Ángulos del servo (AJUSTA SI EL LÁPIZ NO SUBE/BAJA BIEN)
const int PEN_UP_ANGLE = 80;    // Ángulo cuando el lápiz está arriba
const int PEN_DOWN_ANGLE = 120; // Ángulo cuando el lápiz está abajo

/* ============================================================
   PARÁMETROS DE MOVIMIENTO (CALIBRABLES)
   ============================================================ */
unsigned long STEP_TIME = 10;   // ms por "unidad" de avance
unsigned long TURN_TIME = 9;    // ms por grado de giro
int deadTime = 80;              // tiempo muerto entre movimientos

/* ============================================================
   CONFIGURACIÓN DE WIFI
   ============================================================ */
const char* WIFI_SSID = "Familia Moreno";        // 🔧 Cambia por tu red
const char* WIFI_PASSWORD = "Maria123."; // 🔧 Cambia por tu contraseña
const uint16_t WIFI_PORT = 6789;
WiFiServer wifiServer(WIFI_PORT);
WiFiClient wifiClient;

/* ============================================================
   INICIALIZACIÓN
   ============================================================ */
void setupPins() {
  pinMode(A_H, OUTPUT);
  pinMode(A_L, OUTPUT);
  pinMode(B_H, OUTPUT);
  pinMode(B_L, OUTPUT);
  pinMode(MOTOR_EN_PIN, OUTPUT);

  digitalWrite(A_H, LOW);
  digitalWrite(A_L, LOW);
  digitalWrite(B_H, LOW);
  digitalWrite(B_L, LOW);
  digitalWrite(MOTOR_EN_PIN, LOW); // Motores inicialmente apagados
}

/* ============================================================
   CONTROL DE ACOPLE
   ============================================================ */
void enableMotors() {
  digitalWrite(MOTOR_EN_PIN, HIGH);
  delay(50); // estabilización
}

void disableMotors() {
  digitalWrite(MOTOR_EN_PIN, LOW);
}

/* ============================================================
   CONTROL DE LÁPIZ
   ============================================================ */
void penUp() {
  penServo.attach(SERVO_PIN, 500, 2400);
  penServo.write(PEN_UP_ANGLE);
  delay(250);
  penServo.detach();
}

void penDown() {
  penServo.attach(SERVO_PIN, 500, 2400);
  penServo.write(PEN_DOWN_ANGLE);
  delay(250);
  penServo.detach();
}

/* ============================================================
   PARAR TODO
   ============================================================ */
void stopAll() {
  digitalWrite(A_H, LOW);
  digitalWrite(A_L, LOW);
  digitalWrite(B_H, LOW);
  digitalWrite(B_L, LOW);
}

void notifyReady() {
  Serial.println("READY");
  if (wifiClient && wifiClient.connected()) wifiClient.println("READY");
}

/* ============================================================
   MOVIMIENTOS
   ============================================================ */
void moveForward(int units) {
  if (units <= 0) return;
  stopAll();
  delay(deadTime);

  enableMotors();
  digitalWrite(A_H, HIGH);
  digitalWrite(A_L, LOW);
  digitalWrite(B_H, LOW);
  digitalWrite(B_L, HIGH);

  delay(units * STEP_TIME);

  stopAll();
  disableMotors();

  notifyReady();
}

void moveBackward(int units) {
  if (units <= 0) return;
  stopAll();
  delay(deadTime);

  enableMotors();
  digitalWrite(A_H, LOW);
  digitalWrite(A_L, HIGH);
  digitalWrite(B_H, HIGH);
  digitalWrite(B_L, LOW);

  delay(units * STEP_TIME);

  stopAll();
  disableMotors();

  notifyReady();
}

void reverseTurnRight(int degrees) {
  if (degrees <= 0) return;
  stopAll();
  delay(deadTime);

  enableMotors();
  digitalWrite(A_H, HIGH);
  digitalWrite(A_L, LOW);
  digitalWrite(B_H, HIGH);
  digitalWrite(B_L, LOW);

  delay(degrees * TURN_TIME);

  stopAll();
  disableMotors();

  notifyReady();
}

void reverseTurnLeft(int degrees) {
  if (degrees <= 0) return;
  stopAll();
  delay(deadTime);

  enableMotors();
  digitalWrite(B_H, LOW);
  digitalWrite(B_L, HIGH);
  digitalWrite(A_H, LOW);
  digitalWrite(A_L, HIGH);

  delay(degrees * TURN_TIME);

  stopAll();
  disableMotors();

  notifyReady();
}

void turnRight(int d) { reverseTurnRight(d); }
void turnLeft(int d)  { reverseTurnLeft(d); }
void turnAround()     { turnRight(180); }

/* ============================================================
   FIGURAS
   ============================================================ */
void drawStar(int size) {
  penDown();
  for (int i = 0; i < 5; i++) {
    moveForward(size);
    turnRight(144);
  }
  penUp();

  notifyReady();
}

void drawSuperposedStars() {
  for (int i = 0; i < 3; i++) {
    drawStar(80);
    turnRight(72);
  }

  notifyReady();
}

void testRoutine() {
  Serial.println("Ejecutando test...");
  moveForward(100);
  turnRight(90);
  moveForward(100);
  turnLeft(90);
  drawStar(60);

  notifyReady();
}

/* ============================================================
   PROCESAR COMANDOS
   ============================================================ */
void processCommand(String cmd) {
  cmd.trim();
  cmd.toUpperCase();

  if (cmd.startsWith("F")) {
    int steps = cmd.substring(1).toInt();
    if (steps > 0) moveForward(steps);
  }
  else if (cmd.startsWith("B")) {
    int steps = cmd.substring(1).toInt();
    if (steps > 0) moveBackward(steps);
  }
  else if (cmd.startsWith("R")) {
    int deg = cmd.substring(1).toInt();
    if (deg > 0) turnRight(deg);
  }
  else if (cmd.startsWith("L")) {
    int deg = cmd.substring(1).toInt();
    if (deg > 0) turnLeft(deg);
  }
  else if (cmd == "U") {
    penUp();
    notifyReady();
  }
  else if (cmd == "D") {
    penDown();
    notifyReady();
  }
  else if (cmd == "STAR") drawStar(80);
  else if (cmd == "SUPERSTAR") drawSuperposedStars();
  else if (cmd == "TEST") testRoutine();
  else if (cmd == "STOP") {
    stopAll();
    notifyReady();
  }
  else {
    Serial.println("Comando no reconocido");
    if (wifiClient && wifiClient.connected()) wifiClient.println("Comando no reconocido");
  }
}

void setupWiFi() {
  WiFi.mode(WIFI_STA);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Conectando WiFi");
  int retries = 0;
  while (WiFi.status() != WL_CONNECTED && retries < 60) {
    delay(500);
    Serial.print(".");
    retries++;
  }
  Serial.println();
  if (WiFi.status() == WL_CONNECTED) {
    wifiServer.begin();
    wifiServer.setNoDelay(true);
    Serial.print("WiFi listo. IP: ");
    Serial.println(WiFi.localIP());
  } else {
    Serial.println("⚠️ WiFi no disponible");
  }
}

void handleWiFiClient() {
  if (WiFi.status() != WL_CONNECTED) return;

  if (!wifiClient || !wifiClient.connected()) {
    WiFiClient candidate = wifiServer.available();
    if (candidate) {
      wifiClient = candidate;
      wifiClient.setTimeout(20000);
      Serial.println("Cliente WiFi conectado");
      wifiClient.println("READY");
    }
    return;
  }

  if (wifiClient.available()) {
    String cmd = wifiClient.readStringUntil('\n');
    processCommand(cmd);
  } else if (!wifiClient.connected()) {
    wifiClient.stop();
    Serial.println("Cliente WiFi desconectado");
  }
}

/* ============================================================
   SETUP & LOOP
   ============================================================ */
void setup() {
  setupPins();
  Serial.begin(115200);
  setupWiFi();
  penUp();
  Serial.println("Robot listo. Conecta por USB o WiFi TCP.");
}

void loop() {
  if (Serial.available()) {
    String cmd = Serial.readStringUntil('\n');
    processCommand(cmd);
  }
  handleWiFiClient();
}