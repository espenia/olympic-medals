FROM postgres:14.1-alpine
LABEL author="TDD-grupo5"
LABEL description="Postgres Image for TDD-grupo5-medallero"
LABEL version="1.0"
COPY *.sql /docker-entrypoint-initdb.d/
