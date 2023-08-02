Requerimientos
--
Debes desarrollar una API REST en Spring Boot utilizando Java 11 o superior, con las siguientes funcionalidades:

a) Debe contener un servicio llamado api-rest que reciba 2 números, los sume, y le aplique un porcentaje adquirido de un servicio externo. Por ejemplo, si el servicio recibe 5 y 5 como valores, y el porcentaje devuelto por el servicio externo es 10, entonces (5 + 5) + 10% = 11.
Consideraciones:

El servicio externo puede ser un mock y debe devolver el porcentaje sumado.

El porcentaje devuelto por el servicio externo no cambiará en 30 minutos.

Si el servicio externo falla, se debe devolver el último valor retornado. Si no hay valor, la API debe retornar un error.

Si el servicio falla, se puede reintentar hasta 3 veces.

b) Historial de todos los llamados a todos los endpoints, junto con la respuesta en caso de haber sido exitoso. Responder en JSON, con datos paginados. El guardado del historial de llamadas no debe sumar tiempo al servicio invocado y, en caso de falla, no debe impactar el llamado al servicio principal.

c) La API soporta recibir como máximo 3 RPM (request por minuto). En caso de superar ese umbral, debe retornar un error con el código HTTP y mensaje adecuado.

d) El historial se debe almacenar en una base de datos PostgreSQL.

e) Incluir errores HTTP. Mensajes y descripciones para la serie 4XX.

Se deben incluir tests unitarios.

Esta API debe ser desplegada en un docker container. El docker puede estar en un dockerhub público. La base de datos también debe correr en un contenedor docker. Se recomienda usar docker compose.

Debes agregar un Postman Collection o Swagger para que probemos tu API.

Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y cómo utilizarlo.

Tener en cuenta que la aplicación funcionará de la forma de un sistema distribuido donde puede existir más de una réplica del servicio funcionando en paralelo.





Introducción
---


¡Bienvenido al repositorio del proyecto Tenpo Challenge! Este proyecto está desarrollado utilizando Spring.

Dependencias
--
El proyecto actualmente depende de las siguientes librerías:

Java 17
Gson
Spring Framework 3
PostgreSQL
springdoc
Redisson
Bucket4j
Arquitectura


Arquitectura
--
El proyecto sigue una clara separación de responsabilidades con los siguientes componentes:

Controlador (Controller): Responsable de manejar las solicitudes entrantes y mapear respuestas.

Servicio (Service): Contiene la lógica del negocio y se encarga de procesar y validar datos.

Repositorio (Repository): Maneja las interacciones con las bases de datos, proporcionando una abstracción para el acceso a datos.

Configuración (Configuration): Administra la configuración de las dependencias utilizadas en toda la aplicación.





Ejecutar el proyecto localmente
Ejecutar JAVA localmente:

Primero, asegúrate de tener instalado Java 17 y IntelliJ.
Clona el repositorio https://github.com/papamasro/taxChallenge.
Abre el proyecto y configura las variables de entorno:
~~~
DATABASE_URL=jdbc:postgresql://localhost:5432/postgres;DATABASE_USERNAME=postgres;DATABASE_PASSWORD=mysecretpassword
~~~
Ejecutar PostgreSQL localmente:

Para ejecutar una base de datos PostgreSQL de forma local, puedes utilizar el siguiente comando de Docker:
~~~
docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres
~~~
Ejecutar Redis localmente:

Para ejecutar Redis en un contenedor de Docker de forma local, puedes utilizar el siguiente comando:
~~~
docker run -d -p 6379:6379 --name myredis --network redisnet redis
~~~
Ejecutar el proyecto en Docker
El servicio dockerizado se encuentra alojado en Docker-Hub. Para descargar la imagen, utiliza esta instrucción:

~~~
docker pull papamas/demo-java_app:1.0.0
~~~
Para correr el servicio mediante docker-compose, desde la carpeta raíz del proyecto, utiliza este comando:

~~~
docker-compose up --build
~~~

A tener en cuenta
--
Configuración de Redis
La configuración actual es básica, posiblemente esto no funcione para ejecutar varias instancias y que funcione correctamente el rate limiter.

Si tienes problemas con Redis, seguramente tengas que cambiar una configuración en application.properties.
Cambia spring.data.redis.host=redis por spring.data.redis.host=localhost o viceversa.



API
--
Login
--
Crear usuario:
--

