// pages/index/index.js
const mix = require("../../utils/mix");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    swiper: { //轮播图
      img1: "/images/swiper/img1.jpg",
      img2: "/images/swiper/img2.jpg",
      img3: "/images/swiper/img3.jpg"
    },
    notices: [{ //公告
      noticeId: 1,
      noticeTitle: "公告一",
      textNotice: "你好，这是公告一"
    }, ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    mix.plain('notice/four').then(res => {
      this.setData({
        notices: res
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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
  // 公告点击展示
  showNotice(e) {
    const originText = e.target.dataset.text
    const content= originText.replace(/\n/g, "\r\n")
    console.log(content);
    wx.showModal({
      title: e.target.dataset.title,
      content: content,
      showCancel: false
    });
  }
})