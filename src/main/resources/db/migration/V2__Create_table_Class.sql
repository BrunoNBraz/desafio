Create Table class(
    id int not null AUTO_INCREMENT PRIMARY KEY,
    creation_date timestamp default current_timestamp not null,
    name varchar(128) not null,
    description text
)