FROM mysql:5.7

ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_DATABASE parser

#EXPOSE 3306

COPY ./src/main/resources/db/migration/V1__Initial_version.sql /docker-entrypoint-initdb.d/

WORKDIR /docker-entrypoint-initdb.d/

CMD mysql -u root -p MYSQL_ROOT_PASSWORD MYSQL_DATABASE < V1__Initial_version.sql

CMD ["mysqld"]
