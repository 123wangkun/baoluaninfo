<template>
  <div class="RadarChart1">
    <div id="RadarChart1" class="chart"/>
    <div class="user">
      <div class="text">
        <img src="../../../assets/img/ry.png">
        分管领导：王大锤
      </div>
      <div class="t"/>
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
  data() {
    return {
      chart: null,
      value: '综合管理部',
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
      ]
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
      this.chart = echarts.init(
        document.getElementById('RadarChart1'),
        'macarons'
      )
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
              color: '#4A4A4A',
              borderRadius: 3,
              padding: [3, 5]
            }
          },
          indicator: [
            { name: '登陆次数\n登陆比例50%（累计登陆50次）', max: 6500 },
            { name: '学分统计\n4556', max: 16000 },
            { name: '考试正确率\n通过率50%（已组织40次）', max: 30000 },
            { name: '岗位课程\n学习比例70%（已组织25次）', max: 38000 },
            { name: '学习统计\n56777', max: 52000 },
            {
              name: '领取学习红包总数\n持证比例30%（累计发放34个）',
              max: 25000
            }
          ],
          triggerEvent: true
        },
        series: [
          {
            name: '预算 vs 开销（Budget vs spending）',
            type: 'radar',
            data: [
              {
                value: [4300, 10000, 28000, 35000, 50000, 19000],
                name: '分管领导1',
                lineStyle: {
                  normal: {
                    color: '#BAA7DF'
                  }
                },
                areaStyle: {
                  normal: {
                    color: '#BAA7DF'
                  }
                },
                itemStyle: {
                  normal: {
                    color: '#BAA7DF'
                  }
                }
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
.RadarChart1 {
  width: 100%;
  height: 388px;
  border: 1px solid #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.1);
  padding: 20px 0;
  position: relative;
  .chart {
    width: 100%;
    height: 100%;
  }
  .user {
    position: absolute;
    top: 0;
    left: 0;
    color: #5096eb;
    display: flex;
    height: 40px;
    .text {
      background: #f2f2f2;
      height: 40px;
      display: flex;
      flex-direction: row;
      align-items: center;
      padding: 0 30px 0 10px;
      img {
        height: 32px;
      }
    }
    .t {
      width: 0;
      height: 0;
      border-width: 20px 10px 20px 10px;
      border-style: solid;
      border-color: transparent transparent transparent #f2f2f2; /*透明 透明  黄*/
    }
  }
  .select {
    position: absolute;
    right: 10px;
    top: 10px;
  }
}
</style>

