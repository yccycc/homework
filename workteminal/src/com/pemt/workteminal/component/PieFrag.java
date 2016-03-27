package com.pemt.workteminal.component;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.pemt.workteminal.R;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class PieFrag extends Fragment {
    private LinearLayout mChartLl;
    private int[] mGdNums;
    private View mParentView;
    private final static int TO_BE_DEALED_COLOR = 0xFF4D50C0;
    private final static int TO_BE_UPLOADED_COLOR = 0xFFBD814F;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.mainpage_pie,null);
        mChartLl = (LinearLayout) mParentView.findViewById(R.id.chart_space_ll);
        int[] colors = new int[]{TO_BE_DEALED_COLOR, TO_BE_UPLOADED_COLOR};
        mGdNums = new int[]{185, 20};
        DefaultRenderer renderer = buildCategoryRenderer(colors);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(1);
        r.setHighlighted(true);
        View chartView = ChartFactory.getPieChartView(PieFrag.this.getActivity(), buildCategoryDataset("工单",mGdNums), renderer);
        mChartLl.addView(chartView);
        renderer.setClickEnabled(true);
        chartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeriesSelection seriesSelection = ((GraphicalView)chartView).getCurrentSeriesAndPoint();
                if (seriesSelection == null) {
                    Toast
                            .makeText(PieFrag.this.getActivity(), "No chart element was clicked", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(
                            PieFrag.this.getActivity(),
                            "Chart element data point index " + seriesSelection.getPointIndex()
                                    + " was clicked" + " point value=" + seriesSelection.getValue(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mParentView;
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
