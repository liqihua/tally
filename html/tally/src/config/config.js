//const API_URL = "http://localhost:9003/tally"
const API_URL = "http://47.106.100.193"
const API = {
  LOGIN : API_URL + "/api/loginApiController/login",
  SAVE_LOG_PAY : API_URL + "/api/logPayApiController/saveLogPay",
  GET_LOG_LIST : API_URL + "/api/logPayApiController/findPayLogList",
  DELETE_PAY_LOG : API_URL + "/api/logPayApiController/deleteLogPay",
}
export default API

