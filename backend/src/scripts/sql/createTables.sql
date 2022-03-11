drop database if exists `sailing-club`;
create database `sailing-club`;

create table `sailing-club`.employee
(
    Username varchar(100) not null,
    Password varchar(100) null,
    ID       int auto_increment
        primary key,
    Admin    tinyint(1)   not null
);

create table `sailing-club`.member
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

create table `sailing-club`.boat
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

create table `sailing-club`.membership_fee
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

create table `sailing-club`.notify_membership_fee
(
    ID     int auto_increment
        primary key,
    Sent   tinyint(1) not null,
    Member int        not null,
    constraint notify_membership_fee_member_ID_fk
        foreign key (Member) references member (ID)
);

create table `sailing-club`.race
(
    ID       int auto_increment
        primary key,
    Name     varchar(100) not null,
    Location varchar(100) not null,
    Date     timestamp    not null,
    Expired  tinyint(1)   not null
);

create table `sailing-club`.registration_fee
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

create table `sailing-club`.storage_fee
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

create table `sailing-club`.notify_storage_fee
(
    ID   int auto_increment
        primary key,
    Sent tinyint(1) not null,
    Boat int        not null,
    constraint notify_storage_fee_boat_ID_fk
        foreign key (Boat) references boat (ID)
);

create table `sailing-club`.payment_type
(
    ID int auto_increment
        primary key
);

/*create table `sailing-club`.credit_card(
    ID int auto_increment
        primary key references `sailing-club`.payment_type (ID),
    CardNumber varchar(16),
    Owner int not null,
    Name varchar(50),
    SecurityCode varchar(3),
    ExpMonth tinyint(1),
    ExpYear smallint(2),
    constraint credit_card_FK
        foreign key (Owner) references member (ID)
);

create table `sailing-club`.bank_transfer(
    ID int auto_increment
        primary key references `sailing-club`.payment_type (ID),
    TransferReceipt varchar()
);*/