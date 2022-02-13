create table employee
(
    Username varchar(100) not null,
    Password varchar(100) null,
    ID       int auto_increment
        primary key
);

create table member
(
    Name       varchar(100) not null,
    Surname    varchar(100) not null,
    Address    varchar(100) not null,
    FiscalCode varchar(16)  not null,
    Username   varchar(100) not null,
    Password   varchar(100) null,
    ID         int auto_increment
        primary key
);

create table boat
(
    Name   varchar(100) not null,
    Owner  int          not null,
    Length double       not null,
    ID     int auto_increment
        primary key,
    constraint boat_FK
        foreign key (Owner) references member (ID)
);

create table membership_fee
(
    ID     int auto_increment
        primary key,
    Member int    not null,
    Date   date   not null,
    Fee    double not null,
    constraint membership_fee_FK
        foreign key (Member) references member (ID)
);

create table race
(
    ID       int auto_increment
        primary key,
    Name     varchar(100) not null,
    Location varchar(100) not null,
    Date     date         not null
);

create table registration_fee
(
    ID   int auto_increment
        primary key,
    Boat int    not null,
    Race int    not null,
    Fee  double not null,
    constraint registration_fee_FK
        foreign key (Boat) references boat (ID),
    constraint registration_fee_FK_1
        foreign key (Race) references race (ID)
);

create table storage_fee
(
    ID   int auto_increment
        primary key,
    Boat int    not null,
    Date date   not null,
    Fee  double not null,
    constraint storage_fee_FK
        foreign key (Boat) references boat (ID)
);
