-- Grant privileges on the 'users' database
GRANT ALL PRIVILEGES ON DATABASE users TO admin;

-- Create the 'public.username' table
CREATE TABLE IF NOT EXISTS public.username
(
    id serial PRIMARY KEY,
    user_name  character varying(64) COLLATE pg_catalog.default  UNIQUE,
    password character varying(64) COLLATE pg_catalog.default,
    role character varying(32) COLLATE pg_catalog.default,
    first_name character varying(64) COLLATE pg_catalog.default,
    last_name character varying(64) COLLATE pg_catalog.default,
    birth_date date,
    mail character varying(32) COLLATE pg_catalog.default UNIQUE
    );
