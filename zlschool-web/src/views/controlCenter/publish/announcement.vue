<template>
  <!-- 通知公告 -->
  <div class="announcement-wrap pd-20">
    <el-input
      v-model="title"
      placeholder="请输入标题名称"
      class="mr-15"/>
    <el-input
      v-model="typeName"
      placeholder="请输入类型名称"
      class="mr-15"/>
    <span>状态</span>
    <el-select v-model="stateSelect" placeholder="状态" class="state mr-15">
      <el-option
        v-for="item in stateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
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
        prop="title"
        label="标题"/>
      <el-table-column
        prop="typeName"
        label="类型名称"/>
      <el-table-column
        prop="state"
        label="状态"/>
      <el-table-column
        prop="createTime"
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
      :title="operate === 0 ? '新增通知公告' : '修改通知公告'"
      class="check-dialog"
      @close="closeDialog">
      <el-form
        ref="addMsg"
        :model="addMsg"
        :rules="addMsgRules"
        label-position="left">
        <el-form-item prop="title" class="dept">
          <div class="name">
            <i>*</i>
            <span>标题：</span>
          </div>
          <el-input
            v-model="addMsg.title"
            placeholder="请输入标题名称"/>
        </el-form-item>
        <el-form-item prop="picName" class="dept">
          <div class="name">
            <i>*</i>
            <span>图片：</span>
          </div>
          <el-input
            v-model="addMsg.picName"
            disabled
            class="pic-input"/>
          <el-upload
            :file-list="picList"
            :before-upload="beforeUpload"
            :http-request="upload"
            :show-file-list="false"
            action="customize"
            accept=".jpg,.jpeg,.png"
            class="upload-btn">
            <el-button v-if="addMsg.picName == ''" type="primary" plain class="btn">选择</el-button>
            <el-button v-else type="primary" plain class="btn" @click="handleRemove">更改</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item prop="typeName" class="dept">
          <div class="name">
            <i>*</i>
            <span>类型名称：</span>
          </div>
          <el-input
            v-model="addMsg.typeName"
            placeholder="请输入类型名称"/>
        </el-form-item>
        <el-form-item prop="state" class="dept">
          <div class="name">
            <i>*</i>
            <span>状态：</span>
          </div>
          <el-radio v-model="addMsg.state" label="启用">启用</el-radio>
          <el-radio v-model="addMsg.state" label="禁用">禁用</el-radio>
        </el-form-item>
        <el-form-item prop="describe" class="dept">
          <div class="name">
            <i/>
            <span>描述信息：</span>
          </div>
          <el-input
            :rows="2"
            v-model="addMsg.describe"
            type="textarea"
            placeholder="请输入描述信息"/>
        </el-form-item>
        <div class="editor-part">
          <el-form-item prop="remark" class="dept">
            <div class="name">
              <i>*</i>
              <span>内容：</span>
            </div>
          </el-form-item>
          <div class="editor">
            <UE ref="ue" :default-msg="defaultMsg" :config="config" :id="ue"/>
            <div v-if="tipShow" class="el-form-item__error">请输入内容</div>
          </div>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog">取 消</el-button>
        <el-button type="primary" @click="editSure">确 定</el-button>
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
import { uploadFile, delFile } from '@/api/common'
import UE from '@/components/ue/ue.vue'
export default {
  name: 'Announcement',
  components: { UE },
  data() {
    return {
      tableList: [{
        title: '文章',
        typeName: '通知公告',
        state: '禁用',
        createTime: '2019-01-01',
        remark: '证书备注'
      }],
      stateOptions: [{
        value: '全部',
        label: '全部'
      }, {
        value: '启用',
        label: '启用'
      }, {
        value: '禁用',
        label: '禁用'
      }],
      addMsg: {},
      addMsgRules: {
        title: [
          { required: true, message: '请输入标题名称', trigger: 'blur' }
        ],
        picName: [
          { required: true, message: '请选择图片', trigger: 'blur' }
        ],
        typeName: [
          { required: true, message: '请输入类型名称', trigger: 'blur' }
        ]
      },
      picList: [],
      file: null,
      fileObj: null,
      title: '',
      typeName: '',
      stateSelect: '全部',
      addDialog: false,
      delDialog: false,
      tipShow: false,
      operate: 0,
      selectIndex: 0,
      defaultMsg: '',
      config: {
        initialFrameWidth: null,
        initialFrameHeight: 200
      },
      ue: 'ue1' // 不同编辑器必须不同的id
    }
  },
  methods: {
    // 搜索
    search() { },
    // 新增
    add() {
      this.addMsg = {}
      this.addMsg.picName = ''
      this.addMsg.state = '启用'
      this.operate = 0
      this.addDialog = true
    },
    // 修改
    edit(index, row) {
      this.selectIndex = index
      this.operate = 1
      this.addMsg = {
        title: row.title,
        picName: row.picName,
        typeName: row.typeName,
        state: row.state,
        describe: row.describe,
        content: row.content
      }
      this.addDialog = true
    },
    // 点击修改弹窗确定
    editSure() {
      this.getUEContent()
      this.$refs.addMsg.validate(valid => {
        if (valid) {
          this.tipShow = false
          if (!this.tipShow) {
            if (this.operate === 0) {
              this.tableList.push(this.addMsg)
            } else {
              this.tableList.splice(this.selectIndex, 1, this.addMsg)
            }
            this.addDialog = false
            this.setUEContent()
          }
        } else {
          this.tipShow = true
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
    // 获取富文本
    getUEContent() {
      const content = this.$refs.ue.getUEContent() // 调用子组件方法
      this.addMsg.content = content
    },
    // 清空富文本内容
    setUEContent() {
      const content = this.$refs.ue.setUEContent()
      this.addMsg.content = content
    }
  }
}
</script>
<style lang="scss">
  .announcement-wrap {
    .check-dialog {
      .el-dialog {
        margin-bottom: 12vh;
        width: 696px;
        .pic-input {
          width: 171px;
        }
        .upload-btn {
          display: inline-block;
        }
        .el-icon-upload {
          width: auto;
          color: #fff;
        }
        .btn {
          padding: 7px 10px;
        }
        .editor-part {
          .dept {
            display: inline-block;
            vertical-align: top;
          }
          .editor {
            position: relative;
            display: inline-block;
            width: 536px;
            .el-form-item__error {
              left: 0;
            }
          }
        }
      }
    }
  }
</style>
