<template>
  <div class="sys-index">
    <el-container>
      <el-aside width="180px">
        <sys_menu></sys_menu>
      </el-aside>
      <el-container>
        <router-view></router-view>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import sys_menu from '@/components/sys/sys_menu'
  export default {
    name: 'page-index',
    components:{
      sys_menu
    }
  }
</script>

<style>
  .sys-index{height:100%;}
</style>
