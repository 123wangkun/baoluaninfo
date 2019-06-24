<template>
  <div class="RankingChart"/>
</template>

<script>
var echarts = require('echarts')
export default {
  data() {
    return {
      chart: null
    }
  },
  mounted() {
    this.initChart()
  },
  beforeDestroy() {
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.chart.setOption({
        xAxis: {
          type: 'category',
          data: [
            '京沪养护处\n80/100人',
            '京沪养护处\n80/100人',
            '京沪养护处\n80/100人',
            '京沪养护处\n80/100人',
            '京沪养护处\n80/100人',
            '京沪养护处\n80/100人'
          ]
        },
        yAxis: [
          {
            type: 'value',
            axisLabel: {
              show: true,
              textStyle: {},
              interval: 0,
              showMinLabel: true,
              formatter: '{value} %'
            },
            min: 0,
            max: 100,
            splitNumber: 10
          }
        ],
        series: [
          {
            barWidth: 20,
            name: '销量',
            type: 'bar',
            data: [80, 75, 65, 55, 45, 30],
            itemStyle: {
              normal: {
                color: function(params) {
                  var colorList = [
                    '#39A0FF',
                    '#37CBCB',
                    '#4CCB72',
                    '#FFC30B',
                    '#F95554',
                    '#975FE4'
                  ]
                  return colorList[params.dataIndex]
                }
              }
            },
            markLine: {
              data: [{ type: 'average', name: '平均值' }]
            }
          }
        ]
      })
    }
  }
}
</script>

<style>
.RankingChart {
  width: 100%;
  height: 440px;
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  padding: 5px;
  margin: 0;
}
</style>
