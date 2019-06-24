<template>
  <div class="score">
    <div class="card">
      <div class="top-box">
        <div class="tab-box">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="我的任务" name="third"/>
            <el-tab-pane label="我的学分" name="first"/>
            <el-tab-pane label="学分排行榜" name="second"/>
            <el-tab-pane label="学习记录" name="fourth"/>
          </el-tabs>
          <router-link to="/userInfo/scoreInfo" class="more">学分获取规则<svg-icon icon-class="arrow" class="icon"/></router-link>
        </div>
        <div v-if="activeName === 'first'" class="info">
          <div class="left">
            <div class="info-title">学分（本年度要求：<span>800</span>）</div>
            <div class="info-count">
              <div class="item">
                <div class="name">总学分获得</div>
                <div class="count">0</div>
              </div>
              <div class="item">
                <div class="name">本年度获得</div>
                <div class="count">0</div>
              </div>
              <div class="item">
                <div class="name">本月获得</div>
                <div class="count">0</div>
              </div>
            </div>
          </div>
          <div class="right">
            <div class="info-title">我的排名</div>
            <div class="count">10</div>
          </div>
        </div>
        <div v-if="activeName === 'second'" >
          <el-table :data="list" border style="width: 100%" class="table">
            <el-table-column label="名次" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.index }}</span>
              </template>
            </el-table-column>
            <el-table-column label="用户名" align="center">
              <template slot-scope="scope">
                <span >{{ scope.row.name }}</span>
              </template>
            </el-table-column>
            <el-table-column label="邮箱" align="center">
              <template slot-scope="scope">
                <span >{{ scope.row.mail }}</span>
              </template>
            </el-table-column>
            <el-table-column label="手机号" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.phone }}</span>
              </template>
            </el-table-column>
            <el-table-column label="当前学分" align="center">
              <template slot-scope="scope">
                <span>{{ scope.row.scroe }}</span>
              </template>
            </el-table-column>
          </el-table>
          <pagination
            v-show="total>0"
            :total="total"
            :page.sync="listQuery.page"
            :limit.sync="listQuery.limit"
            @pagination="getList"
          />
        </div>
        <div v-if="activeName === 'third'" class="myTask">
          <div class="top-item">
            <div class="top-t1 c1">0/<span>2</span></div>
            <div class="top-t2">我的任务</div>
          </div>
          <div class="top-item">
            <div class="top-t1 c2">0/<span>0</span></div>
            <div class="top-t2">岗位课程</div>
          </div>
          <div class="top-item">
            <div class="top-t1 c3">0<span>小时</span></div>
            <div class="top-t2">学习时长</div>
          </div>
        </div>
        <div v-if="activeName === 'fourth'" class="myRecord">
          <div class="recordItem">
            <div class="time-box">
              <div class="year">2019</div>
              <div class="month">02月28日</div>
            </div>
            <div class="point-box">
              <div class="line1"/>
              <div class="point"/>
              <div class="line2"/>
            </div>
            <div class="item-box">
              <img src="https://t.nxb100.com//upload/nxb100/course/image/20180319/1521461426310706058.jpg">
              <div class="text-box">
                <div class="t1">阿里铁军李立恒---发刊词</div>
                <div class="t2"><span>已学100%</span>&nbsp;&nbsp;&nbsp;&nbsp;用时10分51秒&nbsp;&nbsp;&nbsp;&nbsp;学习至阿里铁军第一节</div>
              </div>
              <div class="bt-box">
                <div class="bt">继续学习</div>
              </div>
            </div>
          </div>
          <div class="recordItem">
            <div class="time-box">
              <div class="year">2019</div>
              <div class="month">02月28日</div>
            </div>
            <div class="point-box">
              <div class="line1"/>
              <div class="point"/>
              <div class="line2"/>
            </div>
            <div class="item-box">
              <img src="https://t.nxb100.com//upload/nxb100/course/image/20180319/1521461426310706058.jpg">
              <div class="text-box">
                <div class="t1">阿里铁军李立恒---发刊词</div>
                <div class="t2"><span>已学100%</span>&nbsp;&nbsp;&nbsp;&nbsp;用时10分51秒&nbsp;&nbsp;&nbsp;&nbsp;学习至阿里铁军第一节</div>
              </div>
              <div class="bt-box">
                <div class="bt">继续学习</div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="activeName === 'first'" class="bottom-box">
        <div class="bottom-title">我的学分</div>
        <el-table :data="scoreList" border style="width: 100%" class="table">
          <el-table-column label="操作行为" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.name }}</span>
            </template>
          </el-table-column>
          <el-table-column label="获得学分" align="center">
            <template slot-scope="scope">
              <span style="color:#31a4ff">{{ scope.row.score }}</span>
            </template>
          </el-table-column>
          <el-table-column label="时间" align="center">
            <template slot-scope="scope">
              <span >{{ scope.row.time }}</span>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="listQuery.page"
          :limit.sync="listQuery.limit"
          @pagination="getList"
        />
      </div>
      <div v-if="activeName === 'third'" class="third">
        <div class="tab-box" >
          <el-tabs v-model="activeName1" class="tab-box" @tab-click="handleClick">
            <el-tab-pane label="待完成2" name="first"/>
            <el-tab-pane label="已完成0" name="second"/>
            <el-tab-pane label="逾期任务0" name="third"/>
            <el-tab-pane label="已报名0" name="fourth"/>
          </el-tabs>
          <el-select v-model="value" placeholder="请选择" size="mini" class="select">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </div>
        <div class="list">
          <div class="item">
            <div class="left">
              <div class="t1">测试培训</div>
              <div class="t2">即将结束</div>
              <div class="line"/>
              <div class="t3">已完成0%</div>
            </div>
            <div class="center">
              <div class="center-item "><svg-icon icon-class="time" class="icon"/>截止时间:<span>2019-02-26 23:00</span></div>
              <div class="center-item">
                <div class="margin-r20"><svg-icon icon-class="xuefen" class="icon"/>学分:<span>0</span></div>
                <div><svg-icon icon-class="zhengshu" class="icon"/>证书:<span>暂无证书</span></div>
              </div>
              <div class="center-item">
                <div class="margin-r20">课程:<span>2</span></div>
                <div class="margin-r20">考试:<span>2</span></div>
                <div class="margin-r20">调研:<span>3</span></div>
                <div>作业:<span>1</span></div>
              </div>
            </div>
            <div class="right">
              <div class="tip1"/>
              <div class="tip-text">任务</div>
              <div class="bt">进入学习</div>
            </div>
          </div>
          <div class="item">
            <div class="left">
              <div class="t1">测试</div>
              <div class="t2">即将结束</div>
              <div class="line"/>
              <div class="t3">已完成0%</div>
            </div>
            <div class="center">
              <div class="center-item "><svg-icon icon-class="time" class="icon"/>截止时间:<span>2019-02-26 23:00</span></div>
              <div class="center-item">
                <div class="margin-r20"><svg-icon icon-class="xuefen" class="icon"/>学分:<span>0</span></div>
                <div><svg-icon icon-class="zhengshu" class="icon"/>证书:<span>暂无证书</span></div>
              </div>
              <div class="center-item">
                <div class="margin-r20">课程:<span>2</span></div>
                <div class="margin-r20">考试:<span>2</span></div>
                <div class="margin-r20">调研:<span>3</span></div>
                <div>作业:<span>1</span></div>
              </div>
            </div>
            <div class="right">
              <div class="tip2"/>
              <div class="tip-text">考试</div>
              <div class="bt">进入学习</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
