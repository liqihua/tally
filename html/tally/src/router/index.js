import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      path: '/login',
      name: 'login',
      component: () => import('@/components/sys/sys_login')
    },
    {
      path: '/',
      name: 'index',
      component: () => import('@/components/sys/sys_index'),
      children: [
        {path: '/',component:() => import('@/components/page/page_pay_log_list')},
        {path: 'pay_log_list',component:() => import('@/components/page/page_pay_log_list')}
      ]
    }
  ]
})
