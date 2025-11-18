package com.informatika.myexpensetrackerfikri.expense;

public class ExpenseModel {

    private String name;
    private int amount;
    private String category;
    private String date;

    public ExpenseModel(String name, int amount, String category, String date) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    // Getter
    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
