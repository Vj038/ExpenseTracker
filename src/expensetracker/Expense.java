package expensetracker;

/**
 * Represents a single expense entry.
 * Demonstrates: OOP, encapsulation, constructors, toString override.
 */
public class Expense {

    private String description;
    private double amount;
    private String category;
    private String date;

    // Constructor
    public Expense(String description, double amount, String category, String date) {
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getters
    public String getDescription() { return description; }
    public double getAmount()      { return amount; }
    public String getCategory()    { return category; }
    public String getDate()        { return date; }

    /**
     * Converts this expense to a CSV line for file storage.
     */
    public String toCsvLine() {
        return description + "," + amount + "," + category + "," + date;
    }

    /**
     * Creates an Expense object from a CSV line read from file.
     */
    public static Expense fromCsvLine(String line) {
        String[] parts = line.split(",", 4);
        if (parts.length < 4) return null;
        try {
            String desc     = parts[0].trim();
            double amount   = Double.parseDouble(parts[1].trim());
            String category = parts[2].trim();
            String date     = parts[3].trim();
            return new Expense(desc, amount, category, date);
        } catch (NumberFormatException e) {
            return null; // skip malformed lines
        }
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-10s | %-15s | Rs. %.2f",
                date, category, description, amount);
    }
}
