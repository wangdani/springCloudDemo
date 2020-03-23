//每日增长比较
function byEveryDay(){
    var start =  $.trim($("#startDate").val());
    var end =  $.trim($("#endDate").val());
    var province = $("[name='checkProv']:checked");
    if(province.length<=0){
        xtip.msg('最少选一个省份',{icon:'e',times:3,type:'w'});
        return false;
    }
    var arg = {start:start,end:end};
    var list = selectByObject(arg);
    if(list != null){
        var xzys = new Array();
        var xzqz = new Array();
        var xzsw = new Array();
        var ljys = new Array();
        var ljqz = new Array();
        var ljsw = new Array();
        var provinceTemp = new Array();
        for(var i=0;i<province.length;i++){
            provinceTemp.push(province.eq(i).val());
        }
        var dateTemp = getDayBetween(start,end);
        for(var i=0;i<dateTemp.length;i++){
            xzys[dateTemp[i]] = new Array(province.length);
            xzqz[dateTemp[i]] = new Array(province.length);
            xzsw[dateTemp[i]] = new Array(province.length);
            ljys[dateTemp[i]] = new Array(province.length);
            ljqz[dateTemp[i]] = new Array(province.length);
            ljsw[dateTemp[i]] = new Array(province.length);
        }

        //dataFormatter(obj,provinceTemp,dateTemp);

        for(var i=0;i<list.length;i++){
            //var provinceCode = list[i].provinceCode;
            var provinceName = list[i].provinceName;
            var nowSize = provinceTemp.indexOf(provinceName);
            if(nowSize!=-1){ //1.如果在我选择的省份里
                xzys = returnDataList(nowSize,list[i].yqDate.substring(0,10),provinceName,list[i].xzys,xzys);
                xzqz = returnDataList(nowSize,list[i].yqDate.substring(0,10),provinceName,list[i].xzqz,xzqz);
                xzsw = returnDataList(nowSize,list[i].yqDate.substring(0,10),provinceName,list[i].xzsw,xzsw);
                ljys = returnDataList(nowSize,list[i].yqDate.substring(0,10),provinceName,list[i].ljys,ljys);
                ljqz = returnDataList(nowSize,list[i].yqDate.substring(0,10),provinceName,list[i].ljqz,ljqz);
                ljsw = returnDataList(nowSize,list[i].yqDate.substring(0,10),provinceName,list[i].ljsw,ljsw);
            }
        }
        var dataMap = {};
        dataMap.xzys = xzys;
        dataMap.xzqz = xzqz;
        dataMap.xzsw = xzsw;
        dataMap.ljys = ljys;
        dataMap.ljqz = ljqz;
        dataMap.ljsw = ljsw;

        showHide1();
        e1(dateTemp,provinceTemp,dataMap);
        console.log("xzys:",xzys);
        console.log("dateTemp:",dateTemp);
        console.log("provinceTemp:",provinceTemp);
    }
}
function returnDataList(nowSize,yqDate,provinceName,listidata,xz_Lj){
    var max = 0;
    var sum = 0;
    var size = nowSize;//xz_Lj[yqDate].length;
    max = Math.max(max, listidata);
    sum += listidata;
    xz_Lj[yqDate][size] = {
        name: provinceName,
        value: listidata
    };
    xz_Lj[yqDate + 'max'] = Math.floor(max / 100) * 100;
    xz_Lj[yqDate + 'sum'] = sum;
    return xz_Lj;
}

function e1(dateTemp,provinceTemp,dataMap){
    clearChartFunc();
    var optionsArr = [];

    var formatter = '{c}';
    if($("#showTSWZ").prop('checked')){ //是否显示提示文字
        formatter = '{a}  {c}';
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
    if($("#showLJPie").prop('checked')){
        seriesTypeData.push(
            {
                name: '占比',
                type: 'pie',
                center: ['75%', '35%'],
                radius: '28%',
                z: 100
            })
    }

    for(var i=0;i<dateTemp.length;i++){
        var title = dateTemp[i]+"新型肺炎情况";
        var sums = dateTemp[i]+"sum";
        var seriesData = [
            {data: dataMap.ljys[dateTemp[i]]},
            {data: dataMap.ljqz[dateTemp[i]]},
            {data: dataMap.ljsw[dateTemp[i]]},
            {data: dataMap.xzys[dateTemp[i]]},
            {data: dataMap.xzqz[dateTemp[i]]},
            {data: dataMap.xzsw[dateTemp[i]]}
        ];

        if($("#showLJPie").prop('checked')){
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
    var optionE1 = {
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
                selected: {
                    //'新增疑似': false, '新增确诊': false, '新增死亡': false
                }
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
                    'data':provinceTemp,
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
    chartFunc(optionE1);
}

