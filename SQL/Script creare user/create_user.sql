CREATE USER sdecianu IDENTIFIED BY sdecianu; -- you should replace my user name with yours. “IDENTIFIED BY” means the password.
GRANT CREATE SESSION TO sdecianu; -- necessary for connecting to the database with your new user.
GRANT CREATE ANY INDEX TO sdecianu; -- we use indexes for faster queries.
GRANT ALTER ANY INDEX TO sdecianu;
GRANT DROP ANY INDEX TO sdecianu;
GRANT CREATE ANY PROCEDURE TO sdecianu; -- we will use them in following workshops.
GRANT ALTER ANY PROCEDURE TO sdecianu;
GRANT DROP ANY PROCEDURE TO sdecianu;
GRANT EXECUTE ANY PROCEDURE TO sdecianu;
GRANT CREATE ANY SEQUENCE TO sdecianu; -- we need sequences for inserting data.
GRANT ALTER ANY SEQUENCE TO sdecianu;
GRANT DROP ANY SEQUENCE TO sdecianu;
GRANT SELECT ANY SEQUENCE TO sdecianu;
GRANT CREATE ANY TABLE TO sdecianu;
GRANT ALTER ANY TABLE TO sdecianu;
GRANT DELETE ANY TABLE TO sdecianu;
GRANT DROP ANY TABLE TO sdecianu;
GRANT INSERT ANY TABLE TO sdecianu;
GRANT LOCK ANY TABLE TO sdecianu;
GRANT SELECT ANY TABLE TO sdecianu;
GRANT UPDATE ANY TABLE TO sdecianu;
GRANT CREATE TABLESPACE TO sdecianu;
GRANT ALTER TABLESPACE TO sdecianu;
GRANT DROP TABLESPACE TO sdecianu;
GRANT CREATE ANY VIEW TO sdecianu;
GRANT DROP ANY VIEW TO sdecianu;
GRANT UNDER ANY VIEW TO sdecianu;
alter user sdecianu quota 50m on system;
