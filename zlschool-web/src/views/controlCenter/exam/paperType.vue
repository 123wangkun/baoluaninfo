<template>
  <!-- 试卷类型 -->
  <div class="paper-type pd-20">
    <el-button type="primary" plain class="btn" @click="add">新增</el-button>
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
        prop="name"
        label="类型名称"/>
      <el-table-column
        prop="position"
        label="显示顺序"/>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column
        prop="memo"
        label="备注"/>
      <el-table-column label="操作" min-width="140" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改证书类型弹窗 -->
    <el-dialog
      :visible.sync="addDialog"
      :title="operate == 0 ? '新增试卷类型' : '修改试卷类型'"
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
            <span>类型名称：</span>
          </div>
          <el-input
            v-model="addMsg.name"
            placeholder="请输入类型名称"/>
        </el-form-item>
        <el-form-item prop="position" class="dept">
          <div class="name">
            <i>*</i>
            <span>显示顺序：</span>
          </div>
          <el-input
            v-model.number="addMsg.position"
            placeholder="请输入显示顺序"/>
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
            placeholder="请输入备注"/>
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
  name: 'PaperType',
  data() {
    const validatePosition = (rule, value, callback) => {
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
        name: '学历',
        position: 1,
        createdTime: '2019-01-01',
        memo: ''
      }],
      addMsg: {},
      addMsgRules: {
        name: [
          { required: true, message: '请输入类型名称', trigger: 'blur' }
        ],
        position: [
          { validator: validatePosition, trigger: 'blur' }
        ]
      },
      addDialog: false,
      delDialog: false,
      selectIndex: 0,
      operate: 0
    }
  },
  methods: {
    // 新增证书类型
    add() {
      this.operate = 0
      this.addMsg = {}
      this.addDialog = true
    },
    // 修改证书类型
    edit(index, row) {
      this.operate = 1
      this.selectIndex = index
      this.addMsg = {
        name: row.name,
        position: row.position,
        memo: row.memo
      }
      this.addDialog = true
    },
    // 点击修改证书类型弹窗确定
    addSure() {
      this.$refs.addMsg.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            this.tableList.push(this.addMsg)
          } else {
            this.tableList.splice(this.selectIndex, 1, this.addMsg)
          }
          this.addDialog = false
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
    }
  }
}
</script>