Método: POST
URL: {{url}}/createUser

Respuesta:
~~~
{
"user": "123",
"id": 1
}
~~~
Login:
--
el login no tiene la logica de manejar passwords, solo se utiliza el id para el rate limiter.

Método: Get
URL: {{url}}/login

Respuesta:
~~~
{
"user": "123",
"id": 1
}
~~~
Ejecutar cálculo
--

Método: POST

URL: {{url}}/calculateTax

Cabecera: userId:{{userId}} 

(Es obligatorio este valor, y es el que va a tomar en cuenta el rate limiter para limitar el servicio. Este servicio posee rate limiter)

Respuesta:
~~~
{
"date": 15/05/23 12:13:41,
"tax": 0.10,
"tax": 3.3
}
~~~
Historial de llamadas
--
Se obtienen los llamados entrantes en la app. Incluye paginación.

Método: GET

URL: {{url}}/history?size=10&page=0

Respuesta:
~~~
{
"pageNumber": 0,
"pageSize": 10,
"totalPages": 1,
"result": "[{\"id\":6,\"endpoint\":\"calculateTax\",\"timestamp\":\"2023-08-01 05:59:43.167\",\"statusCode\":200,\"response\":\"{\\\"date\\\":\\\"2023-08-01 05:59:43.166\\\",\\\"tax\\\":0.1,\\\"result\\\":4.4}\"},{\"id\":5,\"endpoint\":\"getExternalTax\",\"timestamp\":\"2023-08-01 05:59:43.166\",\"statusCode\":200,\"response\":\"{\\\"timestamp\\\":\\\"196108043215\\\",\\\"name\\\":\\\"IIGG\\\",\\\"tax\\\":0.1}\"},{\"id\":4,\"endpoint\":\"calculateTax\",\"timestamp\":\"2023-08-01 05:59:38.871\",\"statusCode\":200,\"response\":\"{\\\"date\\\":\\\"2023-08-01 05:59:38.87\\\",\\\"tax\\\":0.1,\\\"result\\\":1.1}\"},{\"id\":3,\"endpoint\":\"getExternalTax\",\"timestamp\":\"2023-08-01 05:59:38.87\",\"statusCode\":200,\"response\":\"{\\\"timestamp\\\":\\\"196108043215\\\",\\\"name\\\":\\\"IIGG\\\",\\\"tax\\\":0.1}\"},{\"id\":1,\"endpoint\":\"getExternalTax\",\"timestamp\":\"2023-08-01 05:59:37.409\",\"statusCode\":200,\"response\":\"{\\\"timestamp\\\":\\\"196108043215\\\",\\\"name\\\":\\\"IIGG\\\",\\\"tax\\\":0.1}\"},{\"id\":2,\"endpoint\":\"calculateTax\",\"timestamp\":\"2023-08-01 05:59:37.409\",\"statusCode\":200,\"response\":\"{\\\"date\\\":\\\"2023-08-01 05:59:37.394\\\",\\\"tax\\\":0.1,\\\"result\\\":1.1}\"}]"
}
~~~

Mejoras Obligatorias
---
Mock: El mock actualmente está hecho con mockachino (https://www.mockachino.com/spaces/428acb5c-9f6a-45) donde se pueden probar los errores, se debe implementar Wiremock

Integración de JWT: Implementar JWT (JSON Web Tokens) para una autenticación y autorización seguras.

Mejora de la Seguridad: Asegurar que la información sensible, como las contraseñas de los usuarios y las URL de las bases de datos, no estén expuestas ni se almacenen en texto plano.

Integración de Micrometer y Grafana: Integrar Micrometer con Grafana para un monitoreo avanzado y la recopilación de métricas.

Builders: Implementar builders en vez de constructores.

Manejo de Errores: Actualmente, la implementación para manejar errores se considera deficiente y necesita mejoras. Se requiere un análisis cuidadoso y mejoras en esta área para asegurar un manejo robusto de errores en toda la aplicación.

Mejoras Opcionales
--
Integración de Lombok: Considerar integrar Lombok para reducir el código repetitivo en el proyecto.

MapStruct: Explorar la posibilidad de utilizar MapStruct para simplificar el mapeo.

Documentación con Swagger: La documentación de Swagger se encuentra en construcción, puedes verla con la configuración básica en: http://localhost:8080/swagger-ui/index.html
