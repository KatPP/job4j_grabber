CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);
CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer references company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);
INSERT INTO company (id, name)
VALUES (1, 'СБЕР'),
       (2, 'ВТБ'),
       (3, 'АЛЬФА БАНК'),
       (4, 'ТИНЬКОФФ'),
       (5, 'РОСБАНК'),
       (6, 'РОССЕЛЬХОЗ БАНК'),
       (7, 'РОСБАНК'),
       (8, 'БИНБАНК');

INSERT INTO person (id, name, company_id)
VALUES (1, 'ГРЕФ', 1),
       (2, 'МИЛЛЕР', 2),
       (3, 'СЕЧИН', 2),
       (4, 'ЛУКАШЕНКО', 4),
       (5, 'БАЙДЕН', 5),
       (6, 'ТРАМП', 5),
       (7, 'ХАРРИС', 6),
       (8, 'МАКРОН', 3),
       (9, 'ШОЛЬЦ', 7),
       (10, 'ДУРОВ', 8),
       (11, 'ШОЛЬЦ', 8),
       (12, 'ВУЧИЧ', 8);

SELECT p.name, c.id, c.name
from person p
         JOIN company c on p.company_id = c.id
WHERE p.company_id != 5;

WITH company_counts AS (SELECT c.name      AS company_name,
                               COUNT(p.id) AS employee_count
                        FROM company c
                                 LEFT JOIN
                             person p ON c.id = p.company_id
                        GROUP BY c.name)
SELECT company_name,
       employee_count
FROM company_counts
WHERE employee_count = (SELECT MAX(employee_count) FROM company_counts);