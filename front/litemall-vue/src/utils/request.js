import axios from 'axios';
import { Dialog, Toast } from 'vant';

// create an axios instance
const service = axios.create({
  baseURL: "http://localhost:8080", // api 的 base_url
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
    config => {
    if (!config.headers['X-Litemall-Token']) {
      config.headers['X-Litemall-Token'] = `${window.localStorage.getItem(
        'Authorization'
      ) || ''}`;
    }
    return config;
  },
  err => Promise.reject(err)
)

// response interceptor
service.interceptors.response.use(
  response => {
    const res = response.data

    if (res.errno === 501) {
        //这里就是鉴权的处理
        //全部页面可用分为，需要权限和不需要权限，不需要权限的不用讨论，正常请求返回即可
        //需要权限的，则相比普通请求多携带一个token，而且后端要多检查一个token
        //没有权限前端是有预设处理的，所以后端只需要按前端错误码格式进行返回即可
        Toast.fail('请登录');
        setTimeout(() => {
          window.location = '#/login/'
        }, 1500)
      return Promise.reject('error')
    } else if (res.errno === 502) {
        Toast.fail('网站内部错误，请联系网站维护人员');
      return Promise.reject('error')
    } if (res.errno === 401) {
      Toast.fail('参数不对');
      return Promise.reject('error')
    } if (res.errno === 402) {
      Toast.fail('参数值不对');
      return Promise.reject('error')
    } if (res.errno === 601) {
        Toast.fail(res.errmsg);
      }
    else if (res.errno !== 0) {
      // 非5xx的错误属于业务错误，留给具体页面处理
      return Promise.reject(response)
    } else {
      return response
    }
  }, error => {
    console.log('err' + error)// for debug
    Dialog.alert({
        title: '警告',
        message: '登录连接超时'
      });
    return Promise.reject(error)
  })

export default service
