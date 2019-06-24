<template>
  <!-- 培训资料 -->
  <div class="task-detail">
    <el-button type="primary" class="btn mr-15" @click="add">新增</el-button>
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
        prop="trainName"
        label="培训名称"/>
      <el-table-column
        prop="fileName"
        label="资料名称"/>
      <el-table-column
        prop="fileSrc"
        label="文件路径"/>
      <el-table-column
        prop="position"
        label="任务顺序"/>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column label="操作" min-width="140" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改弹窗 -->
    <el-dialog
      :visible.sync="addDialog"
      :title="operate === 0 ? '新增培训' : '修改培训'"
      :append-to-body="true"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="fileName" class="dept">
          <div class="name">
            <i>*</i>
            <span>资料名称：</span>
          </div>
          <el-input
            v-model="addMsg.fileName"
            placeholder="请输入资料名称"/>
        </el-form-item>
        <el-form-item prop="file" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>文件选择：</span>
          </div>
          <el-input
            v-model="addMsg.file"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.file == ''" type="primary" plain class="btn" @click="selectLabel">选择</el-button>
          <el-button v-else type="primary" plain class="btn">更改</el-button>
        </el-form-item>
        <el-form-item prop="position" class="dept">
          <div class="name">
            <i>*</i>
            <span>任务顺序：</span>
          </div>
          <el-input
            v-model="addMsg.position"
            placeholder="请输入任务顺序"/>
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
      :append-to-body="true"
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
  name: 'TrainFile',
  data() {
    const validateOrder = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入任务顺序'))
      } else if (!Number.isInteger(value)) {
        callback(new Error('顺序必须是数字'))
      } else {
        callback()
      }
    }
    return {
      tableList: [{
        trainName: '培训',
        fileName: '任务',
        fileSrc: '线上考试',
        position: 1,
        createdTime: '2019-01-01'
      }],
      addMsg: {},
      addMsgRules: {
        fileName: [
          { required: true, message: '请输入资料名称', trigger: 'blur' }
        ],
        file: [
          { required: true, message: '请选择文件', trigger: 'blur' }
        ],
        position: [
          { validator: validateOrder, trigger: 'blur' }
        ]
      },
      addDialog: false,
      delDialog: false,
      taskDialog: false,
      fileDialog: false,
      selectIndex: 0,
      operate: 0 // 0新增，1修改
    }
  },
  methods: {
    // 新增
    add() {
      this.operate = 0
      this.addMsg = {}
      this.addMsg.file = ''
      this.addDialog = true
    },
    // 点击新增/修改弹窗确定
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
        fileName: row.fileName,
        file: row.file,
        position: row.position
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

