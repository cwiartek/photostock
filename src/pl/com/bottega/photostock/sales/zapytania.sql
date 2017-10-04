CREATE TABLE address (id INT AUTO_INCREMENT PRIMARY KEY ,street VARCHAR(255),house_nr VARCHAR(20) NOT NULL,flat_number VARCHAR(20), postal_code CHAR(6) NOT NULL,city VARCHAR(50) NOT NULL);

INSERT INTO authors(firstname, lastname, nationality, date_of_birth) VALUES ('Andrzej','Sap','GB','1948-06-21');

CREATE TABLE  client (id INT AUTO_INCREMENT PRIMARY KEY, firstname VARCHAR(255) NOT NULL,lastname  VARCHAR(255) NOT NULL, document_number VARCHAR(30) NOT NULL, pesel CHAR(11) NOT NULL,
address_id INT NOT NULL, FOREIGN KEY (address_id) REFERENCES address(id));

CREATE TABLE  books (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255) NOT NULL, isbn  VARCHAR(255) NOT NULL, application_date DATE NOT NULL, genre_id INT NOT NULL,
 FOREIGN KEY (genre_id) REFERENCES genres(id));

CREATE TABLE books_authors ( book_id INT NOT NULL, author_id INT NOT NULL, PRIMARY KEY (book_id, author_id), FOREIGN KEY (book_id) REFERENCES books(id), FOREIGN KEY (author_id) REFERENCES authors(id));


SELECT books.title, CONCAT(authors.firstname, ' ', authors.lastname) as autor, genres.name
FROM books
JOIN genres ON genres.id = books.genre_id
JOIN books_authors ON books_authors.book_id = books.id
JOIN authors ON books_authors.author_id = authors.id;

SELECT CONCAT(authors.firstname, ' ',authors.lastname) AS autor, books.title
FROM authors
JOIN books_authors ON books_authors.author_id = authors.id
JOIN books ON books.id = books_authors.book_id
ORDER BY authors.lastname;

SELECT CONCAT(authors.firstname, ' ',authors.lastname) AS autor, books.title
FROM authors
LEFT JOIN books_authors ON books_authors.author_id = authors.id
LEFT JOIN books ON books.id = books_authors.book_id
ORDER BY authors.lastname;

SELECT DISTINCT CONCAT (authors.firstname,' ',authors.lastname) AS 'autor'
FROM authors
JOIN books_authors ON books_authors.author_id = authors.id;

SELECT CONCAT(authors.firstname, ' ',authors.lastname) AS autor
FROM authors
LEFT JOIN books_authors ON books_authors.author_id = authors.id
WHERE books_authors.author_id IS NULL
ORDER BY authors.lastname;

SELECT CONCAT(authors.firstname, ' ',authors.lastname) AS autor
FROM authors
JOIN books_authors ON books_authors.author_id = authors.id
JOIN books ON books.id = books_authors.book_id
JOIN genres ON genres.id = books.genre_id
WHERE genres.name LIKE 'kuchnia%';

CREATE TABLE  specimens (id INT AUTO_INCREMENT PRIMARY KEY, book_id INT NOT NULL, code VARCHAR(255) NOT NULL,
 FOREIGN KEY (book_id) REFERENCES books(id));

 CREATE TABLE lendings (id INT AUTO_INCREMENT PRIMARY KEY, client_id INT NOT NULL, specimen_id INT NOT NULL,
 lending_date DATETIME NOT NULL DEFAULT NOW(), return_date DATETIME DEFAULT NULL, FOREIGN KEY (client_id) REFERENCES clients(id),
 FOREIGN KEY (specimen_id) REFERENCES specimens(id));

 SELECT count(DISTINCT authors.id)
FROM authors
JOIN books_authors ON books_authors.author_id = authors.id;


