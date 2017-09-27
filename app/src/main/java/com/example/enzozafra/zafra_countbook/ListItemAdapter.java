package com.example.enzozafra.zafra_countbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListItemAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Counter> list = new ArrayList<Counter>();
    private Context context;
    private static int EDIT_ACTIVITY_REQUEST_CODE = 2;

    public ListItemAdapter(ArrayList<Counter> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(pos).getName());

        //Handle TextView and display string from your list
        TextView listItemCount = (TextView)view.findViewById(R.id.list_item_count);
        listItemCount.setText(list.get(pos).getCurrentValue().toString());

        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = (ImageButton)view.findViewById(R.id.delete_btn);
        ImageButton incBtn = (ImageButton)view.findViewById(R.id.inc_btn);
        ImageButton decBtn = (ImageButton)view.findViewById(R.id.dec_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.remove(pos);
                notifyDataSetChanged();
            }
        });

        incBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.get(pos).incCounter();
                notifyDataSetChanged();
            }
        });

        decBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.get(pos).decCounter();
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
