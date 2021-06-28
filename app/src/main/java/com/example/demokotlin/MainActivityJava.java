package com.example.demokotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.demokotlin.adapter.AdapterMainJava;
import com.example.demokotlin.model.MyNumber;
import com.example.demokotlin.model.SimpleRandom;

import java.util.ArrayList;

public class MainActivityJava extends AppCompatActivity {
    AdapterMainJava adapterMainJava;
    ArrayList<MyNumber> list = new ArrayList<>();
    SimpleRandom random;
    LinearLayout linearLayoutRoot;
    RecyclerView rvMain;
    TextView tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLayout();
        setContentView(linearLayoutRoot);

        for (int i = 1; i <= 18; i++) {
            list.add(new MyNumber(i));
        }

        myShuffle(list, random);

        adapterMainJava = new AdapterMainJava(this, list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rvMain.setAdapter(adapterMainJava);
        rvMain.setLayoutManager(layoutManager);

        adapterMainJava.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int sizeListChecked = adapterMainJava.getListChecked().size();
                if (sizeListChecked == AdapterMainJava.MAX_SELECTED) {
                    int total = 0;
                    for (int i = 0; i < sizeListChecked; i++) {
                        total = total + list.get(adapterMainJava.getListChecked().get(i)).getNumber();
                    }
                    tvTotal.setText("Total:" + total);
                } else {
                    tvTotal.setText("");
                }
            }
        });
    }

    private void initLayout() {
        int _10dp = dpToPx(10);
        int _20dp = dpToPx(20);
        int _40dp = dpToPx(40);
        linearLayoutRoot = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParamsRoot =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayoutRoot.setGravity(Gravity.CENTER);
        linearLayoutRoot.setOrientation(LinearLayout.VERTICAL);
        linearLayoutRoot.setLayoutParams(layoutParamsRoot);

        rvMain = new RecyclerView(this);
        LinearLayout.LayoutParams layoutParamsRv =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
        rvMain.setPadding(_10dp, 0, _10dp, 0);
        rvMain.setLayoutParams(layoutParamsRv);


        LinearLayout linearLayoutButton = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParamsLnButton =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayoutButton.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutButton.setPadding(_20dp, _40dp, _20dp, _40dp);
        linearLayoutButton.setGravity(Gravity.CENTER);
        linearLayoutButton.setLayoutParams(layoutParamsLnButton);

        Button btnSort = new Button(this);
        LinearLayout.LayoutParams layoutParamsBtnSort = new LinearLayout.LayoutParams(0, _40dp);
        layoutParamsBtnSort.weight = 1f;
        btnSort.setBackground(getResources().getDrawable(R.drawable.customboder_buttonsort));
        btnSort.setText("Sap Xep");
        btnSort.setTransformationMethod(null);
        btnSort.setTextColor(getResources().getColor(R.color.white));
        btnSort.setLayoutParams(layoutParamsBtnSort);
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sizeListChecked = adapterMainJava.getListChecked().size();
                if (sizeListChecked > 0) {
                    for (int i = 0; i < sizeListChecked; i++) {
                        int posTemp = adapterMainJava.getListChecked().get(i);
                        list.get(posTemp).setChecked(false);
                        adapterMainJava.notifyItemChanged(posTemp);
                    }
                    adapterMainJava.getListChecked().clear();
                    tvTotal.setText("");
                }
                adapterMainJava.sort();
                adapterMainJava.notifyDataSetChanged();
            }
        });

        AppCompatButton btnRandom = new AppCompatButton(this);
        LinearLayout.LayoutParams layoutParamsBtnRand = new LinearLayout.LayoutParams(0, _40dp);
        layoutParamsBtnRand.weight = 1f;
        layoutParamsBtnRand.setMargins(_40dp, 0, 0, 0);
        btnRandom.setPadding(0, 0, 0, 0);
        btnRandom.setBackground(getResources().getDrawable(R.drawable.customboder_buttonrand));
        btnRandom.setText("Ngẫu\nnhien");
        btnRandom.setTransformationMethod(null);
        btnRandom.setTextColor(getResources().getColor(R.color.white));
        btnRandom.setLayoutParams(layoutParamsBtnRand);
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sizeListChecked = adapterMainJava.getListChecked().size();
                if (sizeListChecked > 0) {
                    for (int i = 0; i < sizeListChecked; i++) {
                        int posTemp = adapterMainJava.getListChecked().get(i);
                        list.get(posTemp).setChecked(false);
                        adapterMainJava.notifyItemChanged(posTemp);
                    }
                    adapterMainJava.getListChecked().clear();
                    tvTotal.setText("");
                }
                myShuffle(list, random);
                adapterMainJava.notifyDataSetChanged();
            }
        });

        linearLayoutButton.addView(btnSort);
        linearLayoutButton.addView(btnRandom);

        tvTotal = new TextView(this);
        LinearLayout.LayoutParams layoutParamsLnTvTotal =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTotal.setTextColor(getResources().getColor(R.color.black));
        tvTotal.setTextSize(spToPx(10, this));
        tvTotal.setTypeface(Typeface.DEFAULT_BOLD);
        tvTotal.setLayoutParams(layoutParamsLnTvTotal);

        linearLayoutRoot.addView(rvMain);
        linearLayoutRoot.addView(linearLayoutButton);
        linearLayoutRoot.addView(tvTotal);

    }

    public static void myShuffle(ArrayList<MyNumber> array, SimpleRandom random) {
        if (random == null) random = new SimpleRandom();
        int count = array.size();
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    private static void swap(ArrayList<MyNumber> array, int i, int j) {
        MyNumber temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }
}