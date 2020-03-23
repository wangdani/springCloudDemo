function firstClickByAllProvince(){
    var province = $("[name='checkProv']:checked");
    if(province.length<=0){
        xtip.msg('最少选一个省份',{icon:'e',times:3,type:'w'});
        return false;
    }
    $("#bX").val(province.length);
    byAllProvince();
}
//每日增长比较
function byAllProvince(){
    var start =  $.trim($("#startDate").val());
    var end =  $.trim($("#endDate").val());
    var province = $("[name='checkProv']:checked");
    if(province.length<=0){
        xtip.msg('最少选一个省份',{icon:'e',times:3,type:'w'});
        return false;
    }
    $("#pLength").text(province.length);
    var aX = strToInt($("#aX").val());
    var bX = strToInt($("#bX").val());
    if(aX==0 || bX==0 || aX>bX || bX>province.length){
        $("#aX").val(1);
        $("#bX").val(province.length);
        xtip.msg('统计的省份范围不正确，系统自动改为1至'+province.length,{icon:'e',times:3,type:'w'});
    }

    var arg = {start:start,end:end};
    var list = selectByObject(arg);
    if(list != null){
        var dataMap = {};
        dataMap.xzys = new Array();
        dataMap.xzqz = new Array();
        dataMap.xzsw = new Array();
        dataMap.ljys = new Array();
        dataMap.ljqz = new Array();
        dataMap.ljsw = new Array();
        var provinceTemp = new Array();
        for(var i=0;i<province.length;i++){
            provinceTemp.push(province.eq(i).val());
        }


        var dateTemp = getDayBetween(start,end);
        for(var i=0;i<dateTemp.length;i++){
            dataMap.xzys[dateTemp[i]] = new Array();
            dataMap.xzqz[dateTemp[i]] = new Array();
            dataMap.xzsw[dateTemp[i]] = new Array();
            dataMap.ljys[dateTemp[i]] = new Array();
            dataMap.ljqz[dateTemp[i]] = new Array();
            dataMap.ljsw[dateTemp[i]] = new Array();
        }

        var nowSort = $.trim($("[name='nowSort']:checked").val());

        //dataFormatter(obj,provinceTemp,dateTemp);

        for(var i=0;i<list.length;i++){
            //var provinceCode = list[i].provinceCode;
            var provinceName = list[i].provinceName;
            var nowSize = provinceTemp.indexOf(provinceName);
            if(nowSize!=-1){ //1.如果在我选择的省份里
                dataMap = returnDataListByFlag(nowSort,list[i].yqDate.substring(0,10),provinceName,list[i],dataMap);
            }
        }


        showHide3();
        e3(dateTemp,provinceTemp,dataMap);
        console.log("xzys:",dataMap);
        console.log("dateTemp:",dateTemp);
        console.log("provinceTemp:",provinceTemp);
    }
}

function returnDataListByFlag(nowSort,yqDate,provinceName,listi,dataMap){
    //var nowSort = $.trim($("[name='nowSort']:checked").val());
    var size;
    var nowData;
    var listidata;
    if(nowSort=="累计疑似"){
        nowData = dataMap.ljys[yqDate];
        listidata = listi.ljys;
        size = dataMap.ljys[yqDate].length;
    }
    if(nowSort=="累计确诊"){
        nowData = dataMap.ljqz[yqDate];
        listidata = listi.ljqz;
        size = dataMap.ljqz[yqDate].length;
    }
    if(nowSort=="累计死亡"){
        nowData = dataMap.ljsw[yqDate];
        listidata = listi.ljsw;
        size = dataMap.ljsw[yqDate].length;
    }
    if(nowSort=="新增疑似"){
        nowData = dataMap.xzys[yqDate];
        listidata = listi.xzys;
        size = dataMap.xzys[yqDate].length;
    }
    if(nowSort=="新增确诊"){
        nowData = dataMap.xzqz[yqDate];
        listidata = listi.xzqz;
        size = dataMap.xzqz[yqDate].length;
    }
    if(nowSort=="新增死亡"){
        nowData = dataMap.xzsw[yqDate];
        listidata = listi.xzsw;
        size = dataMap.xzsw[yqDate].length;
    }

        nowData[size] = {
            name: provinceName,
            value: listidata
        };

    dataMap.ljys[yqDate][size] = {
        name: provinceName,
        value: listi.ljys
    };
    dataMap.ljqz[yqDate][size] = {
        name: provinceName,
        value: listi.ljqz
    };
    dataMap.ljsw[yqDate][size] = {
        name: provinceName,
        value: listi.ljsw
    };
    dataMap.xzys[yqDate][size] = {
        name: provinceName,
        value: listi.xzys
    };
    dataMap.xzqz[yqDate][size] = {
        name: provinceName,
        value: listi.xzqz
    };
    dataMap.xzsw[yqDate][size] = {
        name: provinceName,
        value: listi.xzsw
    };

        var current;
        var current1;
        var current2;
        var current3;
        var current4;
        var current5;
        var current6;
        for (var i = 0; i < nowData.length - 1; i++) {
            current = nowData[i + 1];
            current1 = dataMap.ljys[yqDate][i + 1];
            current2 = dataMap.ljqz[yqDate][i + 1];
            current3 = dataMap.ljsw[yqDate][i + 1];
            current4 = dataMap.xzys[yqDate][i + 1];
            current5 = dataMap.xzqz[yqDate][i + 1];
            current6 = dataMap.xzsw[yqDate][i + 1];
            var preIndex = i;
            while (preIndex >= 0 && current.value < nowData[preIndex].value) {
                nowData[preIndex + 1] = nowData[preIndex];

                dataMap.ljys[yqDate][preIndex + 1] = dataMap.ljys[yqDate][preIndex];
                dataMap.ljqz[yqDate][preIndex + 1] = dataMap.ljqz[yqDate][preIndex];
                dataMap.ljsw[yqDate][preIndex + 1] = dataMap.ljsw[yqDate][preIndex];
                dataMap.xzys[yqDate][preIndex + 1] = dataMap.xzys[yqDate][preIndex];
                dataMap.xzqz[yqDate][preIndex + 1] = dataMap.xzqz[yqDate][preIndex];
                dataMap.xzsw[yqDate][preIndex + 1] = dataMap.xzsw[yqDate][preIndex];
                preIndex--;
            }
            nowData[preIndex + 1] = current;
            dataMap.ljys[yqDate][preIndex + 1] = current1;
            dataMap.ljqz[yqDate][preIndex + 1] = current2;
            dataMap.ljsw[yqDate][preIndex + 1] = current3;
            dataMap.xzys[yqDate][preIndex + 1] = current4;
            dataMap.xzqz[yqDate][preIndex + 1] = current5;
            dataMap.xzsw[yqDate][preIndex + 1] = current6;
        }
        return dataMap;
}





