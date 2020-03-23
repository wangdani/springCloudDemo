//各省走势图
function byEveryProvince(){
    var start =  $.trim($("#startDate").val());
    var end =  $.trim($("#endDate").val());
    var province = $("[name='checkProv']:checked");
    if(province.length!=1){
        xtip.msg('选择且只能选择一个省份',{icon:'e',times:3,type:'w'});
        return false;
    }

    var provinceVal = province.eq(0).val();
    var arg = {start:start,end:end,provinceName:provinceVal};

    var list = selectByObject(arg);
    if(list != null){
        var xzys = new Array();
        var xzqz = new Array();
        var xzsw = new Array();
        var ljys = new Array();
        var ljqz = new Array();
        var ljsw = new Array();
        var provinceCheck = province.eq(0).val();
        var dateTemp = getDayBetween(start,end);
        for(var i=0;i<dateTemp.length;i++){
            xzys[dateTemp[i]] = 0;
            xzqz[dateTemp[i]] = 0;
            xzsw[dateTemp[i]] = 0;
            ljys[dateTemp[i]] = 0;
            ljqz[dateTemp[i]] = 0;
            ljsw[dateTemp[i]] = 0;
        }
        for(var i=0;i<list.length;i++){
            var provinceName = list[i].provinceName;
            if(provinceName==provinceCheck){
                var yqDate = list[i].yqDate.substring(0,10);
                xzys[yqDate] = list[i].xzys;
                xzqz[yqDate] = list[i].xzqz;
                xzsw[yqDate] = list[i].xzsw;
                ljys[yqDate] = list[i].ljys;
                ljqz[yqDate] = list[i].ljqz;
                ljsw[yqDate] = list[i].ljsw;
            }
        }
        showHide2();
        var arg = {provinceCheck:provinceCheck,start:start,end:end,
            dateTemp:dateTemp,xzys:xzys, xzqz:xzqz, xzsw:xzsw,
            ljys:ljys, ljqz:ljqz,ljsw:ljsw
        }
        e2(arg);
    }
}

function updateE2Data(dateTemp,xz_lj){
    var temp = [];
    for(var i=0;i<dateTemp.length;i++){
        temp.push(xz_lj[dateTemp[i]]);
    }
    return temp;
}
function updateE2SeriesData(data,name){
    var te =  {
        name: name,
        type: 'line',
        data: data,
        markPoint: {
            data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
            ]
        },
        markLine: {
            data: [
                {type: 'average', name: '平均值'}
            ]
        }
    };
    return te;
}


function e2(arg){
    clearChartFunc();
    var title = arg.provinceCheck+"新型肺炎情况";
    //var chartDataPathText = chartDataPath+"\n"++arg.start+"至"+arg.end;
    var seriesData = [];
    seriesData.push( updateE2SeriesData(updateE2Data(arg.dateTemp,arg.ljys),"累计疑似") );
    seriesData.push( updateE2SeriesData(updateE2Data(arg.dateTemp,arg.ljqz),"累计确诊") );
    seriesData.push( updateE2SeriesData(updateE2Data(arg.dateTemp,arg.ljsw),"累计死亡") );
    seriesData.push( updateE2SeriesData(updateE2Data(arg.dateTemp,arg.xzys),"新增疑似") );
    seriesData.push( updateE2SeriesData(updateE2Data(arg.dateTemp,arg.xzqz),"新增确诊") );
    seriesData.push( updateE2SeriesData(updateE2Data(arg.dateTemp,arg.xzsw),"新增死亡") );

    var option = {
        title: {
            text: title,
            subtext: chartDataPath
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: legendData
        },
        toolbox: {
            show: true,
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: arg.dateTemp
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: seriesData
    };
    chartFunc(option);

}