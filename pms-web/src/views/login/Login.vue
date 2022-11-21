<template>
  <div class="login">


  <el-form ref="form" :model="userInfo" :rules="rules">
    <el-form-item class="center">
      <el-badge value="v1.0.0" :type="'success'">
        <h1>虹猫权限管理系统</h1>
      </el-badge>
    </el-form-item>
    <el-form-item label="账&emsp;号" prop="username">
      <el-input v-model="userInfo.username" clearable :autocomplete="false" placeholder="请输入手机号"></el-input>
    </el-form-item>
    <el-form-item label="密&emsp;码" prop="password">
      <el-input v-model="userInfo.password" type="password" clearable show-password placeholder="请输入密码"></el-input>
    </el-form-item>
    <el-form-item label="验证码" prop="code">
      <el-input v-model="userInfo.code" clearable :placeholder="codeBtnInfo.placeholder">
        <template #append>
          <el-button type="primary"
                     :size="'small'"
                     :loading="codeBtnInfo.loading"
                     @click="getCode"
                     :disabled="codeBtnInfo.disabled"

          >
          {{codeBtnInfo.text}}</el-button>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item class="center">
      <el-button type="primary" @click="login">登录</el-button>
      <el-button @click="login">注册</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>

<script setup>
import {Loading} from '@element-plus/icons-vue'
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";
import useStore from '@store/LoginStore.js'
import * as loginApi from '@api/login.api'
const userInfo = $ref({
  username: '',
  password: '',
  code:''
})
const form = $ref(null)
const codeBtnInfo = $ref({
  text: '获取验证码',
  placeholder: '请输入验证码',
  disabled: false,
  loading: false
})
const rules =$ref({
  username: [{required: true, message: '请输入账号', trigger: 'blur'}],
  password: [{required: true, message: '请输入密码', trigger: 'blur'}],
  code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
})
const router = useRouter();
const loginStore = useStore();

function login(){
  form.validate((isValid,errors)=>{
    if(isValid){
      loginApi.login(userInfo).then(res=>{
        ElMessage.success('登录成功')
        console.log(res.data);
        loginStore.setLoginStatus(res.data)
        router.push({name:'Home'})
      }).catch(data=>{
        let msg = data;
        if(data.msg){
          msg = data.msg
          console.log(data)
        }
        ElMessage.error({
          message: msg,
          group: true
        })
      })
    }
  })
}

function getCode(){
  codeBtnInfo.loading = true
  codeBtnInfo.text = "正在获取验证码"
  codeBtnInfo.disabled = true
  setTimeout(()=>{
    codeBtnInfo.loading = false
    codeBtnInfo.placeholder = "请输入验证码,序号: 40"
    let time = 60;
    let index = setInterval(()=>{
      codeBtnInfo.text = time + "s后重新获取"
      time--
      if(time === 0){
        codeBtnInfo.text = "获取验证码"
        codeBtnInfo.disabled = false
        clearInterval(index)
      }
    },1000);
  },5000);
}
</script>
<style scoped>
.login{
  height: 100%;
  width: 100%;
  background: url("@assets/background.jpg") no-repeat;
  background-size: cover;
}
.el-form{
  max-width: 400px;
  width: 60vw;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  box-shadow: 0 0 10px rgba(0,0,0,0.2);
  padding: 20px;
  border-radius: 5px;
  transition: all 0.5s;
  animation: breath 2s infinite alternate;
/*  添加背景毛玻璃*/
  background: rgba(255,255,255,0.3);
  backdrop-filter: blur(10px);
}
/*.el-form:hover{*/
/*  box-shadow: 0 0 20px rgba(0,0,0,0.5);*/
/*  transition: all 0.5s;*/
/*}*/
@keyframes breath {
  form {
    box-shadow: 0 0 10px rgba(68, 229, 36, 0.3);
  }
  to{
    box-shadow: 0 0 50px rgba(68, 229, 36,0.6);
  }
}

.center :deep(.el-form-item__content) {
  justify-content: center;
}
.el-form-item{
  --el-text-color-regular: #000;
}
</style>
