🤖 Copa K-AI 2025 - FTC (GAIA)

Este repositorio contiene el código de control y autonomía desarrollado para la Copa K-AI 2025. Nuestra solución destaca por una navegación de alta precisión y movimientos fluidos, optimizados para los retos específicos de esta temporada de FIRST Tech Challenge (FTC).

---

🚀 Tecnología Destacada: Pedro Pathing

Para la fase autónoma y la asistencia en el movimiento, hemos implementado Pedro Pathing. A diferencia de los métodos tradicionales, esta librería permite:

- Trayectorias Curvas: Movimientos más rápidos y naturales mediante curvas de Bézier.
- Seguimiento Dinámico: Corrección de errores en tiempo real para mantener la precisión milimétrica.
- Optimización de Tiempos: Reducción de frenados innecesarios entre puntos del recorrido, maximizando el puntaje en los 30 segundos de autonomía.

---

📋 Características del Proyecto

- Navegación Autónoma: Rutas complejas diseñadas con PathBuilder.
- Control de Hardware: Configuración optimizada para motores con encoders y sensores de posición.
- TeleOp: Control reactivo con algoritmos de suavizado para el conductor.
- Sistemas: Implementación de máquinas de estados para el control del Intake y el Outtake.

---

🛠️ Instalación y Configuración

1. Clonar el repositorio:
git clone https://github.com/AntonioPV14/Copa-K-AI-2025-GAIA.git

2. Abrir en Android Studio:
Importa el proyecto y espera a que Gradle sincronice las dependencias (incluyendo los archivos de Pedro Pathing).

3. Configuración del Robot:
Asegúrate de que los nombres de los motores en el HardwareMap coincidan con la configuración del Control Hub.

---

📂 Estructura de Carpetas

TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
├── pedroPathing/     # Archivos de configuración de la librería
├── autonomous/       # Rutinas de autonomía (Paths y lógica)
├── teleop/           # Código para el control con mando
└── constants/        # Parámetros de ajuste (PID, Feedforward, etc.)

---

🎮 Controles Rápidos (TeleOp)

Mando              | Función
------------------ | ------------------------------------
Stick Izquierdo     | Traslación (Movimiento omnidireccional)
Stick Derecho       | Rotación del chasis
D-Pad Up/Down       | Control de elevadores (Lifts)
Bumper R/L          | Apertura y cierre de garra

---

🤖 Copa K-AI 2025 - FTC (GAIA) | English Version

This repository contains the control and autonomy code developed for the Copa K-AI 2025. The system emphasizes high-precision navigation and smooth movements, optimized for the challenges of this FIRST Tech Challenge (FTC) season.

---

🚀 Highlight Technology: Pedro Pathing

For autonomous phase and motion assistance, we implemented Pedro Pathing, which allows:

- Curved Trajectories: Faster, more natural movements using Bézier curves.
- Dynamic Following: Real-time error correction to maintain millimeter precision.
- Time Optimization: Reduces unnecessary stops between waypoints, maximizing points during the 30-second autonomous period.

---

📋 Project Features

- Autonomous Navigation: Complex paths designed with PathBuilder.
- Hardware Control: Optimized configuration for motors with encoders and position sensors.
- TeleOp: Reactive control with smoothing algorithms for the driver.
- Systems: Implementation of state machines to manage Intake and Outtake systems.

---

🛠️ Installation and Setup

1. Clone the repository:
git clone https://github.com/AntonioPV14/Copa-K-AI-2025-GAIA.git

2. Open in Android Studio:
Import the project and wait for Gradle to sync dependencies (including Pedro Pathing library files).

3. Robot Setup:
Ensure the motor names in the HardwareMap match the Control Hub configuration.

---

📂 Folder Structure

TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
├── pedroPathing/     # Library configuration files
├── autonomous/       # Autonomous routines (paths and logic)
├── teleop/           # Driver control code
└── constants/        # Tuning parameters (PID, Feedforward, etc.)

---

🎮 Quick Controls (TeleOp)

Controller         | Function
----------------- | ------------------------------------
Left Stick         | Translation (Omni-directional movement)
Right Stick        | Chassis rotation
D-Pad Up/Down      | Lift control
Bumper R/L         | Claw open/close

--------------------------------------------------------

# License | Licencia

MIT License

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

ELSE, THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
