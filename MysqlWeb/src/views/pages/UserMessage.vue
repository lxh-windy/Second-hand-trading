<template>
  <div class="contain">
    <div class="username">
      <p>用户名：</p>
      <input type="text" v-if="usernameChange" v-model="inputName" />
      <span v-else>{{ user.username }}</span>
      <button @click="changName" v-if="usernameChange != true">修改</button>
      <button v-show="usernameChange" @click="changeName">保存</button>
      <button @click="usernameChange = false" v-if="usernameChange === true">返回</button>
    </div>
    <br />
    <div class="password">
      <div>修改密码：<input type="password" v-model="password1" /></div>
      <div>再次确认密码：<input type="password" v-model="password2" /></div>
      <button @click="changepassword">确认修改密码</button>
    </div>
  </div>
</template>

<style scoped>
  .contain {
    padding-top: 50px;
    padding-left: 50px;
    display: flex;
    flex-direction: column;
  }
  .username {
    float: left;
    display: flex;
  }
  .username p {
    font-size: 25px;
    margin-right: 10px;
  }
  .username span {
    font-size: 25px;
    margin-right: 10px;
  }
  .username input {
    font-size: 25px;
    width: 5em;
  }
  .username button {
    font-size: 25px;
    margin-right: 10px;
    margin-left: 10px;
  }
  .password {
    float: left;
    margin-top: 50px;
  }
  .password div {
    font-size: 25px;
    width: 500px;
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
    margin-bottom: 10px;
  }
  .password input {
    font-size: 25px;
  }
  .password button {
    font-size: 25px;
  }
</style>

<script lang="ts" setup>
  import { ref } from 'vue'
  import { userStore } from '@/stores/user.ts'
  import { updatePassword, updateUsername } from '@/hook/UserMessage.ts'
  import { ElMessage } from 'element-plus'

  const usernameChange = ref<boolean>(false)
  const user = userStore()
  const inputName = ref<string>('')
  const password1 = ref<string>('')
  const password2 = ref<string>('')

  function changName() {
    inputName.value = user.username
    usernameChange.value = true
  }

  async function changeName() {
    try {
      await updateUsername({ oldData: user.username, newData: inputName.value })
    } catch {
    } finally {
      usernameChange.value = false
    }
  }

  async function changepassword() {
    try {
      if (password1.value !== password2.value) {
        ElMessage.error('两次密码不一致，无法修改')
        return
      }
      await updatePassword({ oldData: user.username, newData: password1.value })
    } catch {}
  }
</script>
