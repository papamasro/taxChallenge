Introducción
¡Bienvenido al repositorio del proyecto Tenpo Challenge! Este proyecto está desarrollado utilizando Spring y tiene como objetivo [proporcionar una breve descripción del propósito y los objetivos del proyecto].

Manejo de Errores
Actualmente, la implementación para manejar errores se considera deficiente y necesita mejoras. Se requiere un análisis cuidadoso y mejoras en esta área para asegurar un manejo robusto de errores en toda la aplicación.

Estado de Salud (Health Check)
Para verificar el estado de salud de la aplicación, puedes utilizar el siguiente enlace: http://localhost:8080/actuator/health

Mejoras
El proyecto se beneficiaría con las siguientes mejoras:

Mejoras Obligatorias
Integración de JWT: Implementar JWT (JSON Web Tokens) para una autenticación y autorización seguras.

Implementación de Logs: Introducir mecanismos de registro adecuados en todos los servicios y controladores para facilitar la depuración y el monitoreo efectivos.

Mejora de la Seguridad: Asegurar que la información sensible, como las contraseñas de los usuarios y las URL de las bases de datos, no estén expuestas ni se almacenen en texto plano.

Integración de Micrometer y Grafana: Integrar Micrometer con Grafana para un monitoreo avanzado y la recopilación de métricas.

Mejoras Opcionales
Integración de Lombok: Considerar integrar Lombok para reducir el código repetitivo en el proyecto.

MapStruct: Explorar la posibilidad de utilizar MapStruct para simplificar el mapeo entre DTOs (Data Transfer Objects) y entidades.

Documentación con Swagger
Los detalles de los puntos finales de la API se encuentran en la documentación de Swagger. Mientras el proyecto está en progreso, puedes acceder a Swagger UI a través de: http://localhost:8080/swagger-ui/index.html

Dependencias
El proyecto actualmente depende de las siguientes librerías:

Gson
Spring Framework
PostgreSQL
Arquitectura
El proyecto sigue una clara separación de responsabilidades con los siguientes componentes:

Controlador (Controller): Responsable de manejar las solicitudes entrantes y generar respuestas.

Servicio (Service): Contiene la lógica del negocio y se encarga de procesar y validar datos.

Repositorio (Repository): Maneja las interacciones con la base de datos, proporcionando una abstracción para el acceso a datos.

Configuración (Configuration): Administra la configuración de las dependencias utilizadas en toda la aplicación.

Configuración de Redis
Ten en cuenta que Redis no está configurado actualmente para escalar. En consecuencia, el limitador de tasa puede no funcionar correctamente bajo cargas pesadas. Una solución potencial para implementar un mecanismo de almacenamiento en caché distribuida utilizando Redis Cluster se describe en este artículo: Distributed Caching in Java Microservices using Redis Cluster. Sin embargo, esta solución no se ha implementado en este proyecto debido a limitaciones de tiempo.

Ejecutar PostgreSQL localmente
Para ejecutar una base de datos PostgreSQL de forma local, puedes utilizar el siguiente comando de Docker:

bash
Copy code
docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres
Ejecutar Redis localmente
Para ejecutar Redis en un contenedor de Docker de forma local, puedes utilizar el siguiente comando:

bash
Copy code
docker run -d -p 6379:6379 --name myredis --network redisnet redis
No dudes en contactarnos si tienes alguna pregunta o necesitas más ayuda.

¡Gracias por tu interés en el proyecto!



Handler errors are poorly implementated, must be improved


health up http://localhost:8080/actuator/health



improvements
must: add JWT,add logger to all services and controller, hide users passwords and urls of dbs, micrometer and grafana

optative: add lombok, mapstruct

in progress swagger http://localhost:8080/swagger-ui/index.html


dependencies

gson
spring
postgresql



controller responsabilicy mapper object

service business logic

repository access dbs

config configuration of dependencies


the redis is not configure for scaling, the rate limiter will not work property
possible solution not implemented because no time
https://www.nexsoftsys.com/articles/distributed-caching-in-java-microservices-using-redis-cluster.html

run local postgres

docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

run reddis in docker local

docker run -d -p 6379:6379 --name myredis --network redisnet redis






run docker container

# Compilar la aplicación
mvn clean package

# Construir las imágenes Docker
docker-compose build --no-cache

# Iniciar los contenedores
docker-compose up --force-recreate
