google.charts.load('45', {
   // callback: drawCO2Charts,
    packages:['corechart']
});




    function drawChart1() {

        var data = new google.visualization.DataTable();

        data.addColumn('string', 'Year');
        data.addColumn('number', 'CO2 Actual');
        data.addColumn('number', 'CO2 Projected');


        var year = new Date().getFullYear() - 1;
        var stringI;
        for (var i = 1979; i < year ; i = i + 4)
        {
           stringI = i.toString();
           data.addRow([stringI, dataCO2Past[stringI], null ]);
        }

        data.addRow([year.toString(), dataCO2Current[year.toString()], dataCO2Current[year.toString()] ]);

        for (var i = year + 4; i < year + 50; i = i + 4) {
            stringI = i.toString();
            data.addRow([stringI, null, dataCO2Future[stringI] ]);
        }

        var options = {
            title: 'Carbon Dioxide (ppm) vs Time (Years) at Current Rate Of Change',
            curveType: 'function',
            legend: {position: 'bottom'},
            width: 900,
            height: 500,
            series :
                {
                    1: { lineDashStyle: [10, 2] }
                }
        };

        var chart = new google.visualization.LineChart(document.getElementById('linechart_'));

        chart.draw(data, options);

    }

    function drawChart2() {

        var data = new google.visualization.DataTable();

        data.addColumn('string', 'Year');
        data.addColumn('number', 'Global Temperature Actual');
        data.addColumn('number', 'Global Temperature Projected');


        var year = new Date().getFullYear() - 1;
        var stringI;
        for (var i = 1880; i < year ; i = i + 4)
        {
            stringI = i.toString();
            data.addRow([stringI, dataGlobalTempPast[stringI], null ]);
        }

        data.addRow([year.toString(), dataGlobalTempCurrent[year.toString()], dataGlobalTempCurrent[year.toString()] ]);

        for (var i = year + 4; i < 2070; i = i + 4) {
            stringI = i.toString();
            data.addRow([stringI, null, dataGlobalTempFuture[stringI]]);
        }

        var options = {
            title: 'Global Temperature Change (Celsius) vs Time (Years)',
            curveType: 'function',
            legend: {position: 'bottom'},
            width: 900,
            height: 500,
            series :
                {
                    1: { lineDashStyle: [10, 2] }
                }
        };

        var chart = new google.visualization.LineChart(document.getElementById('linechart_'));

        chart.draw(data, options);

    }

    function drawChart3() {

        var data = new google.visualization.DataTable();

        data.addColumn('string', 'Year');
        data.addColumn('number', 'Arctic Ice Extent Actual');
        data.addColumn('number', 'Arctic Ice Extent Projected');


        var year = 2017;
        var stringI;
        for (var i = 1979; i < year ; i = i + 4)
        {
            stringI = i.toString();
            data.addRow([stringI, dataArcticIceExtentPast[stringI], null ]);
        }

        data.addRow([year.toString(), dataArcticIceExtentCurrent[year.toString()], dataArcticIceExtentCurrent[year.toString()] ]);

        for (var i = year + 4; i < 2070; i = i + 4) {
            stringI = i.toString();
            data.addRow([stringI, null, dataArcticIceExtentFuture[stringI] ]);
        }

        var options = {
            title: 'Arctic Ice Minimum Extent (Sq KM) vs Time (Years)',
            curveType: 'function',
            legend: {position: 'bottom'},
            width: 900,
            height: 500,
            series :
                {
                    1: { lineDashStyle: [10, 2] }
                }
        };

        var chart = new google.visualization.LineChart(document.getElementById('linechart_'));

        chart.draw(data, options);
    }

function drawChart4() {

    var data = new google.visualization.DataTable();

    data.addColumn('string', 'Year');
    data.addColumn('number', 'Arctic Ice Area Actual');
    data.addColumn('number', 'Arctic Ice Area Projected');


    var year = 2017;
    var stringI;
    for (var i = 1979; i < year; i = i + 4) {
        stringI = i.toString();
        data.addRow([stringI, dataArcticIceAreaPast[stringI], null]);
    }

    data.addRow([year.toString(), dataArcticIceAreaCurrent[year.toString()], dataArcticIceAreaCurrent[year.toString()]]);

    for (var i = year + 4; i < 2070; i = i + 4) {
        stringI = i.toString();
        data.addRow([stringI, null, dataArcticIceAreaFuture[stringI]]);
    }

    var options = {
        title: 'Arctic Ice Minimum Area (Sq KM) vs Time (Years)',
        curveType: 'function',
        legend: {position: 'bottom'},
        width: 900,
        height: 500,
        series:
            {
                1: {lineDashStyle: [10, 2]}
            }
    };

    var chart = new google.visualization.LineChart(document.getElementById('linechart_'));

    chart.draw(data, options);
    }



