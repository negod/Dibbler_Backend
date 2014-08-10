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
  parentcompany_ID          bigint,
  followe_request_ID        bigint,
  constraint pk_company primary key (ID))
;

create table company_user (
  ID                        bigint auto_increment not null,
  user_ID                   bigint,
  role_ID                   bigint,
  COMPANY_ID                integer not null,
  FOLLOWER_REQ              varchar(20),
  constraint pk_company_user primary key (ID))
;

create table event (
  ID                        bigint auto_increment not null,
  company_ID                bigint,
  category_ID               bigint,
  event_type_ID             bigint,
  event_text_ID             bigint,
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
  user_ID                   bigint not null,
  company_ID                bigint,
  category_ID               bigint,
  event_type_ID             bigint,
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
  session_ID                bigint,
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
  DEVICE_ID                 varchar(40),
  constraint pk_session primary key (ID))
;

create table setting (
  ID                        bigint auto_increment not null,
  MAP_AS_DEFAULT            tinyint(1) default 0 not null,
  FOLLOWONTOP               tinyint(1) default 0 not null,
  ALLOW_PUSH                tinyint(1) default 0 not null,
  LANGUAGE                  varchar(3) not null,
  user_ID                   bigint,
  constraint pk_setting primary key (ID))
;

create table user (
  ID                        bigint auto_increment not null,
  USERNAME                  varchar(40) not null,
  EMAIL                     varchar(60) not null,
  PASSWORD                  varchar(128) not null,
  SALT                      varchar(128) not null,
  GOOGLE_ID                 varchar(40),
  FACEBOOK_ID               varchar(40),
  GENDER                    varchar(1) not null,
  AGE                       integer not null,
  IMAGE                     varchar(100),
  setting_ID                bigint,
  constraint pk_user primary key (ID))
;


create table event_session (
  event_ID                       bigint not null,
  session_ID                     bigint not null,
  constraint pk_event_session primary key (event_ID, session_ID))
;

create table role_user (
  role_ID                        bigint not null,
  user_ID                        bigint not null,
  constraint pk_role_user primary key (role_ID, user_ID))
;
alter table company add constraint fk_company_parentcompany_1 foreign key (parentcompany_ID) references company (ID) on delete restrict on update restrict;
create index ix_company_parentcompany_1 on company (parentcompany_ID);
alter table company add constraint fk_company_followeRequest_2 foreign key (followe_request_ID) references follower_req (ID) on delete restrict on update restrict;
create index ix_company_followeRequest_2 on company (followe_request_ID);
alter table company_user add constraint fk_company_user_user_3 foreign key (user_ID) references user (ID) on delete restrict on update restrict;
create index ix_company_user_user_3 on company_user (user_ID);
alter table company_user add constraint fk_company_user_role_4 foreign key (role_ID) references role (ID) on delete restrict on update restrict;
create index ix_company_user_role_4 on company_user (role_ID);
alter table event add constraint fk_event_company_5 foreign key (company_ID) references company (ID) on delete restrict on update restrict;
create index ix_event_company_5 on event (company_ID);
alter table event add constraint fk_event_category_6 foreign key (category_ID) references category (ID) on delete restrict on update restrict;
create index ix_event_category_6 on event (category_ID);
alter table event add constraint fk_event_eventType_7 foreign key (event_type_ID) references event_type (ID) on delete restrict on update restrict;
create index ix_event_eventType_7 on event (event_type_ID);
alter table event add constraint fk_event_eventText_8 foreign key (event_text_ID) references event_text (ID) on delete restrict on update restrict;
create index ix_event_eventText_8 on event (event_text_ID);
alter table filter add constraint fk_filter_user_9 foreign key (user_ID) references user (ID) on delete restrict on update restrict;
create index ix_filter_user_9 on filter (user_ID);
alter table filter add constraint fk_filter_company_10 foreign key (company_ID) references company (ID) on delete restrict on update restrict;
create index ix_filter_company_10 on filter (company_ID);
alter table filter add constraint fk_filter_category_11 foreign key (category_ID) references category (ID) on delete restrict on update restrict;
create index ix_filter_category_11 on filter (category_ID);
alter table filter add constraint fk_filter_eventType_12 foreign key (event_type_ID) references event_type (ID) on delete restrict on update restrict;
create index ix_filter_eventType_12 on filter (event_type_ID);
alter table movement add constraint fk_movement_session_13 foreign key (session_ID) references session (ID) on delete restrict on update restrict;
create index ix_movement_session_13 on movement (session_ID);
alter table setting add constraint fk_setting_user_14 foreign key (user_ID) references user (ID) on delete restrict on update restrict;
create index ix_setting_user_14 on setting (user_ID);
alter table user add constraint fk_user_setting_15 foreign key (setting_ID) references setting (ID) on delete restrict on update restrict;
create index ix_user_setting_15 on user (setting_ID);



alter table event_session add constraint fk_event_session_event_01 foreign key (event_ID) references event (ID) on delete restrict on update restrict;

alter table event_session add constraint fk_event_session_session_02 foreign key (session_ID) references session (ID) on delete restrict on update restrict;

alter table role_user add constraint fk_role_user_role_01 foreign key (role_ID) references role (ID) on delete restrict on update restrict;

alter table role_user add constraint fk_role_user_user_02 foreign key (user_ID) references user (ID) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table category;

drop table company;

drop table company_user;

drop table event;

drop table event_session;

drop table event_text;

drop table event_type;

drop table filter;

drop table follower_event;

drop table follower_req;

drop table movement;

drop table role;

drop table role_user;

drop table session;

drop table setting;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

