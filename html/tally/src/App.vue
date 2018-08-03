<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
import Vconsole from 'vconsole';

export default {
  name: 'App',
  created:function () {
    let vConsole = new Vconsole();
  }
}
</script>



<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100%;
  height: 100%;
}
</style>
