<template>
  <div class="examOffline">
    <div class="card">
      <div class="filter-container">
        <buttonList :list="listQuery.typeList" v-model="listQuery.type" title="考试类型:"/>
        <buttonList :list="listQuery.objList" v-model="listQuery.obj" title="参加对象:"/>
        <buttonList :list="listQuery.stateList" v-model="listQuery.state" title="是否完成:"/>
        <buttonList :list="listQuery.passList" v-model="listQuery.pass" title="是否及格:"/>
        <div class="input-box">
          <el-input
            v-model="listQuery.key"
            placeholder="主题"
            style="width: 200px;margin-right:20px;"
            class="filter-item"
            size="small"
            @keyup.enter.native="handleFilter"
          /> <el-date-picker
            v-model="listQuery.datetime"
            type="datetime"
            placeholder="选择截至时间"
            size="small"/></div>
      </div>
      <el-table :data="list" border style="width: 100%" class="table">
        <el-table-column label="编号" width="65" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.index }}</span>
          </template>
        </el-table-column>
        <el-table-column label="考试主题" min-width="150px">
          <template slot-scope="scope">
            <span >{{ scope.row.course_name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="参加对象" width="150" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.course_name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="试卷分值" width="150" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.course_name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="及格线" width="150" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.course_name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="及格线" width="150" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.course_name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="发起部门" width="150" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.organizer }}</span>
          </template>
        </el-table-column>
        <el-table-column label="截至时间" width="180" align="center">
          <template slot-scope="scope">
            <i class="el-icon-time"/>
            <span style="margin-left: 5px">{{ scope.row.create_time|timeFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="考试得分" width="150" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.score }}</span>
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
</template>

<script>
import Pagination from '@/components/Pagination'
import moment from 'moment'
import buttonList from '@/components/buttonList'
import { examOfflineList } from '@/api/exam'
export default {
  name: 'ExamOffline',
  components: { Pagination, buttonList },
  filters: {
    timeFilter(date) {
      return moment(date).format('YYYY-MM-DD  HH:mm')
    }
  },
  data() {
    return {
      listQuery: {
        page: 1,
        limit: 10,
        key: '',
        type: '',
        typeList: [
          {
            name: '全部',
            value: ''
          },
          {
            name: '类型1',
            value: '1'
          },
          {
            name: '类型2',
            value: '2'
          },
          {
            name: '类型3',
            value: '3'
          },
          {
            name: '类型4',
            value: '4'
          }
        ],
        obj: '',
        objList: [
          {
            name: '全部',
            value: ''
          },
          {
            name: '对象1',
            value: '1'
          },
          {
            name: '对象2',
            value: '2'
          },
          {
            name: '对象3',
            value: '3'
          },
          {
            name: '对象4',
            value: '4'
          }
        ],
        state: '',
        stateList: [
          {
            name: '全部',
            value: ''
          },
          {
            name: '已完成',
            value: '1'
          },
          {
            name: '未完成',
            value: '2'
          }
        ],
        pass: '',
        passList: [
          {
            name: '全部',
            value: ''
          },
          {
            name: '及格',
            value: '1'
          },
          {
            name: '未达标',
            value: '2'
          }
        ]
      },
      total: 0,
      list: []
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      examOfflineList(this.listQuery)
        .then(response => {
          response.data.forEach(function(item, index) {
            item.index = index + 1
          })
          this.list = response.data
          this.total = response.page.totalResult
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>

<style  lang="scss" scoped>
.examOffline {
  width: 100%;
  padding: 20px;
  min-height: calc(100vh - 110px);
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: white;
    padding: 20px 20px;
    .filter-container {
      .input-box {
        width: 100%;
        display: flex;
        justify-content: flex-end;
      }
    }
    .table {
      margin-top: 20px;
    }
  }
}
</style>

