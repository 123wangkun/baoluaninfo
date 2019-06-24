<template>
  <div class="learnOffline">
    <div class="card">
      <div class="filter-container">
        <buttonList :list="listQuery.typeList" v-model="listQuery.type" title="类型:"/>
        <buttonList :list="listQuery.objList" v-model="listQuery.obj" title="对象:"/>
        <buttonList :list="listQuery.deptList" v-model="listQuery.dept" title="发起部门:"/>
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
            placeholder="选择日期时间"
            size="small"/></div>
      </div>
      <el-table :data="list" border style="width: 100%" class="table">
        <el-table-column label="编号" width="65" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.index }}</span>
          </template>
        </el-table-column>
        <el-table-column label="考试名称" min-width="100px">
          <template slot-scope="scope">
            <span >{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column label="发起部门" width="100" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column label="学习类型" width="100" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column label="学习对象" width="180" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column label="学习文件" width="100" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column label="参加人" width="100" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column label="学习时间" width="180" align="center">
          <template slot-scope="scope">
            <i class="el-icon-time"/>
            <span style="margin-left: 5px">{{ scope.row.study_time|timeFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="100" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.state|stateFilter }}</span>
          </template>
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" icon="el-icon-view" @click="showDetail(scope.row)"/>
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
import buttonList from '@/components/buttonList'
import moment from 'moment'
import { offlinelearnList } from '@/api/learn'

export default {
  name: 'LearnOffline',
  components: { Pagination, buttonList },
  filters: {
    timeFilter(date) {
      return moment(date).format('YYYY-MM-DD  HH:mm')
    },
    stateFilter(state) {
      return state === '1' ? '进行中' : '已结束'
    }
  },
  data() {
    return {
      listQuery: {
        page: 1,
        limit: 10,
        key: '',
        state: '',
        datetime: '',
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
        dept: '',
        deptList: [
          {
            name: '全部',
            value: ''
          },
          {
            name: '部门1',
            value: '1'
          },
          {
            name: '部门2',
            value: '2'
          },
          {
            name: '部门3',
            value: '3'
          },
          {
            name: '部门4',
            value: '4'
          }
        ]
      },
      total: 0,
      list: [],
      defaultImg: 'this.src="' + require('../../assets/img/noImg.png') + '"'
    }
  },
  mounted() {
    this.getList()
  },
  methods: {
    getList() {
      offlinelearnList(this.listQuery)
        .then(response => {
          response.data.forEach(function(item, index) {
            item.index = index + 1
            item.src = process.env.BASE_API + item.src
          })
          this.list = response.data
          this.total = response.page.totalResult
        })
        .catch(error => {
          console.log(error)
        })
    },
    showDetail(item) {
      localStorage.setItem('learnOfflineDetail', JSON.stringify(item))
      this.$router.push({ path: '/learn/offlineDetail' })
    }
  }
}
</script>

<style lang="scss" scoped>
.learnOffline {
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
      .img {
        width: 100px;
      }
    }
  }
}
</style>
