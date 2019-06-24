<template>
  <div class="RadarChart"/>
</template>

<script>
var echarts = require('echarts/lib/echarts')
// 引入柱状图
require('echarts/lib/chart/radar')
// 引入提示框和标题组件
require('echarts/lib/component/tooltip')
require('echarts/lib/component/title')
import { debounce } from '@/utils'
export default {
  name: 'RadarChart',
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.initChart()
    this.__resizeHandler = debounce(() => {
      if (this.chart) {
        this.chart.resize()
      }
    }, 100)
    window.addEventListener('resize', this.__resizeHandler)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    window.removeEventListener('resize', this.__resizeHandler)
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      const self = this
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: {
          text: ''
        },
        tooltip: {},
        legend: {
          data: [
            '登陆次数',
            '学分统计',
            '考试正确率',
            '岗位课程',
            '学习统计',
            '领取学习红包总数'
          ]
        },
        radar: {
          // shape: 'circle',
          name: {
            textStyle: {
              color: '#fff',
              backgroundColor: '#999',
              borderRadius: 3,
              padding: [3, 5]
            }
          },
          indicator: [
            { name: '登陆次数', max: 6500 },
            { name: '学分统计', max: 16000 },
            { name: '考试正确率', max: 30000 },
            { name: '岗位课程', max: 38000 },
            { name: '学习统计', max: 52000 },
            { name: '领取学习红包总数', max: 25000 }
          ],
          triggerEvent: true
        },
        series: [
          {
            name: '预算 vs 开销（Budget vs spending）',
            type: 'radar',
            // areaStyle: {normal: {}},
            data: [
              {
                value: [4300, 10000, 28000, 35000, 50000, 19000],
                name: '分管领导1'
              }
            ]
          }
        ]
      })
      this.chart.on('click', function(params) {
        console.log(params)
        self.$emit('showradarchart', params.name)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.RadarChart {
  width: 100%;
  height: 338px;
}
</style>

