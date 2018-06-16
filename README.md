# Taller2-GSI [![Build Status](https://travis-ci.org/diegoasencio96/Taller2-GSI.svg?branch=master)](https://travis-ci.org/diegoasencio96/Taller2-GSI)

Este proyecto tiene como proposito mostrar la configuración de un proyecto en JAVA con Maven, JUnit, GitHub y Travis Cli. El aplicativo en JAVA comprueba que una frase o un conjunto de frases en ingles sean de Tono Activo o Pasivo, para esta comprobación se hace uso de la librería de apache openNLP para JAVA la cual permite un procesamiento del lenguaje natural para cadenas de caracteres (textos). 

(Códigos fuente del taller 2 realizado en clase del curso de Gestión de Software de la EIS).


## Empezando

Estas instrucciones le permitirán obtener una copia del proyecto en funcionamiento en su máquina local para fines de desarrollo y prueba.


### Requisitos previos

Antes de instalar y ejecutar el proyecto debes asegurar que tengas instalado los siguientes componentes de software:

```
JAVA JDK8
```
```
NetBeans 8.1 
```


### Instalación

La instalación del proyecto es muy sencilla, solo debes seguir los siguientes pasos:

Clonar el reposiorio de GitHub

```
git clone https://github.com/diegoasencio96/Taller2-GSI.git
```

Luego importar el proyecto desde NetBeans

![Importar proyecto desde NetBeans IDE](https://image.ibb.co/khmOZd/abrirproyecto.jpg)


Para finalizar, se debe ejecutar el proyecto con el boton (Run) y nos mostrará la ejecución del proyecto.

![Ejecución del proyecto](https://image.ibb.co/maUXSy/proyecto1.jpg)


## Ejecutando las pruebas

Las pruebas unitarias se realizan con JUnit y estan configuradas con el cliente de Travis de manera automatica.


### Pruebas a metodos del controlador

Estas pruebas estan dirigidas a los métodos implementados por las clases controladoras. Se pretende probar principalmente el método de validar la frase con el fin de asegurar un resultado correcto sobre la conclusión de determinar el tipo de tono de una frase en inglés.

Abrir un terminal ubicado en el directorio del proyecto y ejecutar el siguiente comando:
```
mvn test -B
```
ó bién, al ejecutar el proyecto Maven NetBeans, o al hacer un PUSH al repositorio también se ejecutan las pruebas de JUnit localmente como remotamente por medio del cliente de Travis (Travis CLI).


## Construido con

* [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - JDK JAVA
* [JAVA SE](http://www.oracle.com/technetwork/es/java/javase/downloads/index.html) - Version de JAVA
* [Maven](https://maven.apache.org/) - Gestión y construcción de proyectos
* [Travis CLI](https://travis-ci.org) - Integración continua
* [OpenNLP](https://opennlp.apache.org/) - Procesamiento de texto en lenguaje natural


## Contribuyendo

Lea [CONTRIBUTING.md]() para obtener detalles sobre nuestro código de conducta y sobre el proceso para enviarnos solicitudes de extracción.


## Versiones

Usamos [GitHub](https://github.com/) para versionar. Para las versiones disponibles, vea las etiquetas en este repositorio .


## Autores

* **Diego A Asencio C** - *Integrante 1* - [diegoasencio96](https://github.com/diegoasencio96)
* **Jorge Luis Hernandez** - *Integrante 2* - [jorgejlhc](https://github.com/jorgejlhc)
* **Julian Ramos** - *Integrante 3* - [julian3493](https://github.com/julian3493)

Ver también la lista de [contribuidores](https://github.com/diegoasencio96/Taller2-GSI/contributors) que participaron en este proyecto.


## Licencia

Este proyecto aun no tiene licencia.


## Agradecimientos

* Docente Javier Enciso por motivarnos al desarrollo del proyecto
