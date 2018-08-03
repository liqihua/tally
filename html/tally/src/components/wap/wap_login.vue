<template>
  <div id="wap_login">
    <div class="sys-login-title">tally</div>
    <el-input v-model="username" type="text" placeholder="账号"></el-input>
    <el-input v-model="password" type="password" placeholder="密码"></el-input>
    <el-button type="primary" @click="login()" >登录</el-button>
  </div>
</template>

<script>
  import {browser,login} from '@/js/common.js'

  export default {
    name: 'wap_login',
    data() {
      return {
        username : "",
        password : ""
      }
    },
    created:function(){
      if(!browser.versions.mobile){
        this.$router.push("/login");
      }
    },
    methods: {
      login(){
        login(this,true);
      }
    }
  }
</script>

<style scoped>
  .sys-login-title{font-size: 2rem;color: #fff;padding-top: 20%;}
  #wap_login{background-color: #2d3a4b;width: 100%;height: 100%;text-align: center;}
  .el-input,.el-button{width: 70%;margin-top: 5%;}
</style>
