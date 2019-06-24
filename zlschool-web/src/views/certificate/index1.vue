<template>
  <div class="certificate">
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
            >新增</el-button>
          </div>
          <el-input
            v-model="listQuery.key"
            placeholder="证书名称"
            style="width: 200px;margin-right:20px;"
            class="filter-item"
            size="small"
            @keyup.enter.native="handleFilter"
          />  <el-date-picker
            v-model="value5"
            type="datetimerange"
            range-separator="至"
            start-placeholder="创建开始日期"
            end-placeholder="创建结束日期"
            size="small"
            align="right"/>
        </div>
      </div>
      <el-table :data="list" border style="width: 100%" class="table">
        <el-table-column label="编号" width="65" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.index }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建日期" width="180" align="center">
          <template slot-scope="scope">
            <i class="el-icon-time"/>
            <span style="margin-left: 5px">{{ scope.row.create_time|timeFilter }}</span>
          </template>
        </el-table-column>
        <el-table-column label="名称" min-width="150px">
          <template slot-scope="scope">
            <span class="link-type" @click="getFile(scope.row)">{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="有效期" width="200" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px"/>
          </template>
        </el-table-column>
        <el-table-column label="类型" width="100" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ typeFilter(scope.row.type) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" width="100" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ stateFilter(scope.row.state) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="积分" width="100" align="center">
          <template slot-scope="scope">
            <span style="margin-left: 10px">{{ scope.row.integral }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" type="success" icon="el-icon-edit" @click="handleEdit(scope.row)"/>
            <el-button size="mini" type="danger" icon="el-icon-delete" @click="handleDel(scope.row)"/>
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
    <el-dialog :visible.sync="dialogFormVisible" :title="textMap[dialogStatus]">
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="temp"
        label-position="left"
        label-width="70px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="类型" prop="type">
          <el-select v-model="temp.type" class="filter-item" placeholder="请选择">
            <el-option
              v-for="item in types"
              :key="item.ZD_ID"
              :label="item.NAME"
              :value="item.BIANMA"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="name">
          <el-input v-model="temp.name"/>
        </el-form-item>
        <el-form-item label="证书" prop="file">
          <el-button size="small" type="primary" @click="chooseFile">选择证书</el-button>
          <span v-if="temp.file.name">{{ temp.file.name }}</span>
          <input
            id="fileinput"
            ref="fileinput"
            type="file"
            style="display:none"
            @change="handleFileChange"
          >
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            :autosize="{ minRows: 2, maxRows: 4}"
            v-model="temp.remark"
            type="textarea"
            placeholder
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="submit()">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogDetailVisible" title="证书详情">
      <div class="detail-box">
        <div class="row">
          <div class="left">类型</div>
          <div class="right">{{ typeFilter(detail.type) }}</div>
        </div>
        <div class="row">
          <div class="left">名称</div>
          <div class="right link-type" @click="getFile(detail)">{{ detail.name }}</div>
        </div>
        <div class="row">
          <div class="left">审核状态</div>
          <div class="right">
            {{ stateFilter(detail.state) }}
            <el-button v-show="detail&&detail.state ==='-1'" size="small" type="primary" style="margin-left:30px" @click="chooseFile1">重选证书</el-button>
            <span v-if="temp.file.name">{{ temp.file.name }}</span>
            <input
              id="fileinput1"
              ref="fileinput1"
              type="file"
              style="display:none"
              @change="handleFileChange1"
            >
          </div>
        </div>
        <div class="row">
          <div class="left">创建时间</div>
          <div class="right">{{ detail.create_time|timeFilter }}</div>
        </div>
        <div class="row">
          <div class="left">获取积分</div>
          <div class="right">{{ detail.integral }}</div>
        </div>
        <div class="row">
          <div class="left">备注</div>
          <div class="right">{{ detail.remark }}</div>
        </div>
        <div class="row">
          <div class="left">审批记录</div>
          <div class="right"/>
        </div>
        <div class="recordList">
          <div v-for="item in detail.recordList" :key="item.id" class="item">
            <div>{{ item.Spread2 }} {{ item.status==='1'?'同意':'不同意' }} {{ item.processing_time|timeFilter }} </div>
            <div style="margin-top:5px">{{ item.content }} </div>
          </div>
        </div>
      </div>
    </el-dialog>

</div></template>

<script>
import Pagination from '@/components/Pagination'
import moment from 'moment'
import buttonList from '@/components/buttonList'
import {
  certificateList,
  typeList,
  addCertificate,
  eidtCertificate,
  delCertificate,
  certificateDetail
} from '@/api/certificate'
export default {
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
        key: ''
      },
      state: '',
      states: [
        {
          value: '',
          name: '全部'
        },
        {
          value: 0,
          name: '待审核'
        },
        {
          value: 1,
          name: '审核通过'
        },
        {
          value: -1,
          name: '未审核'
        }
      ],
      type: '',
      types: [],
      total: 0,
      list: [],
      dialogFormVisible: false,
      dialogDetailVisible: false,
      rules: {
        type: [{ required: true, message: '请选择类型', trigger: 'change' }],
        name: [{ required: true, message: '请输入标题', trigger: 'blur' }],
        file: [{ required: true, message: '请选择证书', trigger: 'blur' }]
      },
      dialogStatus: '',
      textMap: {
        update: '编辑证书',
        create: '添加证书'
      },
      temp: {
        id: undefined,
        name: '',
        type: '',
        file: '',
        remark: ''
      },
      detail: {
        recordList: []
      }
    }
  },
  watch: {
    type: function(val) {
      this.getList()
    },
    state: function(val) {
      this.getList()
    }
  },
  mounted() {
    this.getType()
    this.getList()
  },
  methods: {
    chooseFile() {
      this.$refs.fileinput.click()
    },
    // 重选证书
    chooseFile1() {
      this.$refs.fileinput1.click()
    },
    // 添加附件
    handleFileChange() {
      const inputDOM = this.$refs.fileinput
      if (inputDOM.files[0]) {
        console.log(inputDOM.files[0])
        this.temp.file = inputDOM.files[0]
      }
    },
    // 重选证书
    handleFileChange1() {
      this.resetTemp()
      const inputDOM = this.$refs.fileinput1
      if (inputDOM.files[0]) {
        console.log(inputDOM.files[0])
        this.temp.file = inputDOM.files[0]
      }
      this.temp.id = this.detail.id
      eidtCertificate(this.temp)
        .then(response => {
          // 刷新列表
          this.showDetail(this.detail)
          this.dialogFormVisible = false
          this.$message({
            message: '编辑成功',
            type: 'success'
          })
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 附件下载
    getFile(item) {
      console.log(process.env.BASE_API + item.src)
      window.open(process.env.BASE_API + item.src)
    },
    handleDel(row) {
      let msg = ''
      if (row.state === '1') {
        msg = '删除审核通过的证书将扣除对应积分，确定删除此证书？'
      } else {
        msg = '确定删除此证书？'
      }
      this.$confirm(msg, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(() => {
          this.deleteRow(row)
        })
        .catch(() => {})
    },
    handleEdit(row) {
      if (row.state === '1') {
        this.$message({
          message: '审核通过的证书不能修改！',
          type: 'warning'
        })
        return
      }
      this.temp = {
        id: row.id,
        name: row.name,
        type: row.type,
        remark: row.remark,
        file: {
          name: row.src
        }
      }
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    getList() {
      certificateList(
        Object.assign({}, this.listQuery, {
          type: this.type,
          state: this.state
        })
      )
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
    },
    getType() {
      typeList()
        .then(response => {
          const temp = response.data.map(item => {
            return {
              name: item.NAME,
              value: item.BIANMA
            }
          })
          this.types = [
            {
              name: '全部',
              value: ''
            }
          ].concat(temp)
        })
        .catch(error => {
          console.log(error)
        })
    },
    typeFilter(type) {
      if (this.types.length > 0 && type) {
        return this.types.filter(item => item.value === type)[0].name
      } else {
        return ''
      }
    },
    stateFilter(type) {
      if (type) {
        return this.states.filter(item => item.value === Number(type))[0].name
      } else {
        return ''
      }
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        name: '',
        type: '',
        file: ''
      }
    },
    submit() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          if (this.dialogStatus === 'create') {
            this.add()
          } else {
            this.edit()
          }
        }
      })
    },
    // 添加
    add() {
      addCertificate(this.temp)
        .then(response => {
          // 刷新列表
          this.handleFilter()
          this.dialogFormVisible = false
          this.$message({
            message: '添加成功',
            type: 'success'
          })
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 编辑
    edit() {
      eidtCertificate(this.temp)
        .then(response => {
          // 刷新列表
          this.handleFilter()
          this.dialogFormVisible = false
          this.$message({
            message: '编辑成功',
            type: 'success'
          })
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 删除
    deleteRow(row) {
      delCertificate(row)
        .then(response => {
          // 刷新列表
          this.handleFilter()
          this.dialogFormVisible = false
          this.$message({
            message: '删除成功',
            type: 'success'
          })
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 展示详情
    showDetail(row) {
      certificateDetail(row)
        .then(response => {
          this.detail = response.data
          console.log(response)
          this.dialogDetailVisible = true
          // this.$message({
          //   message: '删除成功',
          //   type: 'success'
          // })
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.certificate {
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
        .tb-box {
          flex: 1;
        }
      }
    }
    .table {
      margin-top: 20px;
    }
    .detail-box {
      padding: 0 50px;
      max-height: 400px;
      overflow-y: auto;
      .row {
        display: flex;
        flex-direction: row;

        .left {
          width: 70px;
          font-size: 14px;
          color: #606266;
          line-height: 40px;
          margin: 0 20px 0 0;
          -webkit-box-sizing: border-box;
          box-sizing: border-box;
          font-weight: bold;
        }
        .right {
          flex: 1;
          line-height: 40px;
        }
      }
      .recordList {
        .item {
          border-bottom: 1px solid #666;
          padding: 8px 0;
        }
      }
    }
  }
}
</style>

