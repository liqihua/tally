<template>
  <div class="sys-login" v-loading.fullscreen.lock="loading">
    <div class="sys-login-title">tally</div>
    <div class="sys-login-input">
      <el-input v-model="username" type="text" placeholder="账号"></el-input>
      <el-input v-model="password" type="password" placeholder="密码"></el-input>
      <el-button type="primary" @click="login()" >登录</el-button>
    </div>
  </div>
</template>

<script>
import API from '@/config/config';
import axios from 'axios';
const jsSHA = require("jssha");

export default {
  name: 'login',
  data () {
    return {
      loading : false,
      username : '',
      password : ''
    }
  },
  methods: {
    login:() => {
      if(this.username != null && this.username != '' && this.password != null && this.password != ''){
        var pwd = this.password;
        var shaObj = new jsSHA("SHA-1", "TEXT");
        shaObj.update(pwd);
        pwd = shaObj.getHash("HEX");
        var that = this;
        that.loading = true;
        axios({
          method: 'post',
          url: API.LOGIN,
          headers : {
            "Content-Type":'application/x-www-form-urlencoded; charset=UTF-8'
          },
          data: "username="+this.username+"&password="+pwd
        }).then(function (res) {
          console.log(res);
          that.loading = false;
          if(res.data.resultCode == 10000){
            localStorage.setItem("userId",res.data.resultData.id);
            localStorage.setItem("token",res.data.resultData.token);
            location.href = "/index";
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
}
</script>

<style>
  .sys-login{
    width: 100%;
    height: 100%;
    background-color: #2d3a4b;
    text-align: center;
    padding-top: 100px;
  }
  .sys-login-title{
    color: #eee;
    font-size: 30px;

  }
  .sys-login-input{
    display:inline-block;
    width: 400px;
    margin-top: 20px;
  }
  .sys-login-input .el-input:not(:first-child){
    margin-top: 20px;
  }
  .sys-login-input .el-input{
    border-radius: 5px;
  }
  .sys-login-input .el-button{
    width: 100%;
    margin-top: 20px;
  }
</style>
