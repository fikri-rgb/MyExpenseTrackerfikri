package com.informatika.myexpensetrackerfikri.expense;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.informatika.myexpensetrackerfikri.R;
import com.informatika.myexpensetrackerfikri.data.SharedPrefManager;

import java.util.ArrayList;

public class AddEditExpenseActivity extends AppCompatActivity {

    EditText etName, etAmount, etDate;
    Spinner spCategory;
    Button btnSave, btnDelete;
    SharedPrefManager prefManager;
    ArrayList<ExpenseModel> list;
    int position = -1;  // posisi item saat edit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_expense);

        // Inisialisasi komponen
        etName = findViewById(R.id.etName);
        etAmount = findViewById(R.id.etAmount);
        spCategory = findViewById(R.id.spCategory);
        etDate = findViewById(R.id.etDate);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        // Setup Spinner kategori
        String[] categories = {"Makanan", "Transportasi", "Belanja", "Hiburan", "Pendidikan", "Tagihan", "Lainnya"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapter);


        // SharedPreferences Manager
        prefManager = new SharedPrefManager(this);
        list = prefManager.loadData();

        // Cek apakah activity ini untuk Edit atau Tambah
        position = getIntent().getIntExtra("position", -1);

        if (position != -1) {
            // Mode Edit: isi data yang sudah ada
            ExpenseModel existingExpense = list.get(position);
            etName.setText(existingExpense.getName());
            etAmount.setText(String.valueOf(existingExpense.getAmount()));
            etDate.setText(existingExpense.getDate());

            // Set spinner category ke posisi kategori yang sesuai
            int spinnerPosition = adapter.getPosition(existingExpense.getCategory());
            spCategory.setSelection(spinnerPosition);

            btnDelete.setVisibility(View.VISIBLE);
            btnSave.setText(" Simpan Perubahan");
        } else {
            // Mode Tambah: kosongkan form
            btnDelete.setVisibility(View.GONE);
            btnSave.setText(" Tambah ");
        }

        // Tombol simpan
        btnSave.setOnClickListener(v -> saveData());

        // Tombol hapus
        btnDelete.setOnClickListener(v -> deleteData());
    }

    private void saveData() {
        String name = etName.getText().toString();
        int amount = Integer.parseInt(etAmount.getText().toString());
        String category = spCategory.getSelectedItem().toString();
        String date = etDate.getText().toString();

        ExpenseModel newExpense = new ExpenseModel(name, amount, category, date);

        if (position == -1) {
            // Tambah baru
            list.add(newExpense);
        } else {
            // Edit item
            list.set(position, newExpense);
        }

        prefManager.saveData(list);  // Simpan ke SharedPreferences
        finish();  // Tutup activity
    }

    private void deleteData() {
        if (position != -1) {
            list.remove(position);
            prefManager.saveData(list);
        }
        finish();
    }
}
