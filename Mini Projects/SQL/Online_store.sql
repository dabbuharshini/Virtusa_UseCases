Create database online_store;
use online_store;

create table customers
(
customer_id int primary key,
customer_name VARCHAR(50),
city VARCHAR(50)
);

create table products
(
product_id int primary key,
product_name VARCHAR(100),
category VARCHAR(75),
price decimal(10,2)
);
create table orders
(
order_id int primary key,
order_date date,
customer_id int,
foreign key (customer_id) references customers(customer_id)
);

create table Order_items
(
order_id int,
product_id int,
quantity int,
foreign key (order_id) references orders(order_id),
foreign key (product_id) references products(product_id)
);


insert into customers values
(1,"Raju","Hyderabad"),
(2,"Rani","Chennai"),
(3,"Ram","Banglore"),
(4,"Krishna","Nagpur"),
(5,"Balaji","Noida"),
(6,"Hanuman","Pune");

insert into products values
(101,"Laptop","Electronics",50000),
(102,"Phone","Electronics",20000),
(103,"Shoes","Fashion",30000),
(104,"Watch","Fashion",5000),
(105,"Earpods","Electronics",5000),
(106,"clothes","Fashion",3000);

insert into orders values
(1,'2024-01-10',1),
(2,'2024-01-10',3),
(3,'2024-01-10',2),
(4,'2024-01-10',1),
(5,'2024-01-10',5),
(6,'2024-01-10',4),
(7,'2024-01-10',1),
(8,'2024-01-10',2);

insert into order_items values
(1,101,1),
(1,102,2),
(1,102,1),
(2,103,1),
(3,104,3),
(4,105,2),
(5,106,4);

-- Top-selling products in order--
select p.product_name,sum(ord_it.quantity) as total_sold from order_items ord_it 
join products p on ord_it.product_id=p.product_id group by p.product_name order by total_sold desc;

-- most valuable customers
select c.customer_name,sum(p.price*oi.quantity) as total_spent from customers c
join orders o on c.customer_id=o.customer_id
join order_items oi on o.order_id=oi.order_id
join products p on oi.product_id=p.product_id
group by c.customer_name
order by total_spent desc;

-- Monthly revenue
select month(o.order_date) as month,sum(p.price*oi.quantity) as revenue from orders o
join order_items oi on o.order_id=oi.order_id
join products p on oi.product_id=p.product_id
group by month(o.order_date);

-- category-wise sales analysis 
select p.category,sum(oi.quantity) as total_sales from order_items oi
join products p on oi.product_id=p.product_id
group by p.category;

-- Inactive customers
select customer_name from customers where customer_id not in(select distinct customer_id from orders);
