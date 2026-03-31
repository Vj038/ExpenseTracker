package expensetracker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of expenses.
 * Demonstrates: ArrayList, File I/O (BufferedReader, FileWriter), loops, methods.
 */
public class ExpenseManager {

    private ArrayList<Expense> expenses;
    private static final String FILE_NAME = "expenses.csv";

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
        loadFromFile();
    }

    /**
     * Adds a new expense and saves to file.
     */
    public void addExpense(Expense e) {
        expenses.add(e);
        saveToFile();
        System.out.println("\n✔  Expense added successfully!");
    }

    /**
     * Displays all expenses in a formatted table.
     */
    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("\n  No expenses recorded yet.");
            return;
        }
        printDivider();
        System.out.printf("  %-20s | %-10s | %-15s | %s%n",
                "Date", "Category", "Description", "Amount");
        printDivider();
        for (Expense e : expenses) {
            System.out.println("  " + e);
        }
        printDivider();
        System.out.printf("  %-48s Total: Rs. %.2f%n", "", getTotalAmount());
        printDivider();
    }

    /**
     * Displays expenses filtered by a given category (case-insensitive).
     */
    public void viewByCategory(String category) {
        List<Expense> filtered = new ArrayList<>();
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                filtered.add(e);
            }
        }
        if (filtered.isEmpty()) {
            System.out.println("\n  No expenses found for category: " + category);
            return;
        }
        printDivider();
        System.out.printf("  %-20s | %-10s | %-15s | %s%n",
                "Date", "Category", "Description", "Amount");
        printDivider();
        double total = 0;
        for (Expense e : filtered) {
            System.out.println("  " + e);
            total += e.getAmount();
        }
        printDivider();
        System.out.printf("  %-48s Total: Rs. %.2f%n", "", total);
        printDivider();
    }

    /**
     * Shows a summary: total per category and grand total.
     */
    public void showSummary() {
        if (expenses.isEmpty()) {
            System.out.println("\n  No expenses recorded yet.");
            return;
        }

        // Collect unique categories
        ArrayList<String> categories = new ArrayList<>();
        for (Expense e : expenses) {
            if (!categories.contains(e.getCategory())) {
                categories.add(e.getCategory());
            }
        }

        printDivider();
        System.out.println("  EXPENSE SUMMARY");
        printDivider();
        for (String cat : categories) {
            double catTotal = 0;
            int count = 0;
            for (Expense e : expenses) {
                if (e.getCategory().equalsIgnoreCase(cat)) {
                    catTotal += e.getAmount();
                    count++;
                }
            }
            System.out.printf("  %-20s : Rs. %10.2f  (%d item%s)%n",
                    cat, catTotal, count, count > 1 ? "s" : "");
        }
        printDivider();
        System.out.printf("  %-20s : Rs. %10.2f%n", "GRAND TOTAL", getTotalAmount());
        printDivider();
    }

    /**
     * Deletes the expense at the given 1-based index.
     */
    public boolean deleteExpense(int index) {
        if (index < 1 || index > expenses.size()) {
            System.out.println("\n  Invalid index. Please enter a number between 1 and " + expenses.size());
            return false;
        }
        Expense removed = expenses.remove(index - 1);
        saveToFile();
        System.out.println("\n✔  Deleted: " + removed.getDescription());
        return true;
    }

    public int getCount() {
        return expenses.size();
    }

    // ─── File I/O ────────────────────────────────────────────────────────────

    /**
     * Loads expenses from the CSV data file on startup.
     */
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                Expense e = Expense.fromCsvLine(line);
                if (e != null) {
                    expenses.add(e);
                }
            }
        } catch (IOException ex) {
            System.out.println("  Warning: Could not load saved data — " + ex.getMessage());
        }
    }

    /**
     * Saves all expenses to the CSV data file.
     */
    private void saveToFile() {
        try (FileWriter fw = new FileWriter(FILE_NAME, false)) {
            for (Expense e : expenses) {
                fw.write(e.toCsvLine() + "\n");
            }
        } catch (IOException ex) {
            System.out.println("  Warning: Could not save data — " + ex.getMessage());
        }
    }

    // ─── Helpers ─────────────────────────────────────────────────────────────

    private double getTotalAmount() {
        double total = 0;
        for (Expense e : expenses) total += e.getAmount();
        return total;
    }

    private void printDivider() {
        System.out.println("  " + "-".repeat(65));
    }
}