export default {
  components: { Pagination },
  data() {
    return {
      listQuery: {
        page: 1,
        limit: 10
      },
      activeName: 'third',
      activeName1: 'first',
      total: 21,
      list: [
        {
          index: '1',
          name: '张三',
          mail: 'xxx@qq.com',
          phone: '123569887',
          scroe: '100'
        },
        {
          index: '2',
          name: '张三',
          mail: 'xxx@qq.com',
          phone: '123569887',
          scroe: '100'
        },
        {
          index: '3',
          name: '张三',
          mail: 'xxx@qq.com',
          phone: '123569887',
          scroe: '100'
        },
        {
          index: '3',
          name: '张三',
          mail: 'xxx@qq.com',
          phone: '123569887',
          scroe: '100'
        },
        {
          index: '4',
          name: '张三',
          mail: 'xxx@qq.com',
          phone: '123569887',
          scroe: '100'
        },
        {
          index: '5',
          name: '张三',
          mail: 'xxx@qq.com',
          phone: '123569887',
          scroe: '100'
        }
      ],
      scoreList: [
        {
          name: '学习课程1',
          score: '+100',
          time: '2019-02-28 21:45'
        },
        {
          name: '学习课程2',
          score: '+100',
          time: '2019-02-28 21:45'
        },
        {
          name: '学习课程2',
          score: '+100',
          time: '2019-02-28 21:45'
        },
        {
          name: '学习课程3',
          score: '+100',
          time: '2019-02-28 21:45'
        },
        {
          name: '学习课程4',
          score: '+100',
          time: '2019-02-28 21:45'
        }
      ]
    }
  },
  methods: {
    handleClick() {},
    getList() {}
  }
}
</script>

