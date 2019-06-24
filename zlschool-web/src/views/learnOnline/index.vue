<template>
  <div class="learnOnline">
    <div class="card">
      <div class="title">企业文库</div>
      <div class="filter-container">
        <buttonList :list="typeList" v-model="type" title="类型:"/>
        <buttonList :list="stateList" v-model="state" title="状态:"/>
        <!-- <div class="input-box">
          <el-input
            v-model="title"
            placeholder="主题"
            style="width: 200px;"
            class="filter-item"
            size="small"
            @keyup.enter.native="handleFilter"
          />
        </div> -->
      </div>
      <div v-for="item in resultList.son" :key="item.listId" class="courseList1">
        <!-- <div class="courseTitle" @click="gotoList2(item)">{{ item.listName }}</div>
        <div class="sonCourseDiv">
          <div class="sonCourse clearfix">
            <a v-for="list3 in item.son" :key="list3.listId" @click="gotoList3(list3,item)">{{ list3.listName }}</a>
          </div>
        </div> -->
        <courseList :courselist="item.coursefor8"/>
      </div>
    </div>
  </div>

</template>

<script>
import { getCoursetype, getcourselistfortype1 } from '@/api/learn'
import courseList from '@/components/courselist.vue'
import buttonList from '@/components/buttonList'
export default {
  name: 'LearnOnline',
  components: {
    courseList,
    buttonList
  },
  data() {
    return {
      listLoading: false,
      typeList: [],
      type: '',
      stateList: [
        {
          name: '全部',
          value: ''
        },
        {
          name: '未学习',
          value: '1'
        },
        {
          name: '已学习',
          value: '2'
        }
      ],
      state: '',
      selectIndex1: 0,
      resultList: [],
      title: ''
    }
  },
  computed: {},
  watch: {
    type: function(val) {
      this.getcourselistPage()
    }
  },
  mounted() {
    this.getCoursetype()
  },
  methods: {
    typeClick(index) {
      // 分类点击
      this.selectIndex1 = index
      this.getcourselistPage()
    },
    getCoursetype() {
      getCoursetype()
        .then(response => {
          console.log(response)
          this.type = response.listresult[0].listId
          this.typeList = response.listresult.map(element => {
            return {
              name: element.listName,
              value: element.listId
            }
          })
          this.getcourselistPage()
        })
        .catch(error => {
          this.listLoading = false
          console.log(error)
        })
    },
    getcourselistPage() {
      getcourselistfortype1({
        courselistid: this.type
      })
        .then(response => {
          this.resultList = response.resultList
          console.log(response)
        })
        .catch(error => {
          this.listLoading = false
          console.log(error)
        })
    },
    gotoList2(item) {
      this.$router.push({
        path: '/learn/courseList2',
        query: {
          listId: item.listId,
          listName: item.listName
        }
      })
    },
    gotoList3(item, parent) {
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

<style lang="scss" scoped>
.learnOnline {
  width: 100%;
  padding: 20px;
  min-height: calc(100vh - 110px);
  .card {
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #ebeef5;
    background: white;
    padding: 20px 10px;
    .title {
      margin: 0 10px;
      font-size: 20px;
      border-bottom: 1px solid #e4e7ed;
      padding-bottom: 15px;
    }
    .filter-container {
      padding: 20px 10px 0 10px;
      .input-box {
        width: 100%;
        display: flex;
        justify-content: flex-end;
      }
    }
    .courseList1 {
      margin-bottom: 24px;
      .courseTitle {
        padding-bottom: 6px;
        font-size: 14px;
        font-weight: bold;
        color: #4d4d4d;
        margin-left: 10px;
        cursor: pointer;
        border-bottom: 1px solid #999999;
      }
      .sonCourseDiv {
        width: 100%;
        overflow: hidden;
        margin-left: 10px;
        .sonCourse {
          margin-left: -6px;
          margin-top: 6px;
          margin-bottom: 28px;
          width: 100%;
          a {
            float: left;
            margin-right: 7px;
            padding-left: 7px;
            // margin-top: 7px;
            border-left: 1px solid #999999;
            font-size: 11px;
          }
        }
      }
    }
  }
}
</style>

