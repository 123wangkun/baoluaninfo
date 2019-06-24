<template>
  <div>
    <script :id="id" type="text/plain"/>
  </div>
</template>
<script>
export default {
  name: 'UE',
  props: {
    defaultMsg: {
      type: String,
      default: null
    },
    config: {
      type: Object,
      default: null
    },
    id: {
      type: String,
      default: null
    }
  },
  data() {
    return {
      editor: null
    }
  },
  mounted() {
    const _this = this
    this.editor = window.UE.getEditor(this.id, this.config) // 初始化UE
    this.editor.addListener('ready', function() {
      _this.editor.setContent(_this.defaultMsg) // 确保UE加载完成后，放入内容。
    })
    console.log('上传这堆错误不用理会，上传接口需自行开发配置')
  },
  destroyed() {
    this.editor.destroy()
  },
  methods: {
    // 设置内容
    setUEContent() {
      return this.editor.setContent('')
    },
    // 获取内容
    getUEContent() {
      return this.editor.getContent()
    },
    // 获取纯文本内容
    getUEContentTxt() {
      return this.editor.getContentTxt()
    }
  }
}
</script>
