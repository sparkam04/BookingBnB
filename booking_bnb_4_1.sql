--------------------------------------------------------
--  File created - суббота-июля-08-2017   
--------------------------------------------------------
DROP TABLE "OBJTYPE" cascade constraints;
DROP TABLE "ATTRTYPE" cascade constraints;
DROP TABLE "OBJECTS" cascade constraints;
DROP TABLE "ATTRIBUTES" cascade constraints;
DROP TABLE "OBJREFERENCE" cascade constraints;
DROP SEQUENCE "ATTRTYPE_ID_SEQ";
DROP SEQUENCE "BOOKING_CODE_SEQ";
DROP SEQUENCE "OBJECT_ID_SEQ";
DROP SEQUENCE "OBJECT_TYPE_ID_SEQ";
--------------------------------------------------------
--  DDL for Table OBJTYPE
--------------------------------------------------------

  CREATE TABLE "OBJTYPE" 
   (	"OBJECT_TYPE_ID" NUMBER(20,0), 
	"PARENT_ID" NUMBER(20,0), 
	"CODE" VARCHAR2(20 BYTE), 
	"NAME" VARCHAR2(200 BYTE), 
	"DESCRIPTION" VARCHAR2(1000 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "OBJTYPE"."OBJECT_TYPE_ID" IS '????????????? ?????????? ????';
   COMMENT ON COLUMN "OBJTYPE"."PARENT_ID" IS '?????? ?? ????????????? ????????????? ?????????? ????';
   COMMENT ON COLUMN "OBJTYPE"."CODE" IS '???????? ?????????? ???? ? ?????????? ?????????';
   COMMENT ON COLUMN "OBJTYPE"."NAME" IS '???????? ?????????? ???? ? ???????????? ????????? (??? GUI)';
   COMMENT ON COLUMN "OBJTYPE"."DESCRIPTION" IS '??????????? ???????? ?????????? ???? ? ???????????? ????????? (??? GUI)';
   COMMENT ON TABLE "OBJTYPE"  IS '??????? ???????? ????????? ?????';
--------------------------------------------------------
--  DDL for Table ATTRTYPE
--------------------------------------------------------

  CREATE TABLE "ATTRTYPE" 
   (	"ATTRTYPE_ID" NUMBER(20,0), 
	"OBJECT_TYPE_ID" NUMBER(20,0), 
	"OBJECT_TYPE_ID_REF" NUMBER(20,0), 
	"CODE" VARCHAR2(20 BYTE), 
	"NAME" VARCHAR2(200 BYTE), 
	"IS_MANDATORY" NUMBER(1,0) DEFAULT 0, 
	"ORDER_NUM" NUMBER(2,0) DEFAULT NULL, 
	"IS_MULTIPLE" NUMBER(1,0) DEFAULT 0
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "ATTRTYPE"."OBJECT_TYPE_ID" IS '?????? ?? ????????????? ?????????? ???? ??????, ??????? ????????????? ?????? ?????????? ???';
   COMMENT ON COLUMN "ATTRTYPE"."OBJECT_TYPE_ID_REF" IS '?????? ?? ????????????? ?????????? ???? ??????, ??????? ??? ????????? "????-??-??????" ????????? ? ????????? "????"
	??? ????????? "?????", ? ??????? ????????? ????????? ??? ?????? ?? ATTRTYPE.OBJECT_TYPE_ID';
   COMMENT ON COLUMN "ATTRTYPE"."CODE" IS '???????? ??????????? ???? ? ?????????? ?????????';
   COMMENT ON COLUMN "ATTRTYPE"."NAME" IS '???????? ??????????? ???? ? ???????????? ????????? (??? GUI)';
   COMMENT ON COLUMN "ATTRTYPE"."IS_MANDATORY" IS '???????? ?? ??????? ???????????? ??? ??????????';
   COMMENT ON COLUMN "ATTRTYPE"."ORDER_NUM" IS '??????? ??????????? ????????? ?? ?????????';
   COMMENT ON COLUMN "ATTRTYPE"."IS_MULTIPLE" IS '???????? ?? ??????? ?????????????';
   COMMENT ON TABLE "ATTRTYPE"  IS '??????? ???????? ?????????? ?????';
--------------------------------------------------------
--  DDL for Table OBJECTS
--------------------------------------------------------

  CREATE TABLE "OBJECTS" 
   (	"OBJECT_ID" NUMBER(20,0), 
	"PARENT_ID" NUMBER(20,0), 
	"OBJECT_TYPE_ID" NUMBER(20,0), 
	"NAME" VARCHAR2(2000 BYTE), 
	"DESCRIPTION" VARCHAR2(4000 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON TABLE "OBJECTS"  IS '??????? ???????? ??????????? ????????';
--------------------------------------------------------
--  DDL for Table ATTRIBUTES
--------------------------------------------------------

  CREATE TABLE "ATTRIBUTES" 
   (	"ATTR_ID" NUMBER(20,0), 
	"OBJECT_ID" NUMBER(20,0), 
	"VALUE" VARCHAR2(4000 BYTE), 
	"DATE_VALUE" DATE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "ATTRIBUTES"."VALUE" IS '???????? ???????? ?????????? ??????? ? ???? ?????? ??? ?????';
   COMMENT ON COLUMN "ATTRIBUTES"."DATE_VALUE" IS '???????? ???????? ?????????? ??????? ? ???? ????';
   COMMENT ON TABLE "ATTRIBUTES"  IS '??????? ???????? ????????? ??????????? ????????';
--------------------------------------------------------
--  DDL for Table OBJREFERENCE
--------------------------------------------------------

  CREATE TABLE "OBJREFERENCE" 
   (	"ATTR_ID" NUMBER(20,0), 
	"REFERENCE" NUMBER(20,0), 
	"OBJECT_ID" NUMBER(20,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "OBJREFERENCE"."ATTR_ID" IS '?????? ?? ?????????? ??? ??? ????????????? ????? ????? ???????????? ????????';
   COMMENT ON COLUMN "OBJREFERENCE"."REFERENCE" IS '?????? ?? ????????? 2-?? ??????? ????????????? ????? ? ?????????? "????"';
   COMMENT ON COLUMN "OBJREFERENCE"."OBJECT_ID" IS '?????? ?? ????????? 1-?? ??????? ????????????? ????? ? ?????????? "?????"';
   COMMENT ON TABLE "OBJREFERENCE"  IS '??????? ???????? ?????? ????? ???????????? ????????';
--------------------------------------------------------
--  DDL for Sequence ATTRTYPE_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ATTRTYPE_ID_SEQ"  MINVALUE 1 MAXVALUE 1000 INCREMENT BY 1 START WITH 62 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence BOOKING_CODE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "BOOKING_CODE_SEQ"  MINVALUE 1 MAXVALUE 1000000 INCREMENT BY 1 START WITH 6 NOCACHE  NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence OBJECT_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OBJECT_ID_SEQ"  MINVALUE 1 MAXVALUE 10000000 INCREMENT BY 1 START WITH 217 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence OBJECT_TYPE_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OBJECT_TYPE_ID_SEQ"  MINVALUE 1 MAXVALUE 1000 INCREMENT BY 1 START WITH 15 NOCACHE  NOORDER  NOCYCLE ;
REM INSERTING into OBJTYPE
SET DEFINE OFF;
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('1',null,'COUNTRY','Country',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('2','1','CITY','City',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('3','2','LOCATION','Location',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('4','3','HOTEL','Hotel',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('5','4','ROOM','Room',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('6',null,'STATUS','Status',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('7','5','BOOKING','Booking',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('8',null,'ROLE','Role',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('9','8','USER','User',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('10',null,'PAY_SYS','PaySystem',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('11',null,'IMAGE','Image',null);
Insert into OBJTYPE (OBJECT_TYPE_ID,PARENT_ID,CODE,NAME,DESCRIPTION) values ('13','7','RATING','Rating',null);
REM INSERTING into ATTRTYPE
SET DEFINE OFF;
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('1','1',null,'COUNTRY_ID','Country ID','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('2','1',null,'COUNTRY_NAME','Country Name','1','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('3','2',null,'CITY_NAME','City Name','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('5','3',null,'STREET_ADDR','Street Address','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('6','3',null,'POSTAL_CODE','Postal Code','0','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('7','3',null,'GPS_COORDS','GPS Coordinates','0','3','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('9','4',null,'HOTEL_NAME','Hotel Name','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('10','4','9','OWNER_ID','Owner ID','1','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('12','4',null,'PHONE','Phone Number','1','4','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('13','4',null,'DESCRIPTION','Description','0','5','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('14','4',null,'HOTEL_RATING','Hotel Rating','1','6','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('15','4',null,'HAS_WIFI','Has WIFI','0','7','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('16','4',null,'HAS_SHUTTLE','Has Shuttle','0','8','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('17','4',null,'HAS_SMOKING','Has Smoking','0','9','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('18','4',null,'HAS_PARKING','Has Parking','0','10','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('19','4',null,'HAS_CONDITIONING','Has Conditioning','0','11','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('20','4',null,'HAS_PETS','Has Pets','0','12','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('21','4',null,'HAS_POOL','Has Pool','0','13','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('22','4',null,'HAS_KITCHEN','Has Kitchen','0','14','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('23','4',null,'HAS_BREAKFAST','Has Breakfast','0','15','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('24','4',null,'CHECK_IN_TIME','Check-In Time','1','16','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('25','4',null,'CHECK_OUT_TIME','Check-Out Time','1','17','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('26','4',null,'IS_PREORDER','Is Preorder','0','18','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('27','4','10','PAY_SYS_ID','Hotel Pay System','0','19','1');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('28','5',null,'ROOM_NO','Room Number','0','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('29','5',null,'NUM_PLACES','Num Places','1','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('30','5',null,'HAS_BATHROOM','Has Bathroom','0','3','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('31','5',null,'HAS_TV','Has TV','0','4','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('32','5',null,'HAS_EXTRA_BED','Has Extra Bed','0','5','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('33','5',null,'COST','Cost','1','6','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('35','7',null,'BOOKING_CODE','Booking Code','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('37','7','9','USER_ID','User ID','1','3','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('38','7',null,'DATE_CHECK_IN','Date Check-In','1','4','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('39','7',null,'DATE_CHECK_OUT','Date Check-Out','1','5','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('40','7',null,'NUM_PERSONS','Num Persons','1','6','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('41','7',null,'IS_PAID','Is Paid','1','7','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('42','7','6','STATUS_ID','Status ID','1','8','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('43','7','10','PAY_SYS_ID','Pay Sys ID','1','9','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('44','8',null,'ROLE_NAME','Role Name','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('45','9',null,'FIRST_NAME','First Name','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('46','9',null,'LAST_NAME','Last Name','1','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('47','9',null,'PHONE','Phone Number','0','3','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('48','9',null,'EMAIL','Email','1','4','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('49','9',null,'PASSWORD','Password','1','5','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('51','10',null,'PAY_SYS_NAME','Pay Sys Name','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('34','6',null,'STATUS_NAME','Status Name','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('52','11',null,'IMAGE_URL','Image URL','1','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('54','13',null,'MESSAGE','Message','0','1','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('55','13',null,'RATING_VALUE','Rating Value','1','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('56','13',null,'RATING_COMMENT','Rating Comment','0','3','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('53','11',null,'IMAGE_DESCRIPTION','Image Description','0','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('57','4','11','HOTEL_IMAGE','Hotel Image','0','20','1');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('36','7',null,'MESSAGE','Message','0','2','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('59','5','11','ROOM_IMAGE','Room Image','0','7','1');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('60','7',null,'BOOKING_COST','Booking Cost','1','10','0');
Insert into ATTRTYPE (ATTRTYPE_ID,OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,CODE,NAME,IS_MANDATORY,ORDER_NUM,IS_MULTIPLE) values ('61','5',null,'IS_ENABLED','Is Enabled','0','8','0');
REM INSERTING into OBJECTS
SET DEFINE OFF;
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('1',null,'1','Ukraine',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('2',null,'1','Canada',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('3','1','2','Kiev',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('4','1','2','Odesa',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('5','2','2','Ottava',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('6','3','3','Khreschatyc 1/64a',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('7','4','3','Mykhaylovskaya',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('8','6','4','Royal City',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('9','7','4','Bristol',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('10','8','5','President Lux 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('11','8','5','Lux 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('12','8','5','Lux 2',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('13','8','5','Double 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('14','8','5','Single 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('15','9','5','Lux 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('16','9','5','Double 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('17','9','5','Double 2',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('18','9','5','Single 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('19','9','5','Single 2',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('20',null,'8','Client',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('21',null,'8','Hotel Owner',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('22','20','9','Chepovskij Ivan',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('23','20','9','Ababin Mikhaylo',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('24','20','9','Krivosheeva Marina',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('25','20','9','Maksimov Maik',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('26','20','9','Slodianyuk Olga',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('27','21','9','Dodon Vlad',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('28','21','9','Spark Alexander',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('29',null,'6','Status Cancelled',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('30',null,'6','Status New',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('31',null,'6','Status Processing',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('32',null,'6','Status Completed',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('33',null,'10','Visa',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('34',null,'10','Master Card',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('35',null,'10','Pay System Cash',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('36',null,'10','Pay System Travel Check',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('37','10','7','Booking1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('62','1','2','Dnepr',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('99',null,'11','Hotel Image 3',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('82','4','3','Deribasovskaya 6',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('81','1','2','Izmail',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('75','1','2','Lviv',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('97',null,'11','Hotel Image 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('98',null,'11','Hotel Image 2',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('100',null,'11','Hotel Image 4',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('110','82','4','New Hotel3',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('137',null,'11','Room Image 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('138',null,'11','Room Image 2',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('139',null,'11','Image room img descr!',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('148','110','5','New Room1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('158',null,'11','Image',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('157','11','7','Booking 1',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('163',null,'8','Role Admin',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('169','157','13','Rating 166',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('167','37','13','Rating 2',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('200','110','5','New Room',null);
Insert into OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) values ('199','14','7','Booking 5',null);
REM INSERTING into ATTRIBUTES
SET DEFINE OFF;
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('1','1','UA',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('2','1','Ukraine',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('1','2','CA',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('2','2','Canada',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('3','3','Kiev',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('3','4','Odesa',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('3','5','Ottava',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('5','6','Khreschatyc 1/64a',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('6','6','123654',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('7','6','50.446329, 30.520716',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('5','7','Mykhaylovskaya 25',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('6','7','654123',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('7','7','30.420716, 70.813925',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('9','8','Royal City',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('12','8','096-31-45-561',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('13','8','The best hotel in the center of the town.',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('15','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('16','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('17','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('18','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('19','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('20','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('21','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('22','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('23','8','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('24','8','10:00:00',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('25','8','19:00:00',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('26','8','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('9','9','Bristol',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('12','9','067-12-52-871',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('13','9','Great hotel in the center of the city.',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('15','9','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('16','9','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('17','9','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('18','9','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('19','9','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('20','9','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('21','9','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('22','9','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('23','9','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('24','9','07:00:00',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('25','9','21:00:00',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('26','9','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','10','3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','10','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','10','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','10','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','10','10000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','11','3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','11','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','11','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','11','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','11','10000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','12','3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','12','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','12','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','12','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','12','8000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','13','2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','13','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','13','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','13','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','13','3000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','14','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','14','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','14','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','14','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','14','1000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','15','3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','15','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','15','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','15','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','15','5000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','16','2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','16','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','16','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','16','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','16','1500',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','17','2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','17','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','17','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','17','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','17','1300',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','18','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','18','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','18','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','18','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','18','700',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','19','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','19','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','19','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','19','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','19','300',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('44','163','Admin',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('55','167','4',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('45','22','Ivan',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('46','22','Chepovskij',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('47','22','099-27-12-751',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('48','22','ivan.chepovskij@gmail.com',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('49','22','agent007',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('45','23','Mikhaylo',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('46','23','Ababin',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('47','23','075-12-53-961',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('48','23','ababintnn@mail.ru',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('49','23','laplasinmyheart',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('45','24','Marina',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('46','24','Krivosheeva',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('47','24','056-12-53-961',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('48','24','inlovewithsempai@mail.ru',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('49','24','ppap1234',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('45','25','Maik',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('46','25','Maksimov',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('47','25','012-43-86-264',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('48','25','judinotjedi@maksimov.org',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('49','25','nomoneynohoney',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('45','26','Olga',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('46','26','Slobodianyuk',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('47','26','099-12-25-961',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('48','26','oolga@gmail.com',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('45','27','Vlad',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('46','27','Dodon',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('47','27','089-13-53-071',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('48','27','vlad.dodon@gmail.com',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('49','27','vlad_dodon',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('45','28','Alexander',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('46','28','Spark',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('47','28','096-35-86-135',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('48','28','alex.spark@gmail.com',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('49','28','alex_spark',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('35','37','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','10','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('37','37','22',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('38','37','2017-05-01',to_date('01.05.17','DD.MM.RR'));
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('39','37','2017-05-07',to_date('07.05.17','DD.MM.RR'));
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('40','37','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','11','2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('41','37','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('43','37','33',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','137','url4',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('3','62','Dnepr',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('5','82','Deribasovskaya 6',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('3','75','Lviv',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('3','81','Izmail',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('14','9','4',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('6','82','65006',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('7','82','65.446329, 36.520716',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('14','8','5',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('9','110','New Hotel3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('12','110','097-77-77-777',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('13','110','Best hotel description',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('14','110','5',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('15','110','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('16','110','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('17','110','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('18','110','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('19','110','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('20','110','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('21','110','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('22','110','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('23','110','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('24','110','13:00:00',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('25','110','12:00:00',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('26','110','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','12','3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','13','4',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','14','5',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','15','6',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','16','7',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','17','8',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','18','9',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','19','10',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('51','33','Visa',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('51','34','Master Card',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('49','26',null,null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('34','29','Cancelled',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('34','30','New',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('34','31','Processing',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('34','32','Completed',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','99','image descr1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','99','url1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','97','image descr 2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','97','url2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','98','image descr 3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','98','url3',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','100','img descr',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','100','img url',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('44','20','Client',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('44','21','Hotel Owner',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('36','37','hello',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('51','35','Cash',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('51','36','Travel Check',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','148','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','148','7',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','148','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','148','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','148','false',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','148','991',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','137','room img descr',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','138','url5',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','138','room img descr',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','139','www.',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','139','room img descr!',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('35','157','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('36','157','hello!',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('38','157',null,to_date('21.06.17','DD.MM.RR'));
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('39','157',null,to_date('28.06.17','DD.MM.RR'));
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('40','157','2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('41','157','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('52','158','url',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('53','158','my image descr',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('55','169','5',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('54','169','Comment!!',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('54','167','comment2',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','10','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','11','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','12','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','13','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','14','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','15','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','16','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('35','199','5',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('36','199','helo World!!!',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('38','199',null,to_date('01.05.17','DD.MM.RR'));
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('39','199',null,to_date('07.05.17','DD.MM.RR'));
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('40','199','7',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('60','37','700000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('60','157','70000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('56','169',null,null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('56','167',null,null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('41','199','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('60','199','18000',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','17','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','18','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','19','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','148','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('28','200','1',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('29','200','5',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('30','200','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('31','200','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('32','200','true',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('33','200','999',null);
Insert into ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE) values ('61','200','1',null);
REM INSERTING into OBJREFERENCE
SET DEFINE OFF;
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('10','27','8');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('10','28','9');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('10','28','110');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('27','33','110');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('27','34','110');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('27','35','9');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('27','97','8');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('27','98','8');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('27','99','8');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('37','22','37');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('37','22','157');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('37','22','199');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('42','30','157');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('42','30','199');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('42','32','37');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('43','33','37');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('43','33','157');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('43','33','199');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('57','33','8');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('57','34','8');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('57','97','110');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('57','98','110');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('57','99','110');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('57','100','9');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('59','137','10');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('59','138','148');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('59','138','200');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('59','139','10');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('59','139','11');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('59','139','148');
Insert into OBJREFERENCE (ATTR_ID,REFERENCE,OBJECT_ID) values ('59','139','200');
--------------------------------------------------------
--  DDL for Index CON_ATTRIBUTES_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CON_ATTRIBUTES_PK" ON "ATTRIBUTES" ("ATTR_ID", "OBJECT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index CON_ATTRTYPE_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX "CON_ATTRTYPE_ID" ON "ATTRTYPE" ("ATTRTYPE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index CON_OBJECTS_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX "CON_OBJECTS_ID" ON "OBJECTS" ("OBJECT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index CON_OBJECT_TYPE_ID
--------------------------------------------------------

  CREATE UNIQUE INDEX "CON_OBJECT_TYPE_ID" ON "OBJTYPE" ("OBJECT_TYPE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index CON_OBJREFERENCE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CON_OBJREFERENCE_PK" ON "OBJREFERENCE" ("ATTR_ID", "REFERENCE", "OBJECT_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SYS_C009045
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C009045" ON "OBJTYPE" ("CODE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Trigger ATTRTYPE_ID_GENERATE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "ATTRTYPE_ID_GENERATE" 
BEFORE INSERT OR UPDATE OF ATTRTYPE_ID ON ATTRTYPE
FOR EACH ROW
BEGIN
  IF(:NEW.ATTRTYPE_ID IS NULL)
    THEN :NEW.ATTRTYPE_ID := ATTRTYPE_ID_SEQ.NEXTVAL;
  END IF;
END;
/
ALTER TRIGGER "ATTRTYPE_ID_GENERATE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BOOKING_CODE_GENERATE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "BOOKING_CODE_GENERATE" 
BEFORE INSERT OR UPDATE OF VALUE ON ATTRIBUTES
FOR EACH ROW
         WHEN (NEW.ATTR_ID = 35) BEGIN
   IF(:NEW.VALUE IS NULL)
    THEN :NEW.VALUE := BOOKING_CODE_SEQ.NEXTVAL;
  END IF;
END;
/
ALTER TRIGGER "BOOKING_CODE_GENERATE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger MANDATORY_ATTRS_CHECK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "MANDATORY_ATTRS_CHECK" 
BEFORE INSERT OR UPDATE OF ATTR_ID,VALUE,DATE_VALUE ON ATTRIBUTES
FOR EACH ROW
DECLARE
 IS_MANDATORY ATTRTYPE.IS_MANDATORY%TYPE;
 OBJECT_TYPE_ID_REF ATTRTYPE.OBJECT_TYPE_ID_REF%TYPE;
BEGIN
  SELECT A.IS_MANDATORY, A.OBJECT_TYPE_ID_REF INTO IS_MANDATORY, OBJECT_TYPE_ID_REF
  FROM ATTRTYPE A
  WHERE A.ATTRTYPE_ID = NVL(:NEW.ATTR_ID,:OLD.ATTR_ID);
  IF (IS_MANDATORY = 1 AND OBJECT_TYPE_ID_REF IS NULL AND :NEW.VALUE IS NULL AND :NEW.DATE_VALUE IS NULL)
    THEN RAISE_APPLICATION_ERROR(-20300, 'ATTRIBUTE with ATTRTYPE_ID = '
      || NVL(:NEW.ATTR_ID,:OLD.ATTR_ID)
      || ' must be declared and its value cannot be null.');
  END IF;
END;
/
ALTER TRIGGER "MANDATORY_ATTRS_CHECK" DISABLE;
--------------------------------------------------------
--  DDL for Trigger OBJECT_ID_GENERATE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OBJECT_ID_GENERATE" 
BEFORE INSERT OR UPDATE OF OBJECT_ID ON OBJECTS
FOR EACH ROW
BEGIN
  IF(:NEW.OBJECT_ID IS NULL)
    THEN :NEW.OBJECT_ID := OBJECT_ID_SEQ.NEXTVAL;
  END IF;
END;
/
ALTER TRIGGER "OBJECT_ID_GENERATE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger OBJECT_TYPE_ID_GENERATE
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OBJECT_TYPE_ID_GENERATE" 
BEFORE INSERT OR UPDATE OF OBJECT_TYPE_ID ON OBJTYPE
FOR EACH ROW
BEGIN
  IF(:NEW.OBJECT_TYPE_ID IS NULL)
    THEN :NEW.OBJECT_TYPE_ID := OBJECT_TYPE_ID_SEQ.NEXTVAL;
  END IF;
END;
/
ALTER TRIGGER "OBJECT_TYPE_ID_GENERATE" ENABLE;
--------------------------------------------------------
--  DDL for Trigger OBJREFERENCE_CHECK
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "OBJREFERENCE_CHECK" 
--BEFORE DELETE OR INSERT OR UPDATE ON OBJREFERENCE
BEFORE INSERT OR UPDATE ON OBJREFERENCE
FOR EACH ROW
DECLARE
 OBJECT_TYPE_ID_MUST_REF ATTRTYPE.OBJECT_TYPE_ID_REF%TYPE;
 OBJECT_TYPE_ID_REAL_REF ATTRTYPE.OBJECT_TYPE_ID_REF%TYPE;
 OBJECT_TYPE_ID_MUST ATTRTYPE.OBJECT_TYPE_ID%TYPE;
 OBJECT_TYPE_ID_REAL ATTRTYPE.OBJECT_TYPE_ID%TYPE;
BEGIN
  SELECT A.OBJECT_TYPE_ID_REF, A.OBJECT_TYPE_ID INTO OBJECT_TYPE_ID_MUST_REF, OBJECT_TYPE_ID_MUST
  FROM ATTRTYPE A
  WHERE A.ATTRTYPE_ID = NVL(:NEW.ATTR_ID,:OLD.ATTR_ID);

  SELECT O.OBJECT_TYPE_ID INTO OBJECT_TYPE_ID_REAL
  FROM OBJECTS O
  WHERE O.OBJECT_ID = :NEW.OBJECT_ID;

  SELECT O.OBJECT_TYPE_ID INTO OBJECT_TYPE_ID_REAL_REF
  FROM OBJECTS O
  WHERE O.OBJECT_ID = :NEW.REFERENCE;

  IF (OBJECT_TYPE_ID_MUST <> OBJECT_TYPE_ID_REAL)
    THEN RAISE_APPLICATION_ERROR(-20301, 'WRONG OBJECT_TYPE_ID = '|| OBJECT_TYPE_ID_REAL || 
    ' OF OBJECT WITH OBJECT_ID = ' || :NEW.OBJECT_ID ||
    ', MUST BE ' || OBJECT_TYPE_ID_MUST );
  END IF;

  IF (OBJECT_TYPE_ID_MUST_REF <> OBJECT_TYPE_ID_REAL_REF)
    THEN RAISE_APPLICATION_ERROR(-20302, 'WRONG OBJECT_TYPE_ID = ' || OBJECT_TYPE_ID_REAL_REF || 
    ' OF OBJECT WITH OBJECT_ID = ' || :NEW.REFERENCE ||
    ', MUST BE ' || OBJECT_TYPE_ID_MUST_REF);
  END IF;
END;
/
ALTER TRIGGER "OBJREFERENCE_CHECK" DISABLE;
--------------------------------------------------------
--  Constraints for Table OBJTYPE
--------------------------------------------------------

  ALTER TABLE "OBJTYPE" ADD UNIQUE ("CODE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "OBJTYPE" ADD CONSTRAINT "CON_OBJECT_TYPE_ID" PRIMARY KEY ("OBJECT_TYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "OBJTYPE" MODIFY ("CODE" NOT NULL ENABLE);
  ALTER TABLE "OBJTYPE" MODIFY ("OBJECT_TYPE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ATTRTYPE
--------------------------------------------------------

  ALTER TABLE "ATTRTYPE" MODIFY ("IS_MULTIPLE" NOT NULL ENABLE);
  ALTER TABLE "ATTRTYPE" ADD CONSTRAINT "CON_ATTRTYPE_ID" PRIMARY KEY ("ATTRTYPE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ATTRTYPE" MODIFY ("ORDER_NUM" NOT NULL ENABLE);
  ALTER TABLE "ATTRTYPE" MODIFY ("IS_MANDATORY" NOT NULL ENABLE);
  ALTER TABLE "ATTRTYPE" MODIFY ("OBJECT_TYPE_ID" NOT NULL ENABLE);
  ALTER TABLE "ATTRTYPE" MODIFY ("ATTRTYPE_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OBJECTS
--------------------------------------------------------

  ALTER TABLE "OBJECTS" ADD CONSTRAINT "CON_OBJECTS_ID" PRIMARY KEY ("OBJECT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "OBJECTS" MODIFY ("OBJECT_TYPE_ID" NOT NULL ENABLE);
  ALTER TABLE "OBJECTS" MODIFY ("OBJECT_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ATTRIBUTES
--------------------------------------------------------

  ALTER TABLE "ATTRIBUTES" ADD CONSTRAINT "CON_ATTRIBUTES_PK" PRIMARY KEY ("ATTR_ID", "OBJECT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "ATTRIBUTES" MODIFY ("OBJECT_ID" NOT NULL ENABLE);
  ALTER TABLE "ATTRIBUTES" MODIFY ("ATTR_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OBJREFERENCE
--------------------------------------------------------

  ALTER TABLE "OBJREFERENCE" ADD CONSTRAINT "CON_OBJREFERENCE_PK" PRIMARY KEY ("ATTR_ID", "REFERENCE", "OBJECT_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "OBJREFERENCE" MODIFY ("OBJECT_ID" NOT NULL ENABLE);
  ALTER TABLE "OBJREFERENCE" MODIFY ("REFERENCE" NOT NULL ENABLE);
  ALTER TABLE "OBJREFERENCE" MODIFY ("ATTR_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table OBJTYPE
--------------------------------------------------------

  ALTER TABLE "OBJTYPE" ADD CONSTRAINT "CON_PARENT_ID" FOREIGN KEY ("PARENT_ID")
	  REFERENCES "OBJTYPE" ("OBJECT_TYPE_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ATTRTYPE
--------------------------------------------------------

  ALTER TABLE "ATTRTYPE" ADD CONSTRAINT "CON_ATTR_OBJECT_TYPE_ID" FOREIGN KEY ("OBJECT_TYPE_ID")
	  REFERENCES "OBJTYPE" ("OBJECT_TYPE_ID") ENABLE;
  ALTER TABLE "ATTRTYPE" ADD CONSTRAINT "CON_ATTR_OBJECT_TYPE_ID_REF" FOREIGN KEY ("OBJECT_TYPE_ID_REF")
	  REFERENCES "OBJTYPE" ("OBJECT_TYPE_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OBJECTS
--------------------------------------------------------

  ALTER TABLE "OBJECTS" ADD CONSTRAINT "CON_OBJ_TYPE_ID" FOREIGN KEY ("OBJECT_TYPE_ID")
	  REFERENCES "OBJTYPE" ("OBJECT_TYPE_ID") ENABLE;
  ALTER TABLE "OBJECTS" ADD CONSTRAINT "CON_PARENTS_ID" FOREIGN KEY ("PARENT_ID")
	  REFERENCES "OBJECTS" ("OBJECT_ID") ON DELETE CASCADE DEFERRABLE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ATTRIBUTES
--------------------------------------------------------

  ALTER TABLE "ATTRIBUTES" ADD CONSTRAINT "CON_AATTRTYPE_ID" FOREIGN KEY ("ATTR_ID")
	  REFERENCES "ATTRTYPE" ("ATTRTYPE_ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "ATTRIBUTES" ADD CONSTRAINT "CON_AOBJECT_ID" FOREIGN KEY ("OBJECT_ID")
	  REFERENCES "OBJECTS" ("OBJECT_ID") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OBJREFERENCE
--------------------------------------------------------

  ALTER TABLE "OBJREFERENCE" ADD CONSTRAINT "CON_RATTRTYPE_ID" FOREIGN KEY ("ATTR_ID")
	  REFERENCES "ATTRTYPE" ("ATTRTYPE_ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "OBJREFERENCE" ADD CONSTRAINT "CON_REFERENCE" FOREIGN KEY ("REFERENCE")
	  REFERENCES "OBJECTS" ("OBJECT_ID") ON DELETE CASCADE ENABLE;
  ALTER TABLE "OBJREFERENCE" ADD CONSTRAINT "CON_ROBJECT_ID" FOREIGN KEY ("OBJECT_ID")
	  REFERENCES "OBJECTS" ("OBJECT_ID") ON DELETE CASCADE ENABLE;
