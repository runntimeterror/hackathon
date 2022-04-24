package com.example.hackathon.ui.macroeconomic;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

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
import com.example.hackathon.R;
import com.example.hackathon.repository.AnnualGDPEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MacroEconomicGraphFragment extends Fragment {
//    private TestingRepoViewModel testingRepoViewModel;
    // TODO :REMOVE
    int max = 100;
    int min = 1;
    int range = max - min + 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root =  inflater.inflate(R.layout.fragment_macroeconomic_graph, container, false);
       MacroEconomicViewModel macroEconomicViewModel = ((MacroEconomicFragment) getParentFragment()).getMacroEconomicViewModel();
//        testingRepoViewModel = new ViewModelProvider(this).get(TestingRepoViewModel.class);
       macroEconomicViewModel.getSearchResults().observe(
                getViewLifecycleOwner(),
                new Observer<HashMap<String, List<AnnualGDPEntity>>>() {
                    @Override
                    public void onChanged(@Nullable final HashMap<String, List<AnnualGDPEntity>> gdps) {
                        Log.println(Log.INFO, "TESTINGREPOVIEWMODEL",
                                "MacroEconomicFragment:searchResults.observer.onChanged Length:" + gdps.get("meg2").size());

                        AnyChartView anyChartView = (AnyChartView) root.findViewById(R.id.any_chart_view);
                        anyChartView.setProgressBar((ProgressBar) root.findViewById(R.id.progressBar));

                        Cartesian3d area3d = AnyChart.area3d();

                        area3d.xAxis(0).labels().format("${%Value}");

                        area3d.animation(true);

                        area3d.yAxis(0).title("The Share Price");
                        area3d.xAxis(0).title("Year/Month/Day");
                        area3d.xAxis(0).labels().padding(5d, 5d, 0d, 5d);

                        area3d.title("The cost of ACME\\'s shares<br/>' +\n" +
                                "    '<span style=\"color:#212121; font-size: 13px;\">Statistics was collected from site N during September</span>");

                        area3d.title().useHtml(true);
                        area3d.title().padding(0d, 0d, 20d, 0d);

                        HashMap<String, List<GraphValues>> rawData  = getRawData();

                        //     List<DataEntry> seriesData = new ArrayList<>();
                        List<DataEntry> seriesData = setData(rawData);

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



                        Mapping series1Data = set.mapAs("{ x: 'x', value: 'value' }");
                        Mapping series2Data = set.mapAs("{ x: 'x', value: 'value2' }");
                        Mapping series3Data = set.mapAs("{ x: 'x', value: 'value3' }");
                        Mapping series4Data = set.mapAs("{ x: 'x', value: 'value4' }");
                        Mapping series5Data = set.mapAs("{ x: 'x', value: 'value5' }");


                        Area3d series1 = area3d.area(series1Data);
                        series1.name("ACME Share Price");
                        series1.hovered().markers(false);
                        series1.hatchFill(HatchFillType.BACKWARD_DIAGONAL, "#000", 0.6d, 10d);

                        Area3d series2 = area3d.area(series2Data);
                        series2.name("The Competitor\\'s Share Price");
                        series2.hovered().markers(false);
                        series2.hatchFill(HatchFillType.DIAGONAL_BRICK, "#000", 0.6d, 10d);

                        Area3d series3 = area3d.area(series3Data);
                        series3.name("The Competitor\\'s Share Price");
                        series3.hovered().markers(false);
                        series3.hatchFill(HatchFillType.DIAGONAL_CROSS, "#000", 0.6d, 10d);

                        Area3d series4 = area3d.area(series4Data);
                        series4.name("The Competitor\\'s Share Price");
                        series4.hovered().markers(false);
                        series4.hatchFill(HatchFillType.DASHED_FORWARD_DIAGONAL, "#000", 0.6d, 10d);

                        Area3d series5 = area3d.area(series5Data);
                        series5.name("The Competitor\\'s Share Price");
                        series5.hovered().markers(false);
                        series5.hatchFill(HatchFillType.DASHED_VERTICAL, "#000", 0.6d, 10d);



//
//        final Integer[] i = {1};
//        rawData.forEach((k,v) -> {
//            Mapping series1Data = set.mapAs("{ x: 'x', value: 'value'"+ i[0] +" }");
//            Area3d series1 = area3d.area(series1Data);
//            series1.name("ACME Share Price");
//            series1.hovered().markers(false);
//            series1.hatchFill("diagonal", "#000", 0.6d, 10d);
//            i[0]++;
//        });

                        area3d.tooltip()
                                .position(Position.CENTER_TOP)
                                .positionMode(TooltipPositionMode.POINT)
                                .anchor(Anchor.LEFT_BOTTOM)
                                .offsetX(5d)
                                .offsetY(5d);

                        area3d.interactivity().hoverMode(HoverMode.BY_X);
                        area3d.zAspect(" + (int)(Math.random() * range) + min%");

                        anyChartView.setChart(area3d);
                    }
                }
        );

//        binding.testQueryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", "MacroEconomicFragment: onClick -> viewmodel.findAnnualGDP");
//                testingRepoViewModel.findAnnualGDPs(2015, 2019);
//            }
//        });

        Long[][] hardcodedSeedData = {
                {37029L, 597164L, 5433000L},
                {3702980L, 597160L, 543300L},
                {3702098L, 59716L, 543300L},
                {370298L, 597016L, 543000L},
                {3702098L, 590716L, 53300L},
                {370298L, 597106L, 54300L},
                {370298L, 597016L, 53300L},
        };

        int year = 2014;
        for (Long[] seed : hardcodedSeedData) {
            AnnualGDPEntity entity = new AnnualGDPEntity();
            entity.setYear(year++);
            entity.setIndiaGDP(seed[0]);
            entity.setChinaGDP(seed[1]);
            entity.setUsaGDP(seed[2]);

            macroEconomicViewModel.insertGDPs(entity);
        }

        macroEconomicViewModel.findAnnualGDPs(2014, 2020);

//        try {
////            AssetFileDescriptor descriptor = getContext().getAssets().open("annualgdptable.csv");
////            Log.println(Log.INFO, "TESTINGREPOVIEWMODEL", descriptor.getFileDescriptor().toString());
////            InputStream file = getContext().getAssets().open("images/names.txt");
//
//            FileReader file = new FileReader("annualgdptable.csv");
////            BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
//            BufferedReader buffer = new BufferedReader(file);
//            String line = "";
////            String tableName ="annualgdps";
////            String columns = "year, india, china, usa";
////            String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
////            String str2 = ");";
//
////            db.beginTransaction();
//            while ((line = buffer.readLine()) != null) {
////                StringBuilder sb = new StringBuilder(str1);
//                String[] str = line.split(",");
//                AnnualGDPEntity entity = new AnnualGDPEntity();
//                entity.setYear(Integer.valueOf(str[0]));
//                entity.setIndiaGDP(Long.valueOf(str[1]));
//                entity.setChinaGDP(Long.valueOf(str[2]));
//                entity.setUsaGDP(Long.valueOf(str[3]));
//                testingRepoViewModel.insertGDPs(entity);
//            }
////            db.setTransactionSuccessful();
////            db.endTransaction();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return  root;
    }

    private List<DataEntry> setData(HashMap<String, List<GraphValues>> rawData) {
        List<DataEntry> seriesData = new ArrayList<>();
        Integer noOfGraphs = rawData.size();

        List<GraphValues> graph1;
        List<GraphValues> graph2;
        List<GraphValues> graph3;
        List<GraphValues> graph4;
        List<GraphValues> graph5;

        switch (noOfGraphs) {
            case 1:
                graph1  = rawData.get("graph1");

                for( Integer i =0 ;i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    seriesData.add(new MacroEconomicGraphFragment.CustomDataEntry(g1.getYear(),g1.getValue() ));
                }
                break;
            case 2:
                graph1 = rawData.get("graph1");
                graph2 = rawData.get("graph2");
                for( Integer i =0 ;i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    seriesData.add(new MacroEconomicGraphFragment.CustomDataEntry(g1.getYear(),g1.getValue() , g2.getValue() ));
                }

                break;
            case 3:
                graph1 = rawData.get("graph1");
                graph2 = rawData.get("graph2");
                graph3 = rawData.get("graph3");

                for( Integer i =0 ;i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    GraphValues g3 = graph3.get(i);
                    seriesData.add(new MacroEconomicGraphFragment.CustomDataEntry(g1.getYear(),
                            g1.getValue() ,
                            g2.getValue(),
                            g3.getValue()));
                }
                break;
            case 4:
                graph1 = rawData.get("graph1");
                graph2 = rawData.get("graph2");
                graph3 = rawData.get("graph3");
                graph4 = rawData.get("graph4");

                for( Integer i =0 ;i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    GraphValues g3 = graph3.get(i);
                    GraphValues g4 = graph4.get(i);
                    seriesData.add(new MacroEconomicGraphFragment.CustomDataEntry(g1.getYear(),
                            g1.getValue() ,
                            g2.getValue(),
                            g3.getValue(),
                            g4.getValue()));
                }
                break;
            case 5:
                graph1 = rawData.get("graph1");
                graph2 = rawData.get("graph2");
                graph3 = rawData.get("graph3");
                graph4 = rawData.get("graph4");
                graph5 = rawData.get("graph5");

                for( Integer i =0 ;i < graph1.size(); i++) {
                    GraphValues g1 = graph1.get(i);
                    GraphValues g2 = graph2.get(i);
                    GraphValues g3 = graph3.get(i);
                    GraphValues g4 = graph4.get(i);
                    GraphValues g5 = graph5.get(i);
                    seriesData.add(new MacroEconomicGraphFragment.CustomDataEntry(g1.getYear(),
                            g1.getValue() ,
                            g2.getValue() ,
                            g3.getValue() ,
                            g4.getValue() ,
                            g5.getValue() ));
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
    }

    private class GraphValues {
        private String year;
        private Integer value;

        public String getYear() {
            return year;
        }

        public Integer getValue() {
            return value;
        }

        public GraphValues(String year, Integer value) {
            this.year = year;
            this.value = value;
        }
    }

    private HashMap<String, List<GraphValues>> getRawData() {
        HashMap<String, List<GraphValues>> rawData = new HashMap<String, List<GraphValues>>();
        rawData.put("graph1", dummyDataGet1());
        rawData.put("graph2", dummyDataGet2());
        rawData.put("graph3", dummyDataGet3());
        rawData.put("graph4", dummyDataGet4());
        rawData.put("graph5", dummyDataGet5());
        return rawData;
    }

    private  List<GraphValues> dummyDataGet1() {
        List<GraphValues> list1 = new ArrayList<>();


        list1.add(new GraphValues("1986", 101+ (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1987", 102+ (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1988", 103));
        list1.add(new GraphValues("1989", 104));
        list1.add(new GraphValues("1990", 105));
        list1.add(new GraphValues("1991",  100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1992", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1993", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1994", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1995", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1996", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1997", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1998", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1999", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2001", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2002", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2003", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2004", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2005", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2006", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2007", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2008", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2009", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2010", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2011", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2012", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2013", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2014", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2015", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2016", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2017", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2018", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2019", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2020", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2021", 100 + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2022", 100 + (int)(Math.random() * range) + min));
        return list1;
    }
    private  List<GraphValues> dummyDataGet2() {
        List<GraphValues> list1 = new ArrayList<>();

        list1.add(new GraphValues("1986",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1987",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1988",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1989",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1990",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1991",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1992",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1993",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1994",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1995",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1996",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1997",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1998",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1999",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2001",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2002",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2003",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2004",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2005",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2006",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2007",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2008",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2009",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2010",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2011",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2012",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2013",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2014",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2015",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2016",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2017",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2018",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2019",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2020",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2021",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2022",  + (int)(Math.random() * range) + min));
        return list1;
    }
    private  List<GraphValues> dummyDataGet3() {
        List<GraphValues> list1 = new ArrayList<>();

        list1.add(new GraphValues("1986",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1987",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1988",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1989",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1990",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1991",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1992",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1993",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1994",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1995",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1996",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1997",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1998",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1999",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2001",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2002",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2003",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2004",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2005",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2006",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2007",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2008",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2009",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2010",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2011",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2012",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2013",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2014",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2015",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2016",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2017",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2018",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2019",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2020",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2021",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2022",  + (int)(Math.random() * range) + min));
        return list1;
    }
    private  List<GraphValues> dummyDataGet4() {
        List<GraphValues> list1 = new ArrayList<>();

        list1.add(new GraphValues("1986",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1987",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1988",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1989",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1990",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1991",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1992",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1993",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1994",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1995",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1996",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1997",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1998",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1999",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2001",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2002",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2003",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2004",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2005",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2006",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2007",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2008",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2009",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2010",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2011",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2012",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2013",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2014",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2015",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2016",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2017",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2018",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2019",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2020",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2021",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2022",  + (int)(Math.random() * range) + min));
        return list1;
    }
    private  List<GraphValues> dummyDataGet5() {
        List<GraphValues> list1 = new ArrayList<>();

        list1.add(new GraphValues("1986",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1987",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1988",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1989",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1990",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1991",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1992",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1993",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1994",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1995",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1996",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1997",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1998",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("1999",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2001",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2003",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2002",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2004",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2005",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2006",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2007",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2008",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2009",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2010",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2011",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2012",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2013",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2014",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2015",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2016",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2017",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2018",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2019",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2020",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2021",  + (int)(Math.random() * range) + min));
        list1.add(new GraphValues("2022",  + (int)(Math.random() * range) + min));
        return list1;
    }
}
