// pages/petSee/petSee.js
const mix = require("../../utils/mix");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    pageNum: 1,
    maxNum: 1,
    base: "/images/",
    petList: [{
      petId: 3,
      petName: "狸花",
      petPhoto: "li-hua.jpg",
      petVariety: "狸花猫",
      petSex: 1,
      petAge: "2000-12-12",
      petInfo: "它很高兴",
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    const json = {
      "numPage": 1,
      "pageSize": 8
    }
    mix.get('pet/four', json).then(res => {
      this.setData({
        petList: res.data,
        maxNum: res.len / 8,
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
    wx.showLoading({
      title: "加载中"
    });
    const numPage = this.data.pageNum,
      maxNum = this.data.maxNum;
    if (numPage < maxNum) {
      this.setData({
        pageNum: numPage + 1
      })
      const json = {
        "numPage": this.data.pageNum,
        "pageSize": 8
      }
      mix.get('pet/four', json).then(res => {
        this.setData({
          petList: this.data.petList.concat(res.data),
          maxNum: res.len / 8
        })
      })
    }
    setTimeout(() => {
      wx.hideLoading()
    }, 200)
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})