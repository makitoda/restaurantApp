package com.example.admin.restaurantapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RestaurantList extends AppCompatActivity {

    public final static String EXTRA_RESTAURANT_ID = "com.example.admin.restaurantapp.id";

    // ArrayList of restaurants
    private final ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);

        // Initialize Recycler of restaurantList
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.restaurantList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Define the variables of restaurant's data
//        int[]    icons2   = getResources().getIntArray(R.array.icons);
        int[] icons = {
                R.drawable.icon_osteriasaviovolpe,
                R.drawable.icon_guuotokomaegastown,
                R.drawable.icon_tuccraftkitchen,
                R.drawable.icon_ancorawaterfrontdining,
                R.drawable.icon_thekegsteakhouse,
                R.drawable.icon_nightingale,
                R.drawable.icon_osteriasaviovolpe,
                R.drawable.icon_guuotokomaegastown,
                R.drawable.icon_tuccraftkitchen,
                R.drawable.icon_ancorawaterfrontdining,
                R.drawable.icon_thekegsteakhouse,
                R.drawable.icon_nightingale
        };
        String[] names   = getResources().getStringArray(R.array.restaurants);
        String[] reviews = getResources().getStringArray(R.array.reviews);

        // Set each restaurant's data to ArrayList
        for (int i = 0; i < names.length; i++) {
            Restaurant restaurant = new Restaurant();
            restaurant.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            restaurant.setName(names[i]);
            restaurant.setReview(reviews[i]);
            restaurants.add(restaurant);
        }

        // Initialize adapter
        RecyclerView.Adapter adapter = new RestaurantAdapter(restaurants);

        // Set adapter to ListView
        recyclerView.setAdapter(adapter);
    }

    public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.Holder> {

        private ArrayList<Restaurant> restaurants;

        RestaurantAdapter(ArrayList<Restaurant> restaurants){
            this.restaurants = restaurants;
        }

        class Holder extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView  name;
            TextView  review;
            View      row;

            Holder(View row) {
                super(row);
                this.icon   = (ImageView) row.findViewById(R.id.icon);
                this.name   = (TextView)  row.findViewById(R.id.name);
                this.review = (TextView)  row.findViewById(R.id.review);
                this.row    = row;
            }
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list, parent, false);

            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {

            final int pos = position;
            holder.icon.setImageBitmap(restaurants.get(position).getIcon());
            holder.name.setText(restaurants.get(position).getName());
            holder.review.setText(restaurants.get(position).getReview());

            // Set onClickListener
            holder.row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // Create intent
                    Intent intent = new Intent(RestaurantList.this, RestaurantDetail.class);
                    intent.putExtra(EXTRA_RESTAURANT_ID, pos);

                    // Start Activity
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return restaurants.size();
        }
    }

    public class Restaurant {
        private Bitmap icon;
        private String name;
        private String review;

        public Bitmap getIcon() {
            return icon;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String getReview() {
            return review;
        }

        void setReview(String review) {
            this.review = review;
        }
    }
}
