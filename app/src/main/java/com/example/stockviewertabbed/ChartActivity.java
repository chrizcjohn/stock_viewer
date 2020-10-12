package com.example.stockviewertabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        LineChart chart = (LineChart) findViewById(R.id.lineChart);

//        chart.setOnChartGestureListener(ChartActivity.this);
//        chart.setOnChartValueSelectedListener(ChartActivity.this);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);


        ArrayList<Entry> yValue = new ArrayList<>();
        yValue.add(new Entry(0,640f));
        yValue.add(new Entry(1,600f));
        yValue.add(new Entry(2,620f));
        yValue.add(new Entry(3,60f));
        yValue.add(new Entry(4,60f));
        yValue.add(new Entry(5,760f));
        yValue.add(new Entry(6,360f));
        yValue.add(new Entry(7,360f));

        LineDataSet set1 = new LineDataSet(yValue,"Data set 1" );
        set1.setFillAlpha(110);

        ArrayList<ILineDataSet>  dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        chart.setData(data);


    }
}