 

  CREATE TABLE "SP_SP"."SP_RT_EMPL_EXPERIENCE_CERT_USE" 
   (	"ID" NUMBER(10,0), 
	"DESCR" VARCHAR2(200 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SEPE" ;
 

  ALTER TABLE "SP_SP"."SP_RT_EMPL_EXPERIENCE_CERT_USE" MODIFY ("ID" NOT NULL ENABLE);

Insert into SP_RT_EMPL_EXPERIENCE_CERT_USE (ID,DESCR) values ('1','��������� �� ���������� ����');
Insert into SP_RT_EMPL_EXPERIENCE_CERT_USE (ID,DESCR) values ('2','������ ������ �������� ������������');
