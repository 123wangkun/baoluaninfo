<template>
  <div class="controlCenter">
    <div class="card">
      <div class="filter-container">
        <buttonList :list="states" v-model="state" title="状态:"/>
        <buttonList :list="types" v-model="type" title="类型:"/>
        <div class="input-box">
          <div class="tb-box">
            <el-button
              class="filter-item"
              type="primary"
              icon="el-icon-edit"
              size="mini"
              @click="handleCreate"
            >新建培训</el-button>
          </div>
          <el-input
            v-model="key"
            placeholder="名称"
            style="width: 200px;"
            class="filter-item"
            size="small"
            @keyup.enter.native="handleFilter"
          />
        </div>
      </div>
      <el-table :data="list" stripe style="width: 100%" class="table">
        <el-table-column label="名称" min-width="150px" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.a }}</span>
          </template>
        </el-table-column>
        <el-table-column label="时间" width="270" align="center">
          <template slot-scope="scope">
            <i class="el-icon-time"/>
            <span >{{ scope.row.b }}</span>
          </template>
        </el-table-column>
        <el-table-column label="类型" width="60" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.c }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="60" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.d }}</span>
          </template>
        </el-table-column>
        <el-table-column label="参与人数" width="70" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.e }}</span>
          </template>
        </el-table-column>
        <el-table-column label="完成人数" width="70" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.f }}</span>
          </template>
        </el-table-column>
        <el-table-column label="课程数" width="60" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.g }}</span>
          </template>
        </el-table-column>
        <el-table-column label="试卷数" width="60" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.h }}</span>
          </template>
        </el-table-column>
        <el-table-column label="调研数" width="60" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.i }}</span>
          </template>
        </el-table-column>
        <el-table-column label="作业数" width="60" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.j }}</span>
          </template>
        </el-table-column>
        <el-table-column label="完成进度" width="70" align="center">
          <template slot-scope="scope">
            <span >{{ scope.row.k }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="200" >
          <template slot-scope="scope">
            <el-button size="mini" plain @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="mini" plain @click="handleDel(scope.row)">发布</el-button>
            <el-button size="mini" plain @click="showDetail(scope.row)">统计</el-button>
            <!-- <el-button size="mini" plain @click="handleDetail(scope.row)">查看详情</el-button> -->
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
import buttonList from '@/components/buttonList'
import Pagination from '@/components/Pagination'
export default {
  name: 'ControlCenter',
  components: { Pagination, buttonList },
  data() {
    return {
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
.controlCenter {
  padding: 20px;
  min-height: calc(100vh - 110px);
  background: #f8f9fd;
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: white;
    padding: 10px;
    .filter-container {
      .input-box {
        width: 100%;
        display: flex;
        .tb-box {
          flex: 1;
        }
      }
    }
    .table {
      margin-top: 20px;
      border: 1px solid #ebeef5;
    }
  }
}
</style>
<style lang="scss">
.controlCenter {
  .table {
    .cell {
      padding: 0;
    }
  }
}
</style>

