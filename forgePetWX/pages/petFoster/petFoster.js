// pages/petFoster/petFoster.js
const mix = require("../../utils/mix");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    base: "/images/",
    nowYear: Date,
    fList: [{
      fosterId: 1,
      petName: "狸花",
      petPhoto: "li-hua.jpg",
      petVariety: "狸花猫",
      petId: 1,
      petSex: 1,
      petAge: "2000-12-12",
      petInfo: "它很高兴",
      fosterInfo: "有事外出",
      clientName: "张三",
      fosterTerm: "2023-12-12"
    }],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    mix.plain('foster/pet').then(res => {
      this.setData({
        fList: res,
        base: mix.base + "common/download?name="
      })
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
    this.onLoad()
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