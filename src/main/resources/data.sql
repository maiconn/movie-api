create table movie (
    id_movie int auto_increment primary key,
    year_movie int not null,
    title varchar(1024) not null,
    studios varchar(512),
    winner boolean null
);

create table producer (
    id_producer int auto_increment primary key,
    name varchar(512) not null unique
);

create table movie_producer (
    id_producer int not null,
    id_movie int not null,
    primary key (id_producer, id_movie),
    foreign key (id_producer) references producer (id_producer),
    foreign key (id_movie) references movie (id_movie)
)