<template>
	<view class="content">
		<uni-section title="工作" type="line" padding>
			<uni-grid :show-border="false" :column="4" @change="selectGrid">
				<uni-grid-item v-for="(item, index) in gridItemMenu1" :index="index" :key="index">
					<view class="grid-item-box">
						<uni-icons :type="item.icon" :size="30" :color="item.color" />
						<text class="text">{{item.title}}</text>
					</view>
				</uni-grid-item>
			</uni-grid>
		</uni-section>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				gridItemMenu1: [{
						title: '项目管理',
						icon: 'list',
						color: '#007aff'
					},
					{
						title: '合同管理',
						icon: 'flag',
						color: '#007aff'
					},
					{
						title: '薪资管理',
						icon: 'wallet',
						color: '#007aff',
					},
					{
						title: '安全巡查',
						icon: 'map',
						color: '#007aff',
					},
					{
						title: '安全培训',
						icon: 'info',
						color: '#007aff',
					},
				]
			}
		},
		onLoad() {

		},
		methods: {
			selectGrid() {}
		},
	}
</script>

<style lang="scss">
	.grid-item-box {
		flex: 1;
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding: 15px 0;

		.text {
			font-size: 14px;
			width: 100%;
			display: flex;
			justify-content: center;
		}
	}
</style>
