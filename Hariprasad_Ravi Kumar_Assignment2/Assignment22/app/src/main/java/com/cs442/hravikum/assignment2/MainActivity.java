package com.cs442.hravikum.assignment2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ArrayList<String> list = new ArrayList<String>();

    //ArrayAdapter<String> adapter;
    private Button btn;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private EditText et;
    List<ListView> items;


    private List<Hotel> myHotels = new ArrayList<Hotel>();
    private ArrayList<String> strArr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        strArr = new ArrayList<String>();
        btn = (Button)findViewById(R.id.button);
        et = (EditText)findViewById(R.id.myEditText);
        lv = (ListView)findViewById(R.id.listView);
        //lv.setAdapter(adapter);




        populateHotelList();
        populateListView();
        registerClickCallBack();

        Button btn1 = (Button)findViewById(R.id.button);

        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);


        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void populateHotelList()
    {
        myHotels.add(new Hotel("Idli","$10","veg",R.drawable.idli ));
        myHotels.add(new Hotel("Dosa","$20","veg",R.drawable.dosa));
        myHotels.add(new Hotel("FriedRice","$40","veg",R.drawable.friedrice ));
        myHotels.add(new Hotel("Samosa", "$60", "veg", R.drawable.samosa));
        myHotels.add(new Hotel("Curry", "$30", "veg", R.drawable.curry));
    }


    private void populateListView()
    {
        final ArrayAdapter<Hotel> adapter = new MyListAdapter();
        final ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String add1 = et.getText().toString();
                strArr.add(et.getText().toString());
                String message1 = "New Item Added";
                myHotels.add(new Hotel(add1, "$", "veg", R.drawable.image));
                Toast.makeText(MainActivity.this, message1, Toast.LENGTH_SHORT).show();
                // adapter.notifyDataSetChanged();
            }
        });

        //list = getListView();

        lv.setDividerHeight(3);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int arg2, long arg3) {
                Hotel clickedHotel = myHotels.get(arg2);
                String str = lv.getItemAtPosition(arg2).toString();
                myHotels.remove(arg2);
                // Can't manage to remove an item here
                String message7 =  clickedHotel.getName() + " is removed";
                Toast.makeText(MainActivity.this, message7, Toast.LENGTH_SHORT).show();

                list.setAdapter(adapter);


                return false;
            }
        });

        registerClickCallBack();

    }
    //  ListView listView1 = (ListView)findViewById(R.id.listView);



    public class MyListAdapter extends ArrayAdapter<Hotel>
    {
        public MyListAdapter()
        {
            super(MainActivity.this,R.layout.item_view,myHotels);
        }

        @Override
        public View getView(int position,View convertView,ViewGroup parent)
        {
            View itemView = convertView;
            if(itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }
            //find item to work with
            Hotel currentHotel = myHotels.get(position);


            //fill the view
            ImageView imageView = (ImageView)itemView.findViewById(R.id.item_icon);
            imageView.setImageResource(currentHotel.getIconId());

            TextView makeText = (TextView) itemView.findViewById(R.id.item_txtName);
            makeText.setText(currentHotel.getName());

            TextView priceText = (TextView) itemView.findViewById(R.id.item_txtPrice);
            priceText.setText(""+currentHotel.getPrice());

            TextView VegNonText = (TextView) itemView.findViewById(R.id.item_txtVeg_Non);
            VegNonText.setText(currentHotel.getVeg_nonv());



            return itemView;
            //return super.getView(position,convertView,parent);
        }

    }
    private void registerClickCallBack()
    {

        ListView list = (ListView)findViewById(R.id.listView);
        list.setClickable(true);
        // ArrayAdapter<Hotel> adapter = new MyListAdapter();
        ArrayAdapter<Hotel> adapter1 = new ArrayAdapter<Hotel>(this,android.R.layout.simple_list_item_1,myHotels);

        //list.setAdapter(adapter);
        // list.setAdapter(adapter1);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {

                Hotel clickedHotel = myHotels.get(position);
                String message = "You Clicked " + clickedHotel.getName() + "  Idli is served best with green chutney\n It is bland";
                String test = clickedHotel.getName();
                String message1 = "You Clicked " + clickedHotel.getName() + "  Dosa is served with lemon paste \n It is tasty ";
                String message2 = "You Clicked " + clickedHotel.getName() + " FriedRice tastes good with curd \n It is spicy";
                String message3 = "You Clicked " + clickedHotel.getName() + " Samosa is served with red chutney \n It is hot";
                String message4 = "You Clicked " + clickedHotel.getName() + "  Curry is an added dish\n It is awesome";
                String message5 = "You Clicked " + clickedHotel.getName();
                if (test == "Idli") {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }else if (test == "Dosa") {
                    Toast.makeText(MainActivity.this, message1, Toast.LENGTH_SHORT).show();
                }else if(test == "FriedRice"){
                    Toast.makeText(MainActivity.this, message2, Toast.LENGTH_SHORT).show();
                }else if(test == "Samosa") {
                    Toast.makeText(MainActivity.this, message3, Toast.LENGTH_SHORT).show();
                }else if(test == "Curry"){
                    Toast.makeText(MainActivity.this, message4, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, message5, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


