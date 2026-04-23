#  Pre-Training & Mini Project Repository

This repository contains projects developed during **Pre-Training** and **Mini Project Use Cases**, implemented using **Java, SQL, and Python**.
Each section demonstrates practical problem-solving and real-world applications.

---

## Project Overview

*  Multi-technology implementation (Java, SQL, Python)
*  Covers real-world use cases and business problems
*  Includes data processing, backend logic, and database analysis
*  Designed for learning, practice, and interviews

---

#  Section 1: Pre-Training Projects

---

## ☕ Java – Online Quiz System

**Description:**  
An interactive quiz system where users can take quizzes and receive automated evaluation.

<p align="center">
  <img src="outputs/Miniproject_Outputs/java-admin.png" width="45%" />
  <img src="outputs/Miniproject_Outputs/java-user(questions1).png" width="45%" />
  <img src="outputs/Miniproject_Outputs/java-user(questions2).png" width="45%" />
  <img src="outputs/Miniproject_Outputs/java-user(questions3).png" width="45%" />
  <img src="outputs/Miniproject_Outputs/java-user(questions4).png" width="45%" />
</p>


---

##  SQL – Online Retail Sales Analysis

**Description:**  
A relational database system to analyze retail sales and generate insights.
**Output:**
```sql
-- Top-selling products in order--
select p.product_name,sum(ord_it.quantity) as total_sold from order_items ord_it 
join products p on ord_it.product_id=p.product_id group by p.product_name order by total_sold desc;
<p align="center"> <img src="outputs/Miniproject_Outputs/SQL_query1.png" width="70%" /> </p>

```sql
-- most valuable customers
select c.customer_name,sum(p.price*oi.quantity) as total_spent from customers c
join orders o on c.customer_id=o.customer_id
join order_items oi on o.order_id=oi.order_id
join products p on oi.product_id=p.product_id
group by c.customer_name
order by total_spent desc;
<p align="center"> <img src="outputs/Miniproject_Outputs/SQL_query2.png" width="70%" /> </p>

```sql
-- Monthly revenue
select month(o.order_date) as month,sum(p.price*oi.quantity) as revenue from orders o
join order_items oi on o.order_id=oi.order_id
join products p on oi.product_id=p.product_id
group by month(o.order_date);
<p align="center"> <img src="outputs/Miniproject_Outputs/SQL_query3.png" width="70%" /> </p>

```sql
-- category-wise sales analysis 
select p.category,sum(oi.quantity) as total_sales from order_items oi
join products p on oi.product_id=p.product_id
group by p.category;
<p align="center"> <img src="outputs/Miniproject_Outputs/SQL_query4.png" width="70%" /> </p>

```sql
-- Inactive customers
select customer_name from customers where customer_id not in(select distinct customer_id from orders);
<p align="center"> <img src="outputs/Miniproject_Outputs/SQL_query5.png" width="70%" /> </p>
---

## Python – Smart Expense Tracker

**Description:**
A tool to track daily expenses and analyze spending behavior.

**Features:**

* Expense logging (date, category, amount)
* Monthly summary
* Category-wise analysis
* Visualization using charts

**Output:**
* Adding and viewing Expenses
<p align="center"> <img src="outputs/Miniproject_Outputs/python1.png" width="70%" /> </p>
* Filtering the Expenses by category and Monthly summary
<p align="center"> <img src="outputs/Miniproject_Outputs/python2.png" width="70%" /> </p>
* Category analysis and showing chart
<p align="center"> <img src="outputs/Miniproject_Outputs/python3.png" width="70%" /> </p>

> Helps users understand spending patterns and optimize expenses 

---

#  Section 2: Mini Project Use Cases

---

##  Java – SmartPay Utility Biller

**Description:**
A billing system that calculates electricity/water bills based on usage slabs.

**Features:**

* Slab-based billing system
* Input validation
* Interface-based design
* Digital receipt generation

**Output:**
* SmartPay utility Billing System
<p align="center"> <img src="outputs/Usecases_Outputs/Java.png" width="70%" /> </p>
---

## SQL – E-Commerce Logistics Tracker

**Description:**
A logistics tracking system to monitor shipments and analyze delivery performance.

**Features:**

* Shipment tracking
* Delay detection
* Partner performance ranking
* Delivery analytics

**Output:**
```sql
-- Delayed Shipment Delivery
select * from shipments where ActualDeliverDate>promisedDate;
<p align="center"> <img src="outputs/Usecases_Outputs/SQL_query1.png" width="70%" /> </p>

```sql
-- Performance Ranking
select partner_ID,
count(Case when status="Delivered" then 1 END) as Delivered,
count(Case when status="Returned" then 1 END) as Returned 
from shipments group by partner_ID;
<p align="center"> <img src="outputs/Usecases_Outputs/SQL_query2.png" width="70%" /> </p>

```sql
-- Zone filtering
select dest_city,count(*) as TotalOrders from shipments
where orderedDate>=curdate()-interval 30 day
group by dest_city
order by TotalOrders desc 
limit 1;
<p align="center"> <img src="outputs/Usecases_Outputs/SQL_query3.png" width="70%" /> </p>

```sql
-- Performance score card
select p.partner_name,count(s.ship_ID) as TotalShipments,
count(case when s.ActualDeliverDate<=s.promisedDate then 1 END) as OntimeDelivery,
count(case when s.ActualDeliverDate>s.promisedDAte then 1 END) as DelayedDelivery,
round((count(case when s.ActualDeliverDate<=s.promisedDate then 1 END)*100.0)/count(*),2) as SuccessRate
from partners p join 
shipments s on p.partner_ID=s.partner_ID
group by p.partner_name order by DelayedDelivery ASC;
<p align="center"> <img src="outputs/Usecases_Outputs/SQL_query4.png" width="70%" /> </p>
---

## Python – Social Media Content Sanitizer

**Description:**
A content moderation tool that filters harmful words and extracts links.

**Features:**

* Banned word filtering
* URL extraction
* Post analysis
* Summary report generation

**Output:**
* Social-Media Content sanitizer
<p align="center"> <img src="outputs/Usecases_Outputs/python.png" width="70%" /> </p>

> Demonstrates automation of content filtering and safety mechanisms 

---

##  Project Structure

```
/project-root
│
├── Mini Projects/
│   ├── Java/
│   ├── SQL/
│   ├── Python/
│
├── UseCases/
│   ├── Java/
│   ├── SQL/
│   ├── Python/
│
├── outputs/
│   ├── Miniproject_Outputs/
│   ├── Usecases_Outputs/
│
└── README.md
```

