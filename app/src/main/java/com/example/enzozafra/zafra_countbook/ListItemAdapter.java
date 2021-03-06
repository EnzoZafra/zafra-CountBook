package com.example.enzozafra.zafra_countbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * This Adapter acts as a bridge between a View and the underlying data for that view.
 * This adapter will create a View for each item in the dataset.
 */
public class ListItemAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Counter> list = new ArrayList<Counter>();
    private Context context;

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

        //Handle TextView and display name from list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(pos).getName());

        //Handle TextView and display current value from list
        TextView listItemCount = (TextView) view.findViewById(R.id.list_item_count);
        listItemCount.setText(list.get(pos).getCurrentValue().toString());

        //Handle TextView and display date from list
        TextView listItemDate = (TextView) view.findViewById(R.id.list_item_date);
        listItemDate.setText(Helpers.setDateFormat(list.get(pos).getDate(), "yyyy-MM-dd"));

        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = (ImageButton) view.findViewById(R.id.delete_btn);
        ImageButton incBtn = (ImageButton) view.findViewById(R.id.inc_btn);
        ImageButton decBtn = (ImageButton) view.findViewById(R.id.dec_btn);
        ImageButton resetBtn = (ImageButton) view.findViewById(R.id.reset_btn);

        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Counter selected = list.get(pos);
                selected.setCurrentValue(selected.getInitialValue());
                notifyDataSetChanged();
                ((MainActivity) context).saveInFile();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.remove(pos);
                notifyDataSetChanged();
                ((MainActivity) context).saveInFile();

            }
        });

        incBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                list.get(pos).incCounter();
                notifyDataSetChanged();
                ((MainActivity) context).saveInFile();
            }
        });

        decBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Counter selected = list.get(pos);
                if (selected.getCurrentValue() != 0) {
                    list.get(pos).decCounter();
                    notifyDataSetChanged();
                    ((MainActivity) context).saveInFile();
                }
            }
        });

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        ((MainActivity) context).updateSummary();
        super.notifyDataSetChanged();
    }
}
