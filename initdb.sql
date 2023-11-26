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
    mail character varying(32) COLLATE pg_catalog.default UNIQUE NOT NULL
    );


CREATE TABLE IF NOT EXISTS public.event
(
    id serial PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    edition INTEGER NOT NULL,
    participants_count INTEGER,
    category VARCHAR(64) NOT NULL,
    location VARCHAR(64) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    distance INTEGER NOT NULL,
    official_site VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS public.athlete
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    country VARCHAR(64) NOT NULL,
    birth_date DATE,
    gold_medals INTEGER NOT NULL,
    silver_medals INTEGER NOT NULL,
    bronze_medals INTEGER NOT NULL,
    user_id INTEGER NOT NULL REFERENCES username(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.classification
(
    id SERIAL PRIMARY KEY,
    duration INTEGER NOT NULL,
    position INTEGER NOT NULL,
    athlete_first_name VARCHAR(64) NOT NULL,
    athlete_last_name VARCHAR(64) NOT NULL,
    athlete_id INTEGER REFERENCES athlete(id),
    event_id INTEGER NOT NULL REFERENCES event(id) ON DELETE CASCADE
    );

