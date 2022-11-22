<template>
  <el-container>
    <el-header>
      <h1>虹猫权限管理系统</h1>
    </el-header>
    <el-container>
      <el-aside>
        <el-menu
            active-text-color="#ffd04b"
            background-color="#606266"
            text-color="#fff"
            router
        >
          <el-sub-menu>
            <template #title>
              <el-icon>
                <Setting/>
              </el-icon>
              <span>系统设置</span>
            </template>
            <el-menu-item index="/account">
              <el-icon><User /></el-icon>
              账号管理
            </el-menu-item>
            <el-menu-item index="/role">
              <el-icon><Avatar /></el-icon>
              角色管理
            </el-menu-item>
            <el-menu-item index="/permission">
              <el-icon><Lock /></el-icon>
              权限管理
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item index="2">
            <el-icon>
              <icon-menu/>
            </el-icon>
            <span>Navigator Two</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import {Setting,Avatar,User,Lock} from '@element-plus/icons-vue'
</script>

<style scoped>
.el-container {
  height: 100%;
}

.el-header {
  background: #303133;
  line-height: var(--el-header-height);
}

.el-header h1 {
  margin: 0;
  color: #fff;
}

.el-aside {
  background: #606266;
}
</style>
