<template>
  <div class="score">
    <div class="card">
      <ListTitle :has-more="false" title="我的成就"/>
      <div class="chart-box">
        <div class="left">
          <div id="chart1"/>
        </div>
        <div class="right">
          <div id="chart2"/>
        </div>
      </div>
    </div>
    <div class="tab-box">
      <el-radio-group v-model="activeName">
        <el-radio-button label="tab1">我的积分</el-radio-button>
        <el-radio-button label="tab2">我的学习</el-radio-button>
        <el-radio-button label="tab3">我的考试</el-radio-button>
        <el-radio-button label="tab4">我的证书</el-radio-button>
        <el-radio-button label="tab5">我的培训待办</el-radio-button>
      </el-radio-group>
    </div>
    <div v-show="activeName=='tab1'" class="tab1-box">
      <div v-show="!showRank" class="search-box">
        <el-select v-model="value" placeholder="积分类型" size="small" style="margin-right:20px;">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>
        时间：
        <el-date-picker
          v-model="value1"
          size="small"
          type="date"
          placeholder="选择日期"
          style="margin-right:20px;"/>
        <el-button type="primary" round size="small">查询</el-button>
      </div>
      <div v-show="showRank" class="search-box1">
        <div class="bt" @click="hideRank">
          返回
        </div>
        <div class="date-box">
          时间：
          <el-date-picker
            v-model="value1"
            size="small"
            type="date"
            placeholder="选择日期"
            style="margin-right:20px;"/>
          <el-button type="primary" round size="small">查询</el-button>
        </div>
      </div>
      <el-table v-show="showRank" :data="list" border class="table">
        <el-table-column label="部门排名" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
        <el-table-column label="部门" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户名" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
        <el-table-column label="积分分值" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
        <el-table-column label="手机号" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
        <el-table-column label="公司排名" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
        <el-table-column label="中心排名" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
      </el-table>
      <div v-show="!showRank" class="info">
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
          <div class="info-count">
            <div class="item">
              <div class="name">公司排名</div>
              <div class="count">0</div>
            </div>
            <div class="item">
              <div class="name">中心排名</div>
              <div class="count">0</div>
            </div>
            <div class="item">
              <div class="name">部门排名</div>
              <div class="count">0</div>
            </div>
            <el-button type="primary" plain size="medium" @click="rank">查看排名</el-button>
          </div>
        </div>
      </div>
    </div>
    <div v-show="activeName=='tab1'&&!showRank" class="card" style="margin-top:0" >
      <ListTitle :has-more="false" title="我的学分"/>
      <div class="table-box">
        <el-table :data="list" border class="table">
          <el-table-column label="操作行为" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="获得学分" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="时间" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
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
    </div>
    <div v-show="activeName=='tab2'" class="tab2-box">
      <ListTitle :has-more="false" title="学习记录"/>
      <div class="search-box">
        时间：
        <el-date-picker
          v-model="value1"
          size="small"
          type="date"
          placeholder="选择日期"
          style="margin-right:20px;"/>
        <el-button type="primary" round size="small">查询</el-button>
      </div>
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
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常学习</div>
            </div>
          </div>
          <div class="item-line"/>
        </div>
        <div class="arrow-box">
          <img src="../../assets/img/right.png">
        </div>
      </div>
      <div class="row">
        <div class="left">
          <div id="chart3"/>
        </div>
        <div class="right">
          <div id="chart4"/>
        </div>
      </div>
    </div>
    <div v-show="activeName=='tab2'" class="tab2-box1">
      <div class="left">
        <ListTitle title="学习计划"/>
        <ul class="list1">
          <li v-for="(item,index) in list1" :key="index">
            <div class="point"/>
            <span class="title">2019.1.4 安全类教育系统学习即将开始</span>
            <el-button type="primary" size="mini">立即开始</el-button>
          </li>
        </ul>
      </div>
      <div class="right">
        <ListTitle title="最近学习"/>
        <ul class="list1">
          <li v-for="(item,index) in list1" :key="index">
            <div class="point"/>
            <span class="title">2019.1.4 安全类教育系统学习即将开始</span>
            <el-button type="primary" size="mini">重新学习</el-button>
          </li>
        </ul>
      </div>
    </div>
    <div v-show="activeName=='tab3'" class="tab3-box">
      <ListTitle :has-more="false" title="考试记录"/>
      <div class="search-box">
        时间：
        <el-date-picker
          v-model="value1"
          size="small"
          type="date"
          placeholder="选择日期"
          style="margin-right:20px;"/>
        <el-button type="primary" round size="small">查询</el-button>
      </div>
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
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">完成日常考试</div>
            </div>
          </div>
          <div class="item-line"/>
        </div>
        <div class="arrow-box">
          <img src="../../assets/img/right.png">
        </div>
      </div>
      <div class="row">
        <div class="left">
          <div id="chart5"/>
        </div>
        <div class="right">
          <div id="chart6"/>
        </div>
      </div>
    </div>
    <div v-show="activeName=='tab3'" class="tab3-box1">
      <div class="left">
        <ListTitle title="学习计划"/>
        <ul class="list1">
          <li v-for="(item,index) in list1" :key="index">
            <div class="point"/>
            <span class="title">2019.1.4 安全类教育系统学习即将开始</span>
            <el-button type="primary" size="mini">立即开始</el-button>
          </li>
        </ul>
      </div>
      <div class="right">
        <ListTitle title="最近学习"/>
        <ul class="list1">
          <li v-for="(item,index) in list1" :key="index">
            <div class="point"/>
            <span class="title">2019.1.4 安全类教育系统学习即将开始</span>
            <el-button type="primary" size="mini">重新学习</el-button>
          </li>
        </ul>
      </div>
    </div>
    <div v-show="activeName=='tab4'" class="tab4-box">
      <ListTitle :has-more="false" title="证书上传记录"/>
      <div class="search-box">
        时间：
        <el-date-picker
          v-model="value1"
          size="small"
          type="date"
          placeholder="选择日期"
          style="margin-right:20px;"/>
        <el-button type="primary" round size="small">查询</el-button>
      </div>
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
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-top">
              <div class="time">2019.01.01</div>
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item">
            <div class="line"/>
            <div class="point"/>
            <div class="line"/>
            <div class="text-bottom">
              <div class="time">2019.01.01</div>
              <div class="name">上传一级建造师</div>
            </div>
          </div>
          <div class="item-line"/>
        </div>
        <div class="arrow-box">
          <img src="../../assets/img/right.png">
        </div>
      </div>
      <div class="tab4-row">
        <div class="left">
          <div class="item-box">
            <div class="item1">
              <img src="../../assets/image/c1.png">
              <div class="">
                <div class="count">100</div>
                <div class="name">证书总数</div>
              </div>
            </div>
            <div class="item-row">
              <div class="item" style="margin-right:5px;">
                <img src="../../assets/image/c2.png">
                <div class="">
                  <div class="count">98</div>
                  <div class="name">已审核</div>
                </div>
              </div>
              <div class="item" style="margin-left:5px;">
                <img src="../../assets/image/c3.png">
                <div class="">
                  <div class="count">98</div>
                  <div class="name">待审核</div>
                </div>
              </div>
            </div>
            <div class="item-row">
              <div class="item" style="margin-right:5px;">
                <img src="../../assets/image/c4.png">
                <div class="">
                  <div class="count">1</div>
                  <div class="name">重新审核</div>
                </div>
              </div>
              <div class="item" style="margin-left:5px;">
                <img src="../../assets/image/c5.png">
                <div class="">
                  <div class="count">50</div>
                  <div class="name">即将到期</div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="right">
          <div id="chart7"/>
        </div>
      </div>
      <div class="search-box-bottom">
        <el-select v-model="value" placeholder="证书类型" size="small" style="margin-right:20px;">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>
        <el-select v-model="value" placeholder="证书状态" size="small" style="margin-right:20px;">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"/>
        </el-select>
        <el-button type="primary" round size="small">查询</el-button>
      </div>
      <div class="tab4-tab">
        <el-table :data="list" border class="table">
          <el-table-column label="序号" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="证书类型" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="证书编号" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="证书状态" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="上传时间" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="有效时间" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
          <el-table-column label="证书预览" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.a }}</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div v-show="activeName=='tab5'" class="tab5-box">
      <div class="myTask">
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
      <div class="third">
        <div class="third-tab-box" >
          <el-tabs v-model="activeName1" class="third-tab-box" @tab-click="handleClick">
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
var echarts = require('echarts')
import ListTitle from '../dashboard/components/ListTitle'
import Pagination from '@/components/Pagination'
export default {
  components: {
    ListTitle,
    Pagination
  },
  data() {
    return {
      listQuery: {
        page: 1,
        limit: 15
      },
      chart1: null,
      chart2: null,
      chart3: null,
      chart4: null,
      chart5: null,
      chart6: null,
      chart7: null,
      activeName: 'tab1',
      activeName1: 'first',
      list: [{ a: '1' }, { a: '1' }, { a: '1' }, { a: '1' }],
      list1: [{ a: '1' }, { a: '1' }, { a: '1' }, { a: '1' }],
      total: 100,
      options: [
        {
          label: '类型1',
          value: '1'
        },
        {
          label: '类型2',
          value: '1'
        }
      ],
      value1: '2019-03-26',
      showRank: false
    }
  },
  watch: {
    activeName: function(value) {
      const self = this
      if (value === 'tab2') {
        setTimeout(function() {
          self.initChart3()
          self.initChart4()
        }, 500)
      }
      if (value === 'tab3') {
        setTimeout(function() {
          self.initChart5()
          self.initChart6()
        }, 500)
      }
      if (value === 'tab4') {
        setTimeout(function() {
          self.initChart7()
        }, 500)
      }
    }
  },
  beforeDestroy() {
    this.chart1.dispose()
    this.chart1 = null
    this.chart2.dispose()
    this.chart2 = null
    this.chart3.dispose()
    this.chart3 = null
    this.chart4.dispose()
    this.chart4 = null
    this.chart5.dispose()
    this.chart5 = null
    this.chart6.dispose()
    this.chart6 = null
    this.chart7.dispose()
    this.chart7 = null
  },
  mounted() {
    this.initChart1()
    this.initChart2()
  },
  methods: {
    initChart1() {
      this.chart1 = echarts.init(document.getElementById('chart1'), 'macarons')
      this.chart1.setOption({
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
          data: [
            '提问数量',
            '证书数量',
            '学习次数',
            '考试次数',
            '培训次数',
            '答复次数'
          ]
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
              { value: 15, name: '提问数量' },
              { value: 5, name: '证书数量' },
              { value: 20, name: '学习次数' },
              { value: 9, name: '考试次数' },
              { value: 12, name: '培训次数' },
              { value: 3, name: '答复次数' }
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
    },
    initChart2() {
      this.chart2 = echarts.init(document.getElementById('chart2'), 'macarons')
      this.chart2.setOption({
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
          }
          // formatter: '{b} <br/> 积分分值：{c}<br/> 排名：{c[1]}'
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
          data: ['2016', '2017', '2018', '2019', '2020', '2021'],
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
        yAxis: [
          {
            name: '积分分值',
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
          {
            name: '公司排名',
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
          }
        ],
        series: [
          {
            name: '积分分值',
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
                    color: '#a2c4f3' // 0% 处的颜色
                  },
                  {
                    offset: 1,
                    color: '#fff' // 100% 处的颜色
                  }
                ]
              }
            },
            lineStyle: {
              color: '#4686F8',
              width: 2
            },
            symbol: 'circle',
            symbolSize: 14,
            itemStyle: {
              color: '#FFFFFE',
              borderColor: '#4686F8',
              borderWidth: 1
            }
          },
          {
            name: '公司排名',
            data: [7, 10, 40, 15, 30, 20],
            max: 50,
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
                    color: '#c9f3f5' // 0% 处的颜色
                  },
                  {
                    offset: 1,
                    color: '#fff' // 100% 处的颜色
                  }
                ]
              }
            },
            lineStyle: {
              color: '#7EECC5',
              width: 2
            },
            symbol: 'circle',
            symbolSize: 14,
            itemStyle: {
              color: '#FFFFFE',
              borderColor: '#7EECC5',
              borderWidth: 1
            }
          }
        ]
      })
    },
    initChart3() {
      this.chart3 = echarts.init(document.getElementById('chart3'), 'macarons')
      this.chart3.setOption({
        color: ['#FEB45D', '#468DE7'],
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['线上学习', '线下学习']
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
              { value: 1548, name: '线上学习' },
              { value: 535, name: '线下学习' }
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
    },
    initChart4() {
      this.chart4 = echarts.init(document.getElementById('chart4'), 'macarons')
      this.chart4.setOption({
        color: [
          '#5E8FDC',
          '#F7D23B',
          '#19B5E6',
          '#F4A94E',
          '#74D56E',
          '#F56E5A'
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['专业类', '日常类', '其他', '继续教育类', '安全类', '技术类']
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
              { value: 1548, name: '专业类' },
              { value: 535, name: '日常类' },
              { value: 100, name: '其他' },
              { value: 800, name: '继续教育类' },
              { value: 600, name: '安全类' },
              { value: 300, name: '技术类' }
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
    },
    initChart5() {
      this.chart5 = echarts.init(document.getElementById('chart5'), 'macarons')
      this.chart5.setOption({
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
          }
        },
        color: ['#59B1F0', '#468DE7'],
        xAxis: {
          type: 'category',
          data: ['线上', '线下']
        },
        yAxis: [
          {
            name: '考试次数',
            type: 'value'
          },
          {
            name: '通过次数',
            type: 'value'
          }
        ],
        series: [
          {
            data: [12, 20],
            barWidth: 20,
            type: 'bar'
          },
          {
            data: [15, 30],
            type: 'line',
            lineStyle: {
              color: '#FDB35C',
              width: 2
            },
            symbol: 'circle',
            symbolSize: 12,
            itemStyle: {
              color: '#FDB35C',
              borderColor: '#FDB35C',
              borderWidth: 1
            }
          }
        ]
      })
    },
    initChart6() {
      this.chart6 = echarts.init(document.getElementById('chart6'), 'macarons')
      this.chart6.setOption({
        color: [
          '#5E8FDC',
          '#F7D23B',
          '#19B5E6',
          '#F4A94E',
          '#74D56E',
          '#F56E5A'
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['专业类', '日常类', '其他', '继续教育类', '安全类', '技术类']
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
              { value: 1548, name: '专业类' },
              { value: 535, name: '日常类' },
              { value: 100, name: '其他' },
              { value: 800, name: '继续教育类' },
              { value: 600, name: '安全类' },
              { value: 300, name: '技术类' }
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
    },
    initChart7() {
      this.chart7 = echarts.init(document.getElementById('chart7'), 'macarons')
      this.chart7.setOption({
        color: [
          '#5E8FDC',
          '#F7D23B',
          '#19B5E6',
          '#F4A94E',
          '#74D56E',
          '#F56E5A'
        ],
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          bottom: 10,
          left: 'center',
          data: ['专业类', '日常类', '其他', '继续教育类', '安全类', '技术类']
        },
        series: [
          {
            type: 'pie',
            radius: '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
            data: [
              { value: 1548, name: '专业类' },
              { value: 535, name: '日常类' },
              { value: 100, name: '其他' },
              { value: 800, name: '继续教育类' },
              { value: 600, name: '安全类' },
              { value: 300, name: '技术类' }
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
    },
    rank() {
      this.showRank = true
    },
    hideRank() {
      this.showRank = false
    }
  }
}
</script>
<style lang="scss" scoped>
.score {
  padding: 20px;
  min-height: calc(100vh - 110px);
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
        #chart1 {
          width: 100%;
          height: 100%;
        }
      }
      .right {
        flex: 1;
        #chart2 {
          width: 100%;
          height: 100%;
        }
      }
    }
    .table-box {
      width: 100%;
      padding: 20px 20px 0 20px;
      background: white;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      border-radius: 4px;
      display: flex;
      flex-direction: column;
      align-items: center;
    }
  }
  .tab-box {
    width: 100%;
    margin-top: 20px;
    background: white;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
  }
  .tab1-box {
    padding: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    .search-box {
      background: white;
      padding: 10px;
    }
    .search-box1 {
      display: flex;
      flex-direction: row;
      margin-bottom: 20px;
      .bt {
        background: white;
        padding: 8px 15px;
        margin-right: 10px;
        line-height: 36px;
        cursor: pointer;
      }
      .date-box {
        padding: 10px 20px;
        flex: 1;
        background: white;
      }
    }
    .info {
      display: flex;
      flex-direction: row;
      background: white;
      margin-top: 20px;
      padding: 10px;
      .left {
        flex: 1;
        padding-left: 50px;
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
        flex: 1;
        .info-title {
          margin-top: 10px;
          color: #606266;
          margin-bottom: 20px;
        }
        .info-count {
          display: flex;
          flex-direction: row;
          align-items: flex-start;
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
    }
  }
  .tab2-box,
  .tab3-box,
  .tab4-box {
    background: white;
    border: 1px solid #eee;
    margin-top: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    .search-box {
      display: flex;
      flex-direction: row;
      justify-content: flex-end;
      align-items: center;
      padding: 20px 40px;
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
    .row {
      width: 100%;
      height: 340px;
      padding: 20px;
      display: flex;
      flex-direction: row;
      .left {
        flex: 1;
        border-right: 1px solid #e6e6e6;
        #chart3,
        #chart5 {
          width: 100%;
          height: 100%;
        }
      }
      .right {
        flex: 1;
        #chart4,
        #chart6,
        #chart7 {
          width: 100%;
          height: 100%;
        }
      }
    }
    .tab4-row {
      width: 100%;
      height: 340px;
      padding: 20px;
      display: flex;
      flex-direction: row;
      .left {
        flex: 1;
        border-right: 1px solid #e6e6e6;
        .item-box {
          padding: 0 50px;
          .item1 {
            border-radius: 8px;
            background: #f1f6fa;
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: center;
            padding: 20px;
            img {
              width: 55px;
              margin-right: 20px;
            }
            .count {
              font-size: 20px;
            }
            .name {
              font-size: 16px;
              color: #646464;
            }
          }
          .item-row {
            margin-top: 10px;
            display: flex;
            flex-direction: row;
            .item {
              border-radius: 8px;
              background: #f1f6fa;
              display: flex;
              flex-direction: row;
              flex: 1;
              align-items: center;
              justify-content: center;
              padding: 20px;
              img {
                width: 55px;
                margin-right: 20px;
              }
              .count {
                font-size: 20px;
              }
              .name {
                font-size: 16px;
                color: #646464;
              }
            }
          }
        }
      }
      .right {
        flex: 1;
        #chart7 {
          width: 100%;
          height: 100%;
        }
      }
    }
    .search-box-bottom {
      padding: 20px;
    }
    .tab4-tab {
      padding: 20px;
    }
  }
  .tab2-box1,
  .tab3-box1 {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    .left {
      flex: 1;
      background: white;
      border: 1px solid #eee;
      margin-right: 5px;
      border-radius: 8px;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      .list1 {
        padding: 10px;
        margin: 0;
        li {
          display: flex;
          flex-direction: row;
          align-items: center;
          padding: 10px 0;
          border-bottom: 1px dashed #000;
          cursor: pointer;
          &:active {
            background: #eee;
          }
          &:last-child {
            margin: 0;
          }
          .point {
            width: 8px;
            height: 8px;
            border-radius: 10px;
            background: #fabd2e;
            margin-right: 10px;
          }
          .title {
            flex: 1;
          }
        }
      }
    }
    .right {
      flex: 1;
      background: white;
      border: 1px solid #eee;
      margin-left: 5px;
      border-radius: 8px;
      box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
      .list1 {
        padding: 10px;
        margin: 0;
        li {
          display: flex;
          flex-direction: row;
          align-items: center;
          padding: 10px 0;
          border-bottom: 1px dashed #000;
          cursor: pointer;
          &:active {
            background: #eee;
          }
          &:last-child {
            margin: 0;
          }
          .point {
            width: 8px;
            height: 8px;
            border-radius: 10px;
            background: #fabd2e;
            margin-right: 10px;
          }
          .title {
            flex: 1;
          }
        }
      }
    }
  }
  .tab5-box {
    padding: 20px;
    border: 1px solid #eee;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
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
    .third {
      .third-tab-box {
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
<style lang="scss" >
.score {
  .el-radio-button__inner {
    border: 0;
    font-size: 16px;
  }
  .el-radio-button__orig-radio:checked + .el-radio-button__inner {
    background: #3f9ad8;
  }
}
</style>

