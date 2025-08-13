# Laboratorio 3 — App de Tareas (Kotlin + Jetpack Compose)

Aplicación Android nativa (una sola pantalla) que permite **agregar y visualizar tareas** usando **Kotlin** y **Jetpack Compose**. La app utiliza \*\*estado con \*\*\`\`, muestra **Snackbar** al validar entradas vacías, y coloca una **imagen de fondo** incluida en `res/drawable`. Las cadenas visibles de UI se gestionan en `res/values/strings.xml`.

---

## 🎯 Objetivo del laboratorio

Practicar y evidenciar:

* **Composición de UI** con `@Composable`.
* **Manejo de estado** con `remember` y `mutableStateOf` / `mutableStateListOf`.
* **Interacción con el usuario** y **recomposición automática** al cambiar el estado.
* **Uso de recursos gráficos** (imagen de fondo en `drawable`).
* **Separación de cadenas** en `strings.xml` y acceso con `stringResource`.

---

## ✅ Requerimientos funcionales (cumplimiento)

* **Pantalla única (MainActivity)** con un título visible en la parte superior. 
* **Lista de tareas** (inicialmente vacía) que se renderiza con `LazyColumn`. 
* **Campo de texto** (`OutlinedTextField`) para escribir una nueva tarea. 
* **Botón Agregar** que inserta la tarea a la lista. 
* **Imagen de fondo** incluida como recurso `res/drawable` y mostrada a pantalla completa. 
* **Cadenas**: acceso con `stringResource(R.string.id)` según `strings.xml`. 
* **Validación**: si el campo está vacío, se muestra **Snackbar**. 

> **Componentes recomendados usados:** `Column`, `LazyColumn`, `Image`, `OutlinedTextField`, `Button`, `Card`, `Scaffold` + `SnackbarHost`.

---

## 🧩 Descripción de la implementación

* **Actividad principal**: `MainActivity` establece el tema y compone `AppElements()`.
* **Layout**: `Scaffold` con `SnackbarHost`, `Box` para fondo con `Image`, y contenido en `Column`.
* **Estado**:

  * `taskList`: `remember { mutableStateListOf<String>() }` — lista dinámica de tareas.
  * `newTaskText`: `remember { mutableStateOf("") }` — texto del campo de entrada.
  * `snackbarHostState` + `rememberCoroutineScope()` para lanzar el mensaje de error.
* **Acciones**:

  * **Agregar**: si `newTaskText` no está en blanco, se inserta al inicio de la lista y se limpia el campo.
  * **Error**: si está vacío, se muestra `Snackbar` con el texto de `R.string.empty_task_error`.
* **Renderizado de lista**: `LazyColumn` que crea una `Card` por cada tarea.
* **Accesibilidad**: `contentDescription` para la imagen de fondo.

---

## 🖼️ Recursos gráficos

* La imagen de fondo se referencia en el código como `R.drawable.descarga__13_` y se muestra con `ContentScale.Crop` para cubrir toda la pantalla.
* Colocar el archivo de imagen (PNG/JPG) en `app/src/main/res/drawable/`.

---

## 🌍 Cadenas (`strings.xml`)

Cadenas provistas por el proyecto:

```xml
<resources>
    <string name="app_name">Laboratorio_3</string>
    <string name="app_title">Lista de Tareas</string>
    <string name="background_image_description">Fondo abstracto de montañas nevadas</string>
    <string name="new_task_label">Nueva tarea</string>
    <string name="add_task_button">Agregar</string>
    <string name="empty_task_error">Por favor ingresa una tarea</string>
</resources>
```

Uso en el código:

```kotlin
Text(text = stringResource(id = R.string.app_title))
OutlinedTextField(label = { Text(stringResource(id = R.string.new_task_label)) }, ...)
Button(onClick = { /* ... */ }) { Text(stringResource(id = R.string.add_task_button)) }
Image(contentDescription = stringResource(id = R.string.background_image_description), ...)
```

---

## 🗂️ Estructura del proyecto

```
app/
  src/main/
    java/com/example/laboratorio_3/
      MainActivity.kt
    res/
      drawable/
        descarga__13_.png | .jpg    # imagen de fondo usada por la app
      values/
        strings.xml                  # cadenas de la UI
```

---

## 🚀 Instalación y ejecución

1. **Clonar** el repositorio

   ```bash
   git clone https://github.com/IvanaFD/uvg-ppm-lab3-android.git
   cd uvg-ppm-lab3-android
   ```
2. **Abrir** el proyecto en **Android Studio** (Giraffe o superior) y sincronizar Gradle.
3. **Configurar** un dispositivo físico (depuración USB) o un **emulador**.
4. **Ejecutar** la app con ▶️ **Run**.

> **Requisitos sugeridos**: Kotlin + Jetpack Compose + Material 3; Min SDK 24.

---

## 🧪 Pruebas manuales sugeridas

1. **Lista vacía** al iniciar: se debe mostrar el estado de lista sin elementos.
2. **Agregar una tarea válida**: escribir un texto y pulsar **Agregar** → se inserta como primer elemento y el campo se limpia.
3. **Agregar con campo vacío**: pulsar **Agregar** sin texto → aparece un **Snackbar** con `empty_task_error`.
4. **Desempeño básico**: agregar varias tareas y verificar el scroll de `LazyColumn`.

---

## 👥 Organización del trabajo (Canvas — Lab3)

* El laboratorio se realiza en **grupos de 3 personas**. Completa esta tabla en tu entrega final:

| Rol                | Nombre   | Usuario GitHub |
| ------------------ | -------- | -------------- |
| Desarrollo         | *Marcela, Ivana* | @*anaxmarcela* @*IvanaFD*      |
| QA / Documentación | *Marcela, Ivana* | @*anaxmarcela* @*IvanaFD*       |
| Diseño / UX        | *Marcela, Ivana* | @*anaxmarcela* @*IvanaFD*      |

---

## 📸 Evidencias 

https://github.com/IvanaFD/uvg-ppm-lab3-android/tree/6a2a8f551f31f6bae73cfff52e57741c7c1db949/Video%20de%20Prueba

---

## 🧠 Conceptos de Compose evidenciados

* `@Composable` para componer UI declarativa.
* `remember` / `mutableStateOf` / `mutableStateListOf` para estado observable y recomposición.
* `Scaffold` + `SnackbarHost` para feedback.
* `Column` y `LazyColumn` para la disposición de elementos.
* `Image` a pantalla completa como fondo (`ContentScale.Crop`).

---

