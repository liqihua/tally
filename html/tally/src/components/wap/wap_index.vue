<template>
  <div id="wap_index">
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
      <el-footer>
        <wap_menu></wap_menu>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
  import wap_menu from '@/components/wap/wap_menu'

  export default {
    name: 'wap_index',
    components:{
      wap_menu
    },
    data() {
      return {
      }
    }
  }
</script>

<style scoped>
  #wap_index,.el-container{width: 100%;height: 100%;padding: 0;}
  .el-footer,.el-main {padding: 0;height: 100%;}

</style>
