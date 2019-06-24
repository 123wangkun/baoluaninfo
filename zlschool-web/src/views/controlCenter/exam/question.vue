<template>
  <!-- 题库维护 -->
  <div class="question pd-20">
    <el-input
      v-model="name"
      placeholder="请输入题目名称"
      class="mr-15"/>
    <span>题型</span>
    <el-select v-model="typeSelect" placeholder="请选择题型" class="state mr-15">
      <el-option
        v-for="item in typeOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <el-button type="primary" class="btn" @click="search">搜索</el-button>
    <el-button type="primary" class="btn" @click="add">新增</el-button>
    <el-upload
      :file-list="questionList"
      :before-upload="beforeUpload"
      :http-request="importExcel"
      :on-remove="handleRemove"
      :multiple="true"
      :show-file-list="false"
      action="customize"
      accept=".xls,.xlsx"
      list-type="picture"
      class="import-btn">
      <el-button type="primary" icon="el-icon-upload" class="del-btn"/>
    </el-upload>
    <el-button type="primary" icon="el-icon-download" class="del-btn" @click="exportExcel"/>
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
        label="题目内容"/>
      <el-table-column
        prop="typeName"
        label="题型"/>
      <el-table-column
        prop="score"
        label="分值"/>
      <el-table-column
        prop="answer"
        label="正确答案"/>
      <el-table-column
        prop="createTime"
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
        <el-form-item prop="remark" class="dept">
          <div class="name">
            <i>*</i>
            <span>题干内容：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addMsg.remark"
            type="textarea"
            placeholder="请输入题干内容"/>
        </el-form-item>
        <el-form-item prop="image" class="dept web-pic">
          <div class="name">
            <i>*</i>
            <span>题干图片：</span>
          </div>
          <el-input
            v-model="addMsg.image"
            disabled
            class="pic-input"/>
          <el-upload
            :file-list="addMsg.image"
            :before-upload="beforeUpload"
            :http-request="upload"
            :show-file-list="false"
            action="customize"
            accept=".jpg,.jpeg,.png"
            class="upload-btn"
            style="margin-left:0px;">
            <el-button v-if="addMsg.image == ''" type="primary" plain class="btn">选择</el-button>
            <el-button v-else type="primary" plain class="btn" @click="handleRemove">更改</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="type" class="dept">
          <div class="name">
            <i>*</i>
            <span>题型：</span>
          </div>
          <el-select v-model="addMsg.type" placeholder="请选择">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item prop="optionsId" class="dept">
          <div class="name">
            <i/>
            <span>A：</span>
          </div>
          <el-input
            v-model="addMsg.optionsId"
            placeholder=""/>
        </el-form-item>
        <el-form-item prop="optionsId" class="dept">
          <div class="name">
            <i/>
            <span>B：</span>
          </div>
          <el-input
            v-model="addMsg.optionsId"
            placeholder=""/>
        </el-form-item>
        <el-form-item prop="optionsId" class="dept">
          <div class="name">
            <i/>
            <span>C：</span>
          </div>
          <el-input
            v-model="addMsg.optionsId"
            placeholder=""/>
        </el-form-item>
        <el-form-item prop="optionsId" class="dept">
          <div class="name">
            <i/>
            <span>D：</span>
          </div>
          <el-input
            v-model="addMsg.optionsId"
            placeholder=""/>
        </el-form-item>
        <el-form-item prop="optionsId" class="dept">
          <div class="name">
            <i/>
            <span>E：</span>
          </div>
          <el-input
            v-model="addMsg.optionsId"
            placeholder=""/>
        </el-form-item>
        <el-form-item prop="optionsId" class="dept">
          <div class="name">
            <i/>
            <span>F：</span>
          </div>
          <el-input
            v-model="addMsg.optionsId"
            placeholder=""/>
        </el-form-item>
        <el-form-item prop="answer" class="dept">
          <div class="name">
            <i>*</i>
            <span>正确答案：</span>
          </div>
          <el-input
            v-model.number="addMsg.answer"
            placeholder="请输入正确答案"/>
        </el-form-item>
        <el-form-item prop="score" class="dept">
          <div class="name">
            <i>*</i>
            <span>分值：</span>
          </div>
          <el-input
            v-model.number="addMsg.score"
            placeholder="请输入分值"/>
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
        <el-form-item prop="memo" class="dept">
          <div class="name">
            <i/>
            <span>备注信息：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addMsg.memo"
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
export default {
  name: 'Question',
  data() {
    return {
      tableList: [{
        name: '题目',
        typeName: '单选',
        score: 100,
        answer: 'A',
        createTime: '2018-01-10',
        memo: '备注'
      }],
      typeSelect: 1,
      typeOptions: [{
        value: '全部',
        label: '全部'
      }, {
        value: 1,
        label: '单选题'
      }, {
        value: 2,
        label: '多选题'
      }, {
        value: 3,
        label: '判断题'
      }],
      addMsg: {},
      addMsgRules: {
        name: [
          { required: true, message: '请输入题干内容', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择题型', trigger: 'blur' }
        ],
        answer: [
          { required: true, message: '请输入正确答案', trigger: 'blur' }
        ],
        score: [
          { required: true, message: '请输入分值', trigger: 'blur' }
        ],
        label: [
          { required: true, message: '请选择试题标签', trigger: 'blur' }
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
      questionList: [],
      delDialog: false,
      labelDialog: false,
      selectIndex: 0,
      operate: 0 // 0新增，1修改
    }
  },
  methods: {
    // 搜索
    search() {},
    // 导入
    importExcel(param) {
      const fileObj = new FormData()
      fileObj.append('editormd-image-file', param.file)
      uploadFile(fileObj, 8).then(res => {
        this.$message.success('导入成功')
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 导出
    exportExcel() {
      window.location.href = 'https://res.dev.deecard.net/img33b807200113426b8333751444dcfe477.xlsx'
    },
    // 上传文件前限制大小
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size > 2) {
        this.$message.info('大小不可超过2M')
        return false
      }
    },
    // 上传题干图片
    upload(param) {
      const fileObj = new FormData()
      fileObj.append('editormd-image-file', param.file)
      uploadFile(fileObj, 5).then(res => {
        const file = { url: res.url, size: res.fileSize, type: res.type }
        this.addMsg.image = []
        this.addMsg.image.push(file)
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 删除图片
    handleRemove() {
      const params = {
        fileUrl: this.addMsg.image[0].url
      }
      delFile(params).then(res => {
        this.addMsg.image[0].url = ''
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
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
    }
  }
}
</script>
