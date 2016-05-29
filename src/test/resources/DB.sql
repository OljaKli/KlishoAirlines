DROP TABLE Employee;

CREATE TABLE Employee (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  firstName    VARCHAR(255) CHARACTER SET utf8 NOT NULL,
  lastName    VARCHAR(255) CHARACTER SET utf8 NOT NULL,
  profession INT
);

DROP TABLE Flight;

CREATE TABLE Flight (
  id INT PRIMARY KEY  AUTO_INCREMENT,
  flightNumber VARCHAR(10) NOT NULL,
  apfrom VARCHAR(4) NOT NULL,
  apto VARCHAR(4) NOT NULL,
  departureTime TIME NOT NULL
);

-- Пилоты:
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Алексей', 'Маресьев', 0);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Иван', 'Кожедуб', 0);
INSERT INTO Employee (firstName, lastName, profession) VALUES ('Александр', 'Покрышкин', 0);


-- Рейсы:
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 1142', 'SVO', 'AAQ', '14:55:00');
INSERT INTO Flight (flightNumber, apfrom, apto, departureTime) VALUES ('SU 1784', 'SVO', 'AER', '20:40:00');

