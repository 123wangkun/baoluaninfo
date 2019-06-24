<template>
  <div class="upload-component">
    <el-input
      v-model="fileName"
      disabled/>
    <el-upload
      :file-list="fileList"
      :before-upload="beforeUpload"
      :http-request="upload"
      :show-file-list="false"
      :accept="acceptType"
      action="customize"
      class="upload-btn">
      <el-button v-if="fileName == ''" type="primary" plain class="btn">选择</el-button>
      <el-button v-else type="primary" plain class="btn" @click="handleRemove">更改</el-button>
    </el-upload>
  </div>
</template>

<script>
import { uploadFile, delFile } from '@/api/common'
export default {
  name: 'Upload',
  props: {
    fileList: {
      type: Array,
      default: []
    },
    fileType: {
      type: Number,
      default: null
    },
    fileName: {
      type: String,
      default: ''
    },
    acceptType: {
      type: String,
      default: ''
    }
  },
  data() {},
  methods: {
    beforeUpload(file) {
      const size = file.size / 1024 / 1024
      if (size > 2) {
        this.$message.info('图片大小不可超过2M')
        return false
      }
    },
    // 上传文件
    upload(param) {
      this.fileList = []
      this.fileName = param.file.name
      const fileObj = new FormData()
      fileObj.append('editormd-image-file', param.file)
      uploadFile(fileObj, this.fileType).then(res => {
        const file = { url: res.url, size: res.fileSize, type: res.type }
        this.fileList.push(file)
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    },
    // 删除文件
    handleRemove() {
      const params = {
        fileUrl: this.fileList[0].url
      }
      delFile(params).then(res => {
      }).catch(error => {
        this.$message.error(error.err_msg)
      })
    }
  }
}
</script>
<style lang="scss">
  .upload-component {
    display: inline-block;
    .el-input {
      width: 171px;
    }
    .upload-btn {
      display: inline-block;
    }
    .btn {
      padding:7px 10px;
    }
  }
</style>
