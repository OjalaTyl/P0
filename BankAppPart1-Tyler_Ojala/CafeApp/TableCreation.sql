
create role cafe_employee LOGIN password 'employ33';

grant all privileges on table public.cafe_order to cafe_employee;
grant all privileges on table public.cafe_user to cafe_employee;

CREATE SEQUENCE "cafe_order_order_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;
SELECT setval('"public"."cafe_order_order_id_seq"', 1, true);


create table if not exists cafe_user(
    username VARCHAR(60) not null,
    email varchar(80) not null,
    is_employee boolean,
    password varchar(80),
    num_of_orders int,
    constraint pk_email primary key (email)
);

insert into cafe_user values ('Jim', 'Jim@Jim.Jim', true, 'Jim', 0);
insert into cafe_user values ('Tom', 'Tom@Tom.Tom', true, 'Tom', 0);

create table if not exists cafe_order(
    order_id int default nextval('cafe_order_order_id_seq'::regclass) not null,
    customer_email varchar(80) not null,
    food_name varchar(60),
    cost decimal, 
    constraint pk_order_id primary key (order_id),
    constraint fk_customer_email foreign key (customer_email) references cafe_user
);


CREATE SEQUENCE "cafe_items_item_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 2147483647
 START 1
 CACHE 1;
SELECT setval('"public"."cafe_items_item_id_seq"', 1, true);

create table if not exists cafe_items(
    item_id int default nextval('cafe_items_item_id_seq'::regclass) not null,
    item_name varchar(80),
    cost decimal,
    is_food boolean,
    constraint pk_item_id primary key (item_id)
);

insert into cafe_items values (default, 'Burger', 3.99, true);
insert into cafe_items values (default, 'Fry', 1.99, true);
insert into cafe_items values (default, 'Salad', 5.99, true);
insert into cafe_items values (default, 'Doughnut', 2.50, true);
insert into cafe_items values (default, 'Flatbread Sandwich', 4.99, true);
insert into cafe_items values (default, 'Cookie', 0.99, true);
insert into cafe_items values (default, 'Shake', 3.50, false);
insert into cafe_items values (default, 'Soda', 2.99, false);
insert into cafe_items values (default, 'Malt', 3.50, false);
insert into cafe_items values (default, 'Coffee', 5.99, false);
insert into cafe_items values (default, 'Water', 0.00, false);
insert into cafe_items values (default, 'Bottled Water', 1.99, false);