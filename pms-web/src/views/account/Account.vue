<template>
  <div class="account">
    <div class="search">
      <el-form inline :size="'small'">
        <el-form-item label="用户名">
          <el-input v-model="search.username" placeholder="请输入用户名" clearable/>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="search.status" clearable placeholder="请选择状态">
            <el-option label="启用" :value="1"/>
            <el-option label="封禁" :value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="注册时间">
          <el-date-picker
              v-model="search.createTime"
              type="datetimerange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              clearable
          />
        </el-form-item>
        <el-form-item>
          <el-button @click="loadData" :icon="Search">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="data">
      <div class="btns">
        <el-button @click="updateAccount.show=true;updateAccount.type='新增';updateAccount.data={}">新增用户</el-button>
        <el-button @click="batchDelete">批量删除</el-button>
      </div>
      <el-table ref="tableData" :data="accounts" v-loading="loading">
        <el-table-column type="selection" fixed="left" width="50"></el-table-column>
        <el-table-column prop="username" label="用户名"/>
        <el-table-column prop="updateTime" label="最后修改时间"/>
        <el-table-column prop="createTime" label="注册时间"/>
        <el-table-column prop="status" label="状态">
          <template #default="{row}">
            <el-switch
                v-model="row.status"
                inline-prompt
                :active-value="0"
                :inactive-value="1"
                :loading="row.statusLoading"
                active-text="正常"
                inactive-text="封禁"
                @change = "changeStatus(row)"
                style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
            />
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作">
          <template #default="{row}">
            <el-button :size="'small'" @click="updateAccount.show = true;updateAccount.data= row;updateAccount.type='修改'">修改</el-button>
            <el-popconfirm
                :title="`是否删除[${row.username}]?`"
                @confirm="deleteAccount(row)"
            >
              <template #reference>
                <el-button :size="'small'" type="danger">删除</el-button>
              </template>
            </el-popconfirm>

          </template>
        </el-table-column>
        <template #append>
          <el-pagination
              v-model:current-page="page.page"
              v-model:page-size="page.pageSize"
              :page-sizes="[10, 20, 30, 40]"
              layout="total, sizes, prev, pager, next, jumper"
              :total="page.total"
              @size-change="loadData"
              @current-change="loadData"
          />
        </template>
      </el-table>

    </div>
    <el-dialog draggable
               :title="`${updateAccount.type}账号信息`"
               v-model="updateAccount.show">
      <Update v-if="updateAccount.type === '修改'" :account="updateAccount.data" @close="updateAccount.show = false"/>
      <Insert v-if="updateAccount.type === '新增'" :account="updateAccount.data" @close="updateAccount.show = false"/>
    </el-dialog>
  </div>
</template>

<script setup>
import {ref, watch, computed, reactive} from 'vue';
import {Search} from '@element-plus/icons-vue'
import * as accountApi from '@api/account.api'
import Update from './components/Update.vue'
import Insert from "@views/account/components/Insert.vue";
import {ElMessage} from "element-plus";

const tableData = ref()
const updateAccount = reactive({
  show:false,
  type:'insert',
  data: {}
})

const search = $ref({
  username: '',
  status: '',
  createTime: [],
});

const accounts = ref([])
const loading = ref(false)
const page = ref({
  pageSize: 10,
  page: 1,
  total: 0
})

function batchDelete(){
  let selectionRows = tableData.value.getSelectionRows();
  if(selectionRows.length === 0){
    ElMessage.warning('请选择要删除的数据')
    return
  }
  let ids = selectionRows.map(item => item.id)
  accountApi.deleteByIds(ids).then(res => {
    if(res.code == 200){
      ElMessage.success('删除成功')
      accounts.value = accounts.value.filter(item => !ids.includes(item.id))
    }
  })
}

/**
 * 加载数据
 */
function loadData() {
  loading.value = true;
  accountApi.findAll({
    ...search,
    ...page.value
  }).then(rs => {
    loading.value = false
    accounts.value = rs.data.list
    accounts.value.map(account=>account.statusLoading = false)
    page.value = rs.data.pageInfo
  })
}

function changeStatus(account){
  account.statusLoading = true;
  accountApi.updateStatus(account.id, account.status)
      .then(rs => {
    if(rs.code != 200){
      account.status = account.status == 0 ? 1 : 0
    }
  }).catch(()=>{
    account.status = account.status == 0 ? 1 : 0
  }).finally(()=>{
    account.statusLoading = false;
  })
}

function deleteAccount(account){
  accountApi.deleteByIds([account.id]).then(rs => {
    if(rs.code == 200){
      accounts.value = accounts.value.filter(item => item.id != account.id)
    }
  })
}
loadData();

</script>

<style scoped>
.el-pagination {
  margin: 10px auto;
}
</style>
