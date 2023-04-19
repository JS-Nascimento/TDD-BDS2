CREATE TABLE IF NOT EXISTS city
(
    id
    INT
    AUTO_INCREMENT
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
) NOT NULL
    );


INSERT INTO city(name)
VALUES ('São Paulo');
INSERT INTO city(name)
VALUES ('Brasília');
INSERT INTO city(name)
VALUES ('Fortaleza');
INSERT INTO city(name)
VALUES ('Salvador');
INSERT INTO city(name)
VALUES ('Manaus');
INSERT INTO city(name)
VALUES ('Curitiba');
INSERT INTO city(name)
VALUES ('Goiânia');
INSERT INTO city(name)
VALUES ('Belém');
INSERT INTO city(name)
VALUES ('Porto Alegre');
INSERT INTO city(name)
VALUES ('Rio de Janeiro');
INSERT INTO city(name)
VALUES ('Belo Horizonte');



CREATE TABLE event
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    date    DATE         NOT NULL,
    url     VARCHAR(255) NOT NULL,
    city_id INT          NOT NULL,
    FOREIGN KEY (city_id) REFERENCES city (id)
);

INSERT INTO event(name, date, url, city_id)
VALUES ('Feira do Software', '2021-05-16', 'https://feiradosoftware.com', 1);
INSERT INTO event(name, date, url, city_id)
VALUES ('CCXP', '2021-04-13', 'https://ccxp.com.br', 1);
INSERT INTO event(name, date, url, city_id)
VALUES ('Congresso Linux', '2021-05-23', 'https://congressolinux.com.br', 2);
INSERT INTO event(name, date, url, city_id)
VALUES ('Semana Spring React', '2021-05-03', 'https://devsuperior.com.br', 3);
