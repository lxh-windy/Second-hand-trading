import { Addgood, GetAllgoods, type goodsMessage, Shopping } from '@/api'
import { ElMessage } from 'element-plus'

async function getAllgoods(PageNum: number) {
  try {
    const response = await GetAllgoods(PageNum)
    const data = response.data
    if (data.status === 200) {
      return data
    } else {
      return null
    }
  } catch {
    ElMessage.error('系统错误')
    return null
  }
}

async function addgoods(message: goodsMessage) {
  try {
    const reponse = await Addgood(message)
    const data = reponse.data
    if (data.status === 200) {
      ElMessage.success('添加成功')
    } else {
      ElMessage.error('添加失败')
      return null
    }
  } catch {
    ElMessage.error('系统错误')
  }
}

async function shopping(username: string, gid: string) {
  try {
    const response = await Shopping(username, gid)
    const data = response.data
    if (data.status === 200) {
      ElMessage.success('购买成功')
    } else {
      ElMessage.error('购买失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject()
  }
}

export { getAllgoods, addgoods, shopping }
