\c postgres


DROP DATABASE IF EXISTS users;

CREATE DATABASE users
    WITH
    OWNER = admin
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

\c users

create user admin password 'admin123' superuser

CREATE TABLE IF NOT EXISTS public.username
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    user_name character varying(64) COLLATE pg_catalog."default",
    password character varying(64) COLLATE pg_catalog."default",
    role character varying(32) COLLATE pg_catalog."default",
    first_name character varying(64) COLLATE pg_catalog."default",
    last_name character varying(64) COLLATE pg_catalog."default",
    birth_date date,
    mail character varying(32) COLLATE pg_catalog."default",
    CONSTRAINT user_pk PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.username
    OWNER to admin;