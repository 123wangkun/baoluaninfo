<template>
  <div class="chart1">
    <div class="item">
      <div id="pie"/>
    </div>
    <div class="item">
      <div id="bar"/>
    </div>
  </div>
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
  name: 'Chart1',
  data() {
    return {
      chart1: null,
      chart2: null
    }
  },
  mounted() {
    this.initChart()
    this.__resizeHandler = debounce(() => {
      if (this.chart1) {
        this.chart1.resize()
      }
      if (this.chart2) {
        this.chart2.resize()
      }
    }, 100)
    window.addEventListener('resize', this.__resizeHandler)
  },
  beforeDestroy() {
    if (!this.chart1) {
      return
    }
    if (!this.chart2) {
      return
    }
    window.removeEventListener('resize', this.__resizeHandler)
    this.chart1.dispose()
    this.chart2.dispose()
    this.chart1 = null
    this.chart2 = null
  },
  methods: {
    initChart() {
      this.chart1 = echarts.init(document.getElementById('pie'), 'macarons')
      this.chart2 = echarts.init(document.getElementById('bar'), 'macarons')
      this.chart1.setOption({
        color: ['#2ec8c9', '#b6a3de', '#5ab2ef', '#ffb980'],
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} '
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['在线学习', '线下学习', '基础积分', '考核计分']
        },
        series: [
          {
            name: '积分数量',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              { value: 335, name: '在线学习' },
              { value: 310, name: '线下学习' },
              { value: 234, name: '基础积分' },
              { value: 135, name: '考核计分' }
            ].sort(function(a, b) {
              return a.value - b.value
            }),
            roseType: 'radius',
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
      this.chart2.setOption({
        color: ['#2ec8c9', '#b6a3de', '#5ab2ef', '#ffb980'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
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
        yAxis: {
          type: 'value'
        },
        xAxis: {
          type: 'category',
          data: ['部门1', '部门2', '部门3', '部门4', '部门5', '部门6', '部门7']
        },
        series: [
          {
            name: '在线学习',
            type: 'bar',
            stack: '总量',
            label: {
              // normal: {
              //     show: true,
              //     position: 'insideRight'
              // }
            },
            data: [320, 302, 301, 334, 390, 330, 320]
          },
          {
            name: '线下学习',
            type: 'bar',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'insideRight'
              }
            },
            data: [120, 132, 101, 134, 90, 230, 210]
          },
          {
            name: '基础积分',
            type: 'bar',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'insideRight'
              }
            },
            data: [220, 182, 191, 234, 290, 330, 310]
          },
          {
            name: '考核计分',
            type: 'bar',
            stack: '总量',
            label: {
              normal: {
                show: true,
                position: 'insideRight'
              }
            },
            data: [150, 212, 201, 154, 190, 330, 410]
          }
        ]
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.chart1 {
  display: flex;
  width: 100%;
  flex-direction: row;
  .item {
    flex: 1;
    #pie,
    #bar {
      height: 500px;
    }
  }
}
</style>

