<template>
  <div class="examCenter">
    <div class="top">
      <div class="box bg1">
        <div class="left">
          <div class="name">已完成考试</div>
          <div class="count">100</div>
        </div>
        <img class="iconimg" src="../../assets/image/icon1.png">
      </div>
      <div class="box bg2">
        <div class="left">
          <div class="name">正在进行考试</div>
          <div class="count">100</div>
        </div>
        <img class="iconimg" src="../../assets/image/icon2.png">
      </div>
      <div class="box bg3">
        <div class="left">
          <div class="name">剩余考试任务</div>
          <div class="count">100</div>
        </div>
        <img class="iconimg" src="../../assets/image/icon3.png">
      </div>
      <div class="box bg4">
        <div class="left">
          <div class="name">已领取红包数量</div>
          <div class="count">100</div>
        </div>
        <img class="iconimg" src="../../assets/image/icon4.png">
      </div>
      <div class="box bg5">
        <div class="left">
          <div class="name">全部考试</div>
          <div class="count">100</div>
        </div>
        <img class="iconimg" src="../../assets/image/icon8.png">
      </div>
    </div>
    <div class="row" style="margin-top:10px;">
      <div class="left">
        <ListTitle :has-more="false" title="考试详情"/>
        <div id="chart1" class="chart1"/>
      </div>
      <div class="right">
        <ListTitle :has-more="false" title="考试进度"/>
        <div class="r-box ">
          <div class="box1">
            <div id="chart2" class="chart2" style="height:270px"/>
          </div>
          <div class="list1">
            <div class="item">
              <div class="text-box">
                <div class="t1">时间</div>
                <div class="t2">2019-01-01</div>
                <div class="point"/>
                <div class="t3">已完成</div>
              </div>
              <div class="text-box margin-t">
                <div class="t1">内容</div>
                <div class="t2">安全类考试</div>
              </div>
            </div>
            <div class="item">
              <div class="text-box">
                <div class="t1">时间</div>
                <div class="t2">2019-01-01</div>
                <div class="point"/>
                <div class="t3">已完成</div>
              </div>
              <div class="text-box margin-t">
                <div class="t1">内容</div>
                <div class="t2">安全类考试</div>
              </div>
            </div>
            <div class="item">
              <div class="text-box">
                <div class="t1">时间</div>
                <div class="t2">2019-01-01</div>
                <div class="point"/>
                <div class="t3">已完成</div>
              </div>
              <div class="text-box margin-t">
                <div class="t1">内容</div>
                <div class="t2">安全类考试</div>
              </div>
            </div>
            <div class="item">
              <div class="text-box">
                <div class="t1">时间</div>
                <div class="t2">2019-01-01</div>
                <div class="point"/>
                <div class="t3">已完成</div>
              </div>
              <div class="text-box margin-t">
                <div class="t1">内容</div>
                <div class="t2">安全类考试</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="card">
      <ListTitle :has-more="false" title="考试情况"/>
      <div class="chart-box">
        <div class="left">
          <div id="chart3"/>
        </div>
        <div class="right">
          <div id="chart4"/>
        </div>
      </div>
    </div>
    <div class="card">
      <ListTitle :has-more="false" title="考试记录"/>
      <div class="recordlist">
        <div class="arrow-box">
          <img src="../../assets/img/left.png">
        </div>
        <div class="list">
          <div class="item-line"/>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">安全类考试</div>
            </div>
          </div>
          <div class="item-line"/>
        </div>
        <div class="arrow-box">
          <img src="../../assets/img/right.png">
        </div>
      </div>
      <div class="recordtable">
        <el-table :data="list" border stripe class="table">
          <el-table-column label="序号" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考试主题" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考试类型" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="参与对象" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="发起部门" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="发起人" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考试截止日期" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="要求人数" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="实际人数" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="合格率" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" >
            <template slot-scope="scope">
              <el-button size="mini" plain @click="handleEdit(scope.row)">下载</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
