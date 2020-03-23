var end = new Date();
var day = ("0" + end.getDate()).slice(-2);
var month = ("0" + (end.getMonth() + 1)).slice(-2);
var today = end.getFullYear()+"-"+(month)+"-"+(day) ;
var firstDay = "2020-01-16";
var chartDataPath = "数据来自国家卫健委和各省卫健委通报";
var legendData = ['累计疑似', '累计确诊', '累计死亡','新增疑似', '新增确诊', '新增死亡'];
$(function(){
    hideDiv("#bigChartDiv");
    hide123();
})

function clickChartIs(){
    if($("#hide1").is(':visible')){　　//如果node是显示的则隐藏node元素，否则显示
        byEveryDay();
    }
    if($("#hide2").is(':visible')){　　//如果node是显示的则隐藏node元素，否则显示
        byEveryProvince();
    }
    if($("#hide3").is(':visible')){　　//如果node是显示的则隐藏node元素，否则显示
        byAllProvince();
    }
}


function showDiv(a){
    $(a).show();
}
function hideDiv(a){
    $(a).hide();
}


function hide123(){
    $("#hide1").hide();
    $("#hide2").hide();
    $("#hide3").hide();
}
function showHide1(){
    hide123();
    $("#echartsName").html("每日增长比较");
    $("#hide1").show();
}
function showHide2(){
    hide123();
    $("#echartsName").html("各省走势图");
    $("#hide2").show();
}
function showHide3(){
    hide123();
    $("#echartsName").html("各省比较图");
    $("#hide3").show();
}




var loadId;
$(document).ajaxStart(function(){
    loadId = xtip.load("数据加载中，请稍后");
});
$(document).ajaxStop(function(){
    xtip.close(loadId);
});

function getPath(){
    var location = (window.location+'').split('/');
    var basePath = location[0]+'//'+location[2]+'/'+location[3];
    var url = basePath;
    return url;
}
//比较日期大小
function compareDate(a,b){
    var start = new Date(a.replace("-", "/").replace("-", "/"));
    var end = new Date(b.replace("-", "/").replace("-", "/"));
    if(start>end){
        return 1;
    } else if(start<end){
        return -1;
    }else{
        return 0;
    }
}

Date.prototype.format = function() {
    var s = '';
    var mouth = (this.getMonth() + 1)>=10?(this.getMonth() + 1):('0'+(this.getMonth() + 1));
    var day = this.getDate()>=10?this.getDate():('0'+this.getDate());
    s += this.getFullYear() + '-'; // 获取年份。
    s += mouth + "-"; // 获取月份。
    s += day; // 获取日。
    return (s); // 返回日期。
};
function getDayBetween(begin, end) {
    var arr = [];
    var ab = begin.split("-");
    var ae = end.split("-");
    var db = new Date();
    db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
    var de = new Date();
    de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
    var unixDb = db.getTime() - 24 * 60 * 60 * 1000;
    var unixDe = de.getTime() - 24 * 60 * 60 * 1000;
    for (var k = unixDb; k <= unixDe;) {
        //console.log((new Date(parseInt(k))).format());
        k = k + 24 * 60 * 60 * 1000;
        arr.push((new Date(parseInt(k))).format());
    }
    return arr;
}

function loadDate(){
    var lastDay = $.trim($("#lastDay").val());
    lastDay = lastDay.substring(0,4)+"-"+lastDay.substring(4,6)+"-"+lastDay.substring(6,8); //格式化日期
    $("#startDate").val(firstDay);
    $("#endDate").val(lastDay);
    $("#lastDayFont").html(firstDay + " 至 " + lastDay);
    $("#jzFont").html(lastDay);

    $(".dateTimeWrap").bind('click',function(){
        var start = $.trim($("#startDate").val());
        var end = $.trim($("#endDate").val());

        if(compareDate(start,end)==1){ //如果开始日期大于结束日期
            xtip.msg('开始日期大于结束日期',{icon:'e',times:3,type:'w'});
            $("#startDate").val(firstDay);
            $("#endDate").val(lastDay);
        }
        if(compareDate(start,firstDay)==-1){ //如果开始日期小于初始日期
            xtip.msg('开始日期小于初始日期',{icon:'e',times:3,type:'w'});
            $("#startDate").val(firstDay);
        }
        if(compareDate(end,lastDay)==1){ //如果结束日期大于更新日期
            xtip.msg('结束日期大于更新日期',{icon:'e',times:3,type:'w'});
            $("#endDate").val(lastDay);
        }
    })
}

function checkAll(){
    // 获得上面的复选框
    var checkAll = $("#checkAll");
    checkAll.click(function() {
        if (checkAll.prop("checked") == true) {
            // 上面的复选框已被选中
            $(":checkbox[name='checkProv']").prop("checked", true);
        } else {
            // 上面的复选框没被选中
            $(":checkbox[name='checkProv']").prop("checked", false);
        }
    });
}

function chartFunc(op){
    var nowChartIs = $.trim($("#nowChartIs").val());    //myChart1
    var theme = $.trim($("[name='nowChartTheme']:checked").val());
                //$.trim($("#nowChartTheme").val());      //"dark";
    /*if(theme=="dark"){
        $("#bigChartDiv").css("background-color","#333333");
    }else{
        $("#bigChartDiv").css("background-color","#f3f3f3");
    }*/
    var dom = document.getElementById(nowChartIs);
    var myChart = echarts.init(dom,theme);
    myChart.setOption(op); //静态数据加载


}
function clearChartFunc(){
    var nowChartIs = $.trim($("#nowChartIs").val());    //myChart1
    var theme = $.trim($("[name='nowChartTheme']:checked").val());
                //$.trim($("#nowChartTheme").val());      //"dark";
   /* if(theme=="dark"){
        $("#bigChartDiv").css("background-color","#333333");
    }else{
        $("#bigChartDiv").css("background-color","#f3f3f3");
    }*/
    var dom = document.getElementById(nowChartIs);
    var myChart = echarts.init(dom,theme);
    myChart.clear(); //静态数据加载


}

function strToInt(val){
    val = parseInt($.trim(val));
    if(isNaN(val)){
        val = 0;
    }
    return val;
}