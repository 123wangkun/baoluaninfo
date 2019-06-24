<template>
  <div class="BarChart2">
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
    <div id="BarChart2" class="chart"/>
  </div>
</template>

<script>
var echarts = require('echarts')

import { debounce } from '@/utils'
export default {
  data() {
    return {
      chart: null,
      options: [
        {
          value: '综合管理部',
          label: '综合管理部'
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
      value: '综合管理部'
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
      this.chart = echarts.init(
        document.getElementById('BarChart2'),
        'macarons'
      )
      this.chart.setOption({
        color: ['#F56E5A', '#FCB45C', '#5AB1EF', '#73D48F'],
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'line' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['奖励积分', '技术积分', '基础积分', '考核积分'],
          orient: 'vertical',
          right: 10,
          top: 30
        },
        grid: {
          left: '3%',
          right: '130',
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
            name: '奖励积分',
            barWidth: 20,
            stack: '1',
            type: 'bar',
            data: [320, 332, 301, 334, 390, 330, 320]
          },
          {
            name: '技术积分',
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
            name: '考核积分',
            type: 'bar',
            stack: '1',
            data: [150, 232, 201, 154, 190, 330, 410]
          }
        ]
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.BarChart2 {
  width: 100%;
  height: 440px;
  padding: 20px;
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  .row {
    display: flex;
    flex-direction: row;
    margin: 10px 10px 0 10px;
    .count {
      flex: 1;
      font-size: 20px;
      span {
        font-size: 14px;
      }
    }
  }
  .chart {
    flex: 1;
    width: 100%;
  }
}
</style>

