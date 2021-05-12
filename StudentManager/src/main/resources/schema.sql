CREATE TABLE student (
    id integer identity NOT NULL,
    name varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    address varchar(100) NOT NULL ,
    birthday date NOT NULL ,
    CONSTRAINT pk_student_id PRIMARY KEY(id)
);