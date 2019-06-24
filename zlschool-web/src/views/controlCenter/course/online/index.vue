<template>
  <!-- 线上课程 -->
  <div class="online-course pd-20">
    <el-input
      v-model="courseName"
      placeholder="请输入课程名称"
      class="mr-15"/>
    <el-select v-model="typeSelect" placeholder="请选择" class="name mr-15">
      <el-option
        v-for="item in typeOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <span>状态</span>
    <el-select v-model="stateSelect" placeholder="请选择" class="state mr-15">
      <el-option
        v-for="item in stateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-button type="primary" plain class="btn" @click="add">新增</el-button>
    <el-button type="primary" plain class="btn" @click="activityList">课程活动列表</el-button>
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
        label="课程名称"/>
      <el-table-column
        prop="typeName"
        label="课程类型"/>
      <el-table-column
        prop="state"
        label="课程状态"/>
      <el-table-column
        prop="likeNumber"
        label="总点赞数"/>
      <el-table-column
        prop="collectionNumber"
        label="总收藏数"/>
      <el-table-column
        prop="studyNumber"
        label="学习人数"/>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column label="操作" min-width="220" >
        <template slot-scope="scope">
          <el-button size="mini" type="primary" icon="el-icon-edit" @click="edit(scope.$index, scope.row)"/>
          <el-button size="mini" type="danger" icon="el-icon-delete" @click="del(scope.$index)"/>
          <el-button size="mini" type="primary" @click="check(scope.$index)">审核</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增/修改弹窗 -->
    <el-dialog
      :visible.sync="editDialog"
      :title="operate === 0 ? '新增课程' : '修改课程'"
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
            <span>课程名称：</span>
          </div>
          <el-input
            v-model="addMsg.name"
            placeholder="请输入课程名称"/>
        </el-form-item>
        <el-form-item prop="typeName" class="dept">
          <div class="name">
            <i>*</i>
            <span>课程类型：</span>
          </div>
          <el-select v-model="addMsg.typeName" placeholder="请选择">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="state" class="dept">
          <div class="name">
            <i>*</i>
            <span>课程状态：</span>
          </div>
          <el-select v-model="addMsg.state" placeholder="请选择">
            <el-option
              v-for="item in stateOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="logoSrc" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>PC图片：</span>
          </div>
          <el-input
            v-model="logoSrcName"
            disabled
            class="pic-input"/>
          <!-- <Upload :file-list.sync="logoList" :file-name.sync="addMsg.logoSrcName" :file-type.sync="logoFileType" :accept-type="'.jpg,.jpeg,.png'"/> -->
          <el-upload
            :file-list="addMsg.logoSrc"
            :before-upload="beforeUpload"
            :http-request="upload"
            :show-file-list="false"
            action="customize"
            accept=".jpg,.jpeg,.png"
            class="upload-btn">
            <el-button v-if="logoSrcName == ''" type="primary" plain class="btn">选择</el-button>
            <el-button v-else type="primary" plain class="btn" @click="handleRemove">更改</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="mobileSrc" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>手机图片：</span>
          </div>
          <el-input
            v-model="mobileSrcName"
            disabled
            class="pic-input"/>
          <el-upload
            :file-list="addMsg.mobileSrc"
            :before-upload="beforeUpload"
            :http-request="upload"
            :show-file-list="false"
            action="customize"
            accept=".jpg,.jpeg,.png"
            class="upload-btn">
            <el-button v-if="mobileSrcName == ''" type="primary" plain class="btn">选择</el-button>
            <el-button v-else type="primary" plain class="btn" @click="handleRemove">更改</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="file" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>课程文件：</span>
          </div>
          <el-input
            v-model="fileName"
            disabled
            class="pic-input"/>
          <el-upload
            :file-list="addMsg.file"
            :before-upload="beforeUpload"
            :http-request="upload"
            :show-file-list="false"
            action="customize"
            accept=".xlsx,.xls,.ppt,.pptx,.doc,.docx,.mp4,.avi,.flv,.mov,.wmv,.mpeg"
            class="upload-btn">
            <el-button v-if="addMsg.file == []" type="primary" plain class="btn">选择</el-button>
            <el-button v-else type="primary" plain class="btn" @click="handleRemove">更改</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="totalTime" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>课程时长：</span>
          </div>
          <el-input
            v-model="addMsg.totalTime"
            placeholder="请输入课程时长"
            style="margin-right: 10px;"/>分钟
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
          <el-button v-else type="primary" plain class="btn">更改</el-button>
        </el-form-item>
        <el-form-item prop="descript" class="dept">
          <div class="name">
            <i>*</i>
            <span>课程简介：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addMsg.descript"
            type="textarea"
            placeholder="请输入课程简介"/>
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
    <!-- 审核弹窗 -->
    <el-dialog
      :visible.sync="checkDialog"
      title="审核信息"
      class="edit-dialog"
      @close="closeDialog">
      <el-form
        ref="checkMsg"
        :model="checkMsg"
        label-position="left">
        <el-form-item prop="state" class="dept">
          <div class="name">
            <i>*</i>
            <span>审核状态：</span>
          </div>
          <el-select v-model="checkMsg.state" placeholder="请选择">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="proContent" class="dept">
          <div class="name">
            <i/>
            <span>审核内容：</span>
          </div>
          <el-input
            :rows="2"
            v-model="checkMsg.proContent"
            type="textarea"
            placeholder="请输入审核内容"/>
        </el-form-item>
        <el-form-item prop="proMemo" class="dept">
          <div class="name">
            <i/>
            <span>审核备注：</span>
          </div>
          <el-input
            :rows="2"
            v-model="checkMsg.proMemo"
            type="textarea"
            placeholder="请输入审核备注"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="checkSure">确 定</el-button>
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
    <!-- 课程活动列表弹窗 -->
    <el-dialog
      :visible.sync="activityDialog"
      title="课程活动列表"
      width="70%"
      class="activity-dialog"
      @close="closeDialog">
      <course-activity ref="activity"/>
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
import CourseActivity from './courseActivity'
export default {
  name: 'OnlineCourse',
  components: { CourseActivity },
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
        id: '1',
        name: '课程名字1',
        typeName: '类型',
        state: '已审核',
        likeNumber: 100,
        collectionNumber: 100,
        studyNumber: 100,
        createdTime: '2019-01-01'
      }, {
        id: '2',
        name: '课程名字2',
        typeName: '类型',
        state: '已审核',
        likeNumber: 100,
        collectionNumber: 100,
        studyNumber: 100,
        createdTime: '2019-01-01'
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
        value: 2,
        label: '进行中'
      }, {
        value: 3,
        label: '已结束'
      }, {
        value: 4,
        label: '未启用'
      }],
      addMsg: {},
      checkMsg: {},
      addMsgRules: {
        name: [
          { required: true, message: '请输入课程名字', trigger: 'blur' }
        ],
        logoSrc: [
          { required: true, message: '请上传PC图片', trigger: 'blur' }
        ],
        mobileSrc: [
          { required: true, message: '请上传手机图片', trigger: 'blur' }
        ],
        file: [
          { required: true, message: '请上传课程文件', trigger: 'blur' }
        ],
        totalTime: [
          { required: true, message: '请输入课程时长', trigger: 'blur' }
        ],
        position: [
          { validator: validateOrder, trigger: 'blur' }
        ],
        label: [
          { required: true, message: '请选择课程标签', trigger: 'blur' }
        ],
        descript: [
          { required: true, message: '请输入课程简介', trigger: 'blur' }
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
      courseName: '',
      editDialog: false,
      checkDialog: false,
      delDialog: false,
      activityDialog: false,
      labelDialog: false,
      selectIndex: 0,
      operate: 0,
      logoList: [],
      logoFileType: 9,
      selectCourse: null,
      tagSelect: null,
      logoSrcName: '',
      mobileSrcName: '',
      fileName: ''
    }
  },
  created() {},
  methods: {
    // 搜索
    search() { },
    // 新增
    add() {
      this.logoSrcName = ''
      this.mobileSrcName = ''
      this.fileName = ''
      this.addMsg = {}
      this.addMsg.logoSrc = []
      this.addMsg.mobileSrc = []
      this.addMsg.file = []
      this.addMsg.label = ''
      this.editDialog = true
    },
    // 修改
    edit(index, row) {
      this.selectIndex = index
      this.addMsg = {
        name: row.name,
        typeName: row.typeName,
        state: row.state,
        logoSrc: row.logoSrc,
        mobileSrc: row.mobileSrc,
        file: row.file,
        totalTime: row.totalTime,
        position: row.position,
        label: row.label,
        descript: row.descript,
        memo: row.memo
      }
      this.editDialog = true
    },
    // 点击修改弹窗确定
    addSure() {
      this.$refs.addMsg.validate(valid => {
        if (valid) {
          if (this.operate === 0) {
            this.tableList.push(this.addMsg)
          } else if (this.operate === 1) {
            this.tableList.splice(this.selectIndex, 1, this.addMsg)
            this.editDialog = false
          }
        }
      })
    },
    // 审核信息
    check(index, row) {
      this.selectIndex = index
      this.checkMsg = {
        state: row.state,
        proContent: row.proContent,
        proMemo: row.proMemo
      }
      this.checkDialog = true
    },
    // 点击审核信息弹窗确定
    checkSure() {
      this.tableList.splice(this.selectIndex, 1, this.checkMsg)
      this.checkDialog = false
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
      this.editDialog = false
      this.checkDialog = false
      this.delDialog = false
      this.activityDialog = false
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
      this.selectCourse = val
    },
    // 点击课程活动列表
    activityList() {
      if (this.selectCourse) {
        this.activityDialog = true
        setTimeout(() => {
          this.$refs.activity.search(this.selectCourse)
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
    }
  }
}
</script>
<style lang="scss">
  .online-course {
    .name {
      .el-select,.el-input {
        width: 183px;
      }
    }
    .edit-dialog {
      .el-dialog {
        margin-bottom: 12vh;
      }
    }
    .activity-dialog {
      .el-dialog__body {
        margin-left: 0%;
      }
    }
  }
</style>
