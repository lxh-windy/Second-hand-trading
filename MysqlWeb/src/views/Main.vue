<template>
  <div class="contain">
    <header class="head">校园旧货管理系统<button @click="exit">退出</button></header>
    <nav>
      <el-menu class="el-menu-vertical-demo" default-active="/main/shop" router>
        <el-menu-item index="/main/shop">交易大厅</el-menu-item>
        <el-menu-item index="/main/money">充值大厅</el-menu-item>
        <el-sub-menu index="3">
          <template #title>
            <span>个人中心</span>
          </template>
          <el-menu-item-group title="交易信息">
            <el-menu-item index="/main/buy">已购买</el-menu-item>
            <el-menu-item index="/main/sold">已售出</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="个人信息">
            <el-menu-item index="/main/user">修改个人信息</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
        <el-sub-menu index="4" v-show="pass == '管理员'">
          <template #title>
            <span>管理中心</span>
          </template>
          <el-menu-item index="/main/soldmanager">全部交易信息</el-menu-item>
          <el-menu-item index="/main/usermanager">全部用户信息</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </nav>
    <div class="context"><RouterView /></div>
    <div class="right">
      <div>
        <p>用户名：{{ username }}</p>
        <p>身份：{{ pass }}</p>
        <p>余额：{{ money }}</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
  .head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    text-align: left;
    height: 10%;
    color: #100f0f;
    font-size: 40px;
    background: linear-gradient(to left bottom, rgba(243, 175, 122, 0.9), rgba(239, 48, 48, 0.99));
  }

  .head button {
    height: 50px;
    width: 100px;
    font-size: 20px;
    border-radius: 5px;
    margin-right: 20px;
  }

  nav {
    width: 10%;
    height: 90%;
    float: left;
    background: rgba(228, 214, 205, 0.68);
  }

  .el-menu {
    border: none;
  }

  .el-menu-item,
  :deep(.el-sub-menu__title) {
    font-size: 18px;
    background: rgba(228, 214, 205, 0.68);
  }

  :deep(.el-menu-item-group__title) {
    background: rgba(228, 214, 205, 0.68);
    color: rgba(2, 2, 2, 0.62);
    font-size: 18px;
  }
  .is-active {
    color: #100f0f;
    background-color: rgba(3, 146, 248, 0.25);
  }
  .context {
    width: 70%;
    height: 90%;
    float: left;
    padding: 10px;
  }
  .right {
    width: 20%;
    height: 90%;
    float: left;
    padding: 10px;
  }
  .right div {
    width: 100%;
    height: 400px;
    border: 1px solid #2c3e50;
    border-radius: 5px;
    padding: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
  .right div p {
    font-size: 25px;
    margin-bottom: 50px;
  }
</style>

<script setup lang="ts">
  import { userStore } from '../stores/user.ts'
  import { storeToRefs } from 'pinia'
  import router from '@/router'
  import { onBeforeMount } from 'vue'
  import { check } from '@/hook/check.ts'
  import { getuser } from '@/hook/Login.ts'

  const { username, pass, money } = storeToRefs(userStore())

  function exit() {
    router.replace('/login')
    localStorage.removeItem('token')
    localStorage.removeItem('username')
  }

  onBeforeMount(async () => {
    await check()
    await getuser(localStorage.getItem('username') as string)
  })
</script>
