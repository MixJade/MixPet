// pages/clientUpdate/clientUpdate.js
const mix = require("../../utils/mix");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    columns01: ['女', '男'], //性别选择器中的值
    base: "/images/",
    clientId: 0,
    clientInfo: "",
    clientName: "莹",
    clientPhoto: "zs.jpg",
    clientTel: "s",
    clientUsername: "g",
    clientAge: "2023-3-27",
    clientGender: '0'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    mix.plain("login/user").then(res => {
      if (res) {
        this.setData({
          clientId: res.clientId,
          clientInfo: res.clientInfo,
          clientName: res.clientName,
          clientPhoto: res.clientPhoto,
          clientTel: res.clientTel,
          clientUsername: res.clientUsername,
          clientAge: res.clientAge,
          clientGender: res.clientGender,
          base: mix.base + "common/download?name="
        })
      }
    })
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
  onConfirm01(e) { //性别选择器
    let sex = false;
    if (e.detail.value == 1) sex = true;
    this.setData({
      clientGender: sex
    })
  },
  onConfirm02(e) { //生日选择器
    this.setData({
      clientAge: e.detail.value
    })
  },
  updateClient() {
    mix.put("client", this.data).then(res => {
      if (res.code == 1) {
        wx.showToast({
          title: res.msg,
          icon: 'success',
          duration: 2000
        })
      }
    })
  },
  chooseImg() {
    const that = this;
    wx.chooseMedia({
      count: 1,
      mediaType: ['image'],
      sourceType: ['album', 'camera'],
      success(res) {
        if (res.tempFiles[0].size > 3145700) {
          wx.showToast({
            title: "头像应小于3MB",
            icon: 'error'
          })
        } else {
          wx.uploadFile({
            url: mix.base + 'common/upload',
            filePath: res.tempFiles[0].tempFilePath,
            header: {
              "cookie": wx.getStorageSync("sessionId"),
            },
            name: 'myFile',
            success(res) {
              that.setData({
                clientPhoto: res.data
              })
            }
          })
        }
      }
    })
  }
})