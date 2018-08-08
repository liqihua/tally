import API from '@/config/config';
import axios from 'axios';
import echarts from 'echarts';
const jsSHA = require("jssha");

const login = (that,wap) => {
  if(that.username != null && that.username != '' && that.password != null && that.password != ''){
    var pwd = that.password;
    var shaObj = new jsSHA("SHA-1", "TEXT");
    shaObj.update(pwd);
    pwd = shaObj.getHash("HEX");
    var url = "/";
    if(wap){
      url = "/wap"
    }
    that.loading = true;
    console.log("--- starting login ");
    axios({
      method: 'post',
      url: API.LOGIN,
      headers : {
        "Content-Type":'application/x-www-form-urlencoded; charset=UTF-8'
      },
      data: "username="+that.username+"&password="+pwd
    }).then(function (res) {
      console.log(res);
      that.loading = false;
      if(res.data.resultCode == 10000){
        localStorage.setItem("userId",res.data.resultData.id);
        localStorage.setItem("token",res.data.resultData.token);
        that.$router.push(url);
      }else{
        that.$message.error({
          message: res.data.resultMessage,
          center: true
        });
      }
    }).catch(function (res) {
      console.log("catch:");
      console.log(res);
      that.loading = false;
    });
  }
}



/**
 * 解决两个数相乘出现很多小数
 * @param arg1
 * @param arg2
 * @returns {number}
 */
const accMul = (arg1,arg2) => {
  var m=0,s1=arg1.toString(),s2=arg2.toString();
  try{
    m+=s1.split(".")[1].length;
  }catch(e){}
  try{
    m+=s2.split(".")[1].length;
  }catch(e){}
  return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}

/**
 * 保存记录
 * @param data
 * @param that
 * @param loginUrl
 * @param wap
 * @param noList
 */
const saveLog = (data,that,loginUrl,wap,noList) => {
  //console.log(data);return;
  if(data.total == null || data.total == ''){
    that.$message.error({
      message: "金额不能为空",
      center: true
    });
    return;
  }
  if(isNaN(data.total) && !isDouble(data.total)){
    that.$message.error({
      message: "金额不合法",
      center: true
    });
    return;
  }
  if(data.total <= 0){
    that.$message.error({
      message: "金额必须大于0",
      center: true
    });
    return;
  }
  if(data.productName == null || data.productName == ''){
    that.$message.error({
      message: "商品名称不能为空",
      center: true
    });
    return;
  }
  if(data.productType == null || data.productType == ''){
    that.$message.error({
      message: "商品分类不能为空",
      center: true
    });
    return;
  }
  if(data.payTime == null || data.payTime == ''){
    that.$message.error({
      message: "消费时间不能为空",
      center: true
    });
    return;
  }
  if(data.logId == null){
    data.logId = '';
  }
  if(loginUrl == null || loginUrl == ''){
    loginUrl = "/login";
  }
  var userId = localStorage.getItem("userId");
  var token = localStorage.getItem("token");
  if(userId == null || userId == '' || token == null || token == ''){
    that.$router.push(loginUrl);
  }
  data.total = accMul(data.total,100);
  that.loading = true;
  axios({
    method: 'post',
    url: API.SAVE_LOG_PAY,
    headers : {
      "Content-Type":'application/x-www-form-urlencoded; charset=UTF-8'
    },
    data: "logId="+data.logId+"&total="+data.total+"&productName="+data.productName+"&productType="+data.productType+"&payTime="+data.payTime+"&userId="+userId+"&token="+token
  }).then(function (res) {
    console.log(res);
    that.loading = false;
    if(res.data.resultCode == 10000){
      that.$message({
        message:'保存成功',
        center:true,
        type:'success'
      });
      if(noList){

      }else{
        if(wap){
          getListWAP(that);
        }else{
          getList(that);
        }
      }
      that.total = '';
      that.productName = '';
      that.productType = '';
      that.payTime = '';
    }else if(res.data.resultCode == 42003){
      that.$router.push("/login");
    }else{
      that.$message.error({
        message: res.data.resultMessage,
        center: true
      });
    }
  }).catch(function (res) {
    console.log(res);
    that.loading = false;
  });
}

/**
 * 获取记录列表
 * @param that
 */
const getList = (that) => {
  var userId = localStorage.getItem("userId");
  var token = localStorage.getItem("token");
  if(userId == null || userId == '' || token == null || token == ''){
    that.$router.push("/login");
  }
  axios({
    method: 'get',
    url: API.GET_LOG_LIST+"?userId="+userId+"&token="+token+"&page="+(that.page - 1)+"&pageSize="+that.pageSize,
  }).then(function (res) {
    console.log(res);
    that.loading = false;
    if(res.data.resultCode == 10000){
      for(var i=0;i<res.data.resultData.list.length;i++){
        res.data.resultData.list[i].payTime = res.data.resultData.list[i].payTime.substring(0,10);
      }
      for(var i=0;i<res.data.resultData.list.length;i++){
        res.data.resultData.list[i].total = res.data.resultData.list[i].total / 100;
      }
      that.tableData = res.data.resultData.list;
      console.log(that.tableData);
      that.dataCount = res.data.resultData.count;
      that.page = res.data.resultData.page + 1;
    }else{
      if(res.data.resultCode == 42003){
        that.$router.push("/login");
      }
      that.$message.error({
        message: res.data.resultMessage,
        center: true
      });
    }
  }).catch(function (res) {
    console.log(res);
    that.loading = false;
  });
}


