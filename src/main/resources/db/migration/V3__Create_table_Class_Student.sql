Create TABLE class_student(
    id int AUTO_INCREMENT PRIMARY KEY not null,
    class_id int,
    student_id int,
    foreign key (class_id) references class(id),
    foreign key (student_id) references student(id)
)