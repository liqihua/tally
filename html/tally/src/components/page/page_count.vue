<template>
  <div class="page-count" v-loading.fullscreen.lock="loading">
    <div id="chart-bar" style="width: 1000px;height:600px;"></div>
    <div id="chart-pie" style="width: 1000px;height:600px;"></div>
  </div>
</template>

<script>
  import {drawLine} from '@/js/common.js';

  export default {
    name : 'count',
    data() {
      return {
        loading : false
      }
    },
    created:function () {
      drawLine(this);
    }
  }
</script>

<style scoped>

</style>
