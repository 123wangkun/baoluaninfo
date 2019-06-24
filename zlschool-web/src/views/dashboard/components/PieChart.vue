<template>
  <div class="PieChart">
    <div class="row">
      <div class="count">
        49328
        <span>学术总分</span>
      </div>
      <div class="select">
        <el-select v-model="value" placeholder="请选择" size="small">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </div>
    </div>
    <div class="charts">
      <div id="chart1" class="chart"/>
      <div id="chart2" class="chart"/>
      <div id="chart3" class="chart"/>
      <div id="chart4" class="chart"/>
    </div>
  </div>
</template>

<script>
var echarts = require('echarts')

export default {
  data() {
    return {
      options: [
        {
          value: '我自己',
          label: '我自己'
        },
        {
          value: '机务养护部',
          label: '机务养护部'
        },
        {
          value: '综合管理部1',
          label: '综合管理部1'
        }
      ],
      value: '我自己',
      chart1: null,
      chart2: null,
      chart3: null,
      chart4: null
    }
  },
  mounted() {
    this.initChart1()
    this.initChart2()
    this.initChart3()
    this.initChart4()
  },
  beforeDestroy() {
    this.chart1.dispose()
    this.chart1 = null
  },
  methods: {
    initChart1() {
      this.chart1 = echarts.init(document.getElementById('chart1'), 'macarons')
      this.chart1.setOption({
        title: {
          text: '123456',
          bottom: 10,
          left: 'center',
          textStyle: {
            color: '#5096EB',
            fontStyle: 'normal',
            fontWeight: 'normal',
            fontSize: 18
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['45%', '55%'],
            label: {
              normal: {
                position: 'center',
                formatter: function(params) {
                  if (params.name === 'other') return ''
                  return '奖励积分\n25%'
                },
                textStyle: {
                  color: '#5096EB',
                  fontStyle: 'normal',
                  fontWeight: 'normal',
                  fontSize: 18
                }
              }
            },
            data: [
              {
                value: 75,
                name: 'other',
                itemStyle: {
                  normal: {
                    color: '#ccc'
                  }
                }
              },
              {
                value: 25,
                name: '奖励积分',
                itemStyle: {
                  normal: {
                    color: '#5096EB'
                  }
                }
              }
            ]
          }
        ]
      })
    },
    initChart2() {
      this.chart2 = echarts.init(document.getElementById('chart2'), 'macarons')
      this.chart2.setOption({
        title: {
          text: '123456',
          bottom: 10,
          left: 'center',
          textStyle: {
            color: '#17BAA9',
            fontStyle: 'normal',
            fontWeight: 'normal',
            fontSize: 18
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['45%', '55%'],
            label: {
              normal: {
                position: 'center',
                formatter: function(params) {
                  if (params.name === 'other') return ''
                  return '技术积分\n25%'
                },
                textStyle: {
                  color: '#17BAA9',
                  fontStyle: 'normal',
                  fontWeight: 'normal',
                  fontSize: 18
                }
              }
            },
            data: [
              {
                value: 75,
                name: 'other',
                itemStyle: {
                  normal: {
                    color: '#ccc'
                  }
                }
              },
              {
                value: 25,
                name: '奖励积分',
                itemStyle: {
                  normal: {
                    color: '#17BAA9'
                  }
                }
              }
            ]
          }
        ]
      })
    },
    initChart3() {
      this.chart3 = echarts.init(document.getElementById('chart3'), 'macarons')
      this.chart3.setOption({
        title: {
          text: '123456',
          bottom: 10,
          left: 'center',
          textStyle: {
            color: '#FC9D49',
            fontStyle: 'normal',
            fontWeight: 'normal',
            fontSize: 18
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['45%', '55%'],
            label: {
              normal: {
                position: 'center',
                formatter: function(params) {
                  if (params.name === 'other') return ''
                  return '基础积分\n25%'
                },
                textStyle: {
                  color: '#FC9D49',
                  fontStyle: 'normal',
                  fontWeight: 'normal',
                  fontSize: 18
                }
              }
            },
            data: [
              {
                value: 75,
                name: 'other',
                itemStyle: {
                  normal: {
                    color: '#ccc'
                  }
                }
              },
              {
                value: 25,
                name: '奖励积分',
                itemStyle: {
                  normal: {
                    color: '#FC9D49'
                  }
                }
              }
            ]
          }
        ]
      })
    },
    initChart4() {
      this.chart4 = echarts.init(document.getElementById('chart4'), 'macarons')
      this.chart4.setOption({
        title: {
          text: '123456',
          bottom: 10,
          left: 'center',
          textStyle: {
            color: '#AB76FC',
            fontStyle: 'normal',
            fontWeight: 'normal',
            fontSize: 18
          }
        },
        series: [
          {
            type: 'pie',
            radius: ['45%', '55%'],
            label: {
              normal: {
                position: 'center',
                formatter: function(params) {
                  if (params.name === 'other') return ''
                  return '考核积分\n25%'
                },
                textStyle: {
                  color: '#AB76FC',
                  fontStyle: 'normal',
                  fontWeight: 'normal',
                  fontSize: 18
                }
              }
            },
            data: [
              {
                value: 75,
                name: 'other',
                itemStyle: {
                  normal: {
                    color: '#ccc'
                  }
                }
              },
              {
                value: 25,
                name: '奖励积分',
                itemStyle: {
                  normal: {
                    color: '#AB76FC'
                  }
                }
              }
            ]
          }
        ]
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.PieChart {
  width: 100%;
  height: 280px;
  padding: 20px;
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  padding: 5px;
  margin: 0;
  display: flex;
  flex-direction: column;
  position: relative;
  .row {
    display: flex;
    flex-direction: row;
    margin: 10px 10px 0 10px;
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    .count {
      flex: 1;
      font-size: 20px;
      span {
        font-size: 14px;
      }
    }
    .select {
      margin-right: 20px;
    }
  }
  .charts {
    flex: 1;
    display: flex;
    flex-direction: row;
    padding: 0px 50px;
    .chart {
      flex: 1;
    }
  }
}
</style>

