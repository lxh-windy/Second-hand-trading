import axios from 'axios'
import router from '@/router'
import { ElMessage } from 'element-plus'

const instance = axios.create({ timeout: 5000 })

instance.interceptors.request.use(
  (config) => {
    if (localStorage.getItem('token') != null) {
      config.headers['token'] = localStorage.getItem('token')
      config.headers['username'] = localStorage.getItem('username')
    }
    return config
  },
  (error) => {
    console.log(error)
    return Promise.reject(error)
  },
)

instance.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    if (error.status == 403) {
      if (localStorage.getItem('token') != null) {
        ElMessage.error('登录失效')
      }
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      router.replace('/login')
    }
    return Promise.reject(error)
  },
)

export default instance
