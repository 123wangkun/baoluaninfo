<template>
  <div class="ListTitle">
    <div class="title">{{ title }}</div>
    <div v-if="hasMore" class="more" @click="openMore">查看更多>></div>
  </div>
</template>

<script>
export default {
  name: 'ListTitle',
  props: {
    title: {
      type: String,
      required: true
    },
    hasMore: {
      type: Boolean,
      default: true
    }
  },
  methods: {
    openMore() {
      if (this.title === '最新公告') {
        this.$router.push({
          path: '/noticeList',
          query: {
            title: '最新公告',
            type: 1
          }
        })
      } else if (this.title === '培训活动') {
        this.$router.push({
          path: '/noticeList',
          query: {
            title: '培训活动',
            type: 2
          }
        })
      }
    }
  }
}
</script>
<style lang="scss" scoped>
.ListTitle {
  width: 100%;
  display: flex;
  flex-direction: row;
  padding: 10px 0px 10px 0px;
  border-top-right-radius: 8px;
  border-top-left-radius: 8px;
  color: #222;
  border-bottom: 1px solid #eee;
  .title {
    flex: 1;
  }
  .more {
    color: #888;
    font-size: 14px;
    cursor: pointer;
  }
}
</style>
