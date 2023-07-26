--Составить первый JOIN-запрос,
-- чтобы получить информацию обо всех студентах (достаточно получить только имя и возраст студента)
-- школы Хогвартс вместе с названиями факультетов.

--Составить второй JOIN-запрос, чтобы получить только тех студентов, у которых есть аватарки.

SELECT s.name, s.age
FROM students s
         LEFT JOIN faculties f on s.faculty_id = f.id;

SELECT s.name, s.name, a.id
FROM students s
         INNER JOIN avatar a on s.id = a.student_id;
