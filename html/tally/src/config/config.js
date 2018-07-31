//const API_URL = "http://localhost:9003/tally"
const API_URL = "http://47.106.100.193"
const API = {
  LOGIN : API_URL + "/api/loginApiController/login",
  SAVE_LOG_PAY : API_URL + "/api/logPayApiController/saveLogPay",
  GET_LOG_LIST : API_URL + "/api/logPayApiController/findPayLogList",
  DELETE_PAY_LOG : API_URL + "/api/logPayApiController/deleteLogPay",
}

const productType = [
  {
    label : '一日三餐',
    value : '一日三餐'
  },
  {
    label : '运动健身',
    value : '运动健身'
  },
  {
    label : '娱乐消费',
    value : '娱乐消费'
  },
  {
    label : '聚餐消费',
    value : '聚餐消费'
  },
  {
    label : '房屋租金',
    value : '房屋租金'
  },
  {
    label : '交通出行',
    value : '交通出行'
  },
  {
    label : '生活用品',
    value : '生活用品'
  },
  {
    label : '衣服鞋子',
    value : '衣服鞋子'
  },
  {
    label : '电子产品',
    value : '电子产品'
  },
  {
    label : '医疗消费',
    value : '医疗消费'
  }
]

export default API
export {productType}

