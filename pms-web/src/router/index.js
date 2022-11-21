import {createRouter,createWebHistory} from "vue-router";
import LoginRouter from "@router/LoginRouter";
import ErrorRouter from "@router/ErrorRouter.js";
import HomeRouter from "@router/HomeRouter.js";
import useLogin from '@store/LoginStore.js'
const routes = []

// 登录路由
routes.push(LoginRouter)
routes.push(HomeRouter)
// 错误路由
routes.push(ErrorRouter)

const router = createRouter({
    routes,
    history: createWebHistory()
})

router.beforeEach((to, from) => {
    const loginStore = useLogin()
    if(to.name == 'Login'){
        return true
    }
    if(loginStore.isLogin()){
        return true
    }
    return {name:'Login'}
});
export default router
