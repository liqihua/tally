<template>
  <div class="div-sys-header">
    <div class="div-input-wrap">
      <el-input class="input-total" type="number" placeholder="金额/元" ></el-input>
      <el-input class="input-product-name" type="text" placeholder="商品名称" ></el-input>
      <el-input class="input-product-type" type="text" placeholder="消费分类" ></el-input>
      <el-date-picker v-model="payTime" type="date" placeholder="消费时间"></el-date-picker>
      <el-button id="" type="primary" icon="el-icon-success">save</el-button>
    </div>
  </div>
</template>


<script>
  export default {
    name: 'sys_header',
    data(){
      return {
        payTime:''
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
