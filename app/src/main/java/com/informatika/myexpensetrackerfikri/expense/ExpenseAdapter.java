package com.informatika.myexpensetrackerfikri.expense;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.informatika.myexpensetrackerfikri.R;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private Context context;
    private ArrayList<ExpenseModel> expenseList;

    public ExpenseAdapter(Context context, ArrayList<ExpenseModel> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        ExpenseModel expense = expenseList.get(position);

        holder.tvName.setText(expense.getName());
        holder.tvAmount.setText("Rp " + expense.getAmount());
        holder.tvCategory.setText(expense.getCategory());
        holder.tvDate.setText(expense.getDate());

        // Klik ikon edit -> buka halaman edit
        holder.ivEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditExpenseActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        });

        // Jika ingin item juga bisa diklik untuk edit:
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddEditExpenseActivity.class);
            intent.putExtra("position", position);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvAmount, tvCategory, tvDate;
        ImageView ivEdit;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvDate = itemView.findViewById(R.id.tvDate);
            ivEdit = itemView.findViewById(R.id.ivEdit); // Ikon Edit
        }
    }
}
