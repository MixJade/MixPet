const mix = require("./mix");
const base = mix.base + "login/";

function regRequest(url, methodType, data) {
  let fullUrl = `${base}${url}`;
  let header = {
    'content-type': 'application/json' // 默认值
  };
  wx.showLoading({
    title: "加载中"
  });
  // session存储的cookie,有了redis就删掉
  if (wx.getStorageSync("cookieKey")) header.cookie = wx.getStorageSync("cookieKey");
  return new Promise((resolve, reject) => {
    wx.request({
      url: fullUrl,
      method: methodType,
      data,
      header,
      success: (res) => {
        // 保存cookie，为了使用session，后期如果改用redis,这里要去掉
        if (res.header['Set-Cookie']) wx.setStorageSync('cookieKey', res.header['Set-Cookie']);
        const code = res.data.code;
        if (code == 0) {
          wx.showToast({
            title: res.data.msg,
            icon: 'error'
          })
        }
        resolve(res.data)
      },
      fail: () => {
        wx.showToast({
          title: '服务器未响应',
          icon: 'error'
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  })
}

// 登录操作
function toLogin(data,backNum) {
  wx.request({
    url: base + "user",
    method: 'POST',
    data: data,
    success: (res) => {
      if (res.data.code == 1) {
        wx.setStorageSync("sessionId", res.header["Set-Cookie"]);
        wx.navigateBack({
          delta: backNum
        })
      } else if (res.data.code == 0) {
        wx.showToast({
          title: res.data.msg,
          icon: 'error'
        })
      }
    },
    fail: () => {
      wx.showToast({
        title: '服务器未响应',
        icon: 'error'
      })
    }
  })
}

const regReq = {
  base: base,
  plain: url => regRequest(url, 'GET', ""),
  post: (url, data) => regRequest(url, 'POST', data),
  put: (url, data) => regRequest(url, 'PUT', data),
  login: (data,backNum) => toLogin(data,backNum)
}

module.exports = regReq