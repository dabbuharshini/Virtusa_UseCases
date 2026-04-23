create database Delivery;
use Delivery;
create table partners
(
partner_ID int primary key,
partner_name VARCHAR(50)
);
create table shipments
(
ship_ID int primary key,
partner_ID int,
sourc_city VARCHAR(50),
dest_city VARCHAR(50),
orderedDate DATE,
promisedDate DATE,
ActualDeliverDate DATE,
status VARCHAR(20),
foreign key (partner_ID) references Partners(partner_ID)
);
create table DeliveryLogs
(
Log_Id int primary key,
ship_id int,
logDate DATE,
Remarks VARCHAR(100),
foreign key (ship_id) references shipments(ship_id)
);

INSERT INTO partners VALUES (1,'Ravi Logistics');
INSERT INTO partners VALUES (2,'Krishna Transport');
INSERT INTO partners VALUES (3,'Shiva Couriers');
INSERT INTO partners VALUES (4,'Lakshmi Delivery Services');


INSERT INTO shipments VALUES (101,1,'Hyderabad','Vijayawada','2026-03-15','2026-03-18','2026-03-17','Delivered');
INSERT INTO shipments VALUES (102,2,'Chennai','Bangalore','2026-03-18','2026-03-21','2026-03-22','Delivered');
INSERT INTO shipments VALUES (103,3,'Mumbai','Pune','2026-03-20','2026-03-23','2026-03-22','Delivered');
INSERT INTO shipments VALUES (104,1,'Delhi','Jaipur','2026-03-22','2026-03-25','2026-03-27','Returned');
INSERT INTO shipments VALUES (105,4,'Kolkata','Bhubaneswar','2026-03-25','2026-03-28','2026-03-27','Delivered');
INSERT INTO shipments VALUES (106,2,'Hyderabad','Warangal','2026-03-28','2026-03-31','2026-04-02','Delivered');
INSERT INTO shipments VALUES (107,3,'Chennai','Coimbatore','2026-04-01','2026-04-04','2026-04-03','Delivered');
INSERT INTO shipments VALUES (108,4,'Bangalore','Mysore','2026-04-03','2026-04-06','2026-04-08','Returned');
INSERT INTO shipments VALUES (109,4,'Bangalore','Vijayawada','2026-04-05','2026-04-07','2026-04-10','Returned');
INSERT INTO shipments VALUES (110,2,'Bangalore','Hyderabad','2026-04-06','2026-04-08','2026-04-11','Delivered');

INSERT INTO DeliveryLogs VALUES (1,101,'2026-03-16','Package picked up');
INSERT INTO DeliveryLogs VALUES (2,101,'2026-03-17','Out for delivery');
INSERT INTO DeliveryLogs VALUES (3,102,'2026-03-20','Delayed due to traffic');
INSERT INTO DeliveryLogs VALUES (4,103,'2026-03-21','Reached local hub');
INSERT INTO DeliveryLogs VALUES (5,104,'2026-03-26','Customer not available');
INSERT INTO DeliveryLogs VALUES (6,105,'2026-03-27','On time delivery');
INSERT INTO DeliveryLogs VALUES (7,106,'2026-04-01','Delayed due to rain');
INSERT INTO DeliveryLogs VALUES (8,108,'2026-04-07','Returned to sender');


select * from shipments where ActualDeliverDate>promisedDate;

select partner_ID,
count(Case when status="Delivered" then 1 END) as Delivered,
count(Case when status="Returned" then 1 END) as Returned 
from shipments group by partner_ID;


select dest_city,count(*) as TotalOrders from shipments
where orderedDate>=curdate()-interval 30 day
group by dest_city
order by TotalOrders desc 
limit 1;

select p.partner_name,count(s.ship_ID) as TotalShipments,
count(case when s.ActualDeliverDate<=s.promisedDate then 1 END) as OntimeDelivery,
count(case when s.ActualDeliverDate>s.promisedDAte then 1 END) as DelayedDelivery,
round((count(case when s.ActualDeliverDate<=s.promisedDate then 1 END)*100.0)/count(*),2) as SuccessRate
from partners p join 
shipments s on p.partner_ID=s.partner_ID
group by p.partner_name order by DelayedDelivery ASC;