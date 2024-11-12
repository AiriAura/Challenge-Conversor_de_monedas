# Challenge-Conversor_de_monedas
# Conversor de Monedas - Desafío de Programación

## Descripción

¡Bienvenido al desafío de programación! En este proyecto, construí un **Conversor de Monedas** utilizando **Java**. El programa realiza solicitudes a una API de tasas de cambio, maneja respuestas en formato JSON, filtra monedas específicas y convierte valores de una moneda a otra de manera interactiva. A lo largo de este desafío, aprendí a utilizar **HttpClient** para realizar solicitudes HTTP, **Gson** para el análisis de respuestas JSON, y cómo interactuar eficientemente con APIs.

### Objetivo:
Desarrollar una aplicación de consola que permita convertir valores entre diferentes monedas utilizando tasas de cambio obtenidas desde una API externa. El sistema es interactivo y permite al usuario elegir entre varias opciones de conversión.

## Pasos del Desafío

### 1. Configuración del Ambiente Java
Antes de comenzar con el desarrollo, aseguré que mi ambiente de desarrollo estuviera listo. Instalé **Java 17** y configuré un IDE como **IntelliJ IDEA**. También agregué las dependencias necesarias para trabajar con la API y manejar la respuesta JSON.

### 2. Creación del Proyecto
Creé un nuevo proyecto en mi IDE y añadí las dependencias necesarias para realizar solicitudes HTTP con **HttpClient** y manejar JSON con **Gson**. Configuré el proyecto para empezar a consumir la API de tasas de cambio.

### 3. Consumo de la API
Para consumir la API, utilicé la clase **HttpClient** de Java. Hice una solicitud HTTP GET a la API de tasas de cambio para obtener la información actualizada sobre las tasas de conversión entre diferentes monedas.

### 4. Análisis de la Respuesta JSON
Una vez que recibí la respuesta de la API, utilicé **Gson** para analizar el JSON y convertirlo en un objeto Java. Deserialicé las tasas de cambio y la moneda base, lo que me permitió trabajar con los datos de manera más estructurada.

### 5. Filtro de Monedas
Para el filtro de monedas, utilicé los códigos de moneda proporcionados por la API (como ARS, USD, BRL, etc.). Permití que el usuario eligiera entre algunas de estas monedas para realizar la conversión. Solo mostré las monedas seleccionadas para realizar las conversiones.

### 6. Exhibición de Resultados a los Usuarios
Implementé un menú interactivo en la consola donde el usuario puede seleccionar las opciones de conversión. Una vez que el usuario ingresa las monedas de origen y destino, el programa realiza la conversión utilizando las tasas de cambio obtenidas y muestra el resultado en la consola.

## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación utilizado.
- **HttpClient**: Para realizar solicitudes HTTP a la API de tasas de cambio.
- **Gson**: Biblioteca utilizada para deserializar las respuestas JSON de la API.
- **API de tasas de cambio**: API externa utilizada para obtener las tasas de conversión en tiempo real.

## Instrucciones de Uso

### Requisitos previos:
1. Tener **Java 17** o superior instalado en mi máquina.
2. Tener acceso a internet para obtener las tasas de cambio de la API.

### Configuración:
1. Cloné el repositorio:
   ```bash
   git clone <URL_del_repositorio>
###Abrí el proyecto en mi IDE preferido (IntelliJ IDEA).

Me aseguré de tener las dependencias necesarias configuradas en mi archivo pom.xml o build.gradle si usé Maven o Gradle.

Ejecuté el archivo ExchangeRateClient.java:

bash
Copiar código
javac ExchangeRateClient.java
java ExchangeRateClient
Funcionalidad
Menú interactivo: El programa muestra un menú donde el usuario puede elegir entre realizar una conversión de moneda o salir.
Conversión de moneda: El usuario ingresa:
El valor a convertir.
La moneda de origen (por ejemplo, USD, ARS, BOB).
La moneda de destino (por ejemplo, ARS, BOB, BRL).
Resultados: El programa muestra el resultado de la conversión, usando las tasas de cambio obtenidas de la API.
Ejemplo de Ejecución:
bash
Copiar código
--- Menú de Conversión de Monedas ---
1. Realizar una conversión de moneda
2. Salir
   Seleccione una opción: 1
   Ingrese el valor a convertir: 100
   Ingrese la moneda de origen (USD, ARS, BOB, BRL): USD
   Ingrese la moneda de destino (ARS, BOB, BRL): ARS
   Resultado de la conversión: 100.00 USD = 2200.50 ARS

--- Menú de Conversión de Monedas ---
1. Realizar una conversión de moneda
2. Salir
   Seleccione una opción: 2
   Gracias por usar el convertidor. ¡Hasta luego!
   Cómo Funciona
   Obtención de las tasas de cambio: El programa realiza una solicitud HTTP a la API y recibe las tasas de cambio para diferentes monedas. Estas tasas se deserializan desde el formato JSON utilizando Gson.
   Conversión de monedas: El valor ingresado por el usuario se convierte de acuerdo con la tasa de cambio seleccionada. El programa muestra el resultado de la conversión en la consola.
   Contribuir
   Si deseas contribuir a este proyecto, sigue estos pasos:

Haz un fork del repositorio.
Crea una rama para tu funcionalidad o corrección de errores.
Realiza tus cambios y prueba que todo funcione correctamente.
Envía un pull request con una descripción clara de lo que has hecho.
Licencia
Este proyecto está bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.