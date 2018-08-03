<template>
  <div id="wap_form" v-loading.fullscreen.lock="loading">

    <el-input v-model="total" class="log-input input-total" type="text" placeholder="金额/元" ></el-input>
    <el-input v-model="productName" class="log-input input-product-name" type="text" placeholder="商品名称" ></el-input>
    <el-select v-model="productType" class="log-input" filterable placeholder="商品分类">
      <el-option v-for="item in productTypeArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-input v-model="payTime" class="log-input " type="text" placeholder="消费时间" @focus="openDatePicker()" readonly="true" ></el-input>
    <el-button type="primary" icon="el-icon-success" @click="addLog()">save</el-button>

    <!-- datePicker -->
    <mt-datetime-picker ref="datePicker" type="date" v-model="payTimePicker" :startDate="startDate" :endDate="endDate" @confirm="datePickerConfirm"></mt-datetime-picker>
  </div>
</template>

<script>
  import API,{productType} from '@/config/config';
  import {saveLog} from '@/js/common.js';
  import { DatetimePicker } from 'mint-ui';

  export default {
    name: 'wap_form',
    components:{
      [DatetimePicker.name]:DatetimePicker,
    },
    data() {
      return {
        total:'',
        productName:'',
        productType:'',
        payTime:'',
        productTypeArr:productType,

        payTimePicker:'',
        startDate:'',
        endDate:'',

        loading: false,
      }
    },
    methods:{
      openDatePicker(){
        this.$refs.datePicker.open();
      },
      datePickerConfirm(){
        this.payTime = this.payTimePicker.format('yyyy-MM-dd');
      },
      addLog(){
        var data = {
          total : this.total,
          productName : this.productName,
          productType : this.productType,
          payTime : this.payTime
        }
        saveLog(data,this,"/wap/login",true,true);
      }
    },
    created:function () {
      var date = new Date();
      this.payTime = date.format('yyyy-MM-dd');
      this.payTimePicker = date;
      this.startDate = new Date();
      this.endDate = new Date();
      this.startDate.setTime(date.getTime()-(365*24*60*60*1000));
      this.endDate.setTime(date.getTime()+(365*24*60*60*1000))
    }
  }

  Date.prototype.format = function(format)
  {
    var o = {
      "M+" : this.getMonth()+1, //month
      "d+" : this.getDate(),    //day
      "h+" : this.getHours(),   //hour
      "m+" : this.getMinutes(), //minute
      "s+" : this.getSeconds(), //second
      "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
      "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
      (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
      format = format.replace(RegExp.$1,
        RegExp.$1.length==1 ? o[k] :
          ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
  }
</script>

<style scoped>
  #wap_form{width: 100%;height: 100%;text-align: center;padding: 0;margin: 0;}
  .log-input,.el-button{width: 70%;margin-top:5%;}
  .log-input:first-child{
    margin-top: 30%;
  }
</style>
