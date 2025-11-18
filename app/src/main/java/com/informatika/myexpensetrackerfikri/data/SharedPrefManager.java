package com.informatika.myexpensetrackerfikri.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.informatika.myexpensetrackerfikri.expense.ExpenseModel;
import com.informatika.myexpensetrackerfikri.utils.Constants;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SharedPrefManager {

    private SharedPreferences prefs;
    private Gson gson = new Gson();

    public SharedPrefManager(Context context) {
        prefs = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveData(ArrayList<ExpenseModel> list) {
        prefs.edit().putString(Constants.KEY_DATA, gson.toJson(list)).apply();
    }

    public ArrayList<ExpenseModel> loadData() {
        String json = prefs.getString(Constants.KEY_DATA, null);
        Type type = new TypeToken<ArrayList<ExpenseModel>>(){}.getType();
        return gson.fromJson(json, type) == null ? new ArrayList<>() : gson.fromJson(json, type);
    }
}
