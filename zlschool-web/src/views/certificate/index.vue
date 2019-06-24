<template>
  <div class="certificate">
    <div class="card">
      <ListTitle :has-more="false" title="企业证书信息"/>
      <div class="row">
        <div class="left">
          <div class="top">
            <div class="item bg1">
              <img src="../../assets/image/a2.png">
              <div class="text-box">
                <div class="text1">100</div>
                <div class="text2">学历学位类</div>
              </div>
            </div>
            <div class="item bg2">
              <img src="../../assets/image/a3.png">
              <div class="text-box">
                <div class="text1">80</div>
                <div class="text2">职称类</div>
              </div>
            </div>
            <div class="item bg3">
              <img src="../../assets/image/a1.png">
              <div class="text-box">
                <div class="text1">40</div>
                <div class="text2">从业资格类</div>
              </div>
            </div>
          </div>
          <div class="chart1">
            <div id="chart1"/>
          </div>
        </div>
        <div class="right">
          <div class="search-box">
            <el-select v-model="value" placeholder="积分类型" size="small" style="margin-right:20px;">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"/>
            </el-select>
          </div>
          <div class="chart2">
            <div id="chart2"/>
          </div>
        </div>
      </div>
    </div>
    <div class="card" style="margin-top:20px;">
      <ListTitle :has-more="false" title="个人证书"/>
      <div class="row1">
        <div class="item">
          <div class="search-box">
            <el-select v-model="value1" placeholder="证书类型" size="small" style="margin-right:20px;">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"/>
            </el-select>
            <el-select v-model="value1" placeholder="证书状态" size="small" style="margin-right:20px;">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"/>
            </el-select>
          </div>
          <div class="item-title">学历学位类:</div>
          <div class="item-row">
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">本科</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">研究生</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">博士</div>
            </div>
          </div>
          <div class="item-title">职称类:</div>
          <div class="item-row">
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">技术员</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">技术员</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">技术员</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">技术员</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
          </div>
          <div class="item-title">从业资格类:</div>
          <div class="item-row">
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">一级建造师</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">一级建造师</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">一级建造师</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">一级建造师</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">一级建造师</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
            <div class="item-box">
              <img src="../../assets/image/img1.jpg">
              <div class="text">一级建造师</div>
              <div class="text">有效期：2019.1.1</div>
            </div>
          </div>
        </div>
        <div class="item">
          <div class="search-box">
            <el-select v-model="value" placeholder="积分类型" size="small" style="margin-right:20px;">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"/>
            </el-select>
            <el-select v-model="value3" placeholder="积分类型" size="small" style="margin-right:20px;">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"/>
            </el-select>
          </div>
          <div class="table-box">
            <el-table :data="list" border stripe class="table">
              <el-table-column label="部门" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.a }}</span>
                </template>
              </el-table-column>
              <el-table-column label="姓名" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.a }}</span>
                </template>
              </el-table-column>
              <el-table-column label="证书类型" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.a }}</span>
                </template>
              </el-table-column>
              <el-table-column label="编号" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.a }}</span>
                </template>
              </el-table-column>
              <el-table-column label="有效时间" align="center">
                <template slot-scope="scope">
                  <span>{{ scope.row.a }}</span>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ListTitle from '../dashboard/components/ListTitle'
