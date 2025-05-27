<template>
  <div class="contain">
    <div class="login">
      <p class="title">校园旧物交易平台</p>
      <div class="message">
        <p>登录</p>
        <input type="text" placeholder="用户名" v-model="username" />
        <input type="password" placeholder="密码" v-model="password" />
        <div>
          <el-button type="primary" size="large" @click="login({ username, password })">
            登录
          </el-button>
          <el-button type="primary" size="large" @click="dialogvist = true">注册</el-button>
        </div>
      </div>
    </div>
    <el-dialog v-model="dialogvist" title="注册" width="30%" :before-close="diaClose">
      <input type="text" placeholder="用户名" v-model="diausername" />
      <input type="password" placeholder="密码" v-model="diapassword1" />
      <input type="password" placeholder="再次输入密码" v-model="diapassword2" />
      <template #footer>
        <el-button type="primary" @click="goregister">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
  import { onBeforeMount, ref } from 'vue'
  import { AutoLogin, login } from '@/hook/Login.ts'
  import { register } from '@/hook/register.ts'
  import { ElMessage } from 'element-plus'

  const dialogvist = ref<boolean>(false)

  const username = ref<string>('')
  const password = ref<string>('')

  onBeforeMount(() => {
    AutoLogin()
  })

  const diausername = ref<string>('')
  const diapassword1 = ref<string>('')
  const diapassword2 = ref<string>('')
  const diaClose = () => {
    dialogvist.value = false
    diausername.value = diapassword1.value = diapassword2.value = ''
  }
  async function goregister() {
    if (diapassword1.value !== diapassword2.value) {
      ElMessage.error('两次密码不一致')
      return
    }
    try {
      await register(diausername.value, diapassword1.value)
      diausername.value = diapassword1.value = diapassword2.value = ''
      dialogvist.value = false
    } catch {
      dialogvist.value = true
    }
  }
</script>

<style scoped>
  .contain {
    background-image: url('/marketplace-4233691_1280.jpg');
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
  }
  .login {
    height: 100%;
    width: 45%;
    background-color: rgba(253, 253, 253, 0.5);
    float: right;
    display: flex;
    flex-direction: column;
    text-align: center;
    align-items: center;
  }
  .message {
    padding: 16px;
    height: 300px;
    width: 450px;
    border-radius: 15px;
    display: flex;
    flex-direction: column;
    align-items: center;
    background: linear-gradient(to left bottom, rgba(243, 175, 122, 0.69), rgba(184, 78, 78, 0.99));
  }
  .message p {
    text-align: center;
    font-size: 3rem;
  }
  .message input {
    width: 75%;
    height: 2em;
    border-radius: 5px;
    border: none;
    margin-top: 10px;
    margin-bottom: 10px;
    font-size: 1.2rem;
  }
  .title {
    text-align: center;
    font-size: 3em;
    margin-top: 150px;
    margin-bottom: 2%;
  }

  :deep(.el-dialog__body) {
    text-align: center;
  }

  :deep(.el-dialog) {
    margin-top: 200px;
  }

  .el-dialog__body input {
    height: 2em;
    width: 55%;
    font-size: 20px;
    margin-bottom: 15px;
  }
  :deep(.el-dialog__title) {
    font-size: 2em;
  }
</style>
