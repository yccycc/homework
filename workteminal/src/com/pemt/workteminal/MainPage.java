package com.pemt.workteminal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.pemt.workteminal.component.BarFrag;
import com.pemt.workteminal.component.PieFrag;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends FragmentActivity {
    private GridView mMpMenuGv;
    private int mMenuItemImgs[] = {R.drawable.menu_wait_gd, R.drawable.gd_search, R.drawable.map_nav, R.drawable.job_instruction, R.drawable.system_set};
    private String mMenuLabels[];
    private ViewPager mMpVp;
    private List mFrgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        String menuLabels[] = {getResources().getString(R.string.menu_wait_gd), getResources().getString(R.string.gd_search),
                getResources().getString(R.string.map_nav), getResources().getString(R.string.job_instruction), getResources().getString(R.string.system_set)};
        mMenuLabels = menuLabels;
        mMpMenuGv = (GridView) findViewById(R.id.mp_menu_gv);
        mMpMenuGv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //
                ViewHolder holder;
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.mp_menu_item, null);
                    holder = new ViewHolder();
                    holder.imageView = (ImageView) convertView.findViewById(R.id.menu_item_iv);
                    holder.textView = (TextView) convertView.findViewById(R.id.menu_item_tv);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.imageView.setImageResource(mMenuItemImgs[position]);
                holder.textView.setText(mMenuLabels[position]);
                return convertView;
            }
        });
        mMpVp = (ViewPager) findViewById(R.id.mp_vp);
        mFrgList = new ArrayList<Fragment>();
        mFrgList.add(new PieFrag());
        mFrgList.add(new BarFrag());
        mMpVp.setAdapter(new ChartPagerAdapter(MainPage.this.getSupportFragmentManager(), mFrgList));

    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }

    class ChartPagerAdapter extends FragmentPagerAdapter {

        public ChartPagerAdapter(FragmentManager fm, List<Fragment> fragList) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return (Fragment) mFrgList.get(i);
        }

        @Override
        public int getCount() {
            return mFrgList.size();
        }
    }
}