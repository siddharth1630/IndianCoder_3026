--      FOR STUDENT_LOAN PROJECT 
SELECT * FROM SL_REGISTER_STUDENT;
CREATE TABLE SL_REGISTER_STUDENT(
    NAME VARCHAR2(20),
    ROLL NUMBER(6) CONSTRAINT SLRS_PKROLL PRIMARY KEY,
    EMAIL VARCHAR2(30),
    PASSWORD VARCHAR2(40)
);
drop table sl_register_student;

create or replace procedure sl_authentication(userId in varchar,passwords in varchar,result out varchar) as 
    cnt number(4);
begin
    select count(*) into cnt from sl_register_student where password=passwords and email=userId;
    if(cnt<>0) then
        result:='valid credentials';
    else
        result:='invalid credentials'; 
    end if;
    end;
  /
select count(*)  from sl_register_student where password=123 and email='sid@gmail.com';
SELECT NAME FROM  SL_REGISTER_STUDENT WHERE EMAIL='sid@gmail.com';

create table enterInfo(
    ROLL        NUMBER(6) CONSTRAINT PK_ROLL_ENTERINFO PRIMARY KEY,
    COLLEGE_NAME    VARCHAR2(30),
    COLLEGE_ADDRESS VARCHAR2(50),
    HOME_ADDRESS    VARCHAR2(40),
    CITY        VARCHAR2(20),
    DISTRICT    VARCHAR2(20),
    STATES       VARCHAR2(20),
    PINCODE     NUMBER(6),
    ADHARPIC    BLOB
);
DROP TABLE ENTERINFO;
select * from  enterinfo;
SELECT COUNT(*) FROM enterInfo WHERE ROLL=1;

CREATE TABLE LOAN_DETAIL(
    ROLL NUMBER(6),-- CONSTRAINT LD_PK_ROLL PRIMARY KEY,
    LOAN_AMOUNT NUMBER(15,2),
    TENURE  NUMBER(3),
    TOTAL_BALANCE NUMBER(15,2) DEFAULT 0.0,
    COUNTS  NUMBER(5) DEFAULT 0,
    LOAN_DATE DATE,
    REPAY_DATE DATE
);
select sysdate from dual;
DROP TABLE LOAN_DETAIL;
select * from loan_detail;
INSERT INTO LOAN_DETAIL VALUES(12,23.5,2,25.5,1);
SELECT total_balance,counts FROM LOAN_DETAIL WHERE ROLL=5 order by counts desc;
INSERT INTO LOAN_DETAIL VALUES(1,333,2,555,2,sysdate,TO_DATE('11-6-2020','dd mm yyyy'));
SELECT LOAN_AMOUNT FROM LOAN_DETAIL WHERE ROLL=1;
SELECT l.LOAN_AMOUNT,i.status FROM LOAN_DETAIL l,insert_loan i WHERE  l.roll=i.roll and l.roll=1;
UPDATE LOAN_DETAIL SET LOAN_AMOUNT=5 WHERE ROLL=1;

CREATE TABLE INSERT_LOAN(
    ROLL    NUMBER(6),
    ACCOUNT_NO  NUMBER(16),
    CIF_NO  NUMBER(8),
    LOAN_AMOUNT NUMBER(15,2),
    COUNTS  NUMBER(5),
    status varchar2(10) default 'PENDING'
);
DROP TABLE INSERT_LOAN;
select * from insert_loan;

select * from insert_loan where status='pending';
select e.roll, e.college_name,e.college_address,e.home_address,e.city,e.district,e.states,e.pincode,l.loan_amount,l.tenure,l.total_balance,l.counts,l.loan_date,l.repay_date,i.status from enterinfo e , loan_detail l,INSERT_LOAN i where e.roll=l.roll and l.roll=i.roll and e.roll=i.roll;