<template>
  <div class="BarChart">
    <div class="left">
      <div class="moreBt">更多</div>
      <div id="left"/>
    </div>
    <div class="right">
      <div id="top" class="top"/>
      <div id="bottom" class="bottom"/>
    </div>
  </div>
</template>

<script>
var echarts = require('echarts')

import { debounce } from '@/utils'
export default {
  name: 'BarChart',
  data() {
    return {
      chart1: null,
      chart2: null,
      chart3: null
    }
  },
  mounted() {
    this.initChart1()
    this.initChart2()
    this.initChart3()
    this.__resizeHandler = debounce(() => {
      if (this.chart1) {
        this.chart1.resize()
      }
      if (this.chart2) {
        this.chart2.resize()
      }
      if (this.chart3) {
        this.chart3.resize()
      }
    }, 100)
    window.addEventListener('resize', this.__resizeHandler)
  },
  beforeDestroy() {
    window.removeEventListener('resize', this.__resizeHandler)
    this.chart1.dispose()
    this.chart1 = null
    this.chart2.dispose()
    this.chart2 = null
    this.chart3.dispose()
    this.chart3 = null
  },
  methods: {
    initChart1() {
      this.chart1 = echarts.init(document.getElementById('left'), 'macarons')
      this.chart1.setOption({
        color: ['#588FE1', '#F7D23B', '#FFAC46', '#FF6A52'],
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['基础积分', '技术积分', '奖励积分', '考核积分']
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
              { value: 1548, name: '基础积分' },
              { value: 535, name: '技术积分' },
              { value: 510, name: '奖励积分' },
              { value: 634, name: '考核积分' }
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
      // this.chart.on('click', function(params) {
      //   console.log(params)
      //   self.$emit('showbarchart', params.name)
      // })
    },
    initChart2() {
      this.chart2 = echarts.init(document.getElementById('top'), 'macarons')
      this.chart2.setOption({
        title: {
          text: '通过率',
          left: '20',
          textStyle: {
            fontSize: 14,
            color: '#333'
          }
        },
        tooltip: {
          show: true,
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: 'rgba(48, 141, 213, .8)',
              fontSize: 14
            },
            lineStyle: {
              color: '#7daef5'
            }
          },
          formatter: '数量 : {c}<br/> 名称 : {b}'
        },
        grid: {
          left: '40',
          right: '40',
          bottom: '3%',
          top: '30',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: [
            '京沪养护处',
            '京沪养护处',
            '京沪养护处',
            '京沪养护处',
            '京沪养护处',
            '京沪养护处'
          ],
          axisLine: {
            lineStyle: {
              color: '#82b1f5',
              width: 1
            }
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: '#333',
            fontSize: 14
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#7daef5'
            }
          },
          boundaryGap: false
          // boundaryGap: ['10%', '10%']
        },
        yAxis: {
          type: 'value',
          axisLine: {
            lineStyle: {
              color: '#82b1f5',
              width: 1
            }
          },
          axisTick: {
            show: false
          },
          axisLabel: {
            color: '#333',
            fontSize: 14
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#7daef5'
            }
          }
        },
        series: [
          {
            data: [60, 67, 70, 72, 90, 85],
            type: 'line',
            areaStyle: {
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [
                  {
                    offset: 0,
                    color: '#7daff5' // 0% 处的颜色
                  },
                  {
                    offset: 1,
                    color: '#fff' // 100% 处的颜色
                  }
                ]
              }
            },
            lineStyle: {
              color: '#1971ee',
              width: 2
            },
            symbol: 'circle',
            symbolSize: 14,
            itemStyle: {
              color: '#54d6ff',
              borderColor: '#1971ee',
              borderWidth: 1
            }
          }
        ]
      })
    },
    initChart3() {
      this.chart3 = echarts.init(document.getElementById('bottom'), 'macarons')
      this.chart3.setOption({
        title: {
          text: '学习数量',
          left: '20',
          textStyle: {
            fontSize: 14,
            color: '#333'
          }
        },
        color: ['#59B0F0', '#F56E5A'],
        legend: {
          left: 90,
          data: ['线上', '线下']
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '40',
          right: '40',
          bottom: '3%',
          top: '30px',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            data: [
              '安全类',
              '质量类',
              '技术类',
              '经济类',
              '教育类',
              '教育类',
              '教育类'
            ],
            axisLabel: {
              color: '#333',
              fontSize: 14
            },
            axisLine: {
              lineStyle: {
                color: '#82b1f5',
                width: 1
              }
            },
            axisTick: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              color: '#333',
              fontSize: 14
            },
            axisLine: {
              lineStyle: {
                color: '#82b1f5',
                width: 1
              }
            },
            axisTick: {
              show: false
            }
          }
        ],
        series: [
          {
            name: '线上',
            type: 'bar',
            stack: '广告',
            barWidth: 20,
            data: [12, 13, 100, 13, 9, 23, 21]
          },
          {
            name: '线下',
            type: 'bar',
            stack: '广告',
            barWidth: 20,
            data: [22, 18, 19, 23, 29, 33, 31]
          }
        ]
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.BarChart {
  width: 100%;
  height: 540px;
  padding: 20px;
  display: flex;
  flex-direction: row;
  .left {
    flex: 1;
    border-right: 1px solid #e6e6e6;
    position: relative;
    #left {
      width: 100%;
      height: 100%;
    }
    .moreBt {
      color: #5295f0;
      border: 1px solid #5295f0;
      position: absolute;
      top: 0;
      right: 20px;
      padding: 5px 10px;
      cursor: pointer;
      z-index: 10000;
    }
  }
  .right {
    flex: 1;
    display: flex;
    flex-direction: column;
    .top {
      flex: 1;
    }
    .bottom {
      flex: 1;
    }
  }
}
</style>

