<template>
  <div class="sys-index">
    <el-container>
      <el-aside width="180px">
        <sys_menu></sys_menu>
      </el-aside>
      <el-container>
        <router-view></router-view>
        <!--<el-header>
          <sys_header></sys_header>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>-->
      </el-container>
    </el-container>
  </div>
</template>

<script>
  import sys_menu from '@/components/sys/sys_menu'
  import sys_header from '@/components/sys/sys_header'
  export default {
    name: 'page-index',
    components:{
      sys_menu,
      sys_header
    }
  }
</script>

<style>
  .sys-index{height:100%;}
</style>
