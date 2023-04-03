// pages/register/register.js
const logReq = require("../../utils/logReq");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    email: '',
    sms: '',
    name: '',
    sex: true,
    username: '',
    password: '',
    btnText: "发送验证码",
    btnDisable: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
  // 性别选择
  onChangeSex(e) {
    console.log(e.detail.value);
    this.setData({
      sex: e.detail.value
    });
  },
  // 进行注册
  toRegister() {
    const json = {
      "checkCode": this.data.sms,
      "clientTel": this.data.email,
      "clientName": this.data.name,
      "clientGender": this.data.sex,
      "clientUsername": this.data.username,
      "clientPassword": this.data.password
    }
    for (const key in json) {
      if (json[key] === "") {
        // 存在空值，返回并弹出警告
        wx.showToast({
          icon: 'error',
          title: '表单存在空值',
        })
        return;
      }
    }
    logReq.post("register", json).then(res => {
      if (res.code == 1) {
        wx.showToast({
          icon: 'success',
          title: '注册成功',
        })
        setTimeout(() => {
          const dataLog = {
            username: json.clientUsername,
            password: json.clientPassword,
            remember: true,
          }
          logReq.login(dataLog,2)
        }, 2000)
      }
    })
  },
  // 注册发送验证码
  sendMail() {
    const mail = this.data.email
    let str = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/
    if (str.test(mail)) {
      /*格式正确，发送邮件*/
      this.setBtnTime()
      logReq.plain("regMail?mail=" + mail)
    } else {
      /*格式不正确，弹窗提示*/
      wx.showToast({
        icon: 'error',
        title: '请输入正确邮箱',
      })
    }
  },
  // 按钮倒计时
  setBtnTime() {
    let countDown = 15;
    const that = this
    that.setData({
      btnText: "发送验证码",
      btnDisable: "true"
    })
    let interval = setInterval(function () {
      if (countDown === 0) {
        clearInterval(interval)
        that.setData({
          btnText: "发送验证码",
          btnDisable: ""
        })
        return
      }
      that.setData({
        btnText: "重新发送(" + countDown + ")",
      })
      countDown--;
    }, 1000)
  },
  // 用户名校验
  sureUser(e) {
    logReq.plain("isExist?regUse=" + e.detail.value)
  }
})