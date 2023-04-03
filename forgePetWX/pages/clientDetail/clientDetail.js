// pages/clientDetail/clientDetail.js
const mix = require("../../utils/mix");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    base: "/images/",
    c: {
      clientId: 0,
      clientInfo: "",
      clientName: "莹",
      clientPhoto: "zs.jpg",
      clientTel: "s",
      clientUsername: "g",
      clientAge:"2023-3-27",
      clientGender:'0'
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    mix.plain("login/user").then(res => {
      if (res) {
        this.setData({
          c: res,
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

  }
})