import { createRouter, createWebHistory } from 'vue-router'

// por enquanto só uma rota de exemplo
import HomeView from '../views/HomeView.vue'
import Clientes from '@/views/Clientes.vue'
import Produtos from '@/views/Produtos.vue'
import Vendas from '@/views/Vendas.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/clientes',
      name: 'clientes',
      component: Clientes,
    },
    {
      path: '/produtos',
      name: 'produtos',
      component: Produtos
    },
    {
      path: '/vendas',
      name: 'vendas',
      component: Vendas
    }
  ],
})

export default router
