improvements
must: add JWT,add logger to all services and controller, hide users passwords and urls of dbs,

optative: add lombok





run local postgres

docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

run reddis in docker local

docker run -d -p 6379:6379 --name myredis --network redisnet redis






run docker container

# Compilar la aplicación
mvn clean package

# Construir las imágenes Docker
docker-compose build

# Iniciar los contenedores
docker-compose up
