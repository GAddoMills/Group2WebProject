# Group2WebProject

Add this SQL to create the USER table and insert the users.

```sql
Use sakila;

drop table user;

Create table user(
                     user_id int not null auto_increment primary key,
                     user_name varchar(50) not null unique,
                     user_email varchar(50) not null,
                     user_password varchar(50) not null,
                     user_key varchar(36),
                     last_update timestamp);

Insert into user(
    user_name,
    user_email,
    user_password)
values('elza', 'econtiero@spartaglobal.com' , '12345');

Insert into user(
    user_name,
    user_email,
    user_password)
values('gloria', 'gmills@spartaglobal.com' , 'abcde');
```