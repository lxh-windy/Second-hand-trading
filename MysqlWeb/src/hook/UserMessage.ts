import { type updateUserMessage, UpdatePassword, UpdateUsername } from '@/api'
import { ElMessage } from 'element-plus'
import router from '@/router'

async function updatePassword(message: updateUserMessage) {
  try {
    const response = await UpdatePassword(message)
    if (response.data.status === 200) {
      ElMessage.success('修改密码完成，请重新登录')
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      await router.replace('/login')
    } else {
      ElMessage.error('修改密码失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject()
  }
}

async function updateUsername(message: updateUserMessage) {
  try {
    const response = await UpdateUsername(message)
    if (response.data.status === 200) {
      ElMessage.success('修改用户名完成，请重新登录')
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      await router.replace('/login')
    } else {
      ElMessage.error('修改用户名失败')
      return Promise.reject()
    }
  } catch {
    ElMessage.error('系统错误')
    return Promise.reject()
  }
}

export { updatePassword, updateUsername }
