
    create table Cancha (
        id_cancha int identity not null,
        esta_iluminada bit,
        nombre varchar(255),
        primary key (id_cancha)
    )

    create table Color (
        cod_color int identity not null,
        descripcion varchar(255),
        primary key (cod_color)
    )

    create table Constructor (
        cod_constructor int identity not null,
        domicilio int,
        nombre varchar(255),
        primary key (cod_constructor)
    )

    create table Jugadores (
        id_jugador int identity not null,
        apellido varchar(255),
        domicilo varchar(255),
        nacimiento int,
        nombre varchar(255),
        primary key (id_jugador)
    )

    create table Paletas (
        cod_paleta int identity not null,
        grosor int,
        nombre varchar(255),
        primary key (cod_paleta)
    )

    create table Participacion (
        partido_id_partido int not null,
        jugador_id_jugador int not null,
        primary key (partido_id_partido, jugador_id_jugador)
    )

    create table Partidos (
        id_partido int identity not null,
        fin_partido int,
        inicio_partido int,
        primary key (id_partido)
    )

    alter table Participacion 
        add constraint FK_5c53vc32mkiy9bj4kdpk11udp 
        foreign key (partido_id_partido) 
        references Partidos

    alter table Participacion 
        add constraint FK_ok0xruxsa70uaffkwimt9wvuk 
        foreign key (jugador_id_jugador) 
        references Jugadores
