<template>
  <div class="div-sys-header">
    <div class="div-input-wrap">
      <el-input v-model="total" class="input-total" type="number" placeholder="金额/元" ></el-input>
      <el-input v-model="productName" class="input-product-name" type="text" placeholder="商品名称" ></el-input>
      <el-input v-model="productType" class="input-product-type" type="text" placeholder="商品分类" ></el-input>
      <el-date-picker v-model="payTime" type="date" placeholder="消费时间"></el-date-picker>
      <el-button type="primary" icon="el-icon-success" @click="addLog()">save</el-button>
    </div>
  </div>
</template>


<script>
  import axios from 'axios';
  export default {
    name: 'sys_header',
    data(){
      return {
        total:0,
        productName:'',
        productType:'',
        payTime:''
      }
    },
    methods: {
      addLog(){
        if(this.total == null || this.total == ''){
          alert("金额不能为空");return;
        }
        if(this.total <= 0){
          alert("金额必须大于0");return;
        }
        if(this.productName == null || this.productName == ''){
          alert("商品名称不能为空");return;
        }
        if(this.productType == null || this.productType == ''){
          alert("商品分类不能为空");return;
        }
        if(this.payTime == null || this.payTime == ''){
          alert("消费时间不能为空");return;
        }
        axios.post("http://localhost:9003/api/logPayApiController/saveLogPay",{
          total:this.total,
          productName:this.productName,
          productType:this.productType,
          payTime:this.payTime
        }).then(function(res){
          console.log(res);
        }).catch(function(res){
          console.log(res);
        });
      }
    }
  }
</script>


<style>
  .div-sys-header{
    width: 100%;
    height: 100%;
  }
  .div-sys-header .el-input{
    max-width: 200px;
  }
  .div-input-wrap{margin-top: 20px;}

</style>
