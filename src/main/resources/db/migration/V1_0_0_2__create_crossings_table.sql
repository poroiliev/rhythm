CREATE SEQUENCE public.crossings_id_seq NO MINVALUE NO MAXVALUE NO CYCLE;

CREATE TABLE crossings
(
    id bigint PRIMARY KEY DEFAULT nextval('public.crossings_id_seq'),
    time bigint,
    location varchar,
    enter BOOLEAN,
    person_id bigint,
    CONSTRAINT crossings_people_id_fk FOREIGN KEY (person_id) REFERENCES public.people (id)
);
