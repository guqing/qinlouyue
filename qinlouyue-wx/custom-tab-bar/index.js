Component({
  data: {
    active: 0,
    list: [
      {
        icon: 'home-o',
        text: '首页',
        url: '/pages/index/index'
      },
      {
        icon: 'orders-o',
        text: '订单',
        url: '/pages/order/order'
      },
      {
        icon: 'user-circle-o',
        text: '我的',
        url: '/pages/mine/mine'
      }
    ]
  },

  methods: {
    onChange(event) {
      this.setData({ active: event.detail })
      wx.switchTab({
        url: this.data.list[event.detail].url
      })
    },

    init() {
      const page = getCurrentPages().pop()
      this.setData({
        active: this.data.list.findIndex(item => item.url === `/${page.route}`)
      })
    }
  }
})
