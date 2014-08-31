INSERT INTO `geomarket`.`category` (`NAME_ENG`, `NAME_SWE`) VALUES ('Food', 'Mat');
INSERT INTO `geomarket`.`category` (`NAME_ENG`, `NAME_SWE`) VALUES ('Tickets', 'Biljetter');
INSERT INTO `geomarket`.`category` (`NAME_ENG`, `NAME_SWE`) VALUES ('Discount', 'Rabatt');

INSERT INTO `geomarket`.`company` (`IDNR`, `COMPANY_NAME`, `STREET`, `STREETNR`, `CITY`, `STATE`, `COUNTRY`, `POSTALCODE` ,`LOCATION`) VALUES ('123-456', 'Volvo AB', 'VolvoStreet', '45', 'Gothenburg', 'Vastra Gotaland', 'SWE', '487 98', null);
INSERT INTO `geomarket`.`company` (`IDNR`, `COMPANY_NAME`, `STREET`, `STREETNR`, `CITY`, `STATE`, `COUNTRY`, `POSTALCODE`, `LOCATION`) VALUES ('456-789', 'SJ AB', 'SJ Street', '2', 'Stockholm', 'Losers', 'SWE', '080808', null);
INSERT INTO `geomarket`.`company` (`IDNR`, `COMPANY_NAME`, `STREET`, `STREETNR`, `CITY`, `STATE`, `COUNTRY`, `POSTALCODE`, `LOCATION`) VALUES ('456-789', 'Starbucks', 'Starbucks Street', '2', 'Stockholm', 'Losers', 'SWE', '080808', null);
INSERT INTO `geomarket`.`company` (`IDNR`, `COMPANY_NAME`, `STREET`, `STREETNR`, `CITY`, `STATE`, `COUNTRY`, `POSTALCODE`, `LOCATION`) VALUES ('456-789', 'Mc Donalds', 'Mc Donalds Street', '2', 'Stockholm', 'Losers', 'SWE', '080808', null);
INSERT INTO `geomarket`.`company` (`IDNR`, `COMPANY_NAME`, `STREET`, `STREETNR`, `CITY`, `STATE`, `COUNTRY`, `POSTALCODE`, `LOCATION`) VALUES ('456-789', 'Intersport', 'Intersport Street', '2', 'Stockholm', 'Losers', 'SWE', '080808', null);
INSERT INTO `geomarket`.`company` (`IDNR`, `COMPANY_NAME`, `STREET`, `STREETNR`, `CITY`, `STATE`, `COUNTRY`, `POSTALCODE`, `LOCATION`) VALUES ('456-789', 'Seal', 'Seal Street', '2', 'Stockholm', 'Losers', 'SWE', '080808', null);

INSERT INTO `geomarket`.`event_text` (`HEADING_SWE`, `BODY_SWE`, `BODY_ENG`, `HEADING_ENG`) VALUES ('Afterwork öl 2 för 1', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam,', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam,', 'AfterWork Beer 2for1');
INSERT INTO `geomarket`.`event_text` (`HEADING_SWE`, `BODY_SWE`, `BODY_ENG`, `HEADING_ENG`) VALUES ('Gratis applikation', 'Har du alltid velat ha en gratis app, nu är tillfället för dig visitera oss nu!', 'don´t take this deal!', 'Free app');
INSERT INTO `geomarket`.`event_text` (`HEADING_SWE`, `BODY_SWE`, `BODY_ENG`, `HEADING_ENG`) VALUES ('Hamburgare i röven', 'Det är din tur att biljera!', 'Its your time to shine!', 'Hamburger in ass');
INSERT INTO `geomarket`.`event_text` (`HEADING_SWE`, `BODY_SWE`, `BODY_ENG`, `HEADING_ENG`) VALUES ('Näshårstrimmning', 'Trimma näshåren nu!', 'trim your nose hairs now', 'Nose hair trim');

INSERT INTO `geomarket`.`event_type` (`NAME_ENG`, `DESCRIPTION_ENG`, `NAME_SWE`, `DESCRIPTION_SWE`) VALUES ('10%','10% discount from normal prize','10%', '10% rabatt på normala priset');
INSERT INTO `geomarket`.`event_type` (`NAME_ENG`, `DESCRIPTION_ENG`, `NAME_SWE`, `DESCRIPTION_SWE`) VALUES ('AW', 'After work', 'AW', 'After work');
INSERT INTO `geomarket`.`event_type` (`NAME_ENG`, `DESCRIPTION_ENG`, `NAME_SWE`, `DESCRIPTION_SWE`) VALUES ('2 for 1', 'Get 2 for the prize of 1', '2 för 1', 'Få två till priset av en');
INSERT INTO `geomarket`.`event_type` (`NAME_ENG`, `DESCRIPTION_ENG`, `NAME_SWE`, `DESCRIPTION_SWE`) VALUES ('25%', '25% discount from normal prize', '25%', '25% rabatt på normala priset');
INSERT INTO `geomarket`.`event_type` (`NAME_ENG`, `DESCRIPTION_ENG`, `NAME_SWE`, `DESCRIPTION_SWE`) VALUES ('Free', 'Free for the taking', 'Gratis', 'Gratis att hämta');

INSERT INTO `geomarket`.`event` (`external_ID`, `company_ID`, `category_ID`, `event_type_ID`, `event_text_ID`, `STARTDATE`, `ENDDATE`) VALUES ('fc38d1a9-51cd-43e0-b490-f01cc338433c', '1', '1', '1', '1', '2014-01-01 09:00:00', '2014-12-20 20:20:00');
INSERT INTO `geomarket`.`event` (`external_ID`, `company_ID`, `category_ID`, `event_type_ID`, `event_text_ID`, `STARTDATE`, `ENDDATE`) VALUES ('9ef7aaf0-dadf-422e-baea-770d85b7ee3d','2', '2', '2', '2', '2014-01-01 09:00:00', '2014-11-20 20:20:00');
INSERT INTO `geomarket`.`event` (`external_ID`, `company_ID`, `category_ID`, `event_type_ID`, `event_text_ID`, `STARTDATE`, `ENDDATE`) VALUES ('2c311a36-29ec-42c9-bb3e-f71ac6127946', '3', '3', '3', '3', '2014-01-01 09:00:00', '2014-10-20 20:20:00');
INSERT INTO `geomarket`.`event` (`external_ID`, `company_ID`, `category_ID`, `event_type_ID`, `event_text_ID`, `STARTDATE`, `ENDDATE`) VALUES ('6002865b-9652-40ac-8dbd-46e7af6fddc4','4', '3', '4', '4', '2014-01-01 09:00:00', '2014-09-20 20:20:00');
INSERT INTO `geomarket`.`event` (`external_ID`, `company_ID`, `category_ID`, `event_type_ID`, `event_text_ID`, `STARTDATE`, `ENDDATE`) VALUES ('58bd318f-f68c-4bd8-a9dd-b2fc1975a242', '5', '3', '5', '2', '2014-01-01 09:00:00', '2014-08-20 20:20:00');

