create table if not exist book(
    id int  primary key,
    book_name varchar(100),
    page_number int,
    topic varchar(100),
    realese_date Date
)