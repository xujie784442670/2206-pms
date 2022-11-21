import request from '@utils/request'
/**
 * 用户登录
 * @param params {username,password,code} 用户信息
 * @returns {Promise<AxiosResponse<any>>}
 */
export function login(params){
    return request.post('/login',params)
}
