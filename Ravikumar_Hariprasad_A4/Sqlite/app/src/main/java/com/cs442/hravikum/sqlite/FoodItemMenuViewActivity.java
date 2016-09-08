package com.cs442.hravikum.sqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Hari on 2/13/2016.
 */
public class FoodItemMenuViewActivity extends Activity {
    ListView listView;
    MyCustomAdapter dataAdapter = null,dataAdapter2=null;
    //public static int RESULT_OK=-1;
    Boolean checkLong = false;
    static double total_bill=0;
    // static boolean flag=false;
    DBHelper mydb;
    //  final ListView listView=null;
    final ArrayList<FoodMenuItem> FoodItemList = new ArrayList<FoodMenuItem>();
    public final static String EXTRA_MESSAGE = "com.cs442.Hari.MESSAGE";
    Integer[] imgid={
            R.drawable.butterchicken,
            R.drawable.chickenkabab,
            R.drawable.gheerice,
            R.drawable.gobi,
            R.drawable.gulabjamoon
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mydb = new DBHelper(this);
        setContentView(R.layout.main);


        TextView text=(TextView)findViewById(R.id.TotalBill);
        text.setText("Total Cost:$" + total_bill);
        //Generate list View from ArrayList
        displayListView();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        Log.d("onActivityResult","inside");
        Log.d("requestcode", "value");
        for(FoodMenuItem S:FoodItemList)
        {
            if(S.isSelected())
            {
                S.selected=false;
            }
        }
        dataAdapter.notifyDataSetChanged();
        if (requestCode == 1) {
            // Make sure the request was successful
            Log.d("requestcode","1");
            if (resultCode == RESULT_OK) {
                Log.d("resultok","1");
                try {
                    String sb= data.getStringExtra("Bill");
                    String[] dataSet=sb.split("[|]");
            /*for(String s:data)
            {
                Log.d("data:",s);
            }*/
                    total_bill+=Double.parseDouble(dataSet[2]);
                    Log.d("totalbill", "hi");


                    //RelativeLayout relative=new RelativeLayout(getApplicationContext(),R.layout.content_main,text);
                    mydb.insertOrder(dataSet[0], dataSet[1], dataSet[2]);
                    TextView text=(TextView)findViewById(R.id.TotalBill);
                    text.setText("Total Cost:$" + total_bill);
                    //Generate list View from ArrayList
                    //displayListView();


                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void order(View view)
    {
        //Toast.makeText(getApplicationContext(),"order function",Toast.LENGTH_LONG).show();
        ArrayList<FoodMenuItem> selectList=new ArrayList<FoodMenuItem>();
        for(FoodMenuItem s:FoodItemList)
        {
            if(s.isSelected())
            {
                selectList.add(s);
            }
        }
        if((selectList.size())>0) {
            Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
            //String message = "Hi world!";
            intent.putExtra("ItemList", selectList);
            startActivityForResult(intent,1);
        }
        else
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Please select Items");
            dlgAlert.setTitle("Help");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }
    public void OrderHistory(View v)
    {
        Intent intent = new Intent(getApplicationContext(), OrderHistoryActivity.class);


        startActivity(intent);
    }

    public void reset(View view)
    {
        //Toast.makeText(getApplicationContext(),"reset function",Toast.LENGTH_LONG).show();
        ArrayList<FoodMenuItem> selectList=new ArrayList<FoodMenuItem>();
        for(FoodMenuItem s:FoodItemList)
        {
            if(s.isSelected())
            {
                selectList.add(s);
            }
        }
        if((selectList.size())>0) {
            for(FoodMenuItem s:selectList)
            {
                if(s.isSelected())
                {
                    s.selected=false;
                }
            }
        }
        else
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Please select proper Items");
            dlgAlert.setTitle("Take Help");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
        //listView.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
    }
    private void displayListView() {

        //Array list of countries

        FoodMenuItem Item = new FoodMenuItem("Pizza",5,"Veg","Tomato, Cheese, Onion");
        FoodItemList.add(Item);
        Item = new FoodMenuItem("Sandwich",7,"Veg","Cheese, Spicy, Potato, Tomato");
        FoodItemList.add(Item);
        Item = new FoodMenuItem("Gherra Rice",8,"Veg","Indian rice, Groundnut, Spicy vegitable");
        FoodItemList.add(Item);
        Item = new FoodMenuItem("Gobi Munchury",15,"Veg","Chilli, Tomato, Spicy, Gobi");
        FoodItemList.add(Item);
        Item = new FoodMenuItem("Gulabjammon",10,"Veg","Indian sweet with taste and protiens");
        FoodItemList.add(Item);

        // FoodItemList.addAll(FoodItemList);
        //create an ArrayAdaptar from the String Array
        dataAdapter = new MyCustomAdapter(this,
                R.layout.fooditem_info, FoodItemList);
        listView =(ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                FoodMenuItem Item = (FoodMenuItem) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),
                        "Food Item: " + Item.getName() + "\nDescription:" + Item.getDescription(),
                        Toast.LENGTH_LONG).show();
            }
        });


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
            ImageView image;
            TextView Type;
            TextView name;
            TextView Price;
            CheckBox Item;



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
                convertView = vi.inflate(R.layout.fooditem_info, null);

                holder = new ViewHolder();
                holder.image=(ImageView)convertView.findViewById(R.id.icon);
                holder.Item = (CheckBox) convertView.findViewById(R.id.checkBox1);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.Price = (TextView) convertView.findViewById(R.id.Price);
                holder.Type=(TextView) convertView.findViewById(R.id.Type);

                /*holder.Price = (TextView) convertView.findViewById(R.id.Price);
                holder.Type = (TextView) convertView.findViewById(R.id.Type);*/
                convertView.setTag(holder);

                holder.Item.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        FoodMenuItem Item = (FoodMenuItem) cb.getTag();
                        if(Item.isSelected())
                        {
                            Item.selected=false;
                        }
                        else
                        {
                            Item.selected=true;
                        }
                       /* Toast.makeText(getApplicationContext(),
                                "Selected  Item: " + Item.getName(),
                                Toast.LENGTH_LONG).show();
                        Item.setSelected(cb.isChecked());*/
                    }
                });

            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            //listView.setAdapter(dataAdapter);
            int i=1;
            FoodMenuItem Item = FoodItemList.get(position);
            holder.image.setImageResource(imgid[position]);
            holder.name.setText(" (" + Item.getName() + ")");
            holder.name.setText(Item.getName());
            holder.Price.setText("(" + Item.getPrice() + ")");
            holder.Price.setText("$" + Item.getPrice());
            holder.Type.setText("("+Item.getType() +")");
            holder.Type.setText(Item.getType());
            holder.Item.setChecked(Item.isSelected());
            holder.Item.setTag(Item);



            return convertView;

        }

    }

}
