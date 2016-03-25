package com.pemt.workteminal;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class MainPage extends Activity {
    private GridView mMpMenuGv;
    private int mMenuItemImgs[] = {R.drawable.menu_wait_gd, R.drawable.gd_search, R.drawable.map_nav, R.drawable.job_instruction, R.drawable.system_set};
    private String mMenuLabels[];
    private LinearLayout mChartLl;
    private String[] mDoughNutItems;

    private final static int TO_BE_DEALED_COLOR = 0xFF4D50C0;
    private final static int TO_BE_UPLOADED_COLOR = 0xFFBD814F;
    private int[] mGdNums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage_bar);
        String menuLabels[] = {getResources().getString(R.string.menu_wait_gd), getResources().getString(R.string.gd_search),
                getResources().getString(R.string.map_nav), getResources().getString(R.string.job_instruction), getResources().getString(R.string.system_set)};
        String doughNutItems[] = {getResources().getString(R.string.wait_done), getResources().getString(R.string.wait_uploaded)};
        mDoughNutItems = doughNutItems;
        mMenuLabels = menuLabels;
        mMpMenuGv = (GridView) findViewById(R.id.mp_menu_gv);
        mChartLl = (LinearLayout) findViewById(R.id.chart_space_ll);
        int[] colors = new int[]{TO_BE_DEALED_COLOR, TO_BE_UPLOADED_COLOR};
        mGdNums = new int[]{90, 10};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(1);
        r.setHighlighted(true);
        View chartView = ChartFactory.getPieChartView(this, buildCategoryDataset("工单",mGdNums), renderer);
        mChartLl.addView(chartView);
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
                convertView = getLayoutInflater().inflate(R.layout.mp_menu_item, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.menu_item_iv);
                TextView textView = (TextView) convertView.findViewById(R.id.menu_item_tv);
                imageView.setImageResource(mMenuItemImgs[position]);
                textView.setText(mMenuLabels[position]);
                return convertView;
            }
        });

    }

    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(18);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setLegendTextSize(15);
        renderer.setPanEnabled(false);
        renderer.setShowLegend(false);
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected CategorySeries buildCategoryDataset(String title, int[] gdNums) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double num : gdNums) {
            series.add(mGdNums[k++]+"", num);
        }
        return series;
    }

}