var echarts = require('echarts')
export default {
  name: 'Certificate',
  components: { ListTitle },
  filters: {},
  data() {
    return {
      chart1: null,
      chart2: null,
      value: '京沪处',
      value1: '',
      value3: '本科',
      options: [
        {
          label: '京沪处',
          value: '1'
        },
        {
          label: '京沪处1',
          value: '1'
        }
      ],
      list: [
        {
          a: '　'
        },
        {
          a: '　'
        },
        {
          a: '　'
        },
        {
          a: '　'
        },
        {
          a: '　'
        },
        {
          a: '　'
        },

        {
          a: '　'
        }
      ]
    }
  },
  watch: {},
  beforeDestroy() {
    this.chart1.dispose()
    this.chart1 = null
    this.chart2.dispose()
    this.chart2 = null
  },
  mounted() {
    this.initChart1()
    this.initChart2()
  },
  methods: {
    initChart1() {
      this.chart1 = echarts.init(document.getElementById('chart1'), 'macarons')
      this.chart1.setOption({
        title: {
          text: '证书总数',
          left: '0',
          textStyle: {
            fontSize: 14,
            color: '#333'
          }
        },
        color: ['#F56E5A', '#FEB45D', '#5BB0F0'],
        legend: {
          left: 70,
          data: ['学习学位类', '职称类', '从业资格类']
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          borderWidth: 0,
          left: '40',
          right: '40',
          bottom: '3%',
          top: '30px',
          containLabel: true
        },
        calculable: true,
        xAxis: [
          {
            type: 'category',
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
            },
            data: [
              '徐州养护中心',
              '淮安养护中心',
              '淮安养护中心',
              '淮安养护中心',
              '淮安养护中心'
            ]
          }
        ],
        yAxis: [
          {
            type: 'value',
            splitLine: {
              show: false
            },
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
              interval: 0
            },
            splitArea: {
              show: false
            }
          }
        ],
        series: [
          {
            name: '学习学位类',
            type: 'bar',
            stack: '证书总数',
            barWidth: 20,
            data: [120, 132, 101, 134, 90]
          },
          {
            name: '职称类',
            type: 'bar',
            stack: '证书总数',
            barWidth: 20,
            data: [220, 182, 191, 234, 290]
          },
          {
            name: '从业资格类',
            type: 'bar',
            stack: '证书总数',
            barWidth: 20,
            data: [150, 232, 201, 154, 190]
          }
        ]
      })
    },
    initChart2() {
      this.chart2 = echarts.init(document.getElementById('chart2'), 'macarons')
      this.chart2.setOption({
        color: [
          '#F4A94E',
          '#74D56E',
          '#F56E5A',
          '#5E8FDC',
          '#F7D23B',
          '#19B5E6'
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['本科', '研究生', '博士', '技术员', '助理工程师', '工程师']
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
              { value: 15, name: '本科' },
              { value: 5, name: '研究生' },
              { value: 20, name: '博士' },
              { value: 9, name: '技术员' },
              { value: 12, name: '助理工程师' },
              { value: 3, name: '工程师' }
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
.certificate {
  width: 100%;
  padding: 20px;
  min-height: calc(100vh - 110px);
  .card {
    background: white;
    border: 1px solid #eee;
    margin-right: 5px;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    .row {
      width: 100%;
      height: 540px;
      padding: 20px 0;
      display: flex;
      flex-direction: row;
      .left {
        display: flex;
        flex-direction: column;
        flex: 1;
        border-right: 1px solid #e6e6e6;
        padding: 0 20px;
        .top {
          display: flex;
          flex-direction: row;
          .bg1 {
            background: url(../../assets/image/bg6.png) no-repeat;
          }
          .bg2 {
            background: url(../../assets/image/bg7.png) no-repeat;
          }
          .bg3 {
            background: url(../../assets/image/bg8.png) no-repeat;
          }
          .item {
            flex: 1;
            display: flex;
            flex-direction: row;
            align-items: center;

            background-size: cover;
            position: relative;
            margin-right: 20px;
            height: calc((50vw - 210px) / 3 / 196 * 86);
            padding: 15px;
            &:last-child {
              margin-right: 0px;
            }
            img {
              width: 40px;
            }
            .text-box {
              flex: 1;
              .text1 {
                font-size: 24px;
                color: white;
                text-align: right;
              }
              .text2 {
                margin-top: 10px;
                font-size: 14px;
                color: white;
                text-align: right;
              }
            }
          }
        }
        .chart1 {
          flex: 1;
          width: 100%;
          #chart1 {
            width: 100%;
            height: 100%;
          }
        }
      }
      .right {
        flex: 1;
        display: flex;
        flex-direction: column;
        padding: 0 20px;
        display: flex;
        flex-direction: column;
        .search-box {
          display: flex;
          justify-content: flex-end;
        }
        .chart2 {
          width: 100%;
          flex: 1;
          #chart2 {
            width: 100%;
            height: 100%;
          }
        }
      }
    }
    .row1 {
      width: 100%;
      padding: 20px 0;
      display: flex;
      flex-direction: row;
      .item {
        display: flex;
        flex-direction: column;
        flex: 1;
        padding: 0 20px;
        .search-box {
          display: flex;
          justify-content: flex-end;
        }
        .table-box {
          padding: 20px 0;
        }
        .item-title {
          font-size: 18px;
          margin-top: 20px;
        }
        .item-row {
          width: 100%;
          margin-top: 10px;
          .item-box {
            float: left;
            width: calc((50vw - 280px) / 4);
            margin-right: 20px;
            margin-top: 10px;
            img {
              width: 100%;
            }
            .text {
              text-align: center;
              font-size: 14px;
            }
          }
        }
      }
    }
  }
}
</style>

