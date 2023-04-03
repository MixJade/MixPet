// pages/petOne/petOne.js
const mix = require("../../utils/mix");
Page({
  /**
   * 页面的初始数据
   */
  data: {
    showP: false,
    base: "/images/",
    adoptInfo: "",
    adoptMoney: "200",
    inAdopt: false,
    pet: {
      petId: 1,
      petName: "狸花",
      petPhoto: "li-hua.jpg",
      petVariety: "狸花猫",
      petSex: false,
      petAge: "2000-12-12",
      petStatus: "非常健康",
      petInfo: "它很高兴",
    },
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if (options !== undefined) {
      const id = options.id
      Promise.all([mix.plain("pet/one?petId=" + id),
          mix.plain("adopt/inAdopt?petId=" + id)
        ])
        .then(res => {
          this.setData({
            pet: res[0],
            inAdopt: res[1] > 0,
            base: mix.base + "common/download?name="
          })
          console.log(this.data.inAdopt);
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

  },
  // 展示弹出层
  showPop() {
    this.setData({
      showP: true
    });
  },
  // 关闭弹出层
  onClose() {
    this.setData({
      showP: false
    });
  },
  // 让数字输入框只能输入数字
  justNum(e) {
    const inVal = e.detail
    let value = inVal.replace(/\D/g, '')
    this.setData({
      adoptMoney: value
    })
  },
  // 点击申请
  addAdopt() {
    this.setData({
      showP: false
    });
    const that = this.data
    const json = {
      "petId": that.pet.petId,
      "adoptInfo": that.adoptInfo,
      "adoptMoney": that.adoptMoney
    };
    mix.post("adopt/adoptPet", json).then(res => {
      if (res.code == 1) {
        wx.showToast({
          title: res.msg,
          icon: 'success'
        })
        this.setData({
          inAdopt: true
        });
      }
    })
  }
})