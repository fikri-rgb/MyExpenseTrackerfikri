package com.informatika.myexpensetrackerfikri.expense;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.informatika.myexpensetrackerfikri.R;
import com.informatika.myexpensetrackerfikri.data.SharedPrefManager;

import java.util.ArrayList;

public class ExpenseListActivity extends AppCompatActivity {

    private RecyclerView rvExpenses;
    private TextView tvTotal;
    private Button btnAddExpense;

    private SharedPrefManager sharedPrefManager;
    private ArrayList<ExpenseModel> expenseList;
    private ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);

        rvExpenses = findViewById(R.id.rvExpense);
        tvTotal = findViewById(R.id.tvTotal);
        btnAddExpense = findViewById(R.id.btnAddExpense);

        rvExpenses.setLayoutManager(new LinearLayoutManager(this));
        sharedPrefManager = new SharedPrefManager(this);

        // Tombol untuk tambah data
        btnAddExpense.setOnClickListener(v ->
                startActivity(new Intent(ExpenseListActivity.this, AddEditExpenseActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadExpenses();
    }

    private void loadExpenses() {
        expenseList = sharedPrefManager.loadData();
        expenseAdapter = new ExpenseAdapter(this, expenseList);
        rvExpenses.setAdapter(expenseAdapter);

        int total = 0;
        for (ExpenseModel e : expenseList) {
            total += e.getAmount();
        }

        tvTotal.setText("Total Pengeluaran: Rp " + total);
    }
}
