--Описание структуры: у каждого человека есть машина.
-- Причем несколько человек могут пользоваться одной машиной.
-- У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет).
-- У каждой машины есть марка, модель и стоимость.
-- Также не забудьте добавить таблицам первичные ключи и связать их.

CREATE TABLE cars
(
    id    BIGSERIAL PRIMARY KEY,
    brand VARCHAR(15)             NOT NULL,
    model VARCHAR(31)             NOT NULL,
    price INT CHECK ( price > 0 ) NOT NULL
);

CREATE TABLE owners
(
    id                 BIGSERIAL PRIMARY KEY,
    name               VARCHAR(15)                 NOT NULL,
    age                INT CHECK ( age > 17 )      NOT NULL,
    has_driver_license BOOLEAN DEFAULT true        NOT NULL,
    car_id             BIGINT REFERENCES cars (id) NOT NULL
);

INSERT INTO cars(brand, model, price)
VALUES ('Lada', 'Vesta', 1200000);

INSERT INTO owners(name, age, car_id)
VALUES ('Вася', 24, 1),
       ('Иван', 25, 1);
