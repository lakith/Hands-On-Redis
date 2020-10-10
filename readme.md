# Redis

## Docker

Docker image over [here](https://hub.docker.com/_/redis)

## Running redis

```
docker network create redis
docker run -it --rm --name redis --net redis -p 6379:6379 redis:6.0-alpine
```

## Configuration

Redis configuration documentation [here](https://redis.io/topics/config)

Starting Redis with a custom config

```
docker run -it --rm --name redis --net redis -v ${PWD}/config:/etc/redis/ redis:6.0-alpine redis-server /etc/redis/redis.conf

```

## Security

Redis should not be exposed to public.
Always use a strong password in `redis.conf`

```
requirepass SuperSecretSecureStrongPass
```

## Persistence

Redis Persistence Documentation [here](https://redis.io/topics/persistence)

To Turn On RDB,
Find the key call `dbfilename` in redis config and add the dump file name and location.
Also add a location to dump the files. (add this to `dir`)

```
dir /data
dbfilename dump.rdb
```

To Turn On Append Only Mode (AOF)
Find the key call `appendonly` in redis config and set it to yes.
Then Find `appendfilename` in redis config and add the file name and location.
Also add a location to dump the files. (add this to `dir`)

```
dir /data
appendonly yes
appendfilename appendonly.aof
```

```
docker volume create redis
docker run -it --rm --name redis --net redis -v ${PWD}/config:/etc/redis/ -v redis:/data/  redis:6.0-alpine redis-server /etc/redis/redis.conf
```

## Redis client application

An example application that reads a key from Redis, increments it and writes it back to Redis.

```
cd .\applications\client\

# start go dev environment
docker run -it -v ${PWD}:/go/src -w /go/src --net redis -p 80:80 golang:1.14-alpine

go build client.go
# start the app
./client

# build the container
docker build . -t simpleapp/redis-client:v1.0.0

```

## Run the application

```
cd .\applications\client\
docker build . -t simpleapp/redis-client:v1.0.0

docker run -it --net redis `
-e REDIS_HOST=redis `
-e REDIS_PORT=6379 `
-e REDIS_PASSWORD="SuperSecretSecureStrongPass" `
-p 80:80 `
simpleapp/redis-client:v1.0.0

```
