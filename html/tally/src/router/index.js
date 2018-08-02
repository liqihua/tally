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
        {path: 'pay_log_list',component:() => import('@/components/page/page_pay_log_list')},
        {path: 'count',component:() => import('@/components/page/page_count')}
      ]
    },
    {
      path: '/wap/login',
      name: 'wap_login',
      component: () => import('@/components/wap/wap_login')
    },
    {
      path: '/wap',
      name: 'wap_index',
      component: () => import('@/components/wap/wap_index'),
      children: [
        {path: '/',component:() => import('@/components/wap/wap_list')},
        {path: 'list',component:() => import('@/components/wap/wap_list')},
        {path: 'form',component:() => import('@/components/wap/wap_form')},
        {path: 'chart',component:() => import('@/components/wap/wap_chart')},
      ]
    }
  ]
})
