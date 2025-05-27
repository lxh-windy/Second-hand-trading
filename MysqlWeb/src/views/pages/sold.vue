<template>
  <div class="contain">
    <table>
      <thead>
        <tr>
          <th>物品名</th>
          <th>价格</th>
          <th>时间</th>
          <th>是否在售</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody v-for="item in list" :key="item.gid">
        <tr>
          <td>{{ item.gname }}</td>
          <td>{{ item.price }}</td>
          <td>{{ item.time.split('T')[0] + ' ' + item.time.split('T')[1] }}</td>
          <td>{{ item.status === '0' ? '在售' : '已售出' }}</td>
          <td>
            <button @click="diashow(item.gname, item.gid, item.price)" v-if="item.status === '0'">
              修改售价
            </button>
            <button style="margin-left: 10px" v-if="item.status === '0'" @click="delgood(item.gid)">
              取消销售
            </button>
            <p v-else>无</p>
          </td>
        </tr>
      </tbody>
    </table>
    <el-pagination
      layout="prev, pager, next,jumper"
      :total="total"
      :page-size="15"
      v-model="Page"
      @current-change="changePage"
    />
    <el-dialog v-model="dialogVisible" :title="dialogtitle" width="500">
      <input type="number" v-model="diaNum" />
      <span>元</span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="changeprice">确认</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
  import { onBeforeMount, ref } from 'vue'
  import { deletegood, getAllrecode, updatePrice } from '@/hook/sold.ts'
  import { userStore } from '@/stores/user.ts'

  interface tabledata {
    gid: string
    gname: string
    price: number
    status: string
    time: string
  }
  const total = ref<number>(1)
  const Page = ref<number>(1)
  const dialogVisible = ref<boolean>(false)
  const dialogtitle = ref<string>('')
  const diaNum = ref<number>(0)
  const diagid = ref<string>('')
  const diaName = ref<string>('')
  const user = userStore()
  const list = ref<Array<tabledata>>([])

  function diashow(gname: string, gid: string, price: number) {
    dialogtitle.value = '修改' + gname + '的售价'
    diaName.value = gname
    diaNum.value = price
    diagid.value = gid
    dialogVisible.value = true
  }
  //修改价格
  async function changeprice() {
    try {
      await updatePrice(diagid.value, diaNum.value)
      dialogVisible.value = false
    } catch {
    } finally {
      const res = await getAllrecode(Page.value, user.username)
      if (res !== null) {
        const data = res.data
        list.value = data.list
        total.value = data.total
        Page.value = data.pageNum
      }
    }
  }

  //删除商品
  async function delgood(gid: string) {
    try {
      await deletegood(gid)
    } catch {
    } finally {
      const res = await getAllrecode(Page.value, user.username)
      if (res !== null) {
        const data = res.data
        list.value = data.list
        total.value = data.total
        Page.value = data.pageNum
      }
    }
  }

  onBeforeMount(async () => {
    try {
      const res = await getAllrecode(Page.value, localStorage.getItem('username') as string)
      if (res !== null) {
        const data = res.data
        list.value = data.list
        total.value = data.total
        Page.value = data.pageNum
      }
    } catch {}
  })

  //换页
  async function changePage(val: number) {
    Page.value = val
    try {
      const res = await getAllrecode(Page.value, user.username)
      if (res !== null) {
        const data = res.data
        list.value = data.list
        total.value = data.total
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
    table-layout: fixed;
    border-collapse: collapse;
    width: 90%;
    margin-bottom: 15px;
    border: 2px solid black;
  }
  table th {
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
  .el-dialog input {
    font-size: 20px;
    height: 2em;
    width: 3em;
  }
  .el-dialog span {
    font-size: 20px;
    height: 2em;
    width: 3em;
    margin-left: 10px;
  }
</style>
