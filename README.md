# rhythm

#Install PostgreSQL in Docker

docker pull postgres
docker run --name postgres -p 5432:5432  -e POSTGRES_PASSWORD=qwerty -e POSTGRES_USER=qwerty -d postgres

DB version used while developing:
PostgreSQL 10.4 (Debian 10.4-2.pgdg90+1) on x86_64-pc-linux-gnu
