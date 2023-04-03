const base = "http://localhost:8080/"

function myRequest(url, methodType, data) {
  let fullUrl = `${base}${url}`;
  let header = {
    'content-type': 'application/json' // 默认值
  };
  wx.showLoading({
    title: "加载中"
  });
  // 将存储的 cookie 带上,在登录页面获取cookie
  if (wx.getStorageSync("sessionId")) header.cookie = wx.getStorageSync("sessionId");
  return new Promise((resolve, reject) => {
    wx.request({
      url: fullUrl,
      method: methodType,
      data,
      header,
      success: (res) => {
        const code = res.data.code;
        if (code == 0) {
          wx.showToast({
            title: res.data.msg,
            icon: 'error'
          })
        } else if (code == 401) {
          wx.navigateTo({
            url: '/pages/login/login',
          })
        }
        resolve(res.data)
      },
      fail: () => {
        wx.showToast({
          title: '服务器未响应',
          icon: 'error'
        })
        wx.navigateTo({
          url: '/pages/login/login',
        })
      },
      complete: () => {
        wx.hideLoading()
      }
    })
  })
}

const mix = {
  base: base,
  plain: url => myRequest(url, 'GET', ""),
  get: (url, data) => myRequest(url, 'GET', data),
  post: (url, data) => myRequest(url, 'POST', data),
  put: (url, data) => myRequest(url, 'PUT', data),
  delete: (url, data) => myRequest(url, 'DELETE', data)
}

module.exports = mix