// pages/appointment/appointment.js
const mix = require("../../utils/mix");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    appoint: [{
      appointmentDate: "2023-01-16T21:24:00",
      appointmentId: 4,
      appointmentInfo: "查看骨骼",
      departmentName: "中兽科",
      petName: "心海",
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if (options !== undefined) {
      const id = options.id
      mix.plain("appointment/client?clientId=" + id).then(res => {
        this.setData({
          appoint: res
        })
      })
    }
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

  }
})