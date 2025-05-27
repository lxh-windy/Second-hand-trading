import path from './path.ts'
import axios from '../axios'

interface LoginMessage {
  username: string
  password: string
}

interface UserMessage {
  username: string
  pass: string
}

interface rechargeMessage {
  username: string
  money: number
}

interface goodsMessage {
  gid?: string
  gname: string
  price: number
  username?: string
}

interface updateUserMessage {
  oldData: string
  newData: string
}

function Login(message: LoginMessage) {
  return axios.post(path.base + path.path.login, message)
}

function Check() {
  return axios.get(path.base + path.path.check)
}

function getUSer(username: string) {
  return axios.get(path.base + path.path.getuser, {
    params: {
      username: username,
    },
  })
}

function Register(message: LoginMessage) {
  return axios.post(path.base + path.path.register, message)
}

function GetAllUser(page: number) {
  return axios.get(path.base + path.path.getAllUser, {
    params: {
      pageNum: page,
    },
  })
}

function UpdatePass(message: UserMessage) {
  return axios.post(path.base + path.path.updatapass, message)
}

function DeleteUser(username: string) {
  return axios.get(path.base + path.path.deleteUser, {
    params: {
      username: username,
    },
  })
}

function Recharge(message: rechargeMessage) {
  return axios.post(path.base + path.path.recharge, message)
}

function GetAllgoods(page: number) {
  return axios.get(path.base + path.path.getAllGood, {
    params: {
      PageNum: page,
    },
  })
}

function Addgood(message: goodsMessage) {
  return axios.post(path.base + path.path.addgood, message)
}

function Shopping(username: string, gid: string) {
  return axios.get(path.base + path.path.shopping, {
    params: {
      username: username,
      gid: gid,
    },
  })
}

function GetAllrecord(page: number, username: string) {
  return axios.get(path.base + path.path.getAllrecord, {
    params: {
      username: username,
      pageNum: page,
    },
  })
}

function Updateprice(gid: string, Newmoney: number) {
  return axios.get(path.base + path.path.updatePrice, {
    params: {
      gid: gid,
      NewMoney: Newmoney,
    },
  })
}

function Deletegood(gid: string) {
  return axios.delete(path.base + path.path.deletegood, {
    params: {
      gid: gid,
    },
  })
}

function GetAllBuy(username: string, pageNum: number) {
  return axios.get(path.base + path.path.getAllBuy, {
    params: {
      username: username,
      pageNum: pageNum,
    },
  })
}

function UpdatePassword(message: updateUserMessage) {
  return axios.post(path.base + path.path.updatePassword, message)
}

function UpdateUsername(message: updateUserMessage) {
  return axios.post(path.base + path.path.updateUsername, message)
}

function GetAllBuyMessage(page: number) {
  return axios.get(path.base + path.path.getAllBuyMessage, {
    params: {
      pageNum: page,
    },
  })
}

export {
  type UserMessage,
  type LoginMessage,
  type rechargeMessage,
  type goodsMessage,
  type updateUserMessage,
  Login,
  Check,
  getUSer,
  Register,
  GetAllUser,
  UpdatePass,
  DeleteUser,
  Recharge,
  GetAllgoods,
  Addgood,
  Shopping,
  GetAllrecord,
  Updateprice,
  Deletegood,
  GetAllBuy,
  UpdatePassword,
  UpdateUsername,
  GetAllBuyMessage,
}
