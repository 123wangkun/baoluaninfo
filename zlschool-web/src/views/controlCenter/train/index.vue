<template>
  <!-- 培训管理 -->
  <div class="train-manage pd-20">
    <el-input
      v-model="courseName"
      placeholder="请输入课程名称"
      class="mr-15"/>
    <span>状态</span>
    <el-select v-model="stateSelect" placeholder="请选择" class="state mr-15">
      <el-option
        v-for="item in stateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <el-button type="primary" class="btn" @click="search">搜索</el-button>
    <el-button type="primary" class="btn" @click="add">新增</el-button>
    <el-button type="primary" class="btn" @click="taskDetail">任务详情</el-button>
    <el-button type="primary" class="btn" @click="trainFile">培训资料</el-button>
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
        label="培训名称"/>
      <el-table-column
        prop="startTime"
        label="开始时间"
        min-width="100"/>
      <el-table-column
        prop="endTime"
        label="结束时间"
        min-width="100"/>
      <el-table-column
        prop="integral"
        label="培训所得积分"/>
      <el-table-column
        prop="stateName"
        label="状态"/>
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
            <span>培训名称：</span>
          </div>
          <el-input
            v-model="addMsg.name"
            placeholder="请输入培训名称"/>
        </el-form-item>
        <el-form-item prop="startTime" class="dept">
          <div class="name">
            <i>*</i>
            <span>开始时间：</span>
          </div>
          <el-date-picker
            v-model="addMsg.startTime"
            :editable="false"
            type="date"
            placeholder="请选择开始时间"/>
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
        <el-form-item prop="integral" class="dept">
          <div class="name">
            <i>*</i>
            <span>培训所得积分：</span>
          </div>
          <el-input
            v-model="addMsg.integral"
            placeholder="请输入培训所得积分"/>
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
        <el-form-item prop="label" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>课程标签：</span>
          </div>
          <el-input
            v-model="addMsg.label"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.label == ''" type="primary" plain class="btn" @click="selectLabel">选择</el-button>
          <el-button v-else type="primary" plain class="btn" @click="selectLabel">更改</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="addSure">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 任务详情弹窗 -->
    <el-dialog
      :visible.sync="taskDialog"
      title="任务详情"
      width="70%"
      @close="closeDialog">
      <task-detail ref="taskDetail"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="closeDialog">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 培训资料弹窗 -->
    <el-dialog
      :visible.sync="fileDialog"
      title="培训资料"
      width="70%"
      @close="closeDialog">
      <train-file ref="trainFile"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="closeDialog">确 定</el-button>
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
    <!-- 标签选择弹窗 -->
    <el-dialog
      :visible.sync="labelDialog"
      title="标签选择"
      width="30%"
      @close="closeLabelDialog">
      <el-tree v-if="labelList.length > 0" ref="tree" :data="labelList" :props="defaultProps" show-checkbox node-key="id" @check-change="handleCheckChange" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeLabelDialog">取 消</el-button>
        <el-button type="primary" @click="labelSure">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import TaskDetail from './taskDetail'
import TrainFile from './trainFile'
export default {
  name: 'TrainManage',
  components: { TaskDetail, TrainFile },
  data() {
    const validateOrder = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('请输入显示顺序'))
      } else if (!Number.isInteger(value)) {
        callback(new Error('顺序必须是数字'))
      } else {
        callback()
      }
    }
    return {
      tableList: [{
        name: '培训',
        startTime: '2019-01-01',
        endTime: '2019-01-01',
        integral: 100,
        stateName: '未进行',
        createdTime: '2019-01-01',
        remark: '备注'
      }],
      stateSelect: '',
      stateOptions: [{
        value: '',
        label: '全部'
      }, {
        value: 0,
        label: '未发布'
      }, {
        value: 1,
        label: '已发布'
      }],
      addMsg: {},
      addMsgRules: {
        name: [
          { required: true, message: '请输入培训名称', trigger: 'blur' }
        ],
        startTime: [
          { required: true, message: '请选择开始时间', trigger: 'blur' }
        ],
        endTime: [
          { required: true, message: '请选择结束时间', trigger: 'blur' }
        ],
        integral: [
          { required: true, message: '请输入培训所得积分', trigger: 'blur' }
        ],
        position: [
          { validator: validateOrder, trigger: 'blur' }
        ],
        label: [
          { required: true, message: '请选择标签', trigger: 'blur' }
        ]
      },
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      labelList: [{
        id: 1,
        name: '1-江苏高速养护公司',
        children: [{
          id: 4,
          name: '1-1-总经理',
          children: [{
            id: 7,
            name: '1-1-1-部门领导',
            children: [{
              id: 10,
              name: '1-1-1-1-员工1'
            }, {
              id: 11,
              name: '1-1-1-2-员工2'
            }]
          }]
        }]
      }, {
        id: 2,
        name: '2-上海高速养护公司',
        children: [{
          id: 5,
          name: '2-1-总经理',
          children: [{
            id: 8,
            name: '2-1-1-部门领导'
          }]
        }]
      }, {
        id: 3,
        name: '3-北京高速养护公司',
        children: [{
          id: 6,
          name: '3-1-总经理',
          children: [{
            id: 9,
            name: '3-1-1-部门领导'
          }]
        }]
      }],
      addDialog: false,
      delDialog: false,
      labelDialog: false,
      taskDialog: false,
      fileDialog: false,
      selectIndex: 0,
      operate: 0, // 0新增，1修改
      tagSelect: null,
      courseName: ''
    }
  },
  methods: {
    // 搜索
    search() {
      console.log(this.courseName)
    },
    // 新增
    add() {
      this.operate = 0
      this.addMsg = {}
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
        name: row.name,
        startTime: row.startTime,
        endTime: row.endTime,
        integral: row.integral,
        position: row.position,
        label: row.label
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
      this.taskDialog = false
      this.fileDialog = false
    },
    // 选择标签
    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate)
    },
    selectLabel() {
      this.labelDialog = true
    },
    // 关闭选择标签弹窗
    closeLabelDialog() {
      this.labelDialog = false
    },
    // 点击选择标签弹窗确定
    labelSure() {
      this.labelDialog = false
    },
    // 点击任务详情
    taskDetail() {
      this.taskDialog = true
    },
    // 培训资料
    trainFile() {
      this.fileDialog = true
    }
  }
}
</script>
<style lang="scss">
  .train-manage {
    .edit-dialog {
      .el-dialog {
        width: 390px;
      }
    }
    .el-dialog__body {
      margin-left: 0%;
      .dept {
        .name {
          width: 111px;
        }
      }
      .el-form-item__error {
        left: 116px;
      }
    }
  }
</style>

