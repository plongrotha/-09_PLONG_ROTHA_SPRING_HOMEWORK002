

create table instructors
(
    instructor_id  serial primary key,
    instructor_name varchar(50) not null,
    email varchar(255) unique
);

CREATE TABLE courses
(
    course_id  serial primary key,
    course_name  varchar(50) ,
    description varchar(255),
    instructor_id  int  not null,
    constraint fk_course foreign key (instructor_id) references instructors (instructor_id) ON DELETE CASCADE ON UPDATE CASCADE
);

create table students
(
    student_id   serial primary key,
    student_name varchar(100),
    email        varchar(100)  unique not null,
    phone_number varchar(100)  unique not null
);


CREATE TABLE student_course
(
    studentcourse_id serial primary key,
    student_id int not null,
    course_id  int not null,
    constraint fk_course foreign key (course_id) references courses (course_id) ON UPDATE CASCADE ON DELETE CASCADE,
    constraint fk_student foreign key (student_id) references students (student_id) ON UPDATE CASCADE ON DELETE CASCADE
);


-- --------------------------------------------
INSERT INTO courses (course_name, description, instructor_id) VALUES
('Java Programming', 'Introduction to Java and object-oriented programming.', 1),
('Web Development', 'Covers HTML, CSS, JavaScript and frontend frameworks.', 2),
('Database Systems', 'Relational databases, SQL, normalization, and transactions.', 3),
('Spring Boot Essentials', 'Build REST APIs using Spring Boot framework.', 1),
('Data Structures', 'Learn about arrays, linked lists, trees, graphs, and algorithms.', 2);

select * from courses;


	INSERT INTO instructors (instructor_name, email) VALUES
('John Smith', 'john.smith@example.com'),
('Alice Johnson', 'alice.johnson@example.com'),
('Robert Lee', 'robert.lee@example.com'),
('Emma Davis', 'emma.davis@example.com'),
('Michael Brown', 'michael.brown@example.com');


