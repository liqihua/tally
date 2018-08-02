<template>
  <div id="wap_form" v-loading.fullscreen.lock="loading">

    <el-input v-model="total" class="log-input input-total" type="number" placeholder="金额/元" :label-width="50"></el-input>
    <el-input v-model="productName" class="log-input input-product-name" type="text" placeholder="商品名称" ></el-input>
    <el-select v-model="productType" class="log-input" filterable placeholder="商品分类">
      <el-option v-for="item in productTypeArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
    </el-select>
    <el-date-picker class="log-input" v-model="payTime" type="date" placeholder="消费时间" value-format="yyyy-MM-dd"></el-date-picker>
    <el-button type="primary" icon="el-icon-success" @click="addLog()">save</el-button>


  </div>
</template>

<script>
  import API,{productType} from '@/config/config';
  import axios from 'axios';
  import {saveLog} from '@/js/common.js';

  export default {
    name: 'wap_form',
    data() {
      return {
        total:0,
        productName:'',
        productType:'',
        payTime:'',
        productTypeArr:productType,

        loading: false,
      }
    },
    methods:{
      addLog(){
        var data = {
          total : this.total,
          productName : this.productName,
          productType : this.productType,
          payTime : this.payTime
        }
        saveLog(data,this,"/wap/login",true,true);
      }
    }
  }
</script>

<style scoped>
  #wap_form{width: 100%;height: 100%;text-align: center;padding: 0;margin: 0;}
  .log-input,.el-button{width: 70%;margin-top:5%;}
  .log-input:first-child{
    margin-top: 30%;
  }
</style>
