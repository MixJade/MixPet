// pages/chatClient/chatClient.js
const mix = require("../../utils/mix");
var sotk = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    base: "/images/",
    baseChat: "/images/",
    myHeight: 450,
    inputMsg: '',
    doctorId: 9,
    doctorName: "李",
    msgList: [{
        msgId: 1,
        clientName: "张三",
        doctorName: "李医生",
        isClient: true,
        msgContent: "你好",
        createTime: "2023-3-22T12:21:22",
        clientPhoto: "zs.jpg",
        doctorPhoto: "doctor.jpg",
        isImg: false
      },
      {
        msgId: 2,
        clientName: "张三",
        doctorName: "李医生",
        isClient: false,
        msgContent: "你好",
        createTime: "2023-3-22T12:21:22",
        clientPhoto: "zs.jpg",
        doctorPhoto: "doctor.jpg",
        isImg: false
      },
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if (options !== undefined) {
      const id = options.id,
        name = options.name;
      mix.plain("msg/aDoctor?doctorId=" + id).then(res => {
        this.setData({
          msgList: res,
          doctorId: id,
          doctorName: name,
          base: mix.base + "common/download?name=",
          baseChat: mix.base + "common/downChat?name="
        })
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {
    // 将消息框滑动到最底下
    this.toTheBottom()
    // 建立webSocket链接
    const baseUrl = (mix.base).replace("http", "ws") + "chat"
    let header = {
      'content-type': 'application/json'
    };
    if (wx.getStorageSync("sessionId")) header.cookie = wx.getStorageSync("sessionId");
    // 打开socket链接
    sotk = wx.connectSocket({
      url: baseUrl,
      header: header,
      success: res => {
        console.log('小程序连接成功：', res);
      },
      fail: err => {
        console.log('小程序连接失败' + err);
      }
    });
    //服务器发送监听
    const _this = this
    sotk.onMessage(function (e) {
      _this.requestMsgList()
    })
    //发送消息监听

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    const that = this
    // 动态改变滚动区高度
    wx.getSystemInfo({
      success: function (res) {
        let nowHeight = res.windowHeight - 50;
        that.setData({
          myHeight: nowHeight
        })
      }
    });
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
    sotk.close({
      code: "1000",
      reason: "用户退出",
      success: function () {
        console.log("成功关闭websocket连接");
      }
    })
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    wx.createSelectorQuery().select('#chatBody').boundingClientRect(function (rect) {
      console.log(rect.height);
    }).exec()
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
  // 将消息框滑动到最底下
  toTheBottom() {
    const that = this
    setTimeout(() => {
      wx.createSelectorQuery().select('#chatBody').boundingClientRect(function (rect) {
        that.setData({
          scrollTop: rect.height
        })
      }).exec()
    }, 300)
  },
  //发送消息
  sendMsg() {
    const that = this
    const json = {
      msgContent: that.data.inputMsg,
      doctorId: that.data.doctorId,
      isClient: true
    }
    if (json.msgContent == '') return;
    //保存数据
    mix.post("msg", json).then(res => {
      if (res.code == 1) {
        this.requestMsgList()
        //发送数据
        sotk.send({
          data: JSON.stringify(json),
          success: res => {
            console.info('客户端发送成功');
          }
        });
      }
    })
  },
  // 发送图片
  sendImg() {
    const that = this;
    wx.chooseMedia({
      count: 1,
      mediaType: ['image'],
      sourceType: ['album', 'camera'],
      success(res) {
        if (res.tempFiles[0].size > 3145700) {
          wx.showToast({
            title: "图片应小于3MB",
            icon: 'error'
          })
        } else {
          wx.uploadFile({
            url: mix.base + 'common/upChat',
            filePath: res.tempFiles[0].tempFilePath,
            header: {
              "cookie": wx.getStorageSync("sessionId"),
            },
            name: 'myFile',
            success(res) {
              that.sendImgName(res.data)
            }
          })
        }
      }
    })
  },
  //发送图片名称消息
  sendImgName(imgName) {
    const that = this
    const json = {
      msgContent: imgName,
      doctorId: that.data.doctorId,
      isClient: true,
      isImg: true
    }
    if (json.msgContent == '') return;
    //保存数据
    mix.post("msg", json).then(res => {
      if (res.code == 1) {
        this.requestMsgList()
        //发送数据
        sotk.send({
          data: JSON.stringify(json)
        });
      }
    })
  },
  // 绑定输入消息
  msgHander: function () {},
  // 重新获取消息
  requestMsgList() {
    const id = this.data.doctorId,
      that = this
    mix.plain("msg/aDoctor?doctorId=" + id).then(res => {
      that.setData({
        msgList: res,
        inputMsg: ''
      })
      that.toTheBottom()
    })
  }
})