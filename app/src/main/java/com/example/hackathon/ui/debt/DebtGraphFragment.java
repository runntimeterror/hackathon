package com.example.hackathon.ui.debt;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian3d;
import com.anychart.core.cartesian.series.Area3d;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.hatchfill.HatchFillType;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;


import com.example.hackathon.R;
import com.example.hackathon.repository.debt.DebtServiceEntity;
import com.example.hackathon.repository.debt.TotalReservesEntity;
import com.example.hackathon.state.Country;
import com.example.hackathon.state.Persona;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DebtGraphFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_debt_graph, container, false);


        DebtViewModel debtViewModel =  ((DebtFragment) getParentFragment()).getDebtViewModel();
//        testingRepoViewModel = new ViewModelProvider(this).get(TestingRepoViewModel.class);
        debtViewModel.getSearchResults().observe(
                getViewLifecycleOwner(),
                new Observer<HashMap<String, List<DebtServiceEntity>>>() {
                    @Override
                    public void onChanged(@Nullable final HashMap<String, List<DebtServiceEntity>> gdps) {
                        AnyChartView anyChartView = (AnyChartView) root.findViewById(R.id.any_chart_view);
                        anyChartView.setProgressBar((ProgressBar) root.findViewById(R.id.progressBar));

                        anyChartView.setChart(GetGraph(gdps, debtViewModel.getCheckedGraphs()));
                    }
                }
        );

        String userPersona = Persona.getInstance().getUserPersona();
        if(userPersona.equals(Persona.DATA_SCIENTIST)) {
            Button buttonMacro = root.findViewById(R.id.buttonMacroGraphNotes);
            buttonMacro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                    builder.setTitle("Add notes for graph");

// Set up the input
                    final EditText input = new EditText(root.getContext());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

// Set up the buttons
                    builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // m_Text = input.getText().toString();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            });

            buttonMacro.setVisibility(View.VISIBLE);
        }


        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            InputStream input = new URL("https://277hackathoncountrydata.s3.us-west-2.amazonaws.com/debt-service.csv").openStream();
            Reader reader1 = new InputStreamReader(input, "UTF-8");
//            CSVReader reader = new CSVReader(reader1);
            CSVReader reader = new CSVReaderBuilder(reader1).withSkipLines(1).build();
            List<String[]> myEntries = reader.readAll();

            for (String[] entry : myEntries) {
                if (entry[1].toString().isEmpty()){
                    entry[1] = "0";
                }
                if (entry[2].toString().isEmpty()){
                    entry[2] = "0";
                }
                if (entry[3].toString().isEmpty()){
                    entry[3] = "0";
                }

                DebtServiceEntity entity = new DebtServiceEntity(
                        Integer.valueOf(entry[0]),
                        new BigDecimal(entry[1]).floatValue(),
                        new BigDecimal(entry[2]).floatValue(),
                        new BigDecimal(entry[3]).floatValue()
                );

                debtViewModel.insertDebt(entity);

            }

            input = new URL("https://277hackathoncountrydata.s3.us-west-2.amazonaws.com/totasl-reserves.csv").openStream();
            reader1 = new InputStreamReader(input, "UTF-8");
