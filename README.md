# 📅 Event Planner

## Descripción del Proyecto

**Event Planner** es una aplicación de consola diseñada para gestionar eventos corporativos, sociales y artísticos de manera eficiente. Permite a los usuarios registrar nuevos eventos, supervisar las tareas asociadas y asegurar que todo se ejecute según lo planeado.

La aplicación está desarrollada en Java y utiliza clases como `Event` y `EventTask` para manejar la información necesaria de cada evento y sus respectivas tareas.

---

## Funcionalidades

### 1. ✨ **Añadir Evento**
- Permite crear un nuevo evento con:
    - 🎭 **Título**
    - 📅 **Fecha**
    - 🔍 **Prioridad** (HIGH, MEDIUM, LOW)
- Opción para agregar tareas asociadas al evento.

### 2. ❌ **Borrar Evento**
- Permite borrar un evento específico proporcionando su **título** exacto.
- Si existen eventos con el mismo título, solicita la fecha para identificar cuál borrar.

### 3. 📋 **Listar Eventos**
- Muestra todos los eventos registrados con:
    - 🎭 **Título**
    - 📅 **Fecha**
    - 🔍 **Prioridad**
    - ✔️ **Estado de Tareas** (Completadas/No Completadas)

### 4. ✅/❌ **Marcar/Desmarcar Tarea**
- Permite al usuario seleccionar un evento y ver sus tareas asociadas.
- Permite marcar/desmarcar tareas como **completadas** o **pendientes**.

### 5. 🗂️ **Validación de Fechas**
- Verifica la validez de las fechas introducidas usando excepciones para fechas inválidas.

### 6. 📝 **Manejo de Tareas**
- Adición de múltiples tareas a un evento con opción de marcar/desmarcar tareas.
