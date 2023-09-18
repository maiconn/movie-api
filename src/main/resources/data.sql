create table movie (
    id_movie int auto_increment primary key,
    year_movie int not null,
    title varchar(1024) not null,
    producers varchar(512) not null,
    winner boolean null
);