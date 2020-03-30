create table banking_tb (
    id number (10) ,
    accNum varchar2(30) primary key,
    name varchar2(20),
    balance number(30)
);

create sequence seq_banking
    increment by 1 
    start with 0 
    nomaxvalue
    minvalue 0
    nocycle 
    nocache; 


drop table banking_tb;
commit;

rollback;