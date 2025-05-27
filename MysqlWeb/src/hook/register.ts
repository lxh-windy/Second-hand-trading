import { Register } from '@/api'
import { ElMessage } from 'element-plus'

async function register(username: string, password: string): Promise<void> {
  try {
    const response = await Register({ username, password })
    const data = response.data
    if (data.status === 200) {
      ElMessage.success('注册成功')
      return Promise.resolve()
    } else {
      ElMessage.error('注册失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject()
  }
}

export { register }