<style lang="scss" scoped>
.score {
  padding: 20px;
  min-height: calc(100vh - 110px);
  background: #f8f9fd;
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: #f3f4f6;
    padding: 10px;
    .top-box {
      background: white;
      border-radius: 4px;
      display: flex;
      padding: 15px;
      flex-direction: column;
      .tab-box {
        width: 100%;
        position: relative;
        .more {
          position: absolute;
          top: 10px;
          right: 0;
          color: #909399;
          cursor: pointer;
          .icon {
            margin: 0 10px 0 5px;
          }
        }
      }
      .info {
        display: flex;
        flex-direction: row;
        .left {
          flex: 7;
          .info-title {
            margin-top: 10px;
            color: #606266;
            margin-bottom: 20px;
            span {
              color: #409eff;
            }
          }
          .info-count {
            display: flex;
            flex-direction: row;
            margin-bottom: 20px;
            .item {
              display: flex;
              flex-direction: column;
              align-items: center;
              margin-right: 50px;
              .name {
                color: #909399;
              }
              .count {
                margin-top: 30px;
                font-size: 18px;
              }
            }
          }
        }
        .right {
          flex: 3;
          .info-title {
            margin-top: 10px;
            color: #606266;
            margin-bottom: 20px;
          }
          .count {
            color: #ef5468;
            font-size: 30px;
            font-weight: bold;
          }
        }
      }
      .myTask {
        background: white;
        border-radius: 4px;
        display: flex;
        .top-item {
          flex: 1;
          display: flex;
          flex-direction: column;
          align-items: center;
          .top-t1 {
            padding: 40px 0;
            font-size: 25px;
            font-weight: bold;
            span {
              font-size: 16px;
            }
          }
          .top-t2 {
            padding-bottom: 20px;
            font-size: 18px;
          }
          .c1 {
            color: #f77697;
          }
          .c2 {
            color: #35abee;
          }
          .c3 {
            color: #6e7585;
          }
        }
      }
      .myRecord {
        .recordItem {
          display: flex;
          flex-direction: row;
          .time-box {
            padding-top: 20px;
            .year {
              font-size: 18px;
              font-weight: 400;
              color: #8a8c8f;
            }
            .month {
              line-height: 16px;
              font-size: 12px;
              color: #8a8c8f;
            }
          }
          .point-box {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 0 20px;
            .line1 {
              width: 1px;
              background: #d3d7da;
              height: 20px;
            }
            .point {
              width: 8px;
              height: 8px;
              border-radius: 50%;
              background-color: #d0d6d9;
            }
            .line2 {
              flex: 1;
              width: 1px;
              background: #d3d7da;
            }
          }
          .item-box {
            display: flex;
            flex-direction: row;
            padding-top: 20px;
            flex: 1;
            padding-bottom: 20px;
            border-bottom: 1px solid #eff1f0;
            img {
              width: 200px;
              height: 111px;
              border-radius: 6px;
              margin-right: 15px;
            }
            .text-box {
              flex: 1;
              .t1 {
                color: #333;
                font-size: 18px;
              }
              .t2 {
                color: #666;
                font-size: 14px;
                margin-top: 15px;
                span {
                  color: #31a4ff;
                }
              }
            }
            .bt-box {
              display: flex;
              align-items: center;
              .bt {
                font-size: 14px;
                color: #31a4ff;
                border: 1px solid #31a4ff;
                width: 90px;
                height: 32px;
                text-align: center;
                line-height: 32px;
                cursor: pointer;
              }
            }
          }
        }
      }
    }
    .bottom-box {
      background: white;
      border-radius: 4px;
      display: flex;
      flex-direction: column;
      margin-top: 10px;
      padding: 15px;
      .bottom-title {
        font-size: 18px;
        color: #606266;
        width: 100%;
        border-bottom: 1px solid #e4e7ed;
        padding-bottom: 10px;
        padding-top: 5px;
        margin-bottom: 15px;
      }
    }
    .third {
      .tab-box {
        margin: 10px 0;
        position: relative;
        .select {
          position: absolute;
          top: 6px;
          right: 0;
        }
      }
      .list {
        .item {
          display: flex;
          flex-direction: row;
          background: white;
          border-radius: 4px;
          margin-bottom: 20px;
          .left {
            flex: 3;
            display: flex;
            flex-direction: column;
            align-items: center;
            background: #60baec;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
            color: white;
            .t1 {
              font-size: 25px;
              padding-top: 25px;
            }
            .t2 {
              font-size: 27px;
              padding-top: 25px;
            }
            .line {
              background: #fff;
              align-self: stretch;
              height: 4px;
              margin: 0 40px;
              margin-top: 25px;
            }
            .t3 {
              font-size: 16px;
              padding: 25px 0;
            }
          }
          .center {
            flex: 5;
            padding: 0 0 0 40px;
            display: flex;
            flex-direction: column;
            .center-item {
              border-bottom: 1px solid #f3f3f3;
              flex: 1;
              display: flex;
              flex-direction: row;
              align-items: center;
              color: #919baa;
              span {
                color: #303133;
              }
              .margin-r20 {
                margin-right: 70px;
              }
              .icon {
                font-size: 20px;
                margin-right: 10px;
              }
              &:last-child {
                border: 0;
              }
            }
          }
          .right {
            flex: 2;
            display: flex;
            position: relative;
            align-items: flex-end;
            justify-content: center;
            .bt {
              background: #f78197;
              color: white;
              padding: 15px 40px;
              margin-bottom: 25px;
              font-size: 20px;
            }
            .tip1 {
              width: 0px;
              height: 0px;
              border-top: 40px solid #3fd3ef;
              border-right: 18px solid #3fd3ef;
              border-bottom: 10px solid white;
              border-left: 18px solid #3fd3ef;
              border-bottom-left-radius: 10px;
              border-bottom-right-radius: 10px;
              position: absolute;
              top: 0;
              right: 50px;
            }
            .tip2 {
              width: 0px;
              height: 0px;
              border-top: 40px solid #7cd6bc;
              border-right: 18px solid #7cd6bc;
              border-bottom: 10px solid white;
              border-left: 18px solid #7cd6bc;
              border-bottom-left-radius: 10px;
              border-bottom-right-radius: 10px;
              position: absolute;
              top: 0;
              right: 50px;
            }
            .tip-text {
              padding-top: 10px;
              width: 36px;
              text-align: center;
              position: absolute;
              top: 0;
              right: 50px;
              color: white;
            }
          }
        }
      }
    }
  }
}
</style>
<style  lang="scss" >
.score {
  .el-tabs__item {
    font-size: 18px;
  }
  .el-tabs__item {
    color: #909399;
  }
  .el-tabs__item.is-active {
    color: #303133;
  }
}
</style>
