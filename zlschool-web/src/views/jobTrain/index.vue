<template>
  <div class="jobTrain">
    <div class="card">
      <div class="tab-box" >
        <el-tabs v-model="activeName" class="tab-box" @tab-click="handleClick">
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
              <!-- <div><svg-icon icon-class="zhengshu" class="icon"/>证书:<span>暂无证书</span></div> -->
            </div>
            <div class="center-item">
              <div class="margin-r20">课程:<span>2</span></div>
              <div class="margin-r20">考试:<span>2</span></div>
              <!-- <div class="margin-r20">调研:<span>3</span></div>
              <div>作业:<span>1</span></div> -->
            </div>
          </div>
          <div class="right">
            <div class="tip1"/>
            <div class="tip-text">任务</div>
            <div class="bt" @click="handleDetail()">进入学习</div>
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
              <!-- <div><svg-icon icon-class="zhengshu" class="icon"/>证书:<span>暂无证书</span></div> -->
            </div>
            <div class="center-item">
              <div class="margin-r20">课程:<span>2</span></div>
              <div class="margin-r20">考试:<span>2</span></div>
              <!-- <div class="margin-r20">调研:<span>3</span></div>
              <div>作业:<span>1</span></div> -->
            </div>
          </div>
          <div class="right">
            <div class="tip2"/>
            <div class="tip-text">考试</div>
            <div class="bt" @click="handleDetail()">进入学习</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import buttonList from '@/components/buttonList'
import Pagination from '@/components/Pagination'
export default {
  name: 'JobTrain',
  components: { Pagination, buttonList },
  data() {
    return {
      activeName: 'first',
      listQuery: {
        page: 1,
        limit: 10
      },
      total: 100,
      key: '',
      state: '',
      states: [
        {
          value: '',
          name: '全部'
        },
        {
          value: 0,
          name: '未发布'
        },
        {
          value: 1,
          name: '已发布'
        },
        {
          value: -1,
          name: '已结束'
        }
      ],
      type: '',
      types: [
        {
          value: '',
          name: '全部'
        },
        {
          value: 0,
          name: '线上'
        },
        {
          value: 1,
          name: '线下'
        }
      ],
      list: [
        {
          a: '12331',
          b: '2019-02-26 09:00 ~ 2019-02-26 18:00',
          c: '线上',
          d: '未发布',
          e: '1',
          f: '1',
          g: '1',
          h: '1',
          i: '1',
          j: '1',
          k: '0%'
        },
        {
          a: '测试',
          b: '2019-02-26 09:00 ~ 2019-02-26 18:00',
          c: '线上',
          d: '未发布',
          e: '1',
          f: '1',
          g: '1',
          h: '1',
          i: '1',
          j: '1',
          k: '10%'
        },
        {
          a: '12331',
          b: '2019-02-26 09:00 ~ 2019-02-26 18:00',
          c: '线上',
          d: '未发布',
          e: '1',
          f: '1',
          g: '1',
          h: '1',
          i: '1',
          j: '1',
          k: '0%'
        },
        {
          a: '12331',
          b: '2019-02-26 09:00 ~ 2019-02-26 18:00',
          c: '线上',
          d: '未发布',
          e: '1',
          f: '1',
          g: '1',
          h: '1',
          i: '1',
          j: '1',
          k: '0%'
        },
        {
          a: '12331',
          b: '2019-02-26 09:00 ~ 2019-02-26 18:00',
          c: '线上',
          d: '未发布',
          e: '1',
          f: '1',
          g: '1',
          h: '1',
          i: '1',
          j: '1',
          k: '0%'
        },
        {
          a: '12331',
          b: '2019-02-26 09:00 ~ 2019-02-26 18:00',
          c: '线上',
          d: '未发布',
          e: '1',
          f: '1',
          g: '1',
          h: '1',
          i: '1',
          j: '1',
          k: '0%'
        }
      ]
    }
  },
  methods: {
    handleCreate() {},
    handleDetail() {
      this.$router.push({
        path: '/jobTrain/detail'
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.jobTrain {
  padding: 20px;
  min-height: calc(100vh - 110px);
  background: #f8f9fd;
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: #f3f4f6;
    padding: 10px;
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
            cursor: pointer;
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
</style>
<style lang="scss">
.jobTrain {
  .table {
    .cell {
      padding: 0;
    }
  }
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
