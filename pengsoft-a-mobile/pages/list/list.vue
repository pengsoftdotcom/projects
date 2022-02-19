<template>
	<view>
		<uni-list>
			<uni-list-chat v-for="(item,index) in listData" :key="index" :title="item.author_name" :avatar="item.cover"
				:note="item.title" badge-positon="left" :time="item.published_at" :badge-text="item.text">
				<!-- <view class="chat-custom-right">
					<text class="chat-custom-text">刚刚</text>
					<uni-icons type="star-filled" color="#999" size="18"></uni-icons>
				</view> -->
			</uni-list-chat>
		</uni-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				UNITS: {
					'年': 31557600000,
					'月': 2629800000,
					'天': 86400000,
					'小时': 3600000,
					'分钟': 60000,
					'秒': 1000
				},
				listData: [],
			};
		},
		onLoad(options) {
			setTimeout(() => {
				console.log('start pulldown');
			}, 1000);
			uni.startPullDownRefresh();
		},
		onPullDownRefresh() {
			setTimeout(() => {
				uni.stopPullDownRefresh();
				this.getList()
			}, 1000);
		},
		methods: {
			getList() {
				var data = {
					column: 'id,post_id,title,author_name,cover,published_at' //需要的字段名
				};

				uni.request({
					url: 'https://unidemo.dcloud.net.cn/api/news',
					data: data,
					success: data => {
						if (data.statusCode == 200) {
							let list = this.setTime(data.data);
							list = this.reload ? list : this.listData.concat(list);
							list.map(item => {
								item.text = Math.floor(Math.random() * (1 - 20) + 20)
								return item
							})
							this.listData = this.getRandomArrayElements(list, 10)
						}
					},
					fail: (data, code) => {
						console.log('fail' + JSON.stringify(data));
					}
				});
			},
			getRandomArrayElements(arr, count) {
				var shuffled = arr.slice(0),
					i = arr.length,
					min = i - count,
					temp, index;
				while (i-- > min) {
					index = Math.floor((i + 1) * Math.random());
					temp = shuffled[index];
					shuffled[index] = shuffled[i];
					shuffled[i] = temp;
				}
				return shuffled.slice(min);
			},
			setTime(items) {
				var newItems = [];
				items.forEach(e => {
					newItems.push({
						author_name: e.author_name,
						cover: e.cover,
						id: e.id,
						post_id: e.post_id,
						published_at: this.format(e.published_at),
						title: e.title
					});
				});
				return newItems;
			},
			format(dateStr) {
				var date = this.parse(dateStr)
				var diff = Date.now() - date.getTime();
				if (diff < this.UNITS['天']) {
					return this.humanize(diff);
				}
				var _format = function(number) {
					return (number < 10 ? ('0' + number) : number);
				};
				return date.getFullYear() + '-' + _format(date.getMonth() + 1) + '-' + _format(date.getDate()) + ' ' +
					_format(date.getHours()) + ':' + _format(date.getMinutes());
			},
			parse(str) { //将"yyyy-mm-dd HH:MM:ss"格式的字符串，转化为一个Date对象
				var a = str.split(/[^0-9]/);
				return new Date(a[0], a[1] - 1, a[2], a[3], a[4], a[5]);
			},
		}
	}
</script>

<style lang="scss">

</style>
