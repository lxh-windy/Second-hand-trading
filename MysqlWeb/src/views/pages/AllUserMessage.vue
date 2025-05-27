<template>
  <div class="contain">
    <table>
      <thead>
        <tr>
          <th>用户名</th>
          <th>权限</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody v-for="user in UserList" :key="user.username">
        <tr>
          <td>{{ user.username }}</td>
          <td>{{ user.pass === '0' ? '普通用户' : '管理员' }}</td>
          <td>
            <button @click="dialogshow(user.username, user.pass)">修改权限</button>
            <button @click="delUser(user.username)">删除用户</button>
          </td>
        </tr>
      </tbody>
    </table>
    <el-pagination
      layout="prev, pager, next,jumper"
      :total="Usertotal"
      :page-size="15"
      @current-change="changepage"
    />
    <el-dialog v-model="dialogVisible" :title="dialogtitle" width="500">
      <el-radio-group v-model="radiocheck">
        <el-radio value="0" size="large">普通用户</el-radio>
        <el-radio value="1" size="large">管理员用户</el-radio>
      </el-radio-group>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="changepass">确认</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
  import { onBeforeMount, ref } from 'vue'
  import { getAllUser } from '@/hook/getAllUser.ts'
  import { updatapass } from '@/hook/updatapass.ts'
  import { deleteUser } from '@/hook/deleteUser.ts'
  //修改权限
  const dialogVisible = ref<boolean>(false)
  const dialogtitle = ref<string>('')

  const checkname = ref<string>('')
  const radiocheck = ref<string | null>()

  function dialogshow(name: string, pass: string) {
    dialogtitle.value = '修改用户' + name + '的权限'
    radiocheck.value = pass
    checkname.value = name
    dialogVisible.value = true
  }

  //获取全部用户
  interface tabledata {
    username: string
    pass: string
  }
  interface PageResponse {
    pageNum: number
    total: number
    list: tabledata[]
  }
  const UserList = ref<Array<tabledata>>([])
  const Usertotal = ref<number>(0)
  const Page = ref<number>(1)
  onBeforeMount(async () => {
    try {
      const reponse = await getAllUser(Page.value)
      if (reponse !== null) {
        const data: PageResponse = reponse.data
        UserList.value = data.list
        Usertotal.value = data.total
        Page.value = data.pageNum
      }
    } catch {}
  })
  //修改权限
  async function changepass() {
    try {
      await updatapass({ username: checkname.value, pass: radiocheck.value as string })
      dialogVisible.value = false
      const reponse = await getAllUser(Page.value)
      if (reponse !== null) {
        const data: PageResponse = reponse.data
        UserList.value = data.list
        Usertotal.value = data.total
        Page.value = data.pageNum
      }
    } catch {
      dialogVisible.value = false
    }
  }

  //删除用户
  async function delUser(username: string) {
    try {
      await deleteUser(username)
      const reponse = await getAllUser(Page.value)
      if (reponse !== null) {
        const data: PageResponse = reponse.data
        UserList.value = data.list
        Usertotal.value = data.total
        Page.value = data.pageNum
      }
    } catch {}
  }
  //换页
  async function changepage(val: number) {
    try {
      Page.value = val
      const reponse = await getAllUser(Page.value)
      if (reponse !== null) {
        const data: PageResponse = reponse.data
        UserList.value = data.list
        Usertotal.value = data.total
        Page.value = data.pageNum
      }
    } catch {}
  }
</script>

<style scoped>
  .contain {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 20px;
  }
  table {
    border-collapse: collapse;
    width: 70%;
    margin-bottom: 15px;
    border: 2px solid black;
    table-layout: fixed;
  }
  table th {
    width: 30%;
    border-right: 2px solid black;
    border-bottom: 2px solid black;
    font-size: 23px;
  }
  table td {
    text-align: center;
    width: 30%;
    border: 1px solid black;
    font-size: 20px;
  }
  table td button {
    margin: 5px;
    font-size: 15px;
  }
  :deep(.number) {
    font-size: 20px;
  }
  :deep(.el-pagination__jump span) {
    font-size: 16px;
  }
  :deep(.el-pagination__jump input) {
    font-size: 16px;
  }
  :deep(.el-pager li.is-active) {
    color: white;
    background-color: rgba(239, 135, 9, 0.84);
  }
  :deep(.el-pager li:hover) {
    color: #fa8610;
  }
  :deep(.el-pager li) {
    background-color: rgba(111, 114, 115, 0.06);
    margin-right: 5px;
    margin-left: 5px;
  }
  :deep(.el-pagination button) {
    background-color: rgba(111, 114, 115, 0.06);
  }
  :deep(.el-pagination button:hover) {
    color: #fa8610;
  }
</style>
