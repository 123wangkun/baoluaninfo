<template>
  <!-- 线上考试 -->
  <div class="exam-online pd-20">
    <el-input
      v-model="name"
      placeholder="请输入试卷名称"
      class="mr-15"/>
    <span>试卷类型</span>
    <el-select v-model="typeSelect" placeholder="请选择" class="state mr-15">
      <el-option
        v-for="item in typeOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <span>发布状态</span>
    <el-select v-model="stateSelect" placeholder="请选择" class="state mr-15">
      <el-option
        v-for="item in stateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-button type="primary" plain class="btn" @click="add">新增</el-button>
    <el-button type="primary" plain class="btn" @click="examedUserList">已考用户列表</el-button>
    <el-table
      ref="singleTable"
      :data="tableList"
      border
      stripe
      highlight-current-row
      tooltip-effect="dark"
      class="table mt-15"
      @current-change="handleSelectCourse">
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="name"
        label="试卷名称"/>
      <el-table-column
        prop="typeName"
        label="试卷类型"/>
      <el-table-column
        prop="totalScore"
        label="试卷总分"/>
      <el-table-column
        prop="single"
        label="单选题数"/>
      <el-table-column
        prop="multiple"
        label="多选题数"/>
      <el-table-column
        prop="judge"
        label="判断题数"/>
      <el-table-column
        prop="releaseStatus"
        label="发布状态"/>
      <el-table-column label="操作" min-width="220" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
          <el-button size="mini" type="primary" @click="view(scope.$index)">预览</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改弹窗 -->
    <el-dialog
      :visible.sync="addDialog"
      :title="operate === 0 ? '新增试卷' : '修改试卷'"
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
            <span>试卷名称：</span>
          </div>
          <el-input
            v-model="addMsg.name"
            placeholder="请输入试卷名称"/>
        </el-form-item>
        <el-form-item prop="typeName" class="dept">
          <div class="name">
            <i>*</i>
            <span>试卷类型：</span>
          </div>
          <el-select v-model="addMsg.typeName" placeholder="请选择">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="releaseStatus" class="dept">
          <div class="name">
            <i>*</i>
            <span>试卷状态：</span>
          </div>
          <el-radio v-model="addMsg.releaseStatus" :label="1">发布</el-radio>
          <el-radio v-model="addMsg.releaseStatus" :label="2">未发布</el-radio>
        </el-form-item>
        <el-form-item prop="totalScore" class="dept">
          <div class="name">
            <i>*</i>
            <span>试卷总分：</span>
          </div>
          <el-input
            v-model="addMsg.totalScore"
            placeholder="请输入试卷总分"/>
        </el-form-item>
        <el-form-item prop="passLine" class="dept">
          <div class="name">
            <i>*</i>
            <span>及格线：</span>
          </div>
          <el-input
            v-model="addMsg.passLine"
            placeholder="请输入及格线"/>
        </el-form-item>
        <el-form-item prop="spendTime" class="dept">
          <div class="name">
            <i>*</i>
            <span>考试时长：</span>
          </div>
          <el-input
            v-model="addMsg.spendTime"
            placeholder="请输入考试时长"/>
        </el-form-item>
        <el-form-item prop="integral" class="dept">
          <div class="name">
            <i>*</i>
            <span>获取积分：</span>
          </div>
          <el-input
            v-model="addMsg.integral"
            placeholder="请输入获取积分"/>
        </el-form-item>
        <el-form-item prop="label" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>试卷标签：</span>
          </div>
          <el-input
            v-model="addMsg.label"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.label == ''" type="primary" plain class="btn" @click="selectLabel">选择</el-button>
          <el-button v-else type="primary" plain class="btn" @click="selectLabel">更改</el-button>
        </el-form-item>
        <el-form-item prop="single" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>单选题数：</span>
          </div>
          <el-input
            v-model="addMsg.single"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.single == ''" type="primary" plain class="btn" @click="selectQuestion">选择</el-button>
          <el-button v-else type="primary" plain class="btn" @click="selectQuestion">更改</el-button>
        </el-form-item>
        <el-form-item prop="multiple" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>多选题数：</span>
          </div>
          <el-input
            v-model="addMsg.multiple"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.multiple == ''" type="primary" plain class="btn" @click="selectQuestion">选择</el-button>
          <el-button v-else type="primary" plain class="btn" @click="selectQuestion">更改</el-button>
        </el-form-item>
        <el-form-item prop="judge" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>判断题数：</span>
          </div>
          <el-input
            v-model="addMsg.judge"
            disabled
            class="pic-input"/>
          <el-button v-if="addMsg.judge == ''" type="primary" plain class="btn" @click="selectQuestion">选择</el-button>
          <el-button v-else type="primary" plain class="btn" @click="selectQuestion">更改</el-button>
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
    <!-- 已考用户列表弹窗 -->
    <el-dialog
      :visible.sync="examedUserDialog"
      title="已考用户列表"
      width="70%"
      class="activity-dialog"
      @close="closeDialog">
      <examed-user ref="examedUser"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="closeDialog">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 选择题目弹窗 -->
    <el-dialog
      :visible.sync="questionDialog"
      title="选择题目"
      width="70%"
      class="activity-dialog"
      @close="closeDialog">
      <question ref="question"/>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="closeDialog">确 定</el-button>
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
import { uploadFile, delFile } from '@/api/common'
// import Upload from '@/components/upload/upload'
import examedUser from './examedUser'
import question from './question'
export default {
  name: 'ExamOnline',
  components: { examedUser, question },
  data() {
    return {
      tableList: [{
        id: '1',
        name: '课程名字1',
        typeName: '类型',
        totalScore: 100,
        single: 100,
        multiple: 100,
        judge: 100,
        releaseStatus: '发布'
      }],
      typeSelect: '全部',
      typeOptions: [{
        value: '全部',
        label: '全部'
      }, {
        value: '类型1',
        label: '类型1'
      }, {
        value: '类型2',
        label: '类型2'
      }],
      stateSelect: 2,
      stateOptions: [{
        value: '',
        label: '全部'
      }, {
        value: 1,
        label: '已发布'
      }, {
        value: 2,
        label: '未发布'
      }],
      addMsg: {},
      checkMsg: {},
      addMsgRules: {
        name: [
          { required: true, message: '请输入试卷名称', trigger: 'blur' }
        ],
        totalScore: [
          { required: true, message: '请输入试卷总分', trigger: 'blur' }
        ],
        passLine: [
          { required: true, message: '请输入及格线', trigger: 'blur' }
        ],
        totalTime: [
          { required: true, message: '请输入考试时长', trigger: 'blur' }
        ],
        integral: [
          { required: true, message: '请输入获取积分', trigger: 'blur' }
        ],
        label: [
          { required: true, message: '请选择试卷标签', trigger: 'blur' }
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
      name: '',
      addDialog: false,
      delDialog: false,
      examedUserDialog: false,
      questionDialog: false,
      labelDialog: false,
      selectIndex: 0,
      operate: 0,
      selectPaper: null
    }
  },
  created() {},
  methods: {
    // 搜索
    search() { },
    // 新增
    add() {
      this.addMsg = {}
      this.addMsg.label = ''
      this.addMsg.single = ''
      this.addMsg.multiple = ''
      this.addMsg.judge = ''
      this.addDialog = true
    },
    // 修改
    edit(index, row) {
      this.selectIndex = index
      this.addMsg = {
        name: row.name,
        typeName: row.typeName,
        releaseStatus: row.releaseStatus,
        totalScore: row.totalScore,
        passLine: row.passLine,
        spendTime: row.spendTime,
        integral: row.integral,
        label: row.label,
        single: row.single,
        multiple: row.multiple,
        judge: row.judge,
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
    // 点击已考用户列表
    examedUserList() {
      if (this.selectPaper) {
        this.examedUserDialog = true
        setTimeout(() => {
          this.$refs.examedUser.search(this.selectPaper)
        }, 100)
      } else {
        this.$message.info('请选择试卷')
      }
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
    // 预览
    view() {},
    // 关闭弹窗
    closeDialog() {
      this.addDialog = false
      this.delDialog = false
      this.examedUserDialog = false
      this.questionDialog = false
    },
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size > 2) {
        this.$message.info('图片大小不可超过2M')
        return false
      }
    },
    // 上传图片
    upload(param) {
      this.picList = []
      this.addMsg.picName = param.file.name
      this.fileObj = new FormData()
      this.fileObj.append('editormd-image-file', param.file)
      this.file = param.file
      uploadFile(this.fileObj, 6).then(res => {
        const file = { url: res.url, size: res.fileSize, type: res.type }
        this.picList.push(file)
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 删除图片
    handleRemove() {
      const params = {
        fileUrl: this.picList[0].url
      }
      delFile(params).then(res => {
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 选择课程
    handleSelectCourse(val) {
      console.log(val)
      this.selectPaper = val
    },
    // 点击课程活动列表
    activityList() {
      if (this.selectPaper) {
        this.examedUserDialog = true
        setTimeout(() => {
          this.$refs.activity.search(this.selectPaper)
        }, 100)
      } else {
        this.$message.info('请选择课程')
      }
    },
    // 选择标签
    handleCheckChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate)
    },
    selectLabel() {
      this.labelDialog = true
    },
    closeLabelDialog() {
      this.labelDialog = false
    },
    labelSure() {
      this.labelDialog = false
    },
    // 选择题目
    selectQuestion() {
      this.questionDialog = true
      setTimeout(() => {
        // this.$refs.question.search(this.selectPaper)
      }, 100)
    }
  }
}
</script>

<style lang="scss">
  .exam-online {
    .edit-dialog {
      .el-dialog {
        margin-bottom: 12vh;
      }
    }
  }
</style>
