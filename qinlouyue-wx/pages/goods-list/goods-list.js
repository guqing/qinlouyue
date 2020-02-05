//goods-list.js
//获取应用实例
const app = getApp()
Component({
  options: {
    styleIsolation: 'shared',
    multipleSlots: true // 在组件定义时的选项中启用多slot支持
  },
  properties: {},
  data: {
    nearbyShopDropDownMenu: {
      switchTitle1: '包邮',
      switchTitle2: '团购',
      itemTitle: '综合排序',
      menuOption: [
        { text: '综合排序', value: 0 },
        { text: '距离最近', value: 1 },
        { text: '评分最高', value: 2 },
        { text: '起送价最低', value: 3 },
        { text: '配送费最低', value: 4 },
        { text: '人均高到低', value: 5 },
        { text: '人均低到高', value: 6 }
      ],
      menuOptionValue: 0
    }
  },
  methods: {
    // 附近商家下拉菜单导航
    onConfirm() {
      this.selectComponent('#item').toggle()
    },

    onSwitch1Change({ detail }) {
      this.setData({ 'nearbyShopDropDownMenu.switch1': detail })
    },

    onSwitch2Change({ detail }) {
      this.setData({ 'nearbyShopDropDownMenu.switch2': detail })
    }
  }
})
