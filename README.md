# API Biblioteca

## Introducción
Este proyecto es una API desarrollada en Java para administrar una biblioteca de libros. Proporciona endpoints para gestionar libros, autores, géneros y entidades relacionadas con la biblioteca.

## Configuración de la Base de Datos
En el archivo [application.properties](https:), se encuentra la configuración de la conexión a la base de datos. Es importante asegurarse de que la ruta a la base de datos esté correctamente especificada, así como proporcionar el usuario y la contraseña adecuados para establecer la conexión de manera satisfactoria.

## API Compilada
El archivo JAR [LibraryP](https:) es el ejecutable que pone en funcionamiento la API. Antes de ejecutarlo, es necesario tener en cuenta la configuración de la base de datos y, si es necesario, recompilar el proyecto para que utilice la nueva conexión y evite errores de inicialización.

## Ejecución desde IntelliJ
Para ejecutar la API desde IntelliJ, simplemente ejecute la clase [LibraryPApplication](https:). Esto iniciará la API utilizando Spring Boot.

## Interfaz Web
Para utilizar la interfaz web, es imprescindible tener la configuración correcta de la base de datos y la API en ejecución. Una vez que esto esté configurado, puede abrir la interfaz desde el archivo [index.html](https:).

## Pruebas Unitarias
Las pruebas unitarias se encuentran alojadas en la siguiente [dirección]().