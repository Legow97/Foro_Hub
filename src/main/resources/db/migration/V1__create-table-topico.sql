CREATE TABLE topico(
id bigint not null auto_increment,
titulo varchar(100) not null unique,
mensaje varchar(100) not null unique,
fecha_Creacion TIMESTAMP not null,
status tinyint(1) not null,
autor varchar(100) not null,
curso varchar(100) not null,
respuestas varchar(200),

primary key (id)
);