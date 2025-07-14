## Cтарт через Docker вручную

### mongo container
docker run -d --name mongo-container -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=secret mongo:8.0.12-rc0-noble

docker stop mongo-container

### mongo shell
docker exec -it mongo-container mongosh -u admin -p secret

use myNewDb

db.post.insertOne({ desc: "First post", exp: 1, profile: "New Profile", techs: ["tech1", "tech2", "tech3"] })


### Add mongo-keyfile
```
openssl rand -base64 756 > mongo-keyfile
chmod 400 mongo-keyfile
```

## Быстрый старт через Docker Compose

```bash
docker-compose up
```
Если нужно пересобрать приложение
```bash
docker-compose up --build
```
