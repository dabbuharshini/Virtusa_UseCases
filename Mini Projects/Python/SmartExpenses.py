import csv
import os
from datetime import datetime
import matplotlib.pyplot as plt

FILE_NAME="expenses.csv"
monthly_budget=35000


def initialize_file():
    if not os.path.exists(FILE_NAME):
        with open(FILE_NAME, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['Date', 'Category', 'Amount', 'Description'])

def add_expense():
    date = input("Enter the date (YYYY-MM-DD): ")
    if date=="":
        date = datetime.now().strftime('%Y-%m-%d')
    category = input("Enter the category(Food/Travel/Bills/shopping/Entertainment): ")
    amount = float(input("Enter the amount: "))
    description = input("Enter a description: ")
    with open(FILE_NAME, 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([date, category, amount, description])
    print("Expense added successfully!\n")
    
def view_expenses():
    print("\n---All Expenses---")
    with open(FILE_NAME, mode='r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            print(f"Date: {row['Date']}, Category: {row['Category']}, Amount: {row['Amount']}, Description: {row['Description']}")
    print("\n")

def filter_category():
    category = input("Enter the category to filter by: ")
    found=False
    print(f"\n---Expenses in {category}---")
    with open(FILE_NAME, mode='r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            if row['Category'].lower() == category.lower():
                print(f"Date: {row['Date']}, Amount: {row['Amount']}, Description: {row['Description']}")
                found=True
        if not found:
            print(f"No expenses found for category {category}")
    print("\n")

def monthly_summary():
    month = input("Enter the month (YYYY-MM): ")
    total = 0
    print(f"\n---Expenses in {month}---")
    with open(FILE_NAME, mode='r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            if row['Date'].startswith(month):
                print(f"Date: {row['Date']}, Category: {row['Category']}, Amount: {row['Amount']}, Description: {row['Description']}")
                total += float(row['Amount'])
    print(f"Total Expenses for {month}: {total}\n")

    if total > monthly_budget:
        print("You have exceeded your budget!\n")
    else:
        print("You are within your budget.\n")
    print("\n")

def category_analysis():
    categories={}
    with open(FILE_NAME, mode='r') as file:
        reader=csv.DictReader(file)
        for row in reader:
            cat=row['Category']
            amount=float(row['Amount'])
            if cat in categories:
                categories[cat]+=amount
            else:
                categories[cat]=amount

    print("\n---Category Analysis---")
    for cat,total in categories.items():
        print(f"{cat}:{total}")
    if categories:
        max_cat=max(categories, key=categories.get)
        print(f"Highest Spending Category: {max_cat} with {categories[max_cat]}\n")

        print("\n Suggestion:")
        print(f"\nTry reducing spending on {max_cat}")
    print("\n")
    return categories

def show_chart():
    categories=category_analysis()
    if not categories:
        print("No expenses to show in chart.")
        return
    labels=list(categories.keys())
    values=list(categories.values())
    plt.figure()
    plt.pie(values, labels=labels, autopct='%1.1f%%')
    plt.title("Expense Distribution by Category")
    plt.show()

def main():
    initialize_file()
    while True:
        print("1. Add Expenses")
        print("2. View Expense")
        print("3. Filter by Category")
        print("4. Monthly Summary")
        print("5. Category Analysis")
        print("6. Show Chart")
        print("7. Exit")

        choice = input("Enter your choice: ")
        if choice == '1':
            add_expense()
        elif choice == '2':
            view_expenses()
        elif choice == '3':
            filter_category()
        elif choice == '4':
            monthly_summary()
        elif choice == '5':
            category_analysis()
        elif choice == '6':
            show_chart()
        elif choice == '7':
            print("Thank you!!..")
            break
        else:
            print("Invalid choice. Please try again.\n")
if __name__ == "__main__":
     main()
