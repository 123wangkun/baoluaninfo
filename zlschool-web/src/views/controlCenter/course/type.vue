<template>
  <!-- 课程类型 -->
  <div class="course-type pd-20">
    <el-button type="primary" class="btn" @click="add">新增</el-button>
    <el-table
      ref="multipleTable"
      :data="tableList"
      border
      stripe
      tooltip-effect="dark"
      class="table mt-15">
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="typeName"
        label="类型名称"/>
      <el-table-column
        prop="createTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column
        prop="remark"
        label="备注"/>
      <el-table-column label="操作" min-width="140" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增弹窗 -->
    <el-dialog
      :visible.sync="addDialog"
      :title="operate === 0 ? '新增类型' : '修改类型'"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="typeName" class="dept">
          <div class="name">
            <i>*</i>
            <span>类型名称：</span>
          </div>
          <el-input
            v-model="addMsg.typeName"
            placeholder="请输入类型名称"/>
        </el-form-item>
        <el-form-item prop="order" class="dept">
          <div class="name">
            <i>*</i>
            <span>显示顺序：</span>
          </div>
          <el-input
            v-model.number="addMsg.order"
            placeholder="请输入顺序"/>
        </el-form-item>
        <el-form-item prop="remark" class="dept">
          <div class="name">
            <i/>
            <span>备注信息：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addMsg.remark"
            type="textarea"
            placeholder="请输入备注信息"/>
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
  </div>
</template>

<script>
export default {
  name: 'CourseType',
  data() {
    const validateOrder = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入顺序'))
      } else if (!Number.isInteger(value)) {
        callback(new Error('顺序必须是数字'))
      } else {
        callback()
      }
    }
    return {
      tableList: [{
        order: 1,
        typeName: '超级管理员',
        createTime: '2018-01-10',
        remark: '备注'
      }, {
        order: 2,
        typeName: '管理员',
        createTime: '2018-01-10',
        remark: '备注'
      }],
      addMsg: {},
      addMsgRules: {
        typeName: [
          { required: true, message: '请输入类型名称', trigger: 'blur' }
        ],
        order: [
          { validator: validateOrder, trigger: 'blur' }
        ]
      },
      addDialog: false,
      delDialog: false,
      selectIndex: 0,
      operate: 0 // 0新增，1修改
    }
  },
  methods: {
    // 新增
    add() {
      this.operate = 0
      this.addMsg = {}
      this.addDialog = true
    },
    // 点击新增弹窗确定
    addSure() {
      this.$refs.addMsg.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            this.tableList.push(this.addMsg)
          } else if (this.operate === 1) {
            this.tableList.splice(this.selectIndex, 1, this.addMsg)
          }
          this.addDialog = false
        }
      })
    },
    // 修改
    edit(index, row) {
      this.selectIndex = index
      this.addMsg = {
        typeName: row.typeName,
        order: row.order,
        remark: row.remark
      }
      this.operate = 1
      this.addDialog = true
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
    }
  }
}
</script>
<style lang="scss">
  .course-type {
    .table {
      width: 80%;
    }
    .menu-part {
      .menu {
        display: inline-block;
        min-width: 200px;
        vertical-align: top;
        .el-input,.el-textarea {
          width: 100%;
        }
      }
      .menu:last-child {
        width: 200px;
      }
    }
  }
</style>
