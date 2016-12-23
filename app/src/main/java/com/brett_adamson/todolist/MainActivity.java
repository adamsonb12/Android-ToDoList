package com.brett_adamson.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Brett on 12/21/16.
 *
 * Created with the help of a OneMonth.com online course for Android Development
 *
 */

public class MainActivity extends Activity {

    private StringAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        List<String> items = itemsCache.loadItems(this);

        mAdapter = new StringAdapter(this, R.layout.list_item, R.id.list_item_textview);
        mAdapter.setItems(items);

        ListView listView = (ListView) findViewById(R.id.activity_main_listview);
        listView.setAdapter(mAdapter);

        registerForContextMenu(listView);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.activity_main_fab);
        View.OnClickListener fabOnClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startAddItemActivity();
            }
        };
    floatingActionButton.setOnClickListener(fabOnClickListener);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId() == R.id.activity_main_listview)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_remove_item, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        mAdapter.removeItem(position);
        saveItems();

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.ADD_ITEM_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                String item = data.getStringExtra(Constants.ADD_ITEM_RESULT_KEY);
                mAdapter.addItem(item);
                saveItems();
            }

        }
    }

    private void startAddItemActivity(){
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivityForResult(intent, Constants.ADD_ITEM_REQUEST_CODE);
    }

    private void saveItems()
    {
        List<String> items = mAdapter.getmItems();
        itemsCache.saveItems(this, items);
    }

}
