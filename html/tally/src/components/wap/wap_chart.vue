<template>
  <div id="wap_chart" v-loading.fullscreen.lock="loading">
    <div id="chart-bar"></div>
    <div id="chart-pie"></div>
  </div>
</template>

<script>
  import {drawLine} from '@/js/common.js';
  import $ from 'jquery';

  export default {
    name: 'wap_chart',
    data() {
      return {
        loading : false
      }
    },
    created:function () {
      $("#chart-bar").height($("#chart-bar").width()*2);
      $("#chart-pie").height($("#chart-bar").width()*2);
      drawLine(this);
    }
  }

  $(function () {
    $("#chart-bar").height($("#chart-bar").width()*2);
    $("#chart-pie").height($("#chart-bar").width()*2);
  });

</script>

<style scoped>
  #wap_chart,#chart-bar,#chart-pie{width: 100%;}

</style>
