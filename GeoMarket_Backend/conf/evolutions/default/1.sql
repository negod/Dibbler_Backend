# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  ID                        bigint auto_increment not null,
  NAME_ENG                  varchar(255),
  NAME_SWE                  varchar(255),
  constraint pk_category primary key (ID))
;

create table company (
  ID                        bigint auto_increment not null,
  IDNR                      varchar(40) not null,
  COMPANY_NAME              varchar(40) not null,
  STREET                    varchar(40) not null,
  STREETNR                  varchar(10) not null,
  CITY                      varchar(20) not null,
  STATE                     varchar(20),
  COUNTRY                   varchar(3) not null,
  POSTALCODE                varchar(10) not null,
  LOCATION                  longblob not null,
  PARENTCOMPANY             integer,
  FOLLOWER_REQ_ID           integer,
  constraint pk_company primary key (ID))
;

create table company_user (
  ID                        bigint auto_increment not null,
  USERROLE_ID               integer,
  COMPANY_ID                integer,
  FOLLOWER_REQ              varchar(255),
  constraint pk_company_user primary key (ID))
;

create table event (
  ID                        bigint auto_increment not null,
  COMPANY_ID                integer not null,
  CATEGORY_ID               integer not null,
  EVENT_TYPE_ID             integer not null,
  EVENT_TEXT_ID             integer not null,
  STARTDATE                 datetime not null,
  ENDDATE                   datetime not null,
  IMAGE                     varchar(120),
  QR_CODE                   varchar(38) not null,
  QR_STAT                   integer not null,
  MAX_REDEEM                integer,
  constraint pk_event primary key (ID))
;

create table event_text (
  ID                        bigint auto_increment not null,
  HEADING_SWE               varchar(20) not null,
  BODY_SWE                  varchar(200),
  BODY_ENG                  varchar(200),
  HEADING_ENG               varchar(20),
  constraint pk_event_text primary key (ID))
;

create table event_type (
  ID                        bigint auto_increment not null,
  NAME_ENG                  varchar(20) not null,
  DESCRIPTION_ENG           varchar(100) not null,
  NAME_SWE                  varchar(20),
  DESCRIPTION_SWE           varchar(100),
  constraint pk_event_type primary key (ID))
;

create table filter (
  ID                        bigint auto_increment not null,
  USER_ID                   integer not null,
  COMPANY_ID                integer,
  CATEGORY_ID               integer,
  EVENT_TYPE_ID             integer,
  ACTIVE                    tinyint(1) default 0 not null,
  constraint pk_filter primary key (ID))
;

create table follower_event (
  ID                        bigint auto_increment not null,
  EVENT_ID                  integer not null,
  COMPANY_USER_ID           integer not null,
  QR_CODE                   varchar(40),
  REDEEMED                  tinyint(1) default 0,
  constraint pk_follower_event primary key (ID))
;

create table follower_req (
  ID                        bigint auto_increment not null,
  MANDATORY                 tinyint(1) default 0,
  NAME_ENG                  varchar(40) not null,
  NAME_SWE                  varchar(40),
  constraint pk_follower_req primary key (ID))
;

create table movement (
  ID                        bigint auto_increment not null,
  SESSION_ID                integer not null,
  DATETIME                  datetime,
  GEOM                      longblob,
  constraint pk_movement primary key (ID))
;

create table role (
  ID                        bigint auto_increment not null,
  ROLE                      varchar(30) not null,
  DESCRIPTION               varchar(100),
  constraint pk_role primary key (ID))
;

create table session (
  ID                        bigint auto_increment not null,
  USER_ID                   integer,
  DEVICE_ID                 varchar(40),
  constraint pk_session primary key (ID))
;

create table session_event (
  ID                        bigint auto_increment not null,
  SESSION_ID                integer not null,
  EVENT_ID                  integer not null,
  constraint pk_session_event primary key (ID))
;

create table setting (
  ID                        bigint auto_increment not null,
  USER_ID                   integer not null,
  MAP_AS_DEFAULT            tinyint(1) default 0 not null,
  FOLLOWONTOP               tinyint(1) default 0 not null,
  ALLOW_PUSH                tinyint(1) default 0 not null,
  LANGUAGE                  varchar(3) not null,
  constraint pk_setting primary key (ID))
;

create table user (
  ID                        bigint auto_increment not null,
  USERNAME                  varchar(40) not null,
  EMAIL                     varchar(60) not null,
  PASSWORD                  varchar(255),
  SALT                      varchar(255),
  SETTING_ID                integer,
  GOOGLE_ID                 varchar(255),
  FACEBOOK_ID               varchar(255),
  GENDER                    varchar(255),
  AGE                       integer,
  IMAGE                     varchar(255),
  constraint pk_user primary key (ID))
;

create table userrole (
  ID                        bigint auto_increment not null,
  ROLE_ID                   integer,
  USER_ID                   integer,
  constraint pk_userrole primary key (ID))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table category;

drop table company;

drop table company_user;

drop table event;

drop table event_text;

drop table event_type;

drop table filter;

drop table follower_event;

drop table follower_req;

drop table movement;

drop table role;

drop table session;

drop table session_event;

drop table setting;

drop table user;

drop table userrole;

SET FOREIGN_KEY_CHECKS=1;

