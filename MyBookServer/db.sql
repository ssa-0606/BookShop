use mall_book;

DROP TABLE if exists book;
create table book(
                     id bigint not null auto_increment primary key ,
                     name varchar(64) not null ,
                     author varchar(64) not null ,
                     cover varchar(64) not null,
                     price decimal(10,2) not null,
                     category_id int not null,
                     book_info_id int not null
);