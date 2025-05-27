import { GetAllUser } from '@/api'
import { ElMessage } from 'element-plus'

async function getAllUser(page: number) {
  try {
    const reponse = await GetAllUser(page)
    if (reponse.data.status === 200) {
      return Promise.resolve(reponse.data)
    } else {
      ElMessage.error('获取用户名单失败')
      return Promise.reject(null)
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject(null)
  }
}
export { getAllUser }
