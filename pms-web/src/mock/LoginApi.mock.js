import {mock} from 'mockjs'

export default mock(/\/login/,'post',{
    "msg": "OK", //响应描述
    "code": 200, //响应码
    "data": {
        //8966f16b-2dda-4d1e-ad15-26ca4e506f4a
        "token": /\w{8}-\w{4}-\w{4}-\w{4}-\w{12}/, //授权令牌
        "permissions": [
            "account:insert",
            "account:login",
            "account:delete"
        ], //权限代码列表
        "roles": [
            "account",
            "role",
            "permession"
        ] //角色代码列表
    }
})
