<template>
  <div class="page-count" v-loading.fullscreen.lock="loading">
    <div id="chart-bar" style="width: 1000px;height:600px;"></div>
    <div id="chart-pie" style="width: 1000px;height:600px;"></div>
  </div>
</template>

<script>
  import API from '@/config/config';
  import axios from 'axios';
  import echarts from 'echarts';

  export default {
    name : 'count',
    data() {
      return {
        loading : false
      }
    },
    mounted(){
      this.drawLine();
    },
    methods :{
      drawLine(){
        var userId = localStorage.getItem("userId");
        var token = localStorage.getItem("token");
        if(userId == null || userId == '' || token == null || token == ''){
          this.$router.push("/login");
        }
        var that = this;
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
                  text: '各类型支出总额/元'
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
    }
  }
</script>

<style scoped>

</style>
