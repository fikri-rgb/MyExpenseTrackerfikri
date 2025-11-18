package com.informatika.myexpensetrackerfikri;



import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.informatika.myexpensetrackerfikri.expense.ExpenseListActivity;

public class MainActivity extends AppCompatActivity {

    Button btnOpenExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenExpense = findViewById(R.id.btnOpenExpense);

        btnOpenExpense.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ExpenseListActivity.class))
        );
    }
}
