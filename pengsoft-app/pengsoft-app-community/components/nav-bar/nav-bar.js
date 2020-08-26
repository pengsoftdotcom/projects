// components/nav-bar/nav-bar.js
const global = getApp().globalData;
const systemInfo = global.systemInfo;
let titleBarMarginTop;
if (systemInfo.brand === 'devtools') {
    titleBarMarginTop = (systemInfo.titleBarHeight - 32) / 2;
} else {
    titleBarMarginTop = (systemInfo.titleBarHeight - 32) / 3;
}
const titleBarMarginBottom = systemInfo.titleBarHeight - 32 - titleBarMarginTop;
Component({
    /**
     * 组件的属性列表
     */
    properties: {
        placeholder: {
            type: String
        }
    },

    /**
     * 组件的初始数据
     */
    data: {
        navBarHeight: systemInfo.navBarHeight,
        statusBarHeight: systemInfo.statusBarHeight,
        titleBarHeight: systemInfo.titleBarHeight,
        titleBarMarginTop,
        titleBarMarginBottom,
        systemInfo,
        city: '',
        weather: '',
        temperature: '' 
    },

    lifetimes: {
        attached() {
            global.amap.getRegeo({
                success: res => {
                    let city = res[0].regeocodeData.addressComponent.city;
                    // 直辖市
                    if (city instanceof Array) {
                        city = res[0].regeocodeData.addressComponent.province;
                    }
                    if (city.endsWith('特别行政区')) {
                        city = city.substring(0, city.length - 5);
                    } else if (city.endsWith('自治州')) {
                        if (city.endsWith('藏族羌族自治州')) {
                            city = city.substring(0, city.length - 7);
                        } else if (city.endsWith('土家族苗族自治州')) {
                            city = city.substring(0, city.length - 8);
                        } else {
                            // 数量众多，特殊情况特殊处理
                            city = city.substring(0, city.length - 5);
                        }
                    } else {
                        city = city.substring(0, city.length - 1);
                    }

                    this.setData({
                        city
                    });
                }
            });
            global.amap.getWeather({
                success: res => {
                    this.setData({
                        weather: res.liveData.weather,
                        temperature: res.liveData.temperature
                    });
                }
            });
        }
    },

    /**
     * 组件的方法列表
     */
    methods: {
        openLocationPicker() {
            wx.navigateTo({
              url: '/pages/city-picker/city-picker',
            })
        },
        search(event) {
            wx.request({
              url: 'http://localhost:8080',
              success: () => console.log(1)
            })
            wx.showToast({
                title: event.detail.value,
                icon: 'success'
            });
        }
    }
})