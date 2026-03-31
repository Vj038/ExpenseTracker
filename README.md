# 💰 Personal Expense Tracker

A command-line Java application to record, view, filter, and summarize daily personal expenses. Data is persisted across sessions using CSV file storage.

---

## 📌 Problem Statement

Many students and individuals struggle to keep track of where their money goes each day. Manually noting expenses in a diary is unreliable, and most apps are too complex for simple use. This project solves that with a minimal, focused Java CLI tool that anyone can run on any system with Java installed.

---

## ✨ Features

| Feature | Description |
|--------|-------------|
| ➕ Add Expense | Record description, amount, category, and date |
| 📋 View All | See all expenses in a formatted table with total |
| 🔍 Filter by Category | View only Food, Travel, Shopping, etc. |
| 📊 Summary | Category-wise breakdown and grand total |
| 🗑️ Delete | Remove a specific expense by row number |
| 💾 Persistent Storage | Data saved to `expenses.csv` automatically |

---

## 🗂️ Project Structure

```
ExpenseTracker/
├── src/
│   └── expensetracker/
│       ├── Main.java            # Entry point — menu-driven UI
│       ├── Expense.java         # Model class — represents one expense
│       └── ExpenseManager.java  # Business logic — CRUD + File I/O
├── expenses.csv                 # Auto-generated data file (after first run)
└── README.md
```

---

## ⚙️ Setup & Run

### Prerequisites
- Java JDK 11 or higher
- Any terminal / command prompt

### Step 1 — Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/ExpenseTracker.git
cd ExpenseTracker
```

### Step 2 — Compile
```bash
mkdir -p out
javac -d out src/expensetracker/*.java
```

### Step 3 — Run
```bash
java -cp out expensetracker.Main
```

> On Windows, use `\` instead of `/` in paths.

---

## 🖥️ Sample Output

```
  ╔══════════════════════════════════════════╗
  ║     PERSONAL EXPENSE TRACKER  v1.0      ║
  ║        Built with Java — BYOP            ║
  ╚══════════════════════════════════════════╝

  ┌─ MENU ──────────────────────────┐
  │  1. Add Expense                 │
  │  2. View All Expenses           │
  │  3. Filter by Category          │
  │  4. View Summary                │
  │  5. Delete an Expense           │
  │  6. Exit                        │
  └─────────────────────────────────┘
  Enter choice: 1

  ── Add New Expense ──────────────────────────────
  Description : Lunch at canteen
  Amount (Rs.): 85
  Categories  : Food | Travel | Shopping | Health | Bills | Other
  Category    : Food
  Date (DD-MM-YYYY or press Enter for today):

✔  Expense added successfully!
```

---

## 🧠 Java Concepts Used

- **OOP** — `Expense` class with encapsulation (private fields, getters)
- **Collections** — `ArrayList<Expense>` for in-memory storage
- **File I/O** — `BufferedReader` for reading, `FileWriter` for writing CSV
- **Control Flow** — `switch` statement, `while` loop, `if-else`
- **Input Handling** — `Scanner` with validation loops
- **String Formatting** — `printf` and `format` for aligned table output
- **Static Methods** — Factory method `Expense.fromCsvLine()`
- **Exception Handling** — `try-catch` for `IOException`, `NumberFormatException`

---

## 📁 Data Storage

Expenses are stored in `expenses.csv` in the same directory where you run the program. Each line follows this format:

```
description,amount,category,date
Lunch at canteen,85.0,Food,31-03-2025
Bus ticket,20.0,Travel,31-03-2025
```

The file is created automatically on the first run. You can back it up or open it in any spreadsheet application.

---

## 🙋 Author

**[Your Full Name]**  
Registration No: [Your Reg No]  
Course: Programming in Java  
Institution: [Your College Name]
