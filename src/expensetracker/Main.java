package expensetracker;

import java.util.Scanner;

/**
 * Personal Expense Tracker — Entry Point
 *
 * A command-line application to track daily expenses, view summaries,
 * filter by category, and persist data across sessions using file I/O.
 *
 * Concepts demonstrated:
 *   - Object-Oriented Programming (classes, encapsulation, methods)
 *   - Collections (ArrayList)
 *   - File I/O (BufferedReader, FileWriter)
 *   - Control flow (switch-case, loops, conditionals)
 *   - User input handling (Scanner)
 *   - String formatting (printf)
 *
 * @author  [Your Name]
 * @version 1.0
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ExpenseManager manager = new ExpenseManager();

    public static void main(String[] args) {
        printWelcome();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("  Enter choice: ");

            switch (choice) {
                case 1 -> addExpense();
                case 2 -> manager.viewAllExpenses();
                case 3 -> filterByCategory();
                case 4 -> manager.showSummary();
                case 5 -> deleteExpense();
                case 6 -> {
                    System.out.println("\n  Goodbye! Your data has been saved.\n");
                    running = false;
                }
                default -> System.out.println("\n  Invalid option. Please choose 1-6.");
            }
        }

        scanner.close();
    }

    // ─── Menu actions ────────────────────────────────────────────────────────

    private static void addExpense() {
        System.out.println("\n  ── Add New Expense ──────────────────────────────");

        System.out.print("  Description : ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println("  Description cannot be empty.");
            return;
        }

        double amount = readDouble("  Amount (Rs.): ");
        if (amount <= 0) {
            System.out.println("  Amount must be greater than 0.");
            return;
        }

        System.out.println("  Categories  : Food | Travel | Shopping | Health | Bills | Other");
        System.out.print("  Category    : ");
        String category = scanner.nextLine().trim();
        if (category.isEmpty()) category = "Other";

        System.out.print("  Date (DD-MM-YYYY or press Enter for today): ");
        String date = scanner.nextLine().trim();
        if (date.isEmpty()) {
            date = java.time.LocalDate.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        manager.addExpense(new Expense(description, amount, category, date));
    }

    private static void filterByCategory() {
        System.out.println("\n  Categories  : Food | Travel | Shopping | Health | Bills | Other");
        System.out.print("  Enter category to filter: ");
        String cat = scanner.nextLine().trim();
        manager.viewByCategory(cat);
    }

    private static void deleteExpense() {
        if (manager.getCount() == 0) {
            System.out.println("\n  No expenses to delete.");
            return;
        }
        manager.viewAllExpenses();
        int index = readInt("  Enter the row number to delete (1-" + manager.getCount() + "): ");
        manager.deleteExpense(index);
    }

    // ─── UI helpers ──────────────────────────────────────────────────────────

    private static void printWelcome() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║     PERSONAL EXPENSE TRACKER  v1.0      ║");
        System.out.println("  ║        Built with Java — BYOP            ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println();
    }

    private static void printMenu() {
        System.out.println("\n  ┌─ MENU ──────────────────────────┐");
        System.out.println("  │  1. Add Expense                 │");
        System.out.println("  │  2. View All Expenses           │");
        System.out.println("  │  3. Filter by Category          │");
        System.out.println("  │  4. View Summary                │");
        System.out.println("  │  5. Delete an Expense           │");
        System.out.println("  │  6. Exit                        │");
        System.out.println("  └─────────────────────────────────┘");
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid amount.");
            }
        }
    }
}
