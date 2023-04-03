// pages/doctorSee/doctorSee.js
const mix = require("../../utils/mix");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    base: "/images/",
    doctors: [{ //医生信息
      doctorId: 1,
      doctorPhoto: "doctor.jpg",
      doctorName: "张三",
      doctorGender: true,
      doctorAge: "20",
      doctorJob: "助理医生",
      doctorInfo: "小助理一个"
    }, ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    mix.plain('doctor/page?numPage=1&pageSize=8').then(res => {
      console.log(res);
      this.setData({
        doctors: res.data,
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
    console.log("你到底了");
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})