<template>
  <div class="dashboard-container">
    <!-- <div class="Breadcrumb">
      <Breadcrumb/>
    </div> -->
    <div class="content">
      <div class="row">
        <div class="f2">
          <ListTitle title="最新公告"/>
          <TrainList :list="noticeList"/>
        </div>
        <div class="f1">
          <ListTitle title="培训活动"/>
          <NoticeMoreList1 :list="activeList" style="flex:1;"/>
        </div>
      </div>
      <div class="card">
        <ListTitle :has-more="false" title="学术积分占比概况"/>
        <PieChart/>
      </div>
      <div class="card">
        <ListTitle :has-more="false" title="各部门考试通过率排行榜"/>
        <RankingChart />
      </div>
      <div class="card">
        <ListTitle title="热门课程"/>
        <hotList1 />
      </div>
      <div class="card">
        <ListTitle :has-more="false" title="学习数量和学习方式"/>
        <UserList1 :list="userList"/>
      </div>
      <zszl/>
      <div class="card">
        <ListTitle :has-more="false" title="部门执行情况"/>
        <RadarChart1/>
      </div>
      <div class="card">
        <ListTitle :has-more="false" title="明星学员"/>
        <UserList2 :list="userList"/>
      </div>
      <BarChart2/>
      <el-dialog :visible.sync="dialogTableVisible" title width="65%">
        <chart1 v-if="showbar"/>
        <chart2 v-if="showradar"/>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import Breadcrumb from '@/components/Breadcrumb'
import { mapGetters } from 'vuex'
import ListTitle from './components/ListTitle1'
import TrainList from './components/TrainList'
import NoticeMoreList from './components/NoticeMoreList'
import NoticeMoreList1 from './components/NoticeMoreList1'
import RankingChart from './components/RankingChart'
import zszl from './components/zszl'
import hotList1 from './components/hotList1'
import BarChart from './components/BarChart'
import PieChart from './components/PieChart'
import UserList from './components/UserList'
import UserList2 from './components/UserList2'
import RadarChart1 from './components/RadarChart1'
import BarChart2 from './components/BarChart2'

import operation from './components/operation'
import hotList from './components/hotList'
import RadarChart from './components/RadarChart'
import systemInfo from './components/systemInfo'
import UserList1 from './components/UserList1'
import chart1 from './components/chart1'
import chart2 from './components/chart2'
// import { messageList } from '@/api/index'
export default {
  name: 'Dashboard',
  components: {
    ListTitle,
    NoticeMoreList,
    NoticeMoreList1,
    BarChart,
    UserList,
    operation,
    hotList,
    RadarChart,
    systemInfo,
    chart1,
    chart2,
    TrainList,
    PieChart,
    RankingChart,
    hotList1,
    UserList1,
    zszl,
    RadarChart1,
    UserList2,
    BarChart2,
    Breadcrumb
  },
  data() {
    return {
      noticeList: [
        { TITLE: '【总监办】关于高速公路养护培训后比赛活动', CREATEDATE: 1535695693000 },
        { TITLE: '【总监办】关于高速公路养护培训后比赛活动', CREATEDATE: 1535695693000 },
        { TITLE: '【总监办】关于高速公路养护培训后比赛活动', CREATEDATE: 1535695693000 },
        { TITLE: '【总监办】关于高速公路养护培训后比赛活动', CREATEDATE: 1535695693000 }
      ],
      activeList: [
        { TITLE: '【总监办】关于高速公路养护培训后比赛活动', CREATEDATE: 1535695693000 },
        { TITLE: '【总监办】关于高速公路养护培训后比赛活动', CREATEDATE: 1535695693000 },
        { TITLE: '【总监办】关于高速公路养护培训后比赛活动', CREATEDATE: 1535695693000 }
      ],
      list: [1, 2, 3],
      userList: [1, 2, 3, 4, 5],
      hotList: [1, 2, 3],
      dialogTableVisible: false,
      showbar: false,
      showradar: false
    }
  },
  computed: {
    ...mapGetters(['name', 'roles'])
  },
  mounted() {
    this.messageList(1)
    this.messageList(2)
  },
  methods: {
    showBar(param) {
      console.log(11111)
      this.showbar = true
      this.showradar = false
      this.dialogTableVisible = true
    },
    showradarchart(param) {
      this.showbar = false
      this.showradar = true
      this.dialogTableVisible = true
    },
    messageList(type) {
      // messageList({
      //   page: 1,
      //   limit: 5,
      //   type: type
      // })
      //   .then(response => {
      //     if (type === 1) {
      //       this.noticeList = response.data.filter((item, index) => {
      //         return index < 5
      //       })
      //     } else {
      //       this.activeList = response.data.filter((item, index) => {
      //         return index < 5
      //       })
      //     }
      //   })
      //   .catch(error => {
      //     console.log(error)
      //   })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  .content {
    margin: 10px;
    .row {
      display: flex;
      flex-direction: row;
      .left {
        flex: 1;
        margin-right: 5px;
      }
      .right {
        flex: 1;
        margin-left: 5px;
      }
      .f2 {
        flex: 2;
        margin-right: 5px;
      }
      .f1 {
        flex: 1;
        display: flex;
        flex-direction: column;
        margin-left: 5px;
      }
    }
    .card {
      background: white;
      border-radius: 8px;
      .bottom-box {
        display: flex;
        flex-direction: row;
        .f1 {
          flex: 1;
        }
      }
    }
  }
}
</style>
<style lang="scss">
.Breadcrumb {
  background: url(../../assets/img/topbg1.png) no-repeat;
  background-size: 100% 100%;
  .no-redirect {
    color: white !important;
  }
}
</style>
