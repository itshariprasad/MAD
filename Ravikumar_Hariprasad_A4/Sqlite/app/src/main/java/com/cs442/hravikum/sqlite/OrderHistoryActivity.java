package com.cs442.hravikum.sqlite;

import android.content.Context;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    ListView listView;
    MyCustomAdapter dataAdapter = null;
    DBHelper mydb;
    public  static  ArrayList<Order> OrderHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        mydb=new DBHelper(this);
        OrderHistory=new ArrayList<Order>();
        OrderHistory=mydb.getAllOrderHistory();

        dataAdapter = new MyCustomAdapter(this,
                R.layout.order_history_info, OrderHistory);
        listView =(ListView) findViewById(R.id.listViewOrderHistorycontent);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        Button btn=(Button)findViewById(R.id.Back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private class MyCustomAdapter extends ArrayAdapter<Order> {

        private ArrayList<Order> OrderHistory;

        public MyCustomAdapter(Context context, int textViewResourceId,
                               ArrayList<Order> OrderHistory) {
            super(context, textViewResourceId, OrderHistory);
            this.OrderHistory = new ArrayList<Order>();
            this.OrderHistory.addAll(OrderHistory);
        }

        private class ViewHolder {
            TextView OrderId;
            TextView TimeStamp;
            TextView OrderName;
            TextView OrderPrice;
        }

        public  void removeitem(int position){
            OrderHistory.remove(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            Log.v("ConvertView", String.valueOf(position));

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.order_history_info, null);

                holder = new ViewHolder();
                // holder.Item = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.OrderId = (TextView) convertView.findViewById(R.id.OrderId);
                holder.TimeStamp = (TextView) convertView.findViewById(R.id.TimeStamp);
                holder.OrderName=(TextView) convertView.findViewById(R.id.OrderName);
                holder.OrderPrice=(TextView) convertView.findViewById(R.id.OrderPrice);
                convertView.setTag(holder);


            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            //listView.setAdapter(dataAdapter);
            Order Item = OrderHistory.get(position);
            holder.OrderId.setText(" (" + Item.getId() + ")");
            holder.OrderId.setText(Item.getId().toString());
            holder.TimeStamp.setText("(" + Item.getTimestamp() + ")");
            holder.TimeStamp.setText(Item.getTimestamp().toString());
            holder.OrderName.setText("(" + Item.getName() + ")");
            holder.OrderName.setText(Item.getName());
            holder.OrderPrice.setText("(" + Item.getPrice() + ")");
            holder.OrderPrice.setText("$ "+Item.getPrice().toString());
            return convertView;

        }

    }

}
