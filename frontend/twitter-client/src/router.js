import { createRouter, createWebHistory } from 'vue-router';
import Index from './views/Index.vue';

const routes = [{ path: '/', component: Index }];

const history = createWebHistory();

const router = createRouter({
  history,
  routes,
});

export default router;
