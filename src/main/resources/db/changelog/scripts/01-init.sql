CREATE TABLE IF NOT EXISTS disease
(
    id   bigint GENERATED ALWAYS AS IDENTITY CONSTRAINT disease_pk PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS mixture
(
    id   bigint GENERATED ALWAYS AS IDENTITY CONSTRAINT mixture_pk PRIMARY KEY,
    name varchar(255) NOT NULL,
    disease_id bigint REFERENCES disease(id)
);

CREATE TABLE IF NOT EXISTS formula
(
    id         bigint GENERATED ALWAYS AS IDENTITY CONSTRAINT formula_pk PRIMARY KEY,
    mixture_id bigint REFERENCES mixture (id) NOT NULL,
    value      text
);

CREATE TABLE IF NOT EXISTS alias
(
    code_name varchar(255) PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS variable
(
    id              bigint GENERATED ALWAYS AS IDENTITY CONSTRAINT variable_pk PRIMARY KEY,
    formula_id      bigint REFERENCES formula (id) NOT NULL,
    name            varchar(255)                   NOT NULL,
    alias_code_name varchar(255) REFERENCES alias (code_name),
    value           decimal
);
