import { createApp } from 'vue';
import App from './App.vue';
import './scss/main.scss';
import router from './router.js';

createApp(App).use(router).mount('#app');