//            CSVReader reader = new CSVReader(reader1);
            reader = new CSVReaderBuilder(reader1).withSkipLines(1).build();
            myEntries = reader.readAll();

            for (String[] entry : myEntries) {
                if (entry[1].toString().isEmpty()){
                    entry[1] = "0";
                }
                if (entry[2].toString().isEmpty()){
                    entry[2] = "0";
                }
                if (entry[3].toString().isEmpty()){
                    entry[3] = "0";
                }
                TotalReservesEntity entity = new TotalReservesEntity(
                        Integer.valueOf(entry[0]),
                        new BigDecimal(entry[1]).floatValue(),
                        new BigDecimal(entry[2]).floatValue(),
                        new BigDecimal(entry[3]).floatValue()
                );

                debtViewModel.insertTotalReserves(entity);

//                entity.setYear(Integer.valueOf(entry[0]));
//                entity.setIndiaGrowthRate(Float.valueOf(entry[1]));
//                entity.setChinaGrowthRate(Float.valueOf(entry[2]));
//                entity.setUsaGrowthRate(Float.valueOf(entry[3]));
//                entity.setIndiaGDP(Long.valueOf(entry[1]));
//                entity.setChinaGDP(Long.valueOf(entry[2]));
//                entity.setUsaGDP(Long.valueOf(entry[3]));
            }



            System.out.println(myEntries);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Long[][] hardcodedSeedData = {
//                {37029L, 597164L, 5433000L},
//                {3702980L, 597160L, 543300L},
//                {3702098L, 59716L, 543300L},
//                {370298L, 597016L, 543000L},
//                {3702098L, 590716L, 53300L},
//                {370298L, 597106L, 54300L},
//                {370298L, 597016L, 53300L},
//        };
//
//        int year = 2014;
//        for (Long[] seed : hardcodedSeedData) {
//            DebtServiceEntity entity = new DebtServiceEntity();
//            entity.setYear(year++);
//            entity.setIndiaGDP(seed[0]);
//            entity.setChinaGDP(seed[1]);
//            entity.setUsaGDP(seed[2]);
//
//            macroEconomicViewModel.insertGDPs(entity);
//        }

        debtViewModel.findDebt(1960, 2020);


        return root;
    }

    private Map<String, List<GraphValues>> TransaformData(HashMap<String, List<DebtServiceEntity>> rawdata , ArrayList<String> graphs){
        String country = Country.getInstance().getSelectedCountry();

        // get only those values which are selected
        Map<String, List<DebtServiceEntity>> deptMap2 = rawdata.entrySet().stream()
                .filter(map -> graphs.contains(map.getKey()))
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));

        Map<String, List<GraphValues>> transformedData = new HashMap<>();

        deptMap2.forEach((k,v) ->{
            long val;
            List<GraphValues> employees = v.stream()
                    .map(p -> {
                        GraphValues g = new GraphValues();
                        g.setYear(p.getYear());
                        switch (country) {
                            case Country.INDIA:
                                g.setValue( p.getIndiaGDP());
                                break;
                            case Country.USA:
                                g.setValue( p.getUsaGDP());
                                break;
                            case Country.CHINA:
                                g.setValue( p.getChinaGDP());
                                break;
                        }
                        return  g;
                    })
                    .collect(Collectors.toList());

            transformedData.put(k, employees);
        });

        return transformedData;
    }
    private Cartesian3d GetGraph(HashMap<String, List<DebtServiceEntity>> rawData, ArrayList<String> graphs) {
        Cartesian3d area3d = AnyChart.area3d();

        area3d.xAxis(0).labels().format("{%Value}");

        area3d.animation(true);

        area3d.yAxis(0).title("Values");
        area3d.xAxis(0).title("Year");
        area3d.xAxis(0).labels().padding(5d, 5d, 0d, 5d);

        area3d.title("Debt Table");

        area3d.title().useHtml(true);
        area3d.title().padding(0d, 0d, 20d, 0d);

//        HashMap<String, List<GraphValues>> rawData = getRawData();

        //     List<DataEntry> seriesData = new ArrayList<>();
        Map<String, List<GraphValues>> transformedData =  TransaformData(rawData, graphs);
        List<DataEntry> seriesData = setData(transformedData);

//        seriesData.add(new CustomDataEntry("1986", 162, 42));
//        seriesData.add(new CustomDataEntry("1987", 134, 54));
//        seriesData.add(new CustomDataEntry("1988", 116, 26));
//        seriesData.add(new CustomDataEntry("1989", 122, 32));
//        seriesData.add(new CustomDataEntry("1990", 178, 68));
//        seriesData.add(new CustomDataEntry("1991", 144, 54));
//        seriesData.add(new CustomDataEntry("1992", 125, 35));
//        seriesData.add(new CustomDataEntry("1993", 176, 66));
//        seriesData.add(new CustomDataEntry("1994", 156, 80));
//        seriesData.add(new CustomDataEntry("1995", 195, 120));
//        seriesData.add(new CustomDataEntry("1996", 215, 115));
//        seriesData.add(new CustomDataEntry("1997", 176, 36));
//        seriesData.add(new CustomDataEntry("1998", 167, 47));
//        seriesData.add(new CustomDataEntry("1999", 142, 72));
//        seriesData.add(new CustomDataEntry("2000", 117, 37));
//        seriesData.add(new CustomDataEntry("2001", 113, 23));
//        seriesData.add(new CustomDataEntry("2002", 132, 30));
//        seriesData.add(new CustomDataEntry("2003", 146, 46));
//        seriesData.add(new CustomDataEntry("2004", 169, 59));
//        seriesData.add(new CustomDataEntry("2005", 184, 44));

        Set set = Set.instantiate();
        set.data(seriesData);

        Integer noOfGraphs = transformedData.size();
        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
        Mapping series3Data = set.mapAs("{ x: 'x', value: 'value3' }");
        Mapping series4Data = set.mapAs("{ x: 'x', value: 'value4' }");
        Mapping series5Data = set.mapAs("{ x: 'x', value: 'value5' }");
        Mapping series6Data = set.mapAs("{ x: 'x', value: 'value6' }");

        Area3d series1 = area3d.area(series1Data);

        series1.hovered().markers(false);
        series1.hatchFill(HatchFillType.BACKWARD_DIAGONAL, "#000", 0.6d, 10d);

        Area3d series2 = area3d.area(series2Data);

        series2.hovered().markers(false);
        series2.hatchFill(HatchFillType.DIAGONAL_BRICK, "#000", 0.6d, 10d);

        Area3d series3 = area3d.area(series3Data);

        series3.hovered().markers(false);
        series3.hatchFill(HatchFillType.DIAGONAL_CROSS, "#000", 0.6d, 10d);

        Area3d series4 = area3d.area(series4Data);

        series4.hovered().markers(false);
        series4.hatchFill(HatchFillType.DASHED_FORWARD_DIAGONAL, "#000", 0.6d, 10d);

        Area3d series5 = area3d.area(series5Data);

        series5.hovered().markers(false);
        series5.hatchFill(HatchFillType.DASHED_VERTICAL, "#000", 0.6d, 10d);

        Area3d series6 = area3d.area(series6Data);

        series6.hovered().markers(false);
        series6.hatchFill(HatchFillType.FORWARD_DIAGONAL, "#000", 0.6d, 10d);

        switch (noOfGraphs) {
            case 1:
                series1.name(transformedData.keySet().toArray()[0].toString());
                break;
            case 2:
                series1.name(transformedData.keySet().toArray()[0].toString());
                series2.name(transformedData.keySet().toArray()[1].toString());
                break;
            case 3:
                series1.name(transformedData.keySet().toArray()[0].toString());
                series2.name(transformedData.keySet().toArray()[1].toString());
                series3.name(transformedData.keySet().toArray()[2].toString());
                break;
            case 4:
                series1.name(transformedData.keySet().toArray()[0].toString());
                series2.name(transformedData.keySet().toArray()[1].toString());
                series3.name(transformedData.keySet().toArray()[2].toString());
                series4.name(transformedData.keySet().toArray()[3].toString());
                break;
            case 5:
                series1.name(transformedData.keySet().toArray()[0].toString());
                series2.name(transformedData.keySet().toArray()[1].toString());
                series3.name(transformedData.keySet().toArray()[2].toString());
                series4.name(transformedData.keySet().toArray()[3].toString());
                series5.name(transformedData.keySet().toArray()[4].toString());
                break;
            case 6:
                series1.name(transformedData.keySet().toArray()[0].toString());
                series2.name(transformedData.keySet().toArray()[1].toString());
                series3.name(transformedData.keySet().toArray()[2].toString());
                series4.name(transformedData.keySet().toArray()[3].toString());
                series5.name(transformedData.keySet().toArray()[4].toString());
                series6.name(transformedData.keySet().toArray()[5].toString());
                break;
        }




        area3d.tooltip()
                .position(Position.CENTER_TOP)
                .positionMode(TooltipPositionMode.POINT)
                .anchor(Anchor.LEFT_BOTTOM)
                .offsetX(5d)
                .offsetY(5d);

        area3d.interactivity().hoverMode(HoverMode.BY_X);
        area3d.zAspect(" + (int)(Math.random() * range) + min%");

        return area3d;
    }

    private List<DataEntry> setData(Map<String, List<GraphValues>> transformedData) {
        List<DataEntry> seriesData = new ArrayList<>();
        Integer noOfGraphs = transformedData.size();

        List<GraphValues> graph1;
        List<GraphValues> graph2;
        List<GraphValues> graph3;
        List<GraphValues> graph4;
        List<GraphValues> graph5;
        List<GraphValues> graph6;

        switch (noOfGraphs) {
            case 1:
                graph1 = transformedData.get(transformedData.keySet().toArray()[0]);

                for (Integer i = 0; i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    seriesData.add(new CustomDataEntry(g1.getYear().toString(), g1.getValue()));
                }
                break;
            case 2:
                graph1 = transformedData.get(transformedData.keySet().toArray()[0]);
                graph2 = transformedData.get(transformedData.keySet().toArray()[1]);
                for (Integer i = 0; i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    seriesData.add(new CustomDataEntry(g1.getYear().toString(), g1.getValue(), g2.getValue()));
                }

                break;
            case 3:
                graph1 = transformedData.get(transformedData.keySet().toArray()[0]);
                graph2 = transformedData.get(transformedData.keySet().toArray()[1]);
                graph3 = transformedData.get(transformedData.keySet().toArray()[2]);

                for (Integer i = 0; i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    GraphValues g3 = graph3.get(i);
                    seriesData.add(new CustomDataEntry(g1.getYear().toString(),
                            g1.getValue(),
                            g2.getValue(),
                            g3.getValue()));
                }
                break;
            case 4:
                graph1 = transformedData.get(transformedData.keySet().toArray()[0]);
                graph2 = transformedData.get(transformedData.keySet().toArray()[1]);
                graph3 = transformedData.get(transformedData.keySet().toArray()[2]);
                graph4 = transformedData.get(transformedData.keySet().toArray()[3]);

                for (Integer i = 0; i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    GraphValues g3 = graph3.get(i);
                    GraphValues g4 = graph4.get(i);
                    seriesData.add(new CustomDataEntry(g1.getYear().toString(),
                            g1.getValue(),
                            g2.getValue(),
                            g3.getValue(),
                            g4.getValue()));
                }
                break;
            case 5:
                graph1 = transformedData.get(transformedData.keySet().toArray()[0]);
                graph2 = transformedData.get(transformedData.keySet().toArray()[1]);
                graph3 = transformedData.get(transformedData.keySet().toArray()[2]);
                graph4 = transformedData.get(transformedData.keySet().toArray()[3]);
                graph5 = transformedData.get(transformedData.keySet().toArray()[4]);

                for (Integer i = 0; i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    GraphValues g3 = graph3.get(i);
                    GraphValues g4 = graph4.get(i);
                    GraphValues g5 = graph5.get(i);
                    seriesData.add(new CustomDataEntry(g1.getYear().toString(),
                            g1.getValue(),
                            g2.getValue(),
                            g3.getValue(),
                            g4.getValue(),
                            g5.getValue()));
                }
                break;
            case 6:
                graph1 = transformedData.get(transformedData.keySet().toArray()[0]);
                graph2 = transformedData.get(transformedData.keySet().toArray()[1]);
                graph3 = transformedData.get(transformedData.keySet().toArray()[2]);
                graph4 = transformedData.get(transformedData.keySet().toArray()[3]);
                graph5 = transformedData.get(transformedData.keySet().toArray()[4]);
                graph6 = transformedData.get(transformedData.keySet().toArray()[5]);

                for (Integer i = 0; i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    GraphValues g3 = graph3.get(i);
                    GraphValues g4 = graph4.get(i);
                    GraphValues g5 = graph5.get(i);
                    GraphValues g6 = graph6.get(i);
                    seriesData.add(new CustomDataEntry(g1.getYear().toString(),
                            g1.getValue(),
                            g2.getValue(),
                            g3.getValue(),
                            g4.getValue(),
                            g5.getValue(),
                            g6.getValue()));
                }
                break;
        }


        return seriesData;
    }

    private class CustomDataEntry extends ValueDataEntry {
        CustomDataEntry(String x, Number value) {
            super(x, value);
        }

        CustomDataEntry(String x, Number value, Number value2) {
            super(x, value);
            setValue("value2", value2);

        }
        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);

        }
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);

        }
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4, Number value5) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
            setValue("value5", value5);
        }
        CustomDataEntry(String x, Number value, Number value2, Number value3, Number value4, Number value5, Number value6) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
            setValue("value4", value4);
            setValue("value5", value5);
            setValue("value6", value6);
        }
    }

    private class GraphValues {
        private Integer year;
        private Float value;

        public Integer getYear() {
            return year;
        }

        public Float getValue() {
            return value;
        }

        public void setYear(Integer year) {
            this.year = year;
        }

        public void setValue(Float value) {
            this.value = value;
        }

        public GraphValues() {
        }

        public GraphValues(Integer year, Float value) {
            this.year = year;
            this.value = value;
        }
    }



}
