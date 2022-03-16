drop database if exists `sailing-club-testing`;
create database `sailing-club-testing`;

create table `sailing-club-testing`.employee
(
    Username varchar(100) not null,
    Password varchar(100) null,
    ID       int auto_increment
        primary key,
    Admin    tinyint(1)   not null
);

create table `sailing-club-testing`.member
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

create table `sailing-club-testing`.boat
(
    Name   varchar(100) not null,
    Owner  int          not null,
    Length double       not null,
    ID     int auto_increment
        primary key,
    Enabled tinyint(1)   not null,
    constraint boat_FK
        foreign key (Owner) references member (ID)
);

create table `sailing-club-testing`.membership_fee
(
    ID          int auto_increment
        primary key,
    Member      int         not null,
    Date        timestamp   not null,
    Fee         double      not null,
    PaymentType varchar(20) null,
    constraint membership_fee_FK
        foreign key (Member) references member (ID)
);

create table `sailing-club-testing`.notify_membership_fee
(
    ID     int auto_increment
        primary key,
    Sent   tinyint(1) not null,
    Member int        not null,
    constraint notify_membership_fee_member_ID_fk
        foreign key (Member) references member (ID)
);

create table `sailing-club-testing`.race
(
    ID       int auto_increment
        primary key,
    Name     varchar(100) not null,
    Location varchar(100) not null,
    Date     timestamp    not null,
    Expired  tinyint(1)   not null
);

create table `sailing-club-testing`.registration_fee
(
    ID          int auto_increment
        primary key,
    Boat        int         not null,
    Race        int         not null,
    Fee         double      not null,
    PaymentType varchar(20) null,
    Date        timestamp   not null,
    constraint registration_fee_FK
        foreign key (Boat) references boat (ID),
    constraint registration_fee_FK_1
        foreign key (Race) references race (ID)
);

create table `sailing-club-testing`.storage_fee
(
    ID          int auto_increment
        primary key,
    Boat        int         not null,
    Date        timestamp   not null,
    Fee         double      not null,
    PaymentType varchar(20) null,
    constraint storage_fee_FK
        foreign key (Boat) references boat (ID)
);

create table `sailing-club-testing`.notify_storage_fee
(
    ID   int auto_increment
        primary key,
    Sent tinyint(1) not null,
    Boat int        not null,
    constraint notify_storage_fee_boat_ID_fk
        foreign key (Boat) references boat (ID)
);

create table `sailing-club-testing`.payment_type
(
    ID int auto_increment
        primary key
);