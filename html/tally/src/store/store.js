import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

export default new Vuex.Store({
  state:{
    updateLogList:1
  },
  mutations:{
    doUpdateLogList(state){
      state.updateLogList++
    }
  }
})


