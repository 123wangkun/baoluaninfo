<template>
  <div class="courseList2">
    <div class="card">
      <div class="sonCourseDiv">
        <div class="sonCourse clearfix">
          <a v-for="item in resultList.son" :key="item.listId" @click="gotoList3(item)">{{ item.listName }}</a>
        </div>
      </div>
      <courseList :courselist="resultList.coursefor8"/>
    </div>
  </div>
</template>

<script>
import courseList from '@/components/courselist.vue'
import { getcourselistfortype2 } from '@/api/learn'
export default {
  components: {
    courseList
  },

  data() {
    return {
      resultList: [],
      img_path: '',
      breadcrumb: []
    }
  },
  // watch: {
  //   $route(to, from) {
  //     // 对路由变化作出响应...
  //     this.getcourselistfortype2()
  //     this.getcourselistbySonID()
  //   }
  // },
  created() {
    this.tempRoute = Object.assign({}, this.$route)
    this.setTagsViewTitle()
  },
  mounted() {
    this.getcourselistfortype2()
    this.getcourselistbySonID()
  },

  methods: {
    setTagsViewTitle() {
      const title = this.$route.query.listName
      const route = Object.assign({}, this.tempRoute, {
        title: title
      })
      this.$store.dispatch('updateVisitedView', route)
    },
    getcourselistfortype2() {
      getcourselistfortype2({
        courselistid: this.$route.query.listId
      })
        .then(response => {
          console.log(response)
          this.resultList = response.resultList
        })
        .catch(error => {
          this.listLoading = false
          console.log(error)
        })
    },
    gotoList3(item) {
      this.$router.push({
        path: '/learn/courseList3',
        query: {
          listId: item.listId,
          listName: item.listName
        }
      })
    }
  }
}
</script>

<style lang="scss">
@import '../../assets/css/common.scss';

.courseList2 {
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
</style>

