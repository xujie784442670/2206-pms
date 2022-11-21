import axios from 'axios'
import {ElMessage} from "element-plus";

const baseURL = import.meta.env.VITE_BASE_URL

const instance = axios.create({
    baseURL,
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json'
    }
});

export default instance

/**
 * 响应拦截器
 */
instance.interceptors.response.use((response) => {
    return new Promise((resolve, reject) => {
        if(response.data.code == 200){
            resolve(response.data)
        }else{
            reject(response.data)
        }
    })
}, (error) => {
    if(error.response.data){
        ElMessage.error({
            message: error.response.data.msg,
            group: true
        })
    }else{
        ElMessage.error({
            message: error.message,
            group: true
        })
    }
})

/**
 * Get请求
 * @param url 请求地址
 * @param params 请求参数
 * @returns {Promise<AxiosResponse<any>>}
 */
export function get(url, params) {
    // promise
    return instance.get(url, {
        params
    })
}

/**
 * Post请求
 * @param url 请求地址
 * @param data 请求参数
 * @returns {Promise<AxiosResponse<any>>}
 */
export function post(url, data) {
    return instance.post(url, data)
}

/**
 * Put请求
 * @param url 请求地址
 * @param data 请求参数
 * @returns {Promise<AxiosResponse<any>>}
 */
export function put(url, data) {
    return instance.put(url, data)
}

/**
 * Delete请求
 * @param url 请求地址
 * @param params 请求参数
 * @returns {Promise<AxiosResponse<any>>}
 */
export function remove(url, params) {
    return instance.delete(url, {
        params
    })
}