/**
 * 获取记录列表
 * @param that
 */
function getListWAP(that){
  var userId = localStorage.getItem("userId");
  var token = localStorage.getItem("token");
  if(userId == null || userId == '' || token == null || token == ''){
    that.$router.push("/login");
  }
  that.loading = true;
  axios({
    method: 'get',
    url: API.GET_LOG_LIST+"?userId="+userId+"&token="+token+"&page="+(that.page - 1)+"&pageSize="+that.pageSize,
  }).then(function (res) {
    console.log(res);
    that.loading = false;
    if(res.data.resultCode == 10000){
      for(var i=0;i<res.data.resultData.list.length;i++){
        res.data.resultData.list[i].payTime = res.data.resultData.list[i].payTime.substring(0,10);
      }
      for(var i=0;i<res.data.resultData.list.length;i++){
        res.data.resultData.list[i].total = res.data.resultData.list[i].total / 100;
      }
      that.tableData = res.data.resultData.list;
      console.log(that.tableData);
      that.page = res.data.resultData.page + 1;
    }else{
      if(res.data.resultCode == 42003){
        that.$router.push("/login");
      }
      that.$message.error({
        message: res.data.resultMessage,
        center: true
      });
    }
  }).catch(function (res) {
    console.log(res);
    that.loading = false;
  });
}



const drawLine = (that) => {
  var userId = localStorage.getItem("userId");
  var token = localStorage.getItem("token");
  if(userId == null || userId == '' || token == null || token == ''){
    this.$router.push("/login");
  }
  that.loading = true;
  axios({
    method: 'get',
    url: API.GET_COUNT_TYPE+"?userId="+userId+"&token="+token
  }).then(function (res) {
    console.log(res);
    that.loading = false;
    if(res.data.resultCode == 10000){
      if(res.data.resultData != null && res.data.resultData.length > 0){
        var arrX = [];
        var arrY = [];
        var pieArr = [];
        for(var i=0;i<res.data.resultData.length;i++){
          var total = res.data.resultData[i].total / 100;
          arrX.push(res.data.resultData[i].type);
          arrY.push(total);
          pieArr.push({name:res.data.resultData[i].type,value:total});
        }
        var chartBar = echarts.init(document.getElementById('chart-bar'));
        var chartPie = echarts.init(document.getElementById('chart-pie'));
        var optionBar = {
          title: {
            text: '支出总额/元'
          },
          tooltip: {},
          legend: {
            data:['支出金额']
          },
          xAxis: {
            data: arrX
          },
          yAxis: {},
          series: [{
            name: '支出金额',
            type: 'bar',
            data: arrY
          }]
        };

        var optionPie = {
          title : {
            text: '各类型消费总支出占比',
            subtext: '消费比例图',
            x:'center'
          },
          tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          legend: {
            orient: 'vertical',
            left: 'left',
            data: arrX
          },
          series : [
            {
              name: '访问来源',
              type: 'pie',
              radius : '55%',
              center: ['50%', '60%'],
              data: pieArr,
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffsetX: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        };


        chartBar.setOption(optionBar);
        chartPie.setOption(optionPie);
      }

    }else{
      if(res.data.resultCode == 42003){
        this.$router.push("/login");
      }
      that.$message.error({
        message: res.data.resultMessage,
        center: true
      });
    }
  }).catch(function (res) {
    console.log(res);
    that.loading = false;
  });
}





/**
 * 返回设备浏览器，用于判断是否移动设备
 * @type {{versions: {trident, presto, webKit, gecko, mobile, ios, android, iPhone, iPad, webApp}, language: string, export: {accMul: (function(*, *): number), saveLog: saveLog, getList: getList, getListWAP: getListWAP}}}
 */
var browser = {
  versions: function () {
    var u = navigator.userAgent, app = navigator.appVersion;
    return {   //移动终端浏览器版本信息
      trident: u.indexOf('Trident') > -1, //IE内核
      presto: u.indexOf('Presto') > -1, //opera内核
      webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
      gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
      mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
      ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
      android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
      iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
      iPad: u.indexOf('iPad') > -1, //是否iPad
      webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
    };
  }(),
  language: (navigator.browserLanguage || navigator.language).toLowerCase()
}


//判断一个字符串是否为小数
var isDouble = (s) => {
  if(s.split(".").length != 2){
    return false;
  }
  var reg = new RegExp("\\d+\\.\\d+$|-\\d+\\.\\d+$");
  return reg.test(s);
}



export {accMul,saveLog,getList,getListWAP,browser,login,drawLine}
