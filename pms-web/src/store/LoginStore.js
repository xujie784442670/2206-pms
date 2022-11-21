import {defineStore} from "pinia";

const state = {
    token: '',
    permission: [],
    roles: [],
}

export default defineStore('login',{
    state: ()=>state,
    actions:{
        /**
         * 设置用户登录状态
         * @param data {token,permission,roles} 用户信息
         */
        setLoginStatus(data){
            this.token = data.token
            this.permission = data.permission
            this.roles = data.roles
        }
    },
    getters:{
        /**
         * 获取用户登录状态
         * @returns {function(): boolean}
         */
        isLogin(){
            return ()=>!!this.token
        }
    },
    persist:{
        // 开启持久化
        enabled: true
    }
})
