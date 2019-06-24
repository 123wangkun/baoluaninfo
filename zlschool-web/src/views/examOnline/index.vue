<template>
  <div class="examOnline">
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
            style="width: 200px"
            class="filter-item"
            size="small"
            @keyup.enter.native="handleFilter"
          />
        </div>
        <el-table v-loading="listLoading" :data="list" border style="width: 100%" class="table">
          <el-table-column label="编号" width="65" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.index }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考试主题" min-width="150px" align="center">
            <template slot-scope="scope">
              <span>{{ scope.row.NAME }}</span>
            </template>
          </el-table-column>
          <el-table-column label="类型" min-width="170" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.org_name }}</span>
            </template>
          </el-table-column>
          <el-table-column label="参加对象" width="80" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.remark }}</span>
            </template>
          </el-table-column>
          <el-table-column label="组织人" width="80" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.remark }}</span>
            </template>
          </el-table-column>
          <el-table-column label="积分" width="80" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.gold }}</span>
            </template>
          </el-table-column>
          <el-table-column label="状态" width="80" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.status|statusFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column label="开始时间" width="150" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.dateq|timeFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column label="结束时间" width="150" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.datez|timeFilter }}</span>
            </template>
          </el-table-column>
          <el-table-column label="考试时长" width="100" align="center">
            <template slot-scope="scope">
              <span style="margin-left: 10px">{{ scope.row.date }}分钟</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" width="100">
            <template slot-scope="scope">
              <el-button v-if="scope.row.userStatus ==='0'" size="mini" type="success" @click="gotoDetail(scope.row)">参加考试</el-button>
              <el-button v-else size="mini" @click="gotoDetail(scope.row)">查看试卷</el-button>
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
  </div>
</template>
<script>
import Pagination from '@/components/Pagination'
import { listPaper } from '@/api/exam'
import moment from 'moment'
import buttonList from '@/components/buttonList'
export default {
  name: 'ExamOnline',
  components: { Pagination, buttonList },
  filters: {
    statusFilter(date) {
      if (date === '0') {
        return '进行中'
      } else {
        return '已结束'
      }
    },
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
      list: [],
      listLoading: false
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    handleFilter() {},
    getList() {
      const self = this
      this.listLoading = true
      listPaper(this.listQuery)
        .then(response => {
          this.listLoading = false
          response.data.forEach(function(item, index) {
            item.index =
              index + 1 + (self.listQuery.page - 1) * self.listQuery.limit
          })
          this.list = response.data
          this.total = response.page.totalResult
        })
        .catch(error => {
          this.listLoading = false
          console.log(error)
        })
    },
    gotoDetail(item) {
      this.$router.push({
        path: '/exam/OnlineDetail',
        query: {
          id: item.id
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.examOnline {
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

