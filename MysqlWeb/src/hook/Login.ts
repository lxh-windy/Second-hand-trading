import { type LoginMessage, Login, Check, getUSer } from '@/api'
import router from '@/router'
import { ElMessage } from 'element-plus'
import { AxiosError } from 'axios'
import { userStore } from '../stores/user.ts'

async function login(message: LoginMessage) {
  try {
    const res = await Login(message)
    const response = res.data
    if (response.status === 200) {
      ElMessage.success('登录成功')
      localStorage.setItem('token', response.data)
      localStorage.setItem('username', message.username)
      await getuser(message.username)
      await router.push('/main')
    } else {
      ElMessage.error('用户名或密码错误')
    }
  } catch (error) {
    if (error instanceof AxiosError) {
      ElMessage.error(error.status + '，系统错误')
      console.error(error)
    }
  }
}

async function AutoLogin(): Promise<void> {
  try {
    const response = await Check()
    if (response.status === 200) {
      await getuser(localStorage.getItem('username') as string)
      await router.push('/main')
    }
  } catch {}
}

async function getuser(username: string): Promise<void> {
  try {
    const res = await getUSer(username)
    const data = res.data
    if (data.status === 200) {
      const user = userStore()
      const userdata = data.data
      user.username = userdata.username
      user.money = userdata.money
      if (userdata.pass === 0) {
        user.pass = '普通用户'
      } else {
        user.pass = '管理员'
      }
    } else {
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      ElMessage.error('获取用户信息错误')
      await router.replace('/login')
    }
  } catch {
    ElMessage.error('系统错误')
  }
}

export { login, AutoLogin, getuser }
