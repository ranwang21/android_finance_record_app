package com.example.myfinancials.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myfinancials.R;
import com.example.myfinancials.entities.Record;

import java.util.List;

public class RecordListAdapter extends ArrayAdapter<Record> {
    int idLayout;
    public RecordListAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
        idLayout = resource;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Record record = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(idLayout, null);
        TextView date = convertView.findViewById(R.id.singlelist_date);
        date.setText(record.getDate());

        TextView genre = convertView.findViewById(R.id.singlelist_genre);
        if(record.getId_category() != 8){
            genre.setText("Expense");
        } else {
            genre.setText("Income");
        }

        TextView amt = convertView.findViewById(R.id.singlelist_amount);
        amt.setText(Double.toString(record.getAmount()));

        TextView description = convertView.findViewById(R.id.singlelist_description);
        description.setText(record.getDescription());

        TextView category = convertView.findViewById(R.id.singlelist_category);
        switch (record.getId_category()){
            case 1:
                category.setText("Charges");
                break;
            case 2:
                category.setText("Entertainment");
                break;
            case 3:
                category.setText("Food");
                break;
            case 4:
                category.setText("Clothes");
                break;
            case 5:
                category.setText("Installment");
                break;
            case 6:
                category.setText("Miscellaneous");
                break;
            case 7:
                category.setText("Transport");
                break;
            case 8:
                category.setText("Income");
                break;
        }



        return convertView;
    }
}