var echarts = require('echarts')
import 'echarts-liquidfill'
import ListTitle from '../dashboard/components/ListTitle'
export default {
  components: {
    ListTitle
  },
  data() {
    return {
      chart1: null,
      chart2: null,
      chart3: null,
      chart4: null,
      list: [
        {
          a: ''
        },
        {
          a: ''
        },
        {
          a: ''
        }
      ]
    }
  },
  beforeDestroy() {
    this.chart1.dispose()
    this.chart1 = null
    this.chart2.dispose()
    this.chart2 = null
    this.chart3.dispose()
    this.chart3 = null
  },
  mounted() {
    this.initChart1()
    this.initChart2()
    this.initChart3()
    this.initChart4()
  },
  methods: {
    initChart1() {
      this.chart1 = echarts.init(document.getElementById('chart1'), 'macarons')
      this.chart1.setOption({
        title: {
          text: '总次数',
          left: '20',
          textStyle: {
            fontSize: 14,
            color: '#333'
          }
        },
        color: ['#59B0F0', '#F56E5A'],
        legend: {
          left: 90,
          data: ['线上', '线下', '通过次数']
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
              '安全类',
              '质量类',
              '质量类',
              '质量类',
              '质量类',
              '质量类',
              '质量类',
              '质量类'
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
            name: '线上',
            type: 'bar',
            stack: '总量',
            barWidth: 20,
            barGap: '10%',
            itemStyle: {
              normal: {
                color: 'rgba(255,144,128,1)',
                label: {
                  show: true,
                  textStyle: {
                    color: '#fff'
                  },
                  position: 'insideTop',
                  formatter: function(p) {
                    return p.value > 0 ? p.value : ''
                  }
                }
              }
            },
            data: [9, 3, 5, 6, 2, 2, 8, 5]
          },

          {
            name: '线下',
            type: 'bar',
            stack: '总量',
            barWidth: 20,
            itemStyle: {
              normal: {
                color: 'rgba(0,191,183,1)',
                barBorderRadius: 0,
                label: {
                  show: true,
                  position: 'top',
                  formatter: function(p) {
                    return p.value > 0 ? p.value : ''
                  }
                }
              }
            },
            data: [7, 4, 6, 5, 7, 6, 5, 7]
          },
          {
            name: '通过次数',
            type: 'line',
            stack: '总量',
            symbolSize: 14,
            symbol: 'circle',
            itemStyle: {
              normal: {
                color: 'rgba(252,230,48,1)',
                barBorderRadius: 0,
                label: {
                  show: true,
                  position: 'top',
                  formatter: function(p) {
                    return p.value > 0 ? p.value : ''
                  }
                }
              }
            },
            data: [15, 7, 11, 11, 9, 8, 13, 12]
          }
        ]
      })
    },
    initChart2() {
      this.chart2 = echarts.init(document.getElementById('chart2'), 'macarons')
      this.chart2.setOption({
        series: [
          {
            type: 'liquidFill',
            radius: '80%',
            data: [0.8, 0.8, 0.8],
            outline: {
              show: true,
              itemStyle: {
                borderWidth: 5,
                borderColor: '#1593E7'
              }
            },
            backgroundStyle: {
              borderWidth: 5,
              borderColor: '#1593E7'
              // color: '#1593E7'
            },
            label: {
              normal: {
                formatter: 0.8 * 100 + '%\n已完成学习任务占比',
                textStyle: {
                  fontSize: 20
                }
              }
            }
          }
        ]
      })
    },
    initChart3() {
      this.chart3 = echarts.init(document.getElementById('chart3'), 'macarons')
      this.chart3.setOption({
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
    initChart4() {
      this.chart4 = echarts.init(document.getElementById('chart4'), 'macarons')
      this.chart4.setOption({
        title: {
          text: '考试数量',
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
              '京沪养护处',
              '京沪养护处',
              '京沪养护处',
              '京沪养护处',
              '京沪养护处',
              '京沪养护处',
              '京沪养护处'
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
.examCenter {
  width: 100%;
  padding: 20px;
  min-height: calc(100vh - 110px);
  .top {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: #f3f4f6;
    padding: 20px;
    display: flex;
    flex-direction: row;
    .bg1 {
      background: url(../../assets/image/bg1.png) no-repeat;
    }
    .bg2 {
      background: url(../../assets/image/bg2.png) no-repeat;
    }
    .bg3 {
      background: url(../../assets/image/bg3.png) no-repeat;
    }
    .bg4 {
      background: url(../../assets/image/bg4.png) no-repeat;
    }
    .bg5 {
      background: url(../../assets/image/bg5.png) no-repeat;
    }
    .box {
      background-size: cover;
      display: flex;
      flex-direction: row;
      flex: 1;
      position: relative;
      margin-right: 20px;
      height: calc((100vw - 380px) / 5 / 274 * 108);
      &:last-child {
        margin-right: 0px;
      }
      .bgimg {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
      }
      .left {
        flex: 1;
        color: white;
        padding: 15px;
        .count {
          margin-top: 15px;
          font-size: 25px;
        }
      }
      .iconimg {
        position: absolute;
        top: 15px;
        right: 15px;
        height: 40px;
      }
    }
  }
  .row {
    display: flex;
    flex-direction: row;
    .left {
      flex: 1;
      background: white;
      border: 1px solid #eee;
      margin-right: 5px;
      border-radius: 8px;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      .chart1 {
        height: 350px;
        padding: 20px;
      }
    }
    .right {
      flex: 1;
      background: white;
      border: 1px solid #eee;
      margin-left: 5px;
      border-radius: 8px;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      .r-box {
        display: flex;
        flex-direction: row;
        height: 350px;
        .box1 {
          flex: 1;
          height: 350px;
          padding: 40px 0;
        }
        .list1 {
          flex: 1;
          background: #f9f9f9;
          margin: 40px;
          border: 1px solid #dddddd;
          border-radius: 8px;
          padding: 0px 10px;
          .item {
            padding: 10px;
            border-bottom: 1px dashed #dddddd;
            height: 67px;
            &:last-child {
              border: 0;
            }
            .margin-t {
              margin-top: 5px;
            }
            .text-box {
              display: flex;
              flex-direction: row;
              align-items: center;
              .t1 {
                color: #747474;
                margin-right: 10px;
              }
              .t2 {
                flex: 1;
              }
              .point {
                width: 10px;
                height: 10px;
                border-radius: 10px;
                background: #448de5;
                margin-right: 5px;
              }
              .t3 {
                color: #448de5;
              }
            }
          }
        }
      }
    }
  }
  .card {
    background: white;
    border: 1px solid #eee;
    margin-top: 10px;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    .chart-box {
      width: 100%;
      height: 350px;
      padding: 20px;
      display: flex;
      flex-direction: row;
      .left {
        flex: 1;
        border-right: 1px solid #e6e6e6;
        position: relative;
        #chart3 {
          width: 100%;
          height: 100%;
        }
      }
      .right {
        flex: 1;
        #chart4 {
          width: 100%;
          height: 100%;
        }
      }
    }
    .recordlist {
      width: 100%;
      padding: 20px;
      display: flex;
      flex-direction: row;
      .arrow-box {
        display: flex;
        align-items: center;
        justify-content: center;
        flex: 1;
        img {
          width: 30px;
          cursor: pointer;
        }
      }
      .list {
        margin: 20px 0;
        width: 950px;
        height: 58px;
        display: flex;
        flex-direction: row;
        align-items: center;
        .item-line {
          border-bottom: 1px dashed #4587f8;
          width: 35px;
        }
        .item {
          width: 110px;
          height: 58px;
          display: flex;
          flex-direction: row;
          align-items: center;
          position: relative;
          .text-top {
            position: absolute;
            top: -20px;
            width: 100%;
            text-align: center;
            .time {
              font-size: 16px;
            }
            .name {
              font-size: 14px;
            }
          }
          .text-bottom {
            position: absolute;
            bottom: -20px;
            width: 100%;
            text-align: center;
            .time {
              font-size: 16px;
            }
            .name {
              font-size: 14px;
            }
          }
          .line {
            border-bottom: 1px dashed #4587f8;
            flex: 1;
          }
          .point {
            width: 18px;
            height: 18px;
            border-radius: 18px;
            border: 2px solid #4587f8;
          }
        }
      }
    }
    .recordtable {
      margin: 20px;
    }
  }
}
</style>

