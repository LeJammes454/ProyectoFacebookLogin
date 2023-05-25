# Aplicación de Android con Inicio de Sesión y Compartición en Facebook

Esta es una aplicación de Android que te permite iniciar sesión con tu cuenta de Facebook y compartir contenido en la red social. Utiliza el SDK de Facebook para implementar estas funcionalidades y está desarrollada en Kotlin.

## Funcionalidades

- Inicio de sesión con Facebook: Permite a los usuarios iniciar sesión en la aplicación utilizando sus cuentas de Facebook.
- Compartición de contenido en Facebook: Permite a los usuarios compartir contenido directamente en su perfil de Facebook desde la aplicación.

## Tecnologías Utilizadas

- Android Studio: El entorno de desarrollo integrado (IDE) utilizado para desarrollar la aplicación.
- Kotlin: El lenguaje de programación utilizado para escribir el código de la aplicación.
- Facebook SDK: La biblioteca proporcionada por Facebook para integrar el inicio de sesión y la compartición de contenido en la red social.

## Requisitos de Instalación

- Android Studio 4.0 o superior.
- Android SDK con nivel de API 21 o superior.
- Cuenta de Facebook para configurar una nueva aplicación y obtener el ID de aplicación (App ID).

## Configuración

1. Crea una nueva aplicación en el sitio web de desarrolladores de Facebook y obtén el ID de aplicación (App ID).
2. Abre el proyecto en Android Studio.
3. Agrega el ID de aplicación de Facebook en el archivo `AndroidManifest.xml` en la etiqueta de meta-datos con el nombre `com.facebook.sdk.ApplicationId`.
4. Asegúrate de tener las dependencias necesarias en el archivo `build.gradle` (módulo de la aplicación).
5. Configura los permisos necesarios en el archivo `AndroidManifest.xml`, como `INTERNET` y `ACCESS_NETWORK_STATE`.
6. Implementa el código necesario en la clase `MainActivity` para manejar el inicio de sesión con Facebook y la compartición de contenido.

## Uso

1. Ejecuta la aplicación en un emulador o dispositivo Android.
2. Se mostrará un botón de inicio de sesión con Facebook. Al hacer clic en él, se abrirá la página de inicio de sesión de Facebook en una ventana emergente.
3. Inicia sesión con tus credenciales de Facebook en la ventana emergente.
4. Después de un inicio de sesión exitoso, se mostrará tu nombre y correo en la pantalla principal de la aplicación.
5. Puedes utilizar el botón de compartir contenido para compartir en Facebook. Se abrirá un cuadro de diálogo que te permitirá ingresar una URL y un texto adicional.
6. Una vez que ingreses los detalles y hagas clic en "Compartir", se publicará el contenido en tu perfil de Facebook.

## Contribución

Las contribuciones son bienvenidas. Si deseas mejorar la aplicación, por favor, envía un pull request o crea un issue para discutir tus ideas.

## Licencia

Este proyecto se distribuye bajo la licencia MIT. Consulta el archivo [LICENSE](LIENSE) para obtener más detalles.
😼
.
