<template>
  <div class="BarChart"/>
</template>

<script>
var echarts = require('echarts')

import { debounce } from '@/utils'
export default {
  name: 'BarChart',
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
        color: ['#2ec8c9', '#b6a3de', '#5ab2ef', '#ffb980'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'line' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['在线学习', '线下学习', '基础积分', '考核计分']
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: [
              '综合管理部',
              '机务养护部',
              '综合管理部',
              '机务养护部',
              '综合管理部',
              '机务养护部',
              '综合管理部'
            ]
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '在线学习',
            barWidth: 20,
            stack: '1',
            type: 'bar',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: '线下学习',
            type: 'bar',
            stack: '1',
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '基础积分',
            type: 'bar',
            stack: '1',
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '考核计分',
            type: 'bar',
            stack: '1',
            data: [150, 232, 201, 154, 190, 330, 410]
          }
        ]
      })
      this.chart.on('click', function(params) {
        console.log(params)
        self.$emit('showbarchart', params.name)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.BarChart {
  width: 100%;
  height: 440px;
  padding: 20px;
}
</style>

