<template>
  <div class="jobTrainDetail">
    <div class="card">
      <div class="top-box">
        <div class="title-box">
          <div class="t1">测试培训</div>
          <div class="t2">进行中</div>
          <div class="t3">已完成<span>0%</span></div>
        </div>
        <div class="time">时间：2019-03-04 00:00 ~ 2019-03-04 24:00</div>
      </div>
      <div class="bottom-box">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="任务内容" name="first"/>
          <el-tab-pane label="任务资料" name="second"/>
          <el-tab-pane label="任务简介" name="third"/>
        </el-tabs>
        <div v-show="activeName === 'first'" class="box1">
          <div class="item">
            <div class="name curr">开始</div>
            <div class="time-box">
              <svg-icon icon-class="time1" style="width:20px;height:20px;"/>
              <div class="line"/>
            </div>
            <div class="text-box vhide">
              <div class="t1">新员工培训</div>
              <div class="t2">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成50%</div>
            </div>
            <div class="bt-box vhide">
              <div class="bt">继续学习</div>
            </div>
          </div>
          <div class="item">
            <div class="name">课程</div>
            <div class="time-box">
              <div class="point"><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">新员工培训</div>
              <div class="t2">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>50%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">继续学习</div>
            </div>
          </div>
          <div class="item">
            <div class="name">课程</div>
            <div class="time-box">
              <div class="point"><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">中国金融改革与投资融资策略</div>
              <div class="t2">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>0%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">开始学习</div>
            </div>
          </div>
          <div class="item">
            <div class="name">考试</div>
            <div class="time-box">
              <div class="point "><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">试卷测试</div>
              <div class="t2 vhide">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>0%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">开始考试</div>
            </div>
          </div>
          <div class="item">
            <div class="name">考试</div>
            <div class="time-box">
              <div class="point"><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">试卷测试123</div>
              <div class="t2 vhide">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>0%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">开始考试</div>
            </div>
          </div>
          <div class="item">
            <div class="name">课程</div>
            <div class="time-box">
              <div class="point"><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">新员工培训</div>
              <div class="t2">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>50%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">继续学习</div>
            </div>
          </div>
          <div class="item">
            <div class="name">课程</div>
            <div class="time-box">
              <div class="point"><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">中国金融改革与投资融资策略</div>
              <div class="t2">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>0%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">开始学习</div>
            </div>
          </div>
          <div class="item">
            <div class="name">考试</div>
            <div class="time-box">
              <div class="point "><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">试卷测试</div>
              <div class="t2 vhide">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>0%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">开始考试</div>
            </div>
          </div>
          <div class="item">
            <div class="name">考试</div>
            <div class="time-box">
              <div class="point"><div class="point1"/></div>
              <div class="line"/>
            </div>
            <div class="text-box">
              <div class="t1">试卷测试123</div>
              <div class="t2 vhide">共2节&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已完成&nbsp;<span>0%</span></div>
            </div>
            <div class="bt-box">
              <div class="bt">开始考试</div>
            </div>
          </div>
          <div class="item">
            <div class="name">结束</div>
            <div class="time-box">
              <div class="point" style="background: #DDDDDD;"><div class="point1" style="background: #AAAAAA;"/></div>
            </div>
          </div>
        </div>
        <div v-show="activeName === 'second'" class="box2">
          <div class="item">
            <svg-icon icon-class="attach" class="attach-icon"/>
            <div class="title">试卷心理</div>
            <div class="count">下载次数:0</div>
            <svg-icon icon-class="yj1" class="icon"/>
            <svg-icon icon-class="download" class="icon"/>
          </div>
          <div class="item">
            <svg-icon icon-class="attach" class="attach-icon"/>
            <div class="title">公司日常管理资料</div>
            <div class="count">下载次数:10</div>
            <svg-icon icon-class="yj1" class="icon"/>
            <svg-icon icon-class="download" class="icon"/>
          </div>
          <div class="item">
            <svg-icon icon-class="attach" class="attach-icon"/>
            <div class="title">试卷心理</div>
            <div class="count">下载次数:12</div>
            <svg-icon icon-class="yj1" class="icon"/>
            <svg-icon icon-class="download" class="icon"/>
          </div>
        </div>
        <div v-show="activeName === 'third'" class="box3">&nbsp;</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: 'first'
    }
  }
}
</script>

<style lang="scss" scoped>
.jobTrainDetail {
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
      flex-direction: column;
      padding: 20px;
      .title-box {
        display: flex;
        flex-direction: row;
        align-items: center;
        .t1 {
          font-size: 20px;
          margin-right: 10px;
        }
        .t2 {
          color: #4ecb78;
          border: 1px solid #4ecb78;
          border-radius: 4px;
          font-size: 14px;
          padding: 1px 2px;
          margin-right: 10px;
        }
        .t3 {
          font-size: 14px;
          color: #909399;
          span {
            color: #5db5fe;
          }
        }
      }
      .time {
        font-size: 14px;
        color: #909399;
        margin-top: 10px;
      }
    }
    .bottom-box {
      margin-top: 20px;
      background: white;
      border-radius: 4px;
      display: flex;
      flex-direction: column;
      padding: 20px;
      .box1 {
        padding: 20px;
        .item {
          display: flex;
          flex-direction: row;
          .name {
            color: #909399;
            width: 50px;
          }
          .time-box {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-right: 20px;
            .line {
              width: 1px;
              background: #c1e2fa;
              flex: 1;
            }
            .point {
              background: #cae5fa;
              width: 20px;
              height: 20px;
              border-radius: 15px;
              display: flex;
              justify-content: center;
              align-items: center;
              .point1 {
                background: #71c1ff;
                width: 8px;
                height: 8px;
                border-radius: 15px;
              }
            }
          }
          .text-box {
            flex: 1;
            padding-bottom: 20px;
            .t2 {
              font-size: 14px;
              color: #909399;
              margin-top: 10px;
              span {
                color: #303133;
              }
            }
          }
          .bt-box {
            .bt {
              color: #49abff;
              border: 1px solid #49abff;
              padding: 5px 14px;
              font-size: 16px;
              border-radius: 15px;
            }
          }
          .vhide {
            visibility: hidden;
          }
          .hide {
            display: none;
          }
          .curr {
            color: #71c1ff;
          }
        }
      }
      .box2 {
        .item {
          display: flex;
          flex-direction: row;
          align-items: center;
          border-bottom: 1px dashed #e4e7ed;
          padding: 10px 20px;
          .attach-icon {
            width: 25px;
            height: 25px;
          }
          .title {
            flex: 2;
            margin-left: 10px;
          }
          .count {
            flex: 1;
            font-size: 16px;
            color: #909399;
            width: 100px;
          }
          .icon {
            width: 30px;
            height: 30px;
            color: #409eff;
            margin-left: 20px;
            cursor: pointer;
          }
        }
      }
      .box3 {
        min-height: 400px;
      }
    }
  }
}
</style>

