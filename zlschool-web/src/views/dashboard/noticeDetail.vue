<template>
  <div class="noticeDetail">
    <div class="title">{{ data.TITLE }}</div>
    <div class="sndBox">
      <div>创建时间:{{ data.CREATEDATE |timeFilter }}</div>
      <div/>
    </div>
    <div class="content" v-html="data.CONTENT"/>
  </div>
</template>

<script>
export default {
  name: 'NoticeDetail',
  data() {
    return {
      data: JSON.parse(localStorage.getItem('noticeDetail'))
    }
  },
  created() {
    this.tempRoute = Object.assign({}, this.$route)
    this.setTagsViewTitle()
  },
  methods: {
    setTagsViewTitle() {
      const title = this.data.type === '1' ? '最新公告详情' : '培训活动详情'
      const route = Object.assign({}, this.tempRoute, {
        title: title
      })
      this.$store.dispatch('updateVisitedView', route)
    }
  }
}
</script>

<style lang="scss" scoped>
.noticeDetail {
  padding: 20px 0;
  width: 960px;
  margin: 0 auto;
  background: white;
  min-height: calc(100vh - 110px);
  .title {
    font-size: 20px;
    font-weight: bold;
    text-align: center;
    margin-bottom: 20px;
  }
  .sndBox {
    div {
      width: 100%;
      text-align: center;
    }
  }
  .content {
    margin: 0;
    p {
      margin: 0;
      padding: 0;
      margin-block-start: 0;
      margin-block-end: 0;
    }
  }
}
</style>

