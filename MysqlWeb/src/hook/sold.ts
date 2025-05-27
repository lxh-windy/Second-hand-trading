import { Deletegood, GetAllrecord, Updateprice } from '@/api'
import { ElMessage } from 'element-plus'

async function getAllrecode(page: number, username: string) {
  try {
    const reponse = await GetAllrecord(page, username)
    if (reponse.data.status === 200) {
      return reponse.data
    } else {
      ElMessage.error('查询失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
  }
}

async function deletegood(gid: string) {
  try {
    const reponse = await Deletegood(gid)
    if (reponse.data.status === 200) {
      ElMessage.success('删除成功')
    } else {
      ElMessage.error('删除失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject()
  }
}

async function updatePrice(gid: string, Newmoney: number) {
  try {
    const reponse = await Updateprice(gid, Newmoney)
    if (reponse.data.status === 200) {
      ElMessage.success('更新成功')
    } else {
      ElMessage.error('更新失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject()
  }
}

export { getAllrecode, deletegood, updatePrice }
