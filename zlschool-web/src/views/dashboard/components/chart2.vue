<template>
  <div class="chart2"/>
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
  name: 'Chart2',
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
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        title: {
          text: '登陆次数统计'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} '
        },
        legend: {
          top: 30,
          orient: 'vertical',
          left: 'left',
          data: ['部门1', '部门2', '部门3', '部门4']
        },
        series: [
          {
            name: '积分数量',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              { value: 335, name: '部门1' },
              { value: 310, name: '部门2' },
              { value: 234, name: '部门3' },
              { value: 135, name: '部门4' }
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.chart2 {
  width: 100%;
  height: 500px;
}
</style>