SELECT DISTINCT (books.title)
FROM books
JOIN specimens ON book_id = specimens.book_id
LEFT JOIN lendings ON lendings.specimen_id = specimens_id;
WHERE lendings.id IS NULL OR ((SELECT count(*) FROM lendings lendings.return_date IS NOT NULL;

SELECT nationality, count(*)
FROM authors
GROUP BY nationality;

SELECT nationality, avg(id)
FROM authors
GROUP BY nationality;

SELECT nationality, avg(id), max(id), min(id), count(id)
FROM authors
GROUP BY nationality;

SELECT nationality, avg(id), max(id), min(id), count(id)
FROM authors
WHERE nationality !='FR'
GROUP BY nationality
HAVING count(*) >3;

SELECT nationality,firstname, avg(id), max(id), min(id), count(id)
FROM authors
WHERE nationality !='FR'
GROUP BY nationality,firstname
HAVING count(*) >0;

SELECT genres.name,count(books.id) AS ilosc
FROM genres
LEFT JOIN books ON  books.genre_id = genres.id
GROUP BY genres.name
HAVING count(books.id) >0
ORDER BY ilosc DESC;


SELECT CONCAT(authors.firstname, ' ',authors.lastname) AS autor, genres.name , count(books.id) AS ilosc
FROM genres
CROSS JOIN authors
LEFT JOIN books_authors ON books_authors.author_id = authors.id
LEFT JOIN books ON books.id = books_authors.book_id
WHERE books.genre_id = genres.id OR books.id IS NULL
GROUP BY autor, genres.name
ORDER BY count(books.id) DESC;


SELECT CONCAT(authors.firstname, ' ',authors.lastname) AS autor, count(genres.name) AS ilosc
FROM authors
LEFT JOIN books_authors ON authors.id = books_authors.author_id
LEFT JOIN books ON books_authors.book_id = books.id
LEFT JOIN genres ON books.genre_id = genres.id
WHERE genres.name ='historia'
GROUP BY genres.id,authors.id;

SELECT genres.name, (SELECT count(*) FROM books WHERE genres.id = books.genre_id) AS books_count
FROM genres
ORDER BY books_count DESC;


CREATE INDEX clients_pesel_index
ON clients(pesel);

// 1. Imię i nazwisko pracownika, który ma nazwisko na literę A, który został najwcześniej zatrudniony.

SELECT CONCAT(employees.first_name, ' ', employees.last_name) AS pracownik, MIN(hire_date) AS hire_date
FROM employees
WHERE last_name LIKE 'A%'
GROUP BY hire_date
LIMIT 1;

// 2.Ilość pracowników każdej płci.

SELECT gender , count(gender) AS ilosc
FROM employees
GROUP BY gender;

// 3. Historię pracownika Georgi Facello.

SELECT *
FROM salaries
JOIN employees ON salaries.emp_no = employees.emp_no
WHERE employees.last_name LIKE 'Facello' AND employees.first_name LIKE 'Georgi';

//4. Najczęściej powtarzające się imię wśród pracowników.

SELECT count(first_name) AS ile, first_name
FROM employees
GROUP BY first_name
ORDER BY ile DESC
LIMIT 1;

//5. Pierwszych 5 pracowników, którzy zarabiają najwięcej.

SELECT CONCAT(employees.first_name, ' ' , employees.last_name) AS pracownik ,MAX(salaries.salary)AS salary
FROM employees
JOIN salaries ON employees.emp_no = salaries.emp_no
GROUP BY salary
ORDER BY salary DESC
LIMIT 5;

// 6. 5 najpóźniej zatrudnionych pracowników oraz ich bieżący dział.

SELECT CONCAT(employees.first_name, ' ' , employees.last_name) AS pracownik, MAX(hire_date) AS hire_date,departments.dept_name AS dept_name
FROM employees
JOIN current_dept_emp ON current_dept_emp.emp_no = employees.emp_no
JOIN departments ON  current_dept_emp.dept_no = departments.dept_no
GROUP BY hire_date
ORDER BY hire_date DESC
LIMIT 5;

// 7. Działy oraz ich bieżących kierowników

SELECT departments.dept_name AS department_name, CONCAT(employees.first_name, ' ' , employees.last_name) AS kierownik,dept_manager.from_date AS od, dept_manager.to_date AS do
FROM departments
JOIN dept_manager ON dept_manager.dept_no = departments.dept_no
JOIN current_dept_emp ON dept_manager.emp_no = current_dept_emp.emp_no
JOIN employees ON employees.emp_no = current_dept_emp.emp_no
WHERE dept_manager.to_date > NOW()
ORDER BY do ASC;

// 8. 5 najwcześniej zatrudnionych pracowników wraz z ich bieżącym tytułem, działem oraz zarobkami.

SELECT CONCAT(employees.first_name, ' ' , employees.last_name) AS pracownik, titles.title AS title, departments.dept_name AS department_name,
 salaries.salary AS salary, MIN(hire_date)
 FROM employees
 JOIN titles ON titles.emp_no = employees.emp_no
 JOIN salaries ON employees.emp_no = salaries.emp_no
 JOIN current_dept_emp ON current_dept_emp.emp_no = employees.emp_no
 JOIN departments ON departments.dept_no = current_dept_emp.dept_no
 GROUP BY hire_date
 ORDER BY hire_date ASC
 LIMIT 5;

 // 9. Działy wraz ze średnimi zarobkami, największymi zarobkami, najmniejszymi zarobkami i ilością pracowników.
  Chodzi o bieżące zarobki i bieżącą ilość pracowników.

  SELECT departments.dept_name AS department_name, AVG(salaries.salary) AS avg_salary, MAX(salaries.salary) AS max_salary,
  MIN(salaries.salary) AS min_salary, count(current_dept_emp.emp_no) AS suma
  FROM departments
  JOIN current_dept_emp ON current_dept_emp.dept_no = departments.dept_no
  JOIN salaries ON current_dept_emp.emp_no = salaries.emp_no
  GROUP BY departments.dept_name
  ORDER BY max_salary DESC;

  // 10. Średnią bieżącą pensję kierowników działów

SELECT departments.dept_name AS department_name, CONCAT(employees.first_name, ' ' , employees.last_name) AS kierownik,
dept_manager.from_date AS od, dept_manager.to_date AS do, ROUND(AVG(salaries.salary),2) AS avg_salary
FROM departments
JOIN dept_manager ON dept_manager.dept_no = departments.dept_no
JOIN current_dept_emp ON dept_manager.emp_no = current_dept_emp.emp_no
JOIN employees ON employees.emp_no = current_dept_emp.emp_no
JOIN salaries ON employees.emp_no = salaries.emp_no
WHERE dept_manager.to_date > NOW()
GROUP BY departments.dept_name
ORDER BY avg_salary DESC;

// 11. Imię i nazwisko pracownika, który ma największą różnicę w pensji początkowej i bieżącej.