function e3(dateTemp,provinceTemp,dataMap){
    clearChartFunc();
    var selected = {
        '累计疑似': false, '累计确诊': false, '累计死亡': false,
        '新增疑似': false, '新增确诊': false, '新增死亡': false
    };
    var nowSort = $.trim($("[name='nowSort']:checked").val());
    selected[nowSort] = true;




    var optionsArr = [];

    var formatter = '{b} {c}';
    if($("#showTSWZ3").prop('checked')){ //是否显示提示文字
        formatter = '{b} {a} {c}';
    }
    var seriesTypeData = [];
    for(var i=0;i<legendData.length;i++){
        seriesTypeData.push(
            {name: legendData[i],label: {
                    show: true,
                    formatter: formatter,
                    fontSize: 16,
                    position: 'right'
                }, type: 'bar'}
        )
    }
    if($("#showLJPie3").prop('checked')){
        seriesTypeData.push(
            {
                name: '占比',
                type: 'pie',
                center: ['75%', '35%'],
                radius: '28%',
                z: 100
            })
    }
    var aX = provinceTemp.length-strToInt($("#aX").val())+1;
    var bX = provinceTemp.length-strToInt($("#bX").val());

    for(var i=0;i<dateTemp.length;i++){
        var title = dateTemp[i]+"新型肺炎情况";
        var sums = dateTemp[i]+"sum";
        var seriesData = [
            {data: dataMap.ljys[dateTemp[i]].slice(bX,aX)},
            {data: dataMap.ljqz[dateTemp[i]].slice(bX,aX)},
            {data: dataMap.ljsw[dateTemp[i]].slice(bX,aX)},
            {data: dataMap.xzys[dateTemp[i]].slice(bX,aX)},
            {data: dataMap.xzqz[dateTemp[i]].slice(bX,aX)},
            {data: dataMap.xzsw[dateTemp[i]].slice(bX,aX)},
        ];
        console.log("aXBX",dataMap.ljqz[dateTemp[i]].slice(aX,bX));
        console.log("aXOnBX",dataMap.ljqz[dateTemp[i]]);


        if($("#showLJPie3").prop('checked')){
            seriesData.push(
                {data: [
                        {name: '累计疑似', value: dataMap.ljys[sums]},
                        {name: '累计确诊', value: dataMap.ljqz[sums]},
                        {name: '累计死亡', value: dataMap.ljsw[sums]}
                    ]}
            );
        }

        optionsArr.push(
            {
                title: {text: title},
                series: seriesData
            }
        )
    }
    var readSpeech = $.trim($("#readSpeech").val());
    readSpeech = readSpeech*1000;
    var optionE3 = {
        baseOption: {
            timeline: {
                axisType: 'category',
                // realtime: false,
                loop: false,
                autoPlay: true,
                // currentIndex: 2,
                playInterval: readSpeech,
                // controlStyle: {
                //     position: 'left'
                // },
                data: dateTemp,
                label: {}
            },
            title: {
                subtext: chartDataPath
            },
            tooltip: {
            },
            legend: {
                left: 'right',
                data: legendData,
                selected: selected
            },
            calculable : true,
            grid: {
                top: 80,
                bottom: 100,
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow',
                        label: {
                            show: true,
                            formatter: function (params) {
                                return params.value.replace('\n', '');
                            }
                        }
                    }
                }
            },
            yAxis: [
                {
                    'type':'category',
                    'axisLabel':{'interval':0},
                   // 'data':provinceTemp,
                    splitLine: {show: false}
                }
            ],
            xAxis: [
                {
                    type: 'value',
                    name: '人数（人）'
                }
            ],
            series: seriesTypeData
        },
        options: optionsArr
    };
    chartFunc(optionE3);
}






