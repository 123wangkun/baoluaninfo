<template>
  <div class="noticeListMore">
    <NoticeMoreList :list="list"/>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="messageList"
    />
  </div>
</template>

<script>
import { messageList } from '@/api/index'
import NoticeMoreList from './components/NoticeMoreList'
import Pagination from '@/components/Pagination'
export default {
  components: {
    NoticeMoreList,
    Pagination
  },
  data() {
    return {
      listQuery: {
        page: 1,
        limit: 10,
        key: ''
      },
      total: 0,
      list: []
    }
  },
  mounted() {
    this.tempRoute = Object.assign({}, this.$route)
    this.setTagsViewTitle()
    this.messageList()
  },
  methods: {
    setTagsViewTitle() {
      const title = this.$route.query.title
      const route = Object.assign({}, this.tempRoute, {
        title: title
      })
      this.$store.dispatch('updateVisitedView', route)
    },
    messageList() {
      messageList({
        page: this.listQuery.page,
        limit: this.listQuery.limit,
        type: this.$route.query.type
      })
        .then(response => {
          this.list = response.data
          this.total = response.page.totalResult
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.noticeListMore {
  width: 960px;
  margin: 0 auto;
  background: white;
  min-height: calc(100vh - 110px);
}
</style>
<style lang="scss">
.noticeListMore {
  .NoticeList {
    li {
      box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
    }
  }
}
</style>

