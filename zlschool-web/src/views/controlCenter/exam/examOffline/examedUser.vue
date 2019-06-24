<template>
  <!-- 已考用户列表 -->
  <div class="examed-user pd-20">
    <el-input
      v-model="realName"
      placeholder="请输入用户名称"
      class="mr-15"/>
    <el-select v-model="stateSelect" placeholder="请选择" class="state mr-15">
      <el-option
        v-for="item in stateOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-upload
      :file-list="fileList"
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
        label="考试名称"/>
      <el-table-column
        prop="realName"
        label="用户名称"/>
      <el-table-column
        prop="departName"
        label="所属部门"/>
      <el-table-column
        prop="totalTime"
        label="考试时长"/>
      <el-table-column
        prop="stateName"
        label="考试状态"/>
      <el-table-column
        prop="studyTime"
        label="考试时间"
        min-width="100"/>
      <el-table-column
        prop="score"
        label="得分"/>
      <el-table-column
        prop="memo"
        label="备注"/>
    </el-table>
  </div>
</template>

<script>
import { uploadFile, delFile } from '@/api/common'
export default {
  name: 'ExamedUser',
  data() {
    return {
      tableList: [{
        id: '1',
        name: '课程名称',
        realName: '王小虎',
        departName: '开发',
        totalTime: '120分钟',
        stateName: '已学习',
        studyTime: '2019-01-01',
        score: 80,
        memo: '备注内容'
      }],
      stateSelect: 3,
      stateOptions: [{
        value: '',
        label: '全部'
      }, {
        value: 1,
        label: '未进行'
      }, {
        value: 2,
        label: '已完成'
      }, {
        value: 3,
        label: '未考试'
      }],
      realName: '',
      fileList: []
    }
  },
  created() {},
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
        this.$message.info('图片大小不可超过2M')
        return false
      }
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
    }
  }
}
</script>
