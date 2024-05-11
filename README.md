# API Biblioteca

## Introducción
Este proyecto es una API desarrollada en Java para administrar una biblioteca de libros. Proporciona endpoints para gestionar libros, autores, géneros y entidades relacionadas con la biblioteca.

## Pruebas Unitarias
Las pruebas unitarias se encuentran alojadas en la siguiente [dirección](https://github.com/mariamarmolejo/LibraryP/blob/main/src/test/java/com/example/libraryp/ApiTest.java).

## Configuración de la Base de Datos
En el archivo [application.properties]([https:](https://github.com/mariamarmolejo/LibraryP/blob/main/src/main/resources/application.properties), se encuentra la configuración de la conexión a la base de datos. Es importante asegurarse de que la ruta a la base de datos esté correctamente especificada, así como proporcionar el usuario y la contraseña adecuados para establecer la conexión de manera satisfactoria.

## API Compilada
El archivo JAR [LibraryP](https://drive.google.com/drive/folders/1ea8K6FeIO7MrEpDLvudhVJdZQnWKQ9V6?usp=drive_link) es el ejecutable que pone en funcionamiento la API. Antes de ejecutarlo, es necesario tener en cuenta la configuración de la base de datos y, si es necesario, recompilar el proyecto para que utilice la nueva conexión y evite errores de inicialización.

## Ejecución desde IntelliJ
Para ejecutar la API desde IntelliJ, simplemente ejecute la clase [LibraryPApplication](https://github.com/mariamarmolejo/LibraryP/blob/main/src/main/java/com/example/libraryp/LibraryPApplication.java). Esto iniciará la API utilizando Spring Boot.

## Interfaz Web
Para utilizar la interfaz web, es imprescindible tener la configuración correcta de la base de datos y la API en ejecución. Una vez que esto esté configurado, puede abrir la interfaz desde el archivo [index.html](http://localhost:8080/index.html).
