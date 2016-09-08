package com.cs442.hravikum.sqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class OrderActivity extends AppCompatActivity {

    ListView listView;
    MyCustomAdapter dataAdapter = null;
    double bill=0;
    // public static int SETRESULT=-1;
    public static ArrayList<FoodMenuItem> selectList=new ArrayList<FoodMenuItem>();
    // public static Integer id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        Intent intent = getIntent();
        selectList=(ArrayList<FoodMenuItem>) getIntent().getSerializableExtra("ItemList");


        for(FoodMenuItem S:selectList)
        {
            bill+=S.Price;
        }
        TextView textView = (TextView) findViewById(R.id.bill);
        textView.setText("Total cost of order:$"+bill);
        dataAdapter = new MyCustomAdapter(this,
                R.layout.content_main, selectList);
        listView =(ListView) findViewById(R.id.listViewOrdercontent);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


    }
    public void Cancel(View view)
    {

        finish();

    }
    public void Confirm(View view)
    {
        Intent intent = this.getIntent();
        StringBuilder sb=new StringBuilder();
        Calendar calendar = Calendar.getInstance();

        sb.append(new java.sql.Timestamp(calendar.getTime().getTime()) + "|");
        for(FoodMenuItem f:selectList)
        {
            sb.append(f.getName()+",");
        }
        sb.deleteCharAt((sb.length() - 1));
        sb.append("|" + bill);

        intent.putExtra("Bill", sb.toString());
        // startActivity(intent);
        setResult(RESULT_OK,intent);
        finish();
    }
    private class MyCustomAdapter extends ArrayAdapter<FoodMenuItem> {

        private ArrayList<FoodMenuItem> FoodItemList;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<FoodMenuItem> FoodItemList) {
            super(context, textViewResourceId, FoodItemList);
            this.FoodItemList = new ArrayList<FoodMenuItem>();
            this.FoodItemList.addAll(FoodItemList);
        }

        private class ViewHolder {
            TextView name;
            TextView Price;
            TextView Type;
        }

        public  void removeitem(int position){
            FoodItemList.remove(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.content_order, null);

                holder = new ViewHolder();

                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.Price = (TextView) convertView.findViewById(R.id.Price);
                holder.Type=(TextView) convertView.findViewById(R.id.Type);

                convertView.setTag(holder);


            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            FoodMenuItem Item = FoodItemList.get(position);
            holder.name.setText(" (" + Item.getName() +")");
            holder.name.setText(Item.getName());
            holder.Price.setText("(" + Item.getPrice() + ")");
            holder.Price.setText("$" + Item.getPrice());
            holder.Type.setText("(" + Item.getType() + ")");
            holder.Type.setText(Item.getType());
            return convertView;

        }

    }
}
