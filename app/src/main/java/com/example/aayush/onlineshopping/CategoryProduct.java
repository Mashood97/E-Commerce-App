package com.example.aayush.onlineshopping;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import dbs.DAOs;
import dbs.Databases;
import misc.Adapters;
import misc.Cart;
import misc.Item;

public class CategoryProduct extends AppCompatActivity {
    private Bundle extras;

    private final Cart cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);

        extras = getIntent().getExtras();

        assert extras != null;
        int categoryFlag = extras.getInt("categoryFlag");

        RecyclerView rv = findViewById(R.id.rv_item_display_layout);

        DBThread dbThread = new DBThread(categoryFlag, getApplicationContext(), cart, rv);
        dbThread.start();
    }
}

class DBThread extends Thread{
    private int categoryFlag;
    private Context current;
    private Cart cart;
    private RecyclerView rv;

    DBThread(int categoryFlag, Context current, Cart cart, RecyclerView rv){
        this.categoryFlag = categoryFlag;
        this.current = current;
        this.cart = cart;
        this.rv = rv;
    }

    @Override
    public void run() {
        ArrayList<Item> items_list = new ArrayList<>();
        Cursor c;
        Item item;

        Databases.ProductDatabase db =
                Databases.ProductDatabase.getProductDatabase(current);
        DAOs.ProductDAO dao;
        dao = db.productDAO();

        // TODO: Figure out images
        switch(categoryFlag){
            case 1:
                c = dao.getProductsByCategory("Clothing");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                ((CategoryProduct)current).getSupportActionBar().setTitle("Clothing");
                break;
            case 2:
                c = dao.getProductsByCategory("Books");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                ((CategoryProduct)current).getSupportActionBar().setTitle("Books");
                break;
            case 3:
                c = dao.getProductsByCategory("Appliances");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                ((CategoryProduct)current).getSupportActionBar().setTitle("Appliances");
                break;
            case 4:
                c = dao.getProductsByCategory("Electronics");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                ((CategoryProduct)current).getSupportActionBar().setTitle("Electronics");
                break;
            case 5:
                c = dao.getProductsByCategory("Furniture");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                ((CategoryProduct)current).getSupportActionBar().setTitle("Furniture");
                break;
            case 6:
                c = dao.getProductsByCategory("Food");
                if(c.moveToFirst()){
                    while(!c.isAfterLast()){
                        String name = c.getString(c.getColumnIndex("name"));
                        String category = c.getString(c.getColumnIndex("category"));
                        String size = c.getString(c.getColumnIndex("size"));
                        int qty = c.getInt(c.getColumnIndex("quantity"));
                        float cost = c.getFloat(c.getColumnIndex("cost"));
                        item = new Item(name, category, cost, qty, Integer.parseInt(size), 0);
                        items_list.add(item);
                        c.moveToNext();
                    }
                }
                c.close();
                ((CategoryProduct)current).getSupportActionBar().setTitle("Food");
                break;
            default:
                Log.e("WTF", "How did this happen?!?!");
        }


        Adapters.RecyclerViewAdapter myAdapter = new Adapters.RecyclerViewAdapter(current,
                items_list, cart);
        rv.setLayoutManager(new GridLayoutManager(current, 3));
        rv.setAdapter(myAdapter);
    }
}
