package com.pemt.workteminal.component;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

public class BarFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String[] titles = new String[] { "待处理", "待上传" };
        List<double[]> values = new ArrayList<double[]>();
        values.add(new double[] { 185 });
        values.add(new double[] { 20});
        int[] colors = new int[] { Color.BLUE, Color.CYAN };
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        setChartSettings(renderer, "工单结果", "类型", "数量", 0,
                2, 0, 200, Color.GRAY, Color.LTGRAY);
        ((XYSeriesRenderer) renderer.getSeriesRendererAt(0)).setDisplayChartValues(true);
        ((XYSeriesRenderer) renderer.getSeriesRendererAt(1)).setDisplayChartValues(true);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setXLabels(2);
        renderer.setYLabels(10);
        renderer.setXLabelsAlign(Paint.Align.LEFT);
        renderer.setYLabelsAlign(Paint.Align.LEFT);
        renderer.setXLabelsColor(Color.TRANSPARENT);
        renderer.setYLabelsColor(0,Color.BLACK);
        renderer.setBarSpacing(50.0);
        renderer.setBarWidth(80);
        View view = ChartFactory.getBarChartView(BarFrag.this.getActivity(),
                buildBarDataset(titles, values), renderer, org.achartengine.chart.BarChart.Type.DEFAULT);
        renderer.setClickEnabled(true);
        renderer.setApplyBackgroundColor(true);//必须设置为true，颜色值才生效
        renderer.setBackgroundColor(Color.TRANSPARENT);//设置表格背景色
        renderer.setMarginsColor(Color.TRANSPARENT);//设置周边背景色
        renderer.setClickEnabled(true);
        renderer.setPanEnabled(false,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeriesSelection seriesSelection = ((GraphicalView)view).getCurrentSeriesAndPoint();
                if (seriesSelection == null) {
                    Toast
                            .makeText(BarFrag.this.getActivity(), "No chart element was clicked", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(
                            BarFrag.this.getActivity(),
                            "Chart element data point index " + seriesSelection.getPointIndex()
                                    + " was clicked" + " point value=" + seriesSelection.getValue(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setAxisTitleTextSize(16);
        renderer.setChartTitleTextSize(20);
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        int length = colors.length;
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
                                    String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
                                    int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
    }
    protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {
            CategorySeries series = new CategorySeries(titles[i]);
            double[] v = values.get(i);
            int seriesLength = v.length;
            for (int k = 0; k < seriesLength; k++) {
                series.add(v[k]);
            }
            dataset.addSeries(series.toXYSeries());
        }
        return dataset;
    }
}
