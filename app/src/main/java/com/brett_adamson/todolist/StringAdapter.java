package com.brett_adamson.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brett on 12/21/16.
 */

public class StringAdapter extends ArrayAdapter<String> {

    private int mResource;
    private int mTextViewResource;

    private LayoutInflater mLayoutInflater;
    private List<String> mItems = new ArrayList<>();

    public StringAdapter(Context context, int resource, int textViewResource)
    {
        super(context, resource, textViewResource);

        mResource = resource;
        mTextViewResource = textViewResource;

        mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = mLayoutInflater.inflate(mResource, null);
            viewHolder = new ViewHolder();
            viewHolder.itemTextView = (TextView) convertView.findViewById(mTextViewResource);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final String item = mItems.get(position);
        viewHolder.itemTextView.setText(item);

        return convertView;
    }

    public void setItems(List<String> items)
    {
        mItems = items;
        notifyDataSetChanged();
    }

    public List<String> getmItems()
    {
        return mItems;
    }

    public void addItem(String item)
    {
        if (item == null)
        {
            return;
        }
        mItems.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position)
    {
        mItems.remove(position);
                notifyDataSetChanged();
    }

    static class ViewHolder
    {
        TextView itemTextView;
    }
}
