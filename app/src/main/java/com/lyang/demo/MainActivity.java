package com.lyang.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        initListView();
    }

    private void initListView() {
        ListView mListView = (ListView) findViewById(R.id.FuelList);
        List<FuelInfo> petrolList = new ArrayList<>();
        petrolList.add(new FuelInfo(
                "Ranipauwa Petrol Pump",
                false
        ));
        petrolList.add(new FuelInfo(
                "Kalika Petrol Pump",
                true
        ));
        FuelAdapter mAdapter = new FuelAdapter(this, R.layout.card_list, petrolList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                break;
            case R.id.page_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
        }
        return true;
    }

    private class FuelInfo {
        public String mName;
        public boolean isAvailable;

        public FuelInfo(String name, boolean isA) {
            mName = name;
            isAvailable = isA;
        }
    }

    private class FuelAdapter extends ArrayAdapter<FuelInfo> {

        private List<FuelInfo> mList;
        private int mResource;

        public FuelAdapter(Context context, int resource, List<FuelInfo> objects) {
            super(context, resource, objects);
            mList = objects;
            mResource = resource;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public FuelInfo getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getItemViewType(int position) {
            return 1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FuelHolder mHolder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(mResource, parent, false);
                mHolder = new FuelHolder(convertView);
                convertView.setTag(mHolder);
            } else {
                mHolder = (FuelHolder) convertView.getTag();
            }
            FuelInfo item = getItem(position);
            mHolder.mImage.setImageDrawable(item.isAvailable ? getResources().getDrawable(R.drawable.ic_available) : getResources().getDrawable(R.drawable.ic_not_available));
            mHolder.mName.setText(item.mName);
            return convertView;
        }
    }

    public class FuelHolder {
        public TextView mName;
        public ImageView mImage;

        public FuelHolder(View v) {
            if (v != null) {
                mName = (TextView) v.findViewById(R.id.txtName);
                mImage = (ImageView) v.findViewById(R.id.imgAvailability);
            }
        }
    }

}
