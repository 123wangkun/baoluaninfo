<template>
  <div class="courseDetail">
    <div id="content">
      <div class="title">{{ course.TITLE }}</div>
      <div class="cardBox clearfix">
        <div id="player" class="left"/>
        <div class="right">
          <!-- <div v-if="course.goldcoinsByRead!='0'" class="title">观看课程可获得</div>
          <div v-if="course.goldcoinsByRead!='0'" class="center margn-t12">
            <img src="../../assets/image/score.png" class="score">
            <span class="scoretext">{{ course.goldcoinsByRead }}金币</span>
          </div>
          <div class="title margn-t36">参加考试可获得</div>
          <div class="center margn-t12">
            <img src="../../assets/image/score.png" class="score">
            <span class="scoretext">{{ course.goldcoinsByPass }}金币</span>
          </div> -->
          <div class="center">
            <div class="dzbox margin-r20" @click="isdolike(course.isLike)">
              <img v-if="course.isLike==1" src="../../assets/image/dz_c.png" class="dz">
              <img v-else src="../../assets/image/dz.png" class="dz">
              <span class="dztext">点赞</span>
              <span class="dzcount">({{ course.LIKE_COUNT }})</span>
            </div>
            <div class="dzbox" @click="isdocollect(course.isCollect)">
              <img v-if="course.isCollect==1" src="../../assets/image/sc_c.png" class="dz">
              <img v-else src="../../assets/image/sc.png" class="dz">
              <span class="dztext">收藏</span>
              <span class="dzcount">({{ course.COLLECT_COUNT }})</span>
            </div>
          </div>
          <div class="bt" @click="showPop">自测问答</div>
          <div class="readNum">浏览量:{{ course.READNUM }}</div>
        </div>
      </div>
      <div class="info">
        <div class="left">课程简介:</div>
        <div class="right">{{ course.CONTENT }}</div>
      </div>
      <div v-if="course.listFile&&course.listFile.length>0" class="attach">
        <div class="left">附件下载:</div>
        <ul class="right">
          <li v-for="item in course.listFile" :key="item.file_id" @click="openfile(item.file_path)">
            <img src="../../assets/image/download.png" class="icon">
            <a>{{ item.file_name }}</a>
          </li>
        </ul>
      </div>
      <div class="comment-title-box">
        <div class="comment-title">添加评论:</div>
        <div class="comment-count">{{ commentCount }}/140</div>
      </div>
      <div class="comment-box">
        <el-input :rows="3" v-model="content" :maxlength="140" type="textarea" placeholder=""/>
        <div class="comment-bt" @click="addComment">发表评论</div>
      </div>
      <div class="comment-list-box">
        <div v-if="commentList.length>0" class="comment-list-all">所有评论({{ totalResult }})</div>
        <ul class="comment-list">
          <li v-for="item in commentList" :key="item.comment_id">
            <div class="comment-list-header">
              <img src="../../assets/image/user.png" class="user-icon">
              <div class="user-info">
                <div class="name">{{ item.creation_people_id }}</div>
                <div class="time">发表于{{ item.create_time }}</div>
              </div>
            </div>
            <div class="comment-list-content">
              {{ item.content }}
            </div>
          </li>
        </ul>
        <div v-if="commentList.length>0" class="paginationDiv" style="margin:24px 0 36px 0">
          <el-pagination :total="totalResult" :page-size="showCount" background layout="prev, pager, next" @current-change="currentChange"/>
        </div>
      </div>
    </div>
    <div :style="styleObject" class="pop">
      <div class="pop-main">
        <div class="pop-title">
          <span>自测问答</span>
          <img src="../../assets/image/close.png" @click="closePop">
        </div>
        <div class="pop-content">
          <div>
            <ul class="exam-list">
              <li v-for="(item,index) in examList" :key="item.SELFTEST_ID">
                <div v-if="item.EXAM_TYPE=='1'" class="exam-title">{{ (index+1)+'.'+item.EXAM }}(单选)</div>
                <div v-else-if="item.EXAM_TYPE=='2'" class="exam-title">{{ (index+1)+'.'+item.EXAM }}(多选)</div>
                <div v-else class="exam-title">{{ (index+1)+'.'+item.EXAM }}(判断)</div>
                <div :class="item.class">
                  <el-checkbox-group v-if="item.EXAM_TYPE=='2'" v-model="item.result">
                    <el-checkbox v-for="son in item.son" :label="son.value" :key="son.value">{{ son.text }}</el-checkbox>
                  </el-checkbox-group>
                  <el-radio v-for="son in item.son" v-else v-model="item.result" :key="son.value" :label="son.value">{{ son.text }}</el-radio>
                </div>
              </li>
            </ul>
            <div class="exam-footer">
              <div class="exam-bt" @click="submitAnswer">确定</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getCourseDetail,
  updateRead,
  dolike,
  cancellike,
  docollect,
  cancelcollect,
  submitAnswer,
  addComment,
  commentList
} from '@/api/learn'
export default {
  components: {},
  data() {
    return {
      course: {},
      examList: [],
      breadcrumb: [],
      styleObject: {
        display: 'none'
      },
      content: '',
      loading: false,
      currentpage: 1,
      showCount: 5,
      commentList: [],
      totalResult: 0
    }
  },
  computed: {
    commentCount: function() {
      return this.content.length
    }
  },
  mounted() {
    this.getCourseDetail(true)
    this.getCommentList()
  },
  methods: {
    getCourseDetail(refreshTag) {
      getCourseDetail({
        coursedetailid: this.$route.query.COURSEDETAIL_ID
      })
        .then(response => {
          console.log(response)
          var listFile = response.resultList[0].listFile
          for (const file of listFile) {
            file.file_name =
              file.file_name +
              file.file_path.substr(file.file_path.lastIndexOf('.'))
          }
          this.course = response.resultList[0]
          this.examList = this.examListFilter(response.examList)
          if (response.resultList[0].isRead === 0) {
            this.updateRead()
          }
          if (refreshTag) {
            // eslint-disable-next-line
            polyvPlayer({
              wrap: '#player',
              width: 700,
              height: 400,
              vid: response.resultList[0].VIDEO_ID,
              playsafe: function(vid, next) {
                self.$axios
                  .get('/appvideo/getVideoToken.do?vid=' + vid)
                  .then(function(res) {
                    next(res.data.token)
                  })
              }
            })
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    examListFilter(examList) {
      for (const elem of examList) {
        // 1 单选 2多选 3判断
        elem.son = []
        if (elem.EXAM_TYPE === '1') {
          elem.result = ''
          elem.class = 'exam-column'
        } else if (elem.EXAM_TYPE === '2') {
          elem.result = []
          elem.class = 'exam-column'
        } else {
          elem.result = ''
          elem.class = 'exam-row'
        }
        if (elem.OPTION1 !== '') {
          elem.son.push({
            text: elem.OPTION1,
            value: 'A'
          })
        }
        if (elem.OPTION2 !== '') {
          elem.son.push({
            text: elem.OPTION2,
            value: 'B'
          })
        }
        if (elem.OPTION3 !== '') {
          elem.son.push({
            text: elem.OPTION3,
            value: 'C'
          })
        }
        if (elem.OPTION4 !== '') {
          elem.son.push({
            text: elem.OPTION4,
            value: 'D'
          })
        }
        if (elem.OPTION5 !== '') {
          elem.son.push({
            text: elem.OPTION5,
            value: 'E'
          })
        }
        if (elem.OPTION6 !== '') {
          elem.son.push({
            text: elem.OPTION6,
            value: 'F'
          })
        }
      }
      return examList
    },
    isdolike(isLike) {
      if (isLike === 1) {
        this.cancellike()
      } else {
        this.dolike()
      }
    },
    isdocollect(isdocollect) {
      if (isdocollect === 1) {
        this.cancelcollect()
      } else {
        this.docollect()
      }
    },
    dolike() {
      dolike({
        LIKEFOR_ID: this.$route.query.COURSEDETAIL_ID
      })
        .then(response => {
          console.log(response)
          this.getCourseDetail()
        })
        .catch(error => {
          console.log(error)
        })
    },
    cancellike() {
      cancellike({
        LIKEFOR_ID: this.$route.query.COURSEDETAIL_ID
      })
        .then(response => {
          console.log(response)
          this.getCourseDetail()
        })
        .catch(error => {
          console.log(error)
        })
    },
    docollect() {
      docollect({
        COLLECTFOR_ID: this.$route.query.COURSEDETAIL_ID
      })
        .then(response => {
          console.log(response)
          this.getCourseDetail()
        })
        .catch(error => {
          this.listLoading = false
          console.log(error)
        })
    },
    cancelcollect() {
      cancelcollect({
        COLLECTFOR_ID: this.$route.query.COURSEDETAIL_ID
      })
        .then(response => {
          console.log(response)
          this.getCourseDetail()
        })
        .catch(error => {
          this.listLoading = false
          console.log(error)
        })
    },
    // 已读标示发送
    updateRead() {
      updateRead({
        coursedetailid: this.$route.query.COURSEDETAIL_ID
      })
        .then(response => {
          console.log(response)
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 提交答案
    submitAnswer() {
      var answer = []
      for (const item of this.examList) {
        if (item.result === '' || item.result.length === 0) {
          this.$alert('您有题目未回答', '提示', {
            center: true
          })
          break
        }
        if (item.EXAM_TYPE === '2') {
          // 多选
          answer.push(
            item.result
              .sort(function(a, b) {
                return a > b
              })
              .join('')
          )
        } else {
          // 单选
          answer.push(item.result)
        }
      }
      if (answer.length === this.examList.length) {
        submitAnswer({
          coursedetailid: this.$route.query.COURSEDETAIL_ID,
          answer: JSON.stringify(answer)
        })
          .then(data => {
            console.log(data)
            if (data.code === '33') {
              this.$alert('您有题目未回答正确', '提示', {
                center: true,
                callback: action => {
                  this.closePop()
                }
              })
            } else if (data.code === '01') {
              this.$alert('恭喜你回答正确', '提示', {
                center: true,
                callback: action => {
                  this.closePop()
                }
              })
            } else if (data.code === '02') {
              this.$alert(data.msg, '提示', {
                center: true,
                callback: action => {
                  this.closePop()
                }
              })
            }
          })
          .catch(error => {
            console.log(error)
          })
      }
    },
    closePop() {
      this.styleObject.display = 'none'
    },
    showPop() {
      this.clearResult()
      if (this.examList.length === 0) {
        this.$alert('暂无题目', '提示', {
          center: true
        })
        console.log(1111)
        return
      }
      this.styleObject.display = 'block'
    },
    // 清除答案
    clearResult() {
      for (const item of this.examList) {
        if (Array.isArray(item.result)) {
          item.result = []
        } else {
          item.result = ''
        }
      }
    },
    // 打开附件详情
    openfile(path) {
      window.open(process.env.API_ROOT + '/' + path)
    },
    // 添加评论
    addComment() {
      this.loading = true
      addComment({
        coursedetail_id: this.$route.query.COURSEDETAIL_ID,
        coursedetail_name: this.course.TITLE,
        content: this.content
      })
        .then(data => {
          if (data.code === '01') {
            this.loading = false
            this.$alert('评论成功', '提示', {
              center: true,
              dangerouslyUseHTMLString: true
            })
            this.content = ''
            this.getCommentList()
          } else if (data.code === '44') {
            this.$alert('您暂无权限评论此课程', '提示', {
              center: true,
              dangerouslyUseHTMLString: true
            })
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 获取评论
    getCommentList() {
      commentList({
        coursedetail_id: this.$route.query.COURSEDETAIL_ID,
        currentPage: this.currentpage,
        showCount: this.showCount
      })
        .then(data => {
          this.commentList = data.data
          this.totalResult = data.page.totalResult
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 评论列表分页
    currentChange(currentPage) {
      this.currentpage = currentPage
      this.getCommentList()
    }
  }
}
</script>

<style lang="scss" scoped>
.courseDetail {
  ul,
  li {
    list-style: none;
    margin: 0;
    padding: 0;
  }
  width: 960px;
  margin: 0 auto;
  padding: 20px 8px 8px 8px;
  background: white;
  min-height: calc(100vh - 110px);
  @mixin middle {
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .title {
    font-size: 18px;
    color: #4b4a49;
    margin-left: 10px;
    margin-bottom: 5px;
  }
  .cardBox {
    width: 940px;
    height: 400px;
    .left {
      width: 700px;
      height: 400px;
      float: left;
    }
    .right {
      width: 240px;
      height: 400px;
      box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
      padding: 45px 28px 32px 28px;
      float: left;
      .title {
        font-size: 15px;
        color: #666666;
      }
      .center {
        @include middle;
        margin-top: 120px;
        .score {
          width: 25px;
          margin-right: 10px;
        }
        .dzbox {
          @include middle;
          margin-top: 62px;
          cursor: pointer;
          .dz {
            width: 17px;
            margin-right: 5px;
          }
          .dztext {
            font-size: 10px;
          }
          .dzcount {
            font-size: 10px;
          }
        }
        .scoretext {
          font-size: 23px;
          color: #595757;
        }
      }
      .bt {
        font-size: 22px;
        color: white;
        display: flex;
        justify-content: center;
        align-items: center;
        width: 120px;
        height: 50px;
        border-radius: 8px;
        margin-top: 32px;
        background-color: #eaa82d;
        cursor: pointer;
      }
      .readNum {
        font-size: 12px;
        color: #808080;
        margin-top: 30px;
        margin-left: 5px;
      }
    }
  }
  .info {
    margin-top: 24px;
    display: flex;
    .left {
      width: 80px;
      font-size: 14px;
      color: #666666;
    }
    .right {
      width: 620px;
      word-break: break-all;
      font-size: 14px;
      color: #666666;
    }
  }
  .attach {
    margin-top: 24px;
    margin-bottom: 24px;
    display: flex;
    .left {
      width: 80px;
      font-size: 14px;
      color: #666666;
    }
    .right {
      width: 620px;
      li {
        cursor: pointer;
        @include middle;
        .icon {
          width: 20px;
          margin-right: 5px;
        }
        a {
          color: #808080;
          font-size: 12px;
          &:hover {
            color: #eaa82d;
          }
        }
      }
    }
  }
  .comment-title-box {
    display: flex;
    width: 940px;
    margin: 12px 0 6px 0;
    .comment-title {
      font-size: 14px;
      color: #666666;
      flex: 1;
    }
    .comment-count {
      font-size: 14px;
      color: #a7a7a8;
    }
  }
  .comment-box {
    width: 940px;
    display: flex;
    flex-direction: column;
    .comment-bt {
      font-size: 16px;
      color: white;
      font-weight: bold;
      width: 90px;
      height: 30px;
      border-radius: 4px;
      background-color: #eaa82d;
      cursor: pointer;
      text-align: center;
      line-height: 30px;
      margin-top: 6px;
      align-self: flex-end;
    }
  }
  .comment-list-box {
    width: 940px;
    .comment-list-all {
      font-size: 14px;
      color: #666666;
      margin-bottom: 6px;
    }
    li {
      padding: 20px 0;
      border-bottom: 1px solid #b8b8b8;
      .comment-list-header {
        display: flex;
        align-items: center;
        .user-icon {
          height: 40px;
          margin-right: 6px;
        }
        .user-info {
          flex: 1;
          .name {
            color: #9ac7e2;
            font-size: 12px;
            line-height: 1;
            margin-bottom: 6px;
          }
          .time {
            font-size: 12px;
            color: #808080;
            line-height: 1;
          }
        }
      }
      .comment-list-content {
        font-size: 13px;
        line-height: 20px;
        color: #666666;
        margin-top: 12px;
        word-break: break-all;
      }
      &:first-child {
        border-top: 1px solid #b8b8b8;
      }
    }
    .paginationDiv {
      width: 100%;
      display: flex;
      flex-direction: row;
      justify-content: center;
    }
  }
  .margn-t12 {
    margin-top: 12px;
  }
  .margn-t36 {
    margin-top: 36px;
  }
  .margin-r20 {
    margin-right: 20px;
  }
  .pop {
    position: fixed;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    background-color: rgba(26, 26, 26, 0.5);
    z-index: 100;
    .pop-main {
      width: 760px;
      height: 410px;
      background-color: white;
      border-radius: 8px;
      margin: auto;
      position: absolute;
      left: 0;
      top: 0;
      right: 0;
      bottom: 0;
      display: flex;
      flex-direction: column;
      .pop-title {
        background-color: #dcdcdc;
        width: 100%;
        height: 40px;
        border-top-right-radius: 8px;
        border-top-left-radius: 8px;
        display: flex;
        align-items: center;
        span {
          color: #808080;
          flex: 1;
          margin-left: 20px;
          font-size: 20px;
        }
        img {
          width: 20px;
          height: 20px;
          margin-right: 20px;
        }
      }
      .pop-content {
        flex: 1;
        background-color: white;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        overflow-y: auto;
      }
    }
  }
  .exam-list {
    li {
      margin: 0 20px;
      border-bottom: 1px solid #dcdcdc;
      padding: 20px 0;
      .exam-title {
        font-size: 16px;
        margin-bottom: 10px;
      }
      .exam-row {
        display: flex;
      }
      .exam-column {
        display: flex;
        flex-direction: column;
        .el-radio {
          margin: 3px 0;
        }
        .el-radio + .el-radio {
          margin-left: 0;
        }
        .el-checkbox-group {
          display: flex;
          flex-direction: column;
          .el-checkbox {
            margin: 3px 0;
          }
          .el-checkbox + .el-checkbox {
            margin-left: 0;
          }
        }
      }
    }
  }
  .exam-footer {
    display: flex;
    justify-content: center;
    margin: 20px 0;
    .exam-bt {
      background-color: #931a25;
      color: white;
      padding: 5px 20px;
      border-radius: 8px;
      cursor: pointer;
    }
  }
}
</style>
<style>
.comment-box .el-textarea .el-textarea__inner {
  border: 1px solid #b3b3b3;
}
