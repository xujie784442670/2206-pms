import {get, post, remove,put} from '@utils/request'

/**
 * 获取用户信息
 * @param params {username:string,createTime:Array,status:number,pageSize:number,page:number} 查询条件
 * @returns {Promise<AxiosResponse<*>>}
 */
export const findAll = params =>{
    return get('/account',params)
}
/**
 * 修改用户状态
 * @param id 用户id
 * @param status 用户状态
 * @returns {Promise<AxiosResponse<*>>}
 */
export const updateStatus = (id,status) =>{
    return get(`/account/change`,{id,status})
}
/**
 * 修改用户信息
 * @param account {id,password} 用户信息
 * @returns {Promise<AxiosResponse<*>>}
 */
export const updateById = (account) =>{
    return post('/account',account)
}
/**
 * 新增用户信息
 * @param account {id,username,password} 用户信息
 * @returns {Promise<AxiosResponse<*>>}
 */
export const insertAccount = (account) =>{
    return put('/account',account)
}
/**
 * 批量删除用户
 * @param ids {Array} 用户id数组
 * @returns {Promise<AxiosResponse<*>>}
 */
export const deleteByIds = (ids) =>{
    return remove('/account/delete',ids)
}
