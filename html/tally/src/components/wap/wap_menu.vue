<template>
  <div id="wap_menu">
    <el-button-group>
      <router-link to="/wap/form">
        <el-button type="primary" icon="el-icon-edit-outline" ></el-button>
      </router-link>
      <router-link to="/wap/list">
        <el-button type="primary" icon="el-icon-document" ></el-button>
      </router-link>
      <router-link to="/wap/chart">
        <el-button type="primary" icon="el-icon-picture-outline" ></el-button>
      </router-link>
    </el-button-group>
  </div>
</template>

<script>
  export default {
    name: 'wap_menu',
    data() {
      return {
      }
    }
  }
</script>

<style scoped>
  #wap_menu,.el-button-group{width:100%;height:100%;}
  .el-button{width: 33.33%;height:100%;}
</style>
