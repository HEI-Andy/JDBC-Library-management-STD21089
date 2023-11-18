create table if not exists book(
    id int  primary key,
    book_name varchar(100),
    page_number int,
    topic varchar(100),
    release_date Date
);