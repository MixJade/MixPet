// pages/doctorAppoint/doctorAppoint.js
const mix = require("../../utils/mix");
Page({

  /**
   * 页面的初始数据
   */
  data: {
    base: "/images/",
    doctor: {
      doctorId: 4,
      doctorPhoto: "doctor.jpg",
      doctorCode:"007",
      doctorName: "赵六",
      doctorGender: false,
      doctorAge: "20",
      doctorJob: "助理医生",
      doctorInfo: "小助理一个"
    },
    appoint: [{
      appointmentDate: "2023-02-18T14:30:00",
      appointmentId: 7,
      appointmentInfo: "测试挂号",
      clientName: "散玉",
      petName: "二橘",
    }, ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if (options !== undefined) {
      const id = options.id
      Promise.all([mix.plain("doctor/one?doctorId=" + id), mix.plain("appointment/doctor?doctorId=" + id)])
        .then(res => {
          console.log(res[0]);
          this.setData({
            base: mix.base + "common/download?name=",
            doctor: res[0],
            appoint: res[1],
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