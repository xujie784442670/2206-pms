<template>
  <el-form >
    <el-form-item label="账号" prop="username">
      <el-input v-model="accountModel.username" :disabled="true" placeholder="请输入账号"/>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input  v-model="accountModel.password" type="password" show-password clearable placeholder="请输入密码"/>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="save">保存</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>
import {defineProps, defineEmits, onMounted, watch} from 'vue'
import * as accountApi from '@api/account.api.js'
const props = defineProps({
  account:{
    type:Object,
    required:true
  }
})

let accountModel  = $ref({
  username:'',
  password:'',
  id:''
})

watch(props,(newVal)=>{
  accountModel = props.account
})

onMounted(()=>{
  accountModel = props.account
})

const emits = defineEmits(['close'])


function save(){
  accountApi.updateById(accountModel).then(res=>{
    if(res.code == 200){
      emits('close',true)
    }
  })
}
</script>

<style scoped>

</style>
