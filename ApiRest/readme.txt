improvements
must: add JWT,add logger to all services and controller, hide users passwords and urls of dbs, micrometer and grafana

optative: add lombok, mapstruct




dependencies

gson
spring
postgresql


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
