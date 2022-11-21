import { createApp } from 'vue'
import './style.css'
import App from '@/App.vue'
import router from '@/router'
import store from '@store/index.js'
import LoginApi from '@mock/LoginApi.mock.js'

import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.min'
// axios
// pinia


const app = createApp(App);
// 加载路由
app.use(store)
app.use(router)
// 加载element-plus
app.use(ElementPlus, { locale: zhCn })
app.mount('#app')
