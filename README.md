# Meli challenge

## Introducción.
Esta aplicacion fue desarrollada por Jorge Gabriel Rojas.
Se desarrollo con java 11.
La base de datos es H2 (no seria dificil cambiar la base de datos, solo habria que cambiar los properties y configurar docker-compose)
Se utilizo caffeine para mejorar la performance de las consultas mas utilizadas.
Se utilizo Swagger para mejorar la documentacion de los endpoints y facilitar la ejecucion de los mismos.
Se utilizo como carga inicial el archivo data.sql con algunos datos de prueba ya que el ejercicio contempla no crear los endpoints para la carga inicial de datos

## Compilacion
En el path del proyecto abrimos una consola (linux) o cmd en windows
primero verificar la version java por defecto ya que esta app esta seteada con java 11
./mvnw --version

Si la version de java es menor hay que proceder a configurar java 11 y setearla por defecto. Repetir ese paso hasta asegurarse que esta java 11 por defecto.
Ejecutar el comando para compilar
./mvnw clean package

Si todo sale exitosamente el jar aparecera en la carpeta target

## Ejecucion en docker
Primero que nada ejecutar el paso de compilacion para crear el jar.
Segundo, teniendo el docker configurado correctamente procedemos a hacer el build.
Para esto nos posicionaremos en la carpeta del proyecto asegurandonos estar en el mismo path donde esta el archivo Dockerfile
Con el siguiente comando ejecutaremos el build. 

* docker build -t meli-challenge .

Una vez que se realice el build procedemos a correr la imagen docker

* docker run -p 8082:8082 meli-challenge

Como por defecto para el proyecto use el puerto 8082 el comando anterior mapea esos puertos, si tira un error que el puerto esta siendo usado hay que cambiar el puerto a uno que este libre

Procedemos a abrir la documentacion swagger con un navegador web 
* http://localhost:8082/swagger-ui/index.html

## Documentacion con swagger
Para realizar la operacion de reserva procedemos a buscar los shows con el endpoint "/show/"
Si elegimos por ejemplo el show 1 seguimos a buscar las funciones con el endpoint "/function/show/"
Si elegimos la funcion 1 buscamos las butacas disponibles para ese show con el endpoint "/seats/function/"
Si elegimos la butaca 4 vamos a reservar con el endpoint "/reservation/"
Una vez que ingresamos todos los datos si queremos corroborar que se dio de alta esta el endpoint "/reservation/" que recibe de parametro el id del ticket 