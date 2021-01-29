
    create table Cancha (
        id_cancha integer not null auto_increment,
        esta_iluminada bit,
        nombre varchar(255),
        color_cod_color integer,
        primary key (id_cancha)
    )

    create table Color (
        cod_color integer not null auto_increment,
        descripcion varchar(255),
        primary key (cod_color)
    )

    create table Constructor (
        cod_constructor integer not null auto_increment,
        domicilio integer,
        nombre varchar(255),
        primary key (cod_constructor)
    )

    create table Jugadores (
        id_jugador integer not null auto_increment,
        apellido varchar(255),
        domicilio varchar(255),
        nacimiento integer,
        nombre varchar(255),
        paleta_cod_paleta integer,
        primary key (id_jugador)
    )

    create table Paletas (
        cod_paleta integer not null auto_increment,
        grosor integer,
        nombre varchar(255),
        color_cod_color integer,
        constructor_cod_constructor integer,
        primary key (cod_paleta)
    )

    create table Participacion (
        partido_id_partido integer not null,
        jugador_id_jugador integer not null,
        paleta_cod_paleta integer,
        primary key (partido_id_partido, jugador_id_jugador)
    )

    create table Partidos (
        id_partido integer not null auto_increment,
        fin_partido integer,
        inicio_partido integer,
        cod_color_cancha_cod_color integer,
        id_cancha_id_cancha integer,
        id_jugador_responsable_id_jugador integer,
        primary key (id_partido)
    )

    alter table Cancha 
        add constraint FK_j9tu569ls63jpfb9b0tjdb2p2 
        foreign key (color_cod_color) 
        references Color (cod_color)

    alter table Jugadores 
        add constraint FK_1v2nh55uibw8j4vshieblsyy2 
        foreign key (paleta_cod_paleta) 
        references Paletas (cod_paleta)

    alter table Paletas 
        add constraint FK_axf4f8a6q6kq4r7flfs05m3lb 
        foreign key (color_cod_color) 
        references Color (cod_color)

    alter table Paletas 
        add constraint FK_q45j1fofqlmns6pd73f9r6iaf 
        foreign key (constructor_cod_constructor) 
        references Constructor (cod_constructor)

    alter table Participacion 
        add constraint FK_5c53vc32mkiy9bj4kdpk11udp 
        foreign key (partido_id_partido) 
        references Partidos (id_partido)

    alter table Participacion 
        add constraint FK_ok0xruxsa70uaffkwimt9wvuk 
        foreign key (jugador_id_jugador) 
        references Jugadores (id_jugador)

    alter table Participacion 
        add constraint FK_5shr0ey4cvji5qt0svmvuaxjn 
        foreign key (paleta_cod_paleta) 
        references Paletas (cod_paleta)

    alter table Partidos 
        add constraint FK_9puq65dom5m5n1dlnr08kuwd9 
        foreign key (cod_color_cancha_cod_color) 
        references Color (cod_color)

    alter table Partidos 
        add constraint FK_81f9q4rx69j454b0xhep61jlw 
        foreign key (id_cancha_id_cancha) 
        references Cancha (id_cancha)

    alter table Partidos 
        add constraint FK_9wont5dv4mey7ngyr1q767aiw 
        foreign key (id_jugador_responsable_id_jugador) 
        references Jugadores (id_jugador)
