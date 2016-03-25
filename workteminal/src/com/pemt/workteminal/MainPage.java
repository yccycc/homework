package com.pemt.workteminal;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        String menuLabels[] = {getResources().getString(R.string.menu_wait_gd), getResources().getString(R.string.gd_search),
                getResources().getString(R.string.map_nav), getResources().getString(R.string.job_instruction), getResources().getString(R.string.system_set)};
        String doughNutItems[] = {getResources().getString(R.string.wait_done), getResources().getString(R.string.wait_uploaded)};
        mDoughNutItems = doughNutItems;
        mMenuLabels = menuLabels;
        mMpMenuGv = (GridView) findViewById(R.id.mp_menu_gv);
        mChartLl = (LinearLayout) findViewById(R.id.chart_space_ll);
        int[] colors = new int[]{TO_BE_DEALED_COLOR, TO_BE_UPLOADED_COLOR};
        double[] values = new double[]{90, 10};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(false);
        renderer.setChartTitleTextSize(20);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(1);
        r.setGradientEnabled(true);
        r.setGradientStart(0, Color.RED);
        r.setGradientStop(0, TO_BE_UPLOADED_COLOR);
        r.setHighlighted(true);
        //View chartView = ChartFactory.getDoughnutChartView(this, buildMultipleCategoryDataset("工单",mDoughNutItems,values), renderer);
        View chartView = ChartFactory.getPieChartView(this, buildCategoryDataset("工单",values), renderer);
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
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setPanEnabled(false);
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    protected MultipleCategorySeries buildMultipleCategoryDataset(String title,
                                                                 String[] items, double[] values) {
        MultipleCategorySeries series = new MultipleCategorySeries(title);
            series.add(2007 + "", items, values);
        return series;
    }
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add(mDoughNutItems[k++], value);
        }

        return series;
    }

}