import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      component: Login,
    },
    {
      path: '/main',
      component: () => import('../views/Main.vue'),
      children: [
        {
          path: '/main/shop',
          component: () => import('../views/pages/ShopCenter.vue'),
        },
        {
          path: '/main/money',
          component: () => import('../views/pages/money.vue'),
        },
        {
          path: '/main/buy',
          component: () => import('../views/pages/buy.vue'),
        },
        {
          path: '/main/sold',
          component: () => import('../views/pages/sold.vue'),
        },
        {
          path: '/main/user',
          component: () => import('../views/pages/UserMessage.vue'),
        },
        {
          path: '/main/usermanager',
          component: () => import('../views/pages/AllUserMessage.vue'),
        },
        {
          path: '/main/soldmanager',
          component: () => import('../views/pages/AllBuyMessage.vue'),
        },
      ],
      redirect: '/main/shop',
    },
    { path: '/', redirect: '/login' },
  ],
})

export default router
