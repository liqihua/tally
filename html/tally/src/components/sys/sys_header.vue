<template>
  <div class="div-sys-header" v-loading.fullscreen.lock="loading">
    <div class="div-input-wrap">
      <el-input v-model="total" class="input-total" type="number" placeholder="金额/元" ></el-input>
      <el-input v-model="productName" class="input-product-name" type="text" placeholder="商品名称" ></el-input>
      <el-select v-model="productType" filterable placeholder="商品分类">
        <el-option v-for="item in productTypeArr" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-date-picker v-model="payTime" type="date" placeholder="消费时间" value-format="yyyy-MM-dd"></el-date-picker>
      <el-button type="primary" icon="el-icon-success" @click="addLog()">save</el-button>
    </div>
  </div>
</template>


<script>
  import API,{productType} from '@/config/config';
  import axios from 'axios';
  import store from '@/store/store.js';

  export default {
    name: 'sys_header',
    store,
    data(){
      return {
        loading:false,
        total:0,
        productName:'',
        productType:'',
        payTime:'',
        productTypeArr:productType
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
        var userId = localStorage.getItem("userId");
        var token = localStorage.getItem("token");
        if(userId == null || userId == '' || token == null || token == ''){
          location.href = "/login";
        }
        var that = this;
        that.loading = true;
        axios({
          method: 'post',
          url: API.SAVE_LOG_PAY,
          headers : {
            "Content-Type":'application/x-www-form-urlencoded; charset=UTF-8'
          },
          data: "total="+(parseInt(this.total*100))+"&productName="+this.productName+"&productType="+this.productType+"&payTime="+this.payTime+"&userId="+userId+"&token="+token
        }).then(function (res) {
          console.log(res);
          that.loading = false;
          if(res.data.resultCode == 10000){
            that.$message({
              message:'保存成功',
              center:true,
              type:'success'
            });
            store.commit('doUpdateLogList');
            that.total = 0;
            that.productName = '';
            that.productType = '';
            that.payTime = '';
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
