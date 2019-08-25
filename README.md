Download to run: https://drive.google.com/open?id=1Y6bBcLjty316QKQN186BwJ6SR9alm86v

Run in terminal following commands:

cd to the downloaded folder

docker-compose up --build

Check database contents with pssql:

server: localhost

database: hotelmanagement

port: 5444

username: postgres

password: admin

SELECT * FROM hotel;

\d+ hotel;

Postman link: https://documenter.getpostman.com/view/8514011/SVfJWCDR?version=latest

Topics:

1) Authentication (basic auth (app_user, app_pass))
2) Pagination, hotel name search
3) CRUD operations
4) Caching
5) Backend validation
6) Exception handling, http status 400, 401, 404
7) Common Table Expressions (CTE) / WITH clause
8) Entity vs data transfer objects
9) Application configuration properties
10) Docker
    Dockerfile
    docker-compose
    volumes
    database initialization (schema, index, app user, seeding)
    
Dockerhub link: https://hub.docker.com/r/panca/hotel-management-app


