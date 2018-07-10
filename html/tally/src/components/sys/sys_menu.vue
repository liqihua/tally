<template>
  <el-menu default-active="1"  background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
    <el-menu-item index="1">
      <i class="el-icon-tickets"></i>
      <span slot="title">支出记录</span>
    </el-menu-item>
    <el-menu-item index="2">
      <i class="el-icon-sort"></i>
      <span slot="title">支出统计</span>
    </el-menu-item>

  </el-menu>
</template>

<script>
  export default {
    name: 'sys-menu'
  }
</script>

<style>
.el-menu *{text-decoration: none;}
.el-menu *:hover{background-color: rgb(38,52,69) !important;}
.el-menu{height: 100%;}
</style>
