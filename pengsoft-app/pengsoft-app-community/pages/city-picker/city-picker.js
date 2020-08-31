// pages/city-picker/city-picker.js
Page({

    onLoad(options) {
        this.getCitys()
    },

    onChoose(e) {
        wx.navigateBack({
            success: () => console.log(e)
        })
    },

    getCitys() {
        const data = [
            {
                "name": "鞍山市",
                "index": "A"
            },
            {
                "name": "北京市",
                "index": "B"
            },
            {
                "name": "保定市",
                "index": "B"
            },
            {
                "name": "重庆市",
                "index": "C"
            },
            {
                "name": "上海市",
                "index": "S"
            },
            {
                "name": "天津市",
                "index": "T"
            }
        ];
        const list = [];
        data.forEach(value => {
            const index = value.index;
            const name = value.name;
            const item = list.find(item => item.alpha === index);
            if (item) {
                item.subItems.push({name});
            } else {
                list.push({
                    alpha: index,
                    subItems: [{name}]
                })
            };
        });
        this.setData({list})
    }

})