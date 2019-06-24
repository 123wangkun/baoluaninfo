<template>
  <div class="courseList3">
    <div class="card">
      <courseList :courselist="resultList"/>
      <div v-if="resultList.length>0" class="paginationDiv">
        <el-pagination :total="total" :page-size="pagesize" background layout="prev, pager, next" @current-change="currentChange"/>
      </div>
    </div>
  </div>
</template>

<script>
import courseList from '@/components/courselist.vue'
import { getcourselistPage } from '@/api/learn'
export default {
  components: {
    courseList
  },
  data() {
    return {
      resultList: [],
      breadcrumb: [],
      total: 0,
      pagesize: 12,
      currentpage: 1
    }
  },
  // watch: {
  //   $route(to, from) {
  //     // 对路由变化作出响应...
  //     this.getcourselistPage()
  //     this.getcourselistbySonID()
  //   }
  // },
  created() {
    this.tempRoute = Object.assign({}, this.$route)
    this.setTagsViewTitle()
  },
  mounted() {
    this.getcourselistPage()
  },

  methods: {
    setTagsViewTitle() {
      const title = this.$route.query.listName
      const route = Object.assign({}, this.tempRoute, {
        title: title
      })
      this.$store.dispatch('updateVisitedView', route)
    },
    getcourselistPage() {
      getcourselistPage({
        courselistid: this.$route.query.listId,
        page: this.currentpage,
        limit: this.pagesize
      })
        .then(response => {
          this.resultList = response.resultList
          this.total = response.totalResult
        })
        .catch(error => {
          this.listLoading = false
          console.log(error)
        })
    },
    currentChange(currentPage) {
      this.currentpage = currentPage
      this.getcourselistPage()
    }
  }
}
</script>

<style lang="scss">
@import '../../assets/css/common.scss';
.courseList3 {
  width: 100%;
  padding: 20px;

  min-height: calc(100vh - 110px);
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: white;
    padding: 20px 10px;
    .sonCourseDiv {
      width: 100%;
      overflow: hidden;
      margin-left: 10px;
      .sonCourse {
        margin-left: -6px;
        margin-bottom: 28px;
        width: 100%;
        a {
          float: left;
          margin-right: 7px;
          padding-left: 7px;
          margin-top: 7px;
          border-left: 1px solid #999999;
          font-size: 11px;
        }
      }
    }
  }
}

.nodata {
  width: 100%;
  height: 300px;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

