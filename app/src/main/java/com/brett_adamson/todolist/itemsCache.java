package com.brett_adamson.todolist;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences.Editor;

/**
 * Created by Brett on 12/22/16.
 */

public class itemsCache {

    public static List<String> loadItems(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFERENCES_CONTAINER, Context.MODE_PRIVATE);
        String json = prefs.getString(Constants.ITEMS_CACHE_KEY, null);

        if (json == null)
        {
            return new ArrayList<String>();
        }

        Gson gson = new Gson();

        return gson.fromJson(json, new TypeToken<ArrayList<String>>() {}.getType());

    }

    public static void saveItems(Context context, List<String> items)
    {
        if (items == null)
        {
            itemsCache.saveItems(context, new ArrayList<String>());
            return;
        }

        Gson gson = new Gson();
        String json = gson.toJson(items);

        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFERENCES_CONTAINER, Context.MODE_PRIVATE);
        Editor editor = prefs.edit();
        editor.putString(Constants.ITEMS_CACHE_KEY, json);
        editor.apply();
    }
}
