//logs.js
const util = require('../../utils/util.js')
import Dialog from '../../miniprogram/miniprogram_npm/@vant/weapp/dialog/dialog'
Page({
  data: {
    keyword: '',
    searchStatus: false,
    historyKeyword: ['hello'],
    goodsList: []
  },
  bindSearchTap: function() {
    // 添加搜索历史
    this.data.historyKeyword.push(this.data.keyword)
    this.setSearchKeyword(this.data.historyKeyword)
    this.getSearchResult()
  },
  bindClearInputKeyword: function() {
    this.setData({
      keyword: '',
      searchStatus: false
    })
    this.getHistoryKeyword()
  },
  getSearchResult(keyword) {
    // 商品列表
    this.setData({
      goodsList: [],
      searchStatus: true
    })
  },
  onSearchKeyChange: function(e) {
    this.setData({
      keyword: e.detail
    })
  },
  setSearchKeyword: function(historyKeyword) {
    wx.setStorage({
      key: 'historyKeyword',
      data: historyKeyword
    })
  },
  clearHistory: function() {
    Dialog.confirm({
      message: '清除历史记录?'
    })
      .then(() => {
        // on close
        this.setData({
          historyKeyword: []
        })
        wx.clearStorage()
      })
      .catch(() => {})
  },
  onKeywordTap: function(event) {
    let keyword = event.target.dataset.keyword
    // 设置输入框的文本值
    this.setData({
      keyword: keyword
    })
    // 搜索
    this.getSearchResult(keyword)
  },
  getHistoryKeyword: function() {
    let that = this
    let historyKeyword = wx.getStorageSync('historyKeyword')
    console.log(historyKeyword)
    that.setData({
      historyKeyword: historyKeyword || []
    })
    console.log(that.data.historyKeyword)
  },
  onLoad: function() {
    this.getHistoryKeyword()
  }
})
