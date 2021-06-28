package com.example.demokotlin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import com.example.demokotlin.MainActivityJava;
import com.example.demokotlin.R;
import com.example.demokotlin.model.MyNumber;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.Executors;

public class AdapterMainJava extends RecyclerView.Adapter<AdapterMainJava.ViewHolder> {
    Context context;
    ArrayList<MyNumber> list;
    public static final int MAX_SELECTED = 2;
    ArrayList<Integer> listChecked = new ArrayList<>();

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AdapterMainJava(Context context, ArrayList<MyNumber> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public AdapterMainJava.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(context).inflate(R.layout.item_button, parent, false);
        return new ViewHolder(new LinearLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterMainJava.ViewHolder holder, int position) {
        MyNumber myNumber = list.get(position);
        holder.btnNumber.setText(myNumber.getNumber() + "");
        holder.btnNumber.setSelected(myNumber.isChecked());
        holder.btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNumber.setChecked(!myNumber.isChecked());
                if (myNumber.isChecked())
                    listChecked.add(position);
                else {
                    listChecked.remove((Integer) position);
                }
                if (listChecked.size() > MAX_SELECTED) {
                    list.get(listChecked.get(0)).setChecked(false);
                    notifyItemChanged(listChecked.get(0));
                    listChecked.remove(0);
                }
                notifyItemChanged(position);
                onItemClickListener.onItemClick(null, null, position, 0);
            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<Integer> getListChecked() {
        return listChecked;
    }

    public void sort() {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getNumber() > list.get(j).getNumber()) {
                    MyNumber temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatButton btnNumber;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //btnNumber = itemView.findViewById(R.id.btnNumber);
            btnNumber = new AppCompatButton(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsButton = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    ,ViewGroup.LayoutParams.WRAP_CONTENT);
            btnNumber.setBackgroundDrawable(itemView.getResources().getDrawable(R.drawable.custom_button_select));
            btnNumber.setLayoutParams(layoutParamsButton);
            LinearLayout linearLayout = (LinearLayout) itemView;
            int padding = MainActivityJava.dpToPx(10);
            linearLayout.setPadding(padding, padding, padding, padding);
            linearLayout.addView(btnNumber);
        }
    }


}
