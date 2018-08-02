<template>
  <el-menu default-active="1"  background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    <router-link to="/pay_log_list">
      <el-menu-item index="1">
        <i class="el-icon-document"></i>
        <span slot="title">支出记录</span>
      </el-menu-item>
    </router-link>
    <router-link to="/count">
      <el-menu-item index="2">
        <i class="el-icon-picture-outline"></i>
        <span slot="title">支出统计</span>
      </el-menu-item>
    </router-link>

  </el-menu>
</template>

<script>
  export default {
    name: 'sys-menu'
  }
</script>

<style scoped>
.el-menu *{text-decoration: none;}
.el-menu *:hover{background-color: rgb(38,52,69) !important;}
.el-menu{height: 100%;}
</style>
