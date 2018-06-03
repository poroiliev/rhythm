CREATE SEQUENCE public.people_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;

CREATE TABLE people
(
    id bigint PRIMARY KEY DEFAULT nextval('public.people_id_seq'),
    first_name varchar,
    last_name varchar,
    passport_id bigint,
    nationality varchar
);

CREATE INDEX people_passport_id_index ON public.people (passport_id);
