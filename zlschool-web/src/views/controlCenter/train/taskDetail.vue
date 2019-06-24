<template>
  <!-- 任务详情 -->
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
        prop="taskName"
        label="任务名称"/>
      <el-table-column
        prop="typeName"
        label="任务类型"/>
      <el-table-column
        prop="position"
        label="任务顺序"/>
      <el-table-column
        prop="endTime"
        label="结束时间"
        min-width="100"/>
      <el-table-column
        prop="stateName"
        label="状态"/>
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
      :title="operate === 0 ? '新增任务' : '修改任务'"
      :append-to-body="true"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="taskName" class="dept">
          <div class="name">
            <i>*</i>
            <span>任务名称：</span>
          </div>
          <el-input
            v-model="addMsg.taskName"
            placeholder="请输入任务名称"/>
        </el-form-item>
        <el-form-item prop="typeSelect" class="dept">
          <div class="name">
            <i>*</i>
            <span>任务类型：</span>
          </div>
          <el-select v-model="addMsg.typeSelect" placeholder="请选择">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="task" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>选择任务：</span>
          </div>
          <el-input
            v-model="addMsg.task"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.task == ''" type="primary" plain class="btn" @click="selectTask">选择</el-button>
          <el-button v-else type="primary" plain class="btn" @click="selectTask">更改</el-button>
        </el-form-item>
        <el-form-item prop="endTime" class="dept">
          <div class="name">
            <i>*</i>
            <span>结束时间：</span>
          </div>
          <el-date-picker
            v-model="addMsg.endTime"
            :editable="false"
            type="date"
            placeholder="请选择结束时间"/>
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
        <el-form-item prop="stateName" class="dept">
          <div class="name">
            <i>*</i>
            <span>状态：</span>
          </div>
          <el-radio v-model="addMsg.state" :label="0">启用</el-radio>
          <el-radio v-model="addMsg.state" :label="1">禁用</el-radio>
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
      :append-to-body="true"
      title="您确定删除吗？"
      width="30%"
      @close="closeDialog">
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="delSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 选择任务弹窗 -->
    <el-dialog
      :visible.sync="taskDialog"
      :title="taskTitle"
      :append-to-body="true"
      width="70%"
      @close="closeTaskDialog">
      <!-- 线上课程选择 -->
      <course-online v-if="typeSelect == 1" ref="courseOnline"/>
      <!-- 线下课程选择 -->
      <course-offline v-else-if="typeSelect == 2" ref="courseOffline"/>
      <!-- 线下考试选择 -->
      <exam-offline v-else-if="typeSelect == 3" ref="examOffline"/>
      <!-- 线上考试选择 -->
      <exam-online v-else ref="examOnline"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeTaskDialog">取 消</el-button>
        <el-button type="primary" @click="chooseTaskSure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import courseOnline from './courseOnline'
import courseOffline from './courseOffline'
import examOnline from './examOnline'
import examOffline from './examOffline'
export default {
  name: 'TaskDetail',
  components: { courseOnline, courseOffline, examOnline, examOffline },
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
        taskName: '任务',
        typeName: '线上考试',
        position: 1,
        endTime: '2019-01-01',
        stateName: '启用'
      }],
      typeSelect: 1,
      typeOptions: [{
        value: '',
        label: '全部'
      }, {
        value: 1,
        label: '线上课程'
      }, {
        value: 2,
        label: '线下课程'
      }, {
        value: 3,
        label: '线下考试'
      }, {
        value: 4,
        label: '线上考试'
      }],
      addMsg: {},
      addMsgRules: {
        taskName: [
          { required: true, message: '请输入任务名称', trigger: 'blur' }
        ],
        typeName: [
          { required: true, message: '请选择任务类型', trigger: 'blur' }
        ],
        task: [
          { required: true, message: '请选择任务', trigger: 'blur' }
        ],
        endTime: [
          { required: true, message: '请选择结束时间', trigger: 'blur' }
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
      operate: 0, // 0新增，1修改
      taskTitle: '选择线上课程'
    }
  },
  methods: {
    // 新增
    add() {
      this.operate = 0
      this.addMsg = {}
      this.addMsg.state = 1
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
        taskName: row.taskName,
        typeName: row.typeName,
        task: row.task,
        endTime: row.endTime,
        position: row.position,
        state: row.state,
        memo: row.memo
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
    },
    // 选择任务
    selectTask() {
      console.log(this.addMsg.typeSelect)
      if (this.addMsg.typeSelect === 1) {
        this.taskTitle = '选择线上课程'
        this.taskDialog = true
        setTimeout(() => {
          // this.$refs.learnedUser.search(this.selectCourse)
        }, 100)
        return
      } else if (this.addMsg.typeSelect === 2) {
        this.taskTitle = '选择线下课程'
        this.taskDialog = true
        return
      } else if (this.addMsg.typeSelect === 3) {
        this.taskTitle = '选择线下考试'
        this.taskDialog = true
        return
      } else if (this.addMsg.typeSelect === 4) {
        this.taskTitle = '选择线上考试'
        this.taskDialog = true
        return
      }
    },
    // 关闭选择任务弹窗
    closeTaskDialog() {
      this.taskDialog = false
    },
    // 点击选择任务弹窗确定
    chooseTaskSure() {
      this.taskDialog = true
    }
  }
}
</script>

