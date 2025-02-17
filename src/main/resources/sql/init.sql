CREATE TABLE IF NOT EXISTS company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS company_locales
(
    company_id  INT REFERENCES company (id) on delete cascade ,
    lang        VARCHAR(2),
    description VARCHAR(255) not null,
    PRIMARY KEY (company_id, lang)
);

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(64) NOT NULL UNIQUE,
    password   varchar(128) default '{noop}123',
    birth_date DATE,
    firstname  VARCHAR(64),
    lastname   VARCHAR(64),
    role       VARCHAR(32),
    company_id INT REFERENCES company (id) on delete cascade
);

CREATE TABLE IF NOT EXISTS payment
(
    id          SERIAL PRIMARY KEY,
    amount      INT    NOT NULL,
    receiver_id INT NOT NULL REFERENCES users (id) on delete cascade
);

CREATE TABLE IF NOT EXISTS chat
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_chat
(
    id      SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users (id) on delete cascade ,
    chat_id INT NOT NULL REFERENCES chat (id) on delete cascade ,
    UNIQUE (user_id, chat_id)
);