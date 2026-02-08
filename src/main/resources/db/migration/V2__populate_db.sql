
INSERT INTO client (name)
SELECT *
FROM (
         SELECT 'Maksym Slyvka' UNION ALL
         SELECT 'Elon Musk' UNION ALL
         SELECT 'Neil Armstrong' UNION ALL
         SELECT 'Yuri Gagarin' UNION ALL
         SELECT 'Buzz Aldrin' UNION ALL
         SELECT 'John Glenn' UNION ALL
         SELECT 'Alan Shepard' UNION ALL
         SELECT 'Valentina Tereshkova' UNION ALL
         SELECT 'Chris Hadfield' UNION ALL
         SELECT 'Peggy Whitson'
     ) s
WHERE NOT EXISTS (SELECT 1 FROM client);


MERGE INTO planet (id, name) KEY (id) VALUES ('MARS', 'Mars');
MERGE INTO planet (id, name) KEY (id) VALUES ('VEN', 'Venus');
MERGE INTO planet (id, name) KEY (id) VALUES ('EARTH', 'Earth');
MERGE INTO planet (id, name) KEY (id) VALUES ('JUP', 'Jupiter');
MERGE INTO planet (id, name) KEY (id) VALUES ('SAT', 'Saturn');


INSERT INTO ticket (client_id, from_planet_id, to_planet_id)
SELECT *
FROM (
         SELECT 1, 'EARTH', 'MARS' UNION ALL
         SELECT 2, 'EARTH', 'MARS' UNION ALL
         SELECT 3, 'EARTH', 'VEN'  UNION ALL
         SELECT 4, 'EARTH', 'JUP'  UNION ALL
         SELECT 5, 'MARS',  'EARTH' UNION ALL
         SELECT 6, 'VEN',   'EARTH' UNION ALL
         SELECT 7, 'EARTH', 'SAT'  UNION ALL
         SELECT 8, 'SAT',   'JUP'  UNION ALL
         SELECT 9, 'JUP',   'MARS' UNION ALL
         SELECT 10,'MARS',  'VEN'
     ) t
WHERE NOT EXISTS (SELECT 1 FROM ticket);