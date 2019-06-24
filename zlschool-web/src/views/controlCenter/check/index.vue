<template>
  <!-- 考核管理 -->
  <div class="check pd-20">
    <el-input
      v-model="name"
      placeholder="请输入考核名称"
      class="mr-15"/>
    <el-date-picker
      v-model="startTime"
      :editable="false"
      type="date"
      placeholder="请选择开始时间"/>
    <el-date-picker
      v-model="endTime"
      :editable="false"
      type="date"
      placeholder="请选择结束时间"/>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-button type="primary" plain class="btn" @click="add">新增</el-button>
    <el-button type="primary" plain class="btn" @click="detail">考核详情</el-button>
    <el-table
      ref="singleTable"
      :data="tableList"
      border
      stripe
      highlight-current-row
      tooltip-effect="dark"
      class="table mt-15"
      @current-change="handleSelectCheck">
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="name"
        label="考核名称"/>
      <el-table-column
        prop="content"
        label="考核内容"/>
      <el-table-column
        prop="checkTime"
        label="考核时间"
        min-width="100"/>
      <el-table-column
        prop="memo"
        label="备注"/>
      <el-table-column label="操作" min-width="220" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改弹窗 -->
    <el-dialog
      :visible.sync="addDialog"
      :title="operate === 0 ? '新增考核' : '修改考核信息'"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="name" class="dept">
          <div class="name">
            <i>*</i>
            <span>考核名称：</span>
          </div>
          <el-input
            v-model="addMsg.name"
            placeholder="请输入考核名称"/>
        </el-form-item>
        <el-form-item prop="checkTime" class="dept">
          <div class="name">
            <i>*</i>
            <span>考核时间：</span>
          </div>
          <el-date-picker
            v-model="addMsg.checkTime"
            :editable="false"
            type="date"
            placeholder="请选择考核时间"/>
        </el-form-item>
        <el-form-item prop="content" class="dept">
          <div class="name">
            <i/>
            <span>考核内容：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addMsg.content"
            type="textarea"
            placeholder="请输入考核内容"/>
        </el-form-item>
        <el-form-item prop="memo" class="dept">
          <div class="name">
            <i/>
            <span>备注：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addMsg.memo"
            type="textarea"
            placeholder="请输入备注内容"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="addSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 删除弹窗 -->
    <el-dialog
      :visible.sync="delDialog"
      title="您确定删除吗？"
      width="30%"
      @close="closeDialog">
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="delSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 考核详情弹窗 -->
    <el-dialog
      :visible.sync="detailDialog"
      title="考核详情"
      width="70%"
      class="activity-dialog"
      @close="closeDialog">
      <detail ref="detail"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="closeDialog">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import detail from './detail'
export default {
  name: 'Check',
  components: { detail },
  data() {
    return {
      tableList: [{
        id: '1',
        name: '考核',
        content: '内容',
        checkTime: '2019-01-01',
        memo: '备注'
      }],
      addMsg: {},
      addMsgRules: {
        name: [
          { required: true, message: '请输入考核名称', trigger: 'blur' }
        ],
        checkTime: [
          { required: true, message: '请选择考核时间', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '请输入考核内容', trigger: 'blur' }
        ]
      },
      name: '',
      addDialog: false,
      delDialog: false,
      detailDialog: false,
      selectIndex: 0,
      selectCheck: null
    }
  },
  created() {},
  methods: {
    // 搜索
    search() { },
    // 新增
    add() {
      this.addMsg = {}
    },
    // 修改
    edit(index, row) {
      this.selectIndex = index
      this.addMsg = {
        name: row.name,
        content: row.content,
        checkTime: row.checkTime,
        memo: row.memo
      }
      this.addDialog = true
    },
    // 点击修改弹窗确定
    addSure() {
      this.$refs.addMsg.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            this.tableList.push(this.addMsg)
          } else if (this.operate === 1) {
            this.tableList.splice(this.selectIndex, 1, this.addMsg)
            this.addDialog = false
          }
        }
      })
    },
    // 删除
    del(index) {
      this.selectIndex = index
      this.delDialog = true
    },
    // 点击删除弹窗确定
    delSure() {
      this.tableList.splice(this.selectIndex, 1)
      this.delDialog = false
    },
    // 关闭弹窗
    closeDialog() {
      this.addDialog = false
      this.delDialog = false
      this.detailDialog = false
    },
    // 选择课程
    handleSelectCheck(val) {
      console.log(val)
      this.selectCheck = val
    },
    // 点击考核详情
    detail() {
      if (this.selectCheck) {
        this.detailDialog = true
        setTimeout(() => {
          this.$refs.detail.search(this.selectCheck)
        }, 100)
      } else {
        this.$message.info('请选择考核列表')
      }
    }
  }
}
</script>
<style lang="scss">
  // .check {
  //   .name {
  //     .el-select,.el-input {
  //       width: 183px;
  //     }
  //   }
  //   .edit-dialog {
  //     .el-dialog {
  //       margin-bottom: 12vh;
  //     }
  //   }
  //   .activity-dialog {
  //     .el-dialog__body {
  //       margin-left: 0%;
  //     }
  //   }
  // }
</style>
