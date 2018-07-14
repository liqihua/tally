<template>
  <div class="sys-login">
    <div class="sys-login-title">tally</div>
    <div class="sys-login-input">
      <el-input v-model="username" type="text" placeholder="账号"></el-input>
      <el-input v-model="password" type="password" placeholder="密码"></el-input>
      <el-button type="primary" @click="login()">登录</el-button>
    </div>
  </div>
</template>

<script>
import config from '@/config/config';
import sha from '@/js/sha/sha';
import axios from 'axios';
export default {
  name: 'login',
  data () {
    return {
      username:'',
      password:''
    }
  },
  methods: {
    login(){
      if(this.username != null && this.username != '' && this.password != null && this.password != ''){
        var pwd = this.password;
        var shaObj = new jsSHA("SHA-1", "TEXT");
        shaObj.update(pwd);
        pwd = shaObj.getHash("HEX");
        axios.post(API.LOGIN,{
          username : this.username,
          password : pwd
        }).then(function (res) {
          console.log(res);
        }).catch(function (res) {
          console.log(res);
        })
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
