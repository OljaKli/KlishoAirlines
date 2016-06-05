DROP TABLE Employee;

CREATE TABLE Employee (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  firstName    VARCHAR(255) CHARACTER SET utf8 NOT NULL,
  lastName    VARCHAR(255) CHARACTER SET utf8 NOT NULL,
  profession INT
);

DROP TABLE FlightDays;
DROP TABLE Flight;


CREATE TABLE Flight (
  ID INT PRIMARY KEY  AUTO_INCREMENT,
  flightNumber VARCHAR(10) NOT NULL,
  apfrom VARCHAR(4) NOT NULL,
  apto VARCHAR(4) NOT NULL,
  departureTime TIME NOT NULL
);


CREATE TABLE FlightDays
(
  flightId INT NOT NULL,
  day INT NOT NULL,

  -- CONSTRAINT cc_day CHECK (day BETWEEN 1 AND 7),
  -- The CHECK clause is parsed but ignored by all storage engines.

  CONSTRAINT uc_flightDay UNIQUE (flightId, day),

  FOREIGN KEY (flightId)
  REFERENCES Flight(ID)
    ON DELETE CASCADE
);


-- Пилоты:
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Алексей', 'Маресьев', 0);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Иван', 'Кожедуб', 0);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Александр', 'Покрышкин', 0);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Леонид', 'Жолудев', 0);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Харитон', 'Цховребов', 0);

-- Стюардессы:
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Весна', 'Вулович', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Сьюзан', 'Хелмс', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Калпана', 'Чавла', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Татьяна', 'Иванова', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Валентина', 'Терешкова', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Анна Ли', 'Фишер', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Жюли', 'Пейетт', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Мукаи', 'Тиаки', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Кондакова', 'Елена', 3);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Пегги', 'Уитсон', 3);

-- Радиооператоры:
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Станислав', 'Буневич', 1);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Эрнст', 'Кренкель', 1);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Джон', 'Филлипс', 1);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Анатолий', 'Таныгин', 1);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Вадим', 'Россин', 1);

-- Навигаторы:
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Генри', 'Морган', 2);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Френсис', 'Дрейк', 2);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Эдвард', 'Тич', 2);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Уильям', 'Кидд', 2);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Мэри', 'Рид', 2);

-- Рейсы:
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 1142', 'SVO', 'AAQ', '14:55:00',1);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 1784', 'SVO', 'AER', '20:40:00',1);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 1860', 'SVO', 'EVN', '09:50:00', 2);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 37', 'LED', 'SVO', '03:50:00', 3);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 35', 'LED', 'GYD', '05:10:00', 4);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 100', 'SVO', 'JFK', '09:40:00', 5);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 5612', 'KHV', 'GDX', '11:20:00', 6);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 6485', 'VKO', 'PKC', '12:30:00', 7);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 2322', 'SVO', 'MUC', '09:35:00', 3);
# INSERT INTO Flight (flightNumber, apfrom, apto, departureTime, daysOfWeek) VALUES ('SU 2390', 'SVO', 'ZRH', '10:00:00', 1);

-- Рейсы --test Ola:
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 1142', 'SVO', 'AAQ', '14:55:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 1784', 'SVO', 'AER', '20:40:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 1860', 'SVO', 'EVN', '09:50:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 37', 'LED', 'SVO', '03:50:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 35', 'LED', 'GYD', '05:10:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 100', 'SVO', 'JFK', '09:40:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 5612', 'KHV', 'GDX', '11:20:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 6485', 'VKO', 'PKC', '12:30:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 2322', 'SVO', 'MUC', '09:35:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 2390', 'SVO', 'ZRH', '10:00:00');


-- Рейсы по дням недели:
INSERT INTO FlightDays (day, flightId) VALUES (1,1);
INSERT INTO FlightDays (day, flightId) VALUES (7,2);
INSERT INTO FlightDays (day, flightId) VALUES (5,1);
INSERT INTO FlightDays (day, flightId) VALUES (3,2);
INSERT INTO FlightDays (day, flightId) VALUES (5,2);
INSERT INTO FlightDays (day, flightId) VALUES (5,3);
INSERT INTO FlightDays (day, flightId) VALUES (1,3);
INSERT INTO FlightDays (day, flightId) VALUES (7,4);
INSERT INTO FlightDays (day, flightId) VALUES (7,5);
INSERT INTO FlightDays (day, flightId) VALUES (3,6);
INSERT INTO FlightDays (day, flightId) VALUES (5,7);
INSERT INTO FlightDays (day, flightId) VALUES (1,8);
INSERT INTO FlightDays (day, flightId) VALUES (7,9);
INSERT INTO FlightDays (day, flightId) VALUES (7,10);
INSERT INTO FlightDays (day, flightId) VALUES (3,10);
