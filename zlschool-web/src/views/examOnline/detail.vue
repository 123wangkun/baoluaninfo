<template>
  <div class="examOnlineDetail">
    <div v-show="isAjax" class="examOnline-body">
      <img src="../../assets/img/top.jpg" class="topimg">
      <div class="examDetail">
        <div class="examTitle">
          {{ title }}
        </div>
        <div class="examBody">
          <div class="row">
            <div class="allCount">
              共{{ questionCount }}题
            </div>

          </div>
          <div v-if="panduanList.length>0" class="examBodyTitle">判断题</div>
          <ul class="examlist">
            <li v-for="(item,index) in panduanList" :key="item.id">
              <div class="exam-title">{{ (index+1)+'.'+item.content }}
                <span v-if="item.sfzq=='0'">(本题错误)</span>
              </div>
              <img v-if="item.img_path" :src="item.img_path|imgUrlFilter" class="exam-img">
              <div class="exam-row">
                <el-radio-group v-model="item.result">
                  <el-radio label="A">是</el-radio>
                  <el-radio label="B">否</el-radio>
                </el-radio-group>
              </div>
            </li>
          </ul>
          <div v-if="danxuanList.length>0" class="examBodyTitle">单选题</div>
          <ul class="examlist">
            <li v-for="(item,index) in danxuanList" :key="item.id">
              <div class="exam-title">{{ (index+1+panduanList.length)+'.'+item.content }}
                <span v-if="item.sfzq=='0'">(本题错误)</span>
              </div>
              <img v-if="item.img_path" :src="item.img_path|imgUrlFilter" class="exam-img">
              <div class="exam-column">
                <el-radio v-for="optionItem in item.options" v-model="item.result" :label="optionItem.options_id" :key="optionItem.id">{{ optionItem.options_id+'.'+optionItem.options }}</el-radio>
              </div>
            </li>
          </ul>
          <div v-if="duoxuanList.length>0" class="examBodyTitle">多选题</div>
          <ul class="examlist">
            <li v-for="(item,index) in duoxuanList" :key="item.id">
              <div class="exam-title">{{ (index+1+panduanList.length+danxuanList.length)+'.'+item.content }}
                <span v-if="item.sfzq=='0'">(本题错误)</span>
              </div>
              <img v-if="item.img_path" :src="item.img_path|imgUrlFilter" class="exam-img">
              <div class="exam-column">
                <el-checkbox-group v-model="item.result">
                  <el-checkbox v-for="optionItem in item.options" v-model="item.result" :label="optionItem.options_id" :key="optionItem.id">{{ optionItem.options_id+'.'+optionItem.options }}</el-checkbox>
                </el-checkbox-group>
              </div>
            </li>
          </ul>
          <div v-if="jiandaList.length>0" class="examBodyTitle">分主观题</div>
          <ul class="examlist">
            <li v-for="(item,index) in jiandaList" :key="item.id">
              <div class="exam-title">{{ (index+1+panduanList.length+danxuanList.length+duoxuanList.length)+'.'+item.content }}</div>
              <img v-if="item.img_path" :src="item.img_path|imgUrlFilter" class="exam-img">
              <el-input :rows="3" v-model="item.result" type="textarea" placeholder=""/>
            </li>
          </ul>

        </div>
        <div v-if="status==='0'" class="examfooter">
          <button type="button" class="el-button el-button--primary" @click="submit()">
            <span>提交答案</span>
          </button>
        </div>
      </div>
      <count-down
        v-show="isAjax"
        v-if="status==='0'"
        :current-time="currTime"
        :start-time="startTime"
        :end-time="endTime"
        :day-txt="''"
        :hour-txt="':'"
        :minutes-txt="':'"
        :seconds-txt="''"
        class="countdown"
        @start_callback="countDownS_cb(1)"
        @end_callback="countDownE_cb(1)"/>
      <div v-if="isSubmit" class="popWindow">
        <div class="pop-main">
          <div class="pop-title">
            <span>试卷分析</span>
          </div>
          <div class="pop-content">
            <div class="pop-content-title">本次测试共{{ count }}题</div>
            <span v-if="isSubmit&&count!=okCount" class="okmsg">答对
              <span class="count">{{ okCount }}</span>
              题
            </span>
            <span v-if="isSubmit&&count!=okCount" class="errmsg">答错
              <span class="count">{{ notCount }}</span>
              题
            </span>
            <div v-if="isSubmit&&count==okCount" class="allOKdiv">
              <img src="/static/image/ok.png">
              <span>全部答对</span>
            </div>
          <!-- <div v-if="isSubmit&&count==okCount" class="allOKmsg">
            恭喜获得<img src="../../assets/img/score.png">{{ gold }}
          </div> -->
          </div>
          <div class="pop-footer">
            <!-- <span class="pop-bt" v-if="isSubmit&&count!=okCount">查看试卷</span> -->
            <span class="pop-bt" @click="closePage">确定</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CountDown from '@/components/Countdown'
import { paperDetails, submitExam } from '@/api/exam'
export default {
  name: 'ExamOnlineDetail',
  components: { CountDown },
  data() {
    return {
      title: '',
      questionCount: '',
      paper_id: '',
      panduanList: [],
      danxuanList: [],
      duoxuanList: [],
      jiandaList: [],
      count: '', // 试卷提交后返回
      okCount: '', // 试卷提交后返回
      notCount: '', // 试卷提交后返回
      gold: '', // 试卷提交后返回
      isSubmit: false, // 是否提交
      endTime: 0,
      startTime: 0,
      currTime: 0,
      isAjax: false,
      status: '1'
    }
  },
  mounted() {
    this.paperDetails()
  },
  methods: {
    // 获取考试详情
    paperDetails() {
      paperDetails({ id: this.$route.query.id })
        .then(response => {
          var data = response
          if (data.result === '01') {
            this.currTime = this.startTime = Math.floor(
              new Date().getTime() / 1000
            )
            // this.endTime = this.currTime + 10
            this.endTime = this.currTime + data.data.date * 60
            this.isAjax = true
            this.title = data.data.name
            this.status = data.data.status // 0 可以提交
            this.questionCount = data.data.questionCount
            this.paper_id = data.data.id
            if (data.data.question.PANDUAN) {
              for (const item of data.data.question.PANDUAN) {
                if (item.user_answer && item.user_answer !== '') {
                  item.result = item.user_answer
                } else {
                  item.result = ''
                }
              }
              this.panduanList = data.data.question.PANDUAN
            }
            if (data.data.question.DANXUAN) {
              for (const item of data.data.question.DANXUAN) {
                if (item.user_answer && item.user_answer !== '') {
                  item.result = item.user_answer
                } else {
                  item.result = ''
                }
              }
              this.danxuanList = data.data.question.DANXUAN
            }
            if (data.data.question.DUOXUAN) {
              for (const item of data.data.question.DUOXUAN) {
                if (item.user_answer && item.user_answer !== '') {
                  item.result = item.user_answer.split('')
                } else {
                  item.result = []
                }
              }
              this.duoxuanList = data.data.question.DUOXUAN
            }
            if (data.data.question.JIANDA) {
              for (const item of data.data.question.JIANDA) {
                if (item.user_answer && item.user_answer !== '') {
                  item.result = item.user_answer
                } else {
                  item.result = ''
                }
              }
              this.jiandaList = data.data.question.JIANDA
            }
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    // 非空校验
    submit() {
      const answer = []
      let panduanFlag = true // 判断是否全部回答，默认全部回答，遍历后若有未回答，置为false
      let danxuanFlag = true // 判断是否全部回答，默认全部回答，遍历后若有未回答，置为false
      let duoxuanFlag = true // 判断是否全部回答，默认全部回答，遍历后若有未回答，置为false
      let jiandaFlag = true // 判断是否全部回答，默认全部回答，遍历后若有未回答，置为false
      for (const item of this.panduanList) {
        if (item.result === '') {
          panduanFlag = false
          break
        }
        answer.push({ question_id: item.id, answer: item.result })
      }
      if (!panduanFlag) {
        this.$alert('您有判断题未回答', '提示', {
          center: true
        })
        return
      }
      for (const item of this.danxuanList) {
        if (item.result === '') {
          danxuanFlag = false
          break
        }
        answer.push({ question_id: item.id, answer: item.result })
      }
      if (!danxuanFlag) {
        this.$alert('您有单选题未回答', '提示', {
          center: true
        })
        return
      }
      for (const item of this.duoxuanList) {
        if (item.result.length === 0) {
          duoxuanFlag = false
          break
        }
        answer.push({
          question_id: item.id,
          answer: item.result
            .sort(function(a, b) {
              return a > b
            })
            .join('')
        })
      }
      if (!duoxuanFlag) {
        this.$alert('您有多选题未回答', '提示', {
          center: true
        })
        return
      }
      for (const item of this.jiandaList) {
        if (item.result === '') {
          jiandaFlag = false
          break
        }
        answer.push({ question_id: item.id, answer: item.result })
      }
      if (!jiandaFlag) {
        this.$alert('您有简答题未回答', '提示', {
          center: true
        })
        return
      }
      this.submitExam(answer)
    },
    // 直接提交
    submitNoVerify() {
      const answer = []
      for (const item of this.panduanList) {
        answer.push({ question_id: item.id, answer: item.result })
      }
      for (const item of this.danxuanList) {
        answer.push({ question_id: item.id, answer: item.result })
      }
      for (const item of this.duoxuanList) {
        answer.push({
          question_id: item.id,
          answer: item.result
            .sort(function(a, b) {
              return a > b
            })
            .join('')
        })
      }
      for (const item of this.jiandaList) {
        answer.push({ question_id: item.id, answer: item.result })
      }
      console.log(answer)
      this.submitExam(answer)
    },
    // 试卷提交
    submitExam(answer) {
      submitExam({
        paper_id: this.paper_id,
        answers: JSON.stringify(answer)
      })
        .then(response => {
          var data = response
          if (data.result === '01') {
            console.log(data)
            this.status = '1'
            this.isSubmit = true
            this.count = data.data.count
            this.notCount = data.data.notCount
            this.okCount = data.data.okCount
            this.gold = data.data.gold
          }
        })
        .catch(error => {
          console.log(error)
        })
    },
    closePage() {
      this.isSubmit = false
      this.paperDetails()
    },
    countDownS_cb(x) {
      console.log('start')
    },
    countDownE_cb(x) {
      console.log('end')
      if (this.status === '0') {
        this.submitNoVerify()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.examOnlineDetail {
  width: 100%;
  background: url(../../assets/img/bg2.jpg) no-repeat;
  background-size: 100% 100%;
  .examOnline-body {
    width: 960px;
    margin: 0 auto;
    background: white;
  }
  .topimg {
    width: 960px;
  }
  .examDetail {
    width: 100%;
    box-shadow: 0px 7px 5px rgba(0, 0, 0, 0.3);
    .examTitle {
      height: 72px;
      // background-color: #cccccc;
      font-size: 21px;
      font-weight: bold;
      color: #4d4d4d;
      line-height: 72px;
      text-align: center;
    }
    .examBody {
      padding: 0 48px;
      .row {
        display: flex;
        flex-direction: row;
        border-bottom: 1px dashed #b3b3b3;
        .allCount {
          font-size: 13px;
          font-weight: bold;
          color: #808080;
          padding: 24px 0 12px 0;
        }
      }

      .examBodyTitle {
        font-size: 15px;
        font-weight: bold;
        color: #808080;
        margin: 24px 0 0px 0;
      }
      .examlist {
        li {
          padding: 20px 0;
          border-bottom: 1px solid #b3b3b3;
          .exam-title {
            font-size: 15px;
            line-height: 19px;
            color: #4d4d4d;
            font-weight: bold;
            margin-bottom: 15px;
            span {
              font-size: 12px;
              color: #c1272d;
              font-weight: bold;
            }
          }
          .exam-img {
            width: 350px;
            margin: 0px 0 15px 10px;
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
    }
    .examfooter {
      display: flex;
      justify-content: center;
      padding: 40px 0;
      .bt {
        width: 115px;
        height: 32px;
        color: white;
        background-color: #eaa82d;
        border-radius: 4px;
        text-align: center;
        line-height: 32px;
        font-size: 19px;
        font-weight: bold;
        cursor: pointer;
      }
    }
  }
  .popWindow {
    width: 100%;
    height: 100%;
    background-color: rgba(26, 26, 26, 0.5);
    position: fixed;
    top: 0;
    left: 0;
    z-index: 100;
    .pop-main {
      width: 635px;
      height: 350px;
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
          font-size: large;
        }
      }
      .pop-content {
        flex: 1;
        background-color: white;
        border-bottom-left-radius: 8px;
        border-bottom-right-radius: 8px;
        overflow-y: auto;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        padding: 0 40px;
        .pop-content-title {
          font-size: 27px;
          font-weight: bold;
          color: #808080;
          align-self: flex-start;
          margin-top: 34px;
        }
        .okmsg {
          font-size: 31px;
          line-height: 31px;
          font-weight: bold;
          color: #808080;
          margin-top: 40px;
          .count {
            line-height: 31px;
            color: #29abe2;
          }
        }
        .errmsg {
          font-size: 31px;
          line-height: 31px;
          font-weight: bold;
          color: #808080;
          margin-top: 20px;
          .count {
            line-height: 31px;
            color: #c1272d;
          }
        }
        .allOKdiv {
          display: flex;
          flex-direction: row;
          align-items: center;
          margin-top: 30px;
          img {
            height: 60px;
          }
          span {
            font-size: 38px;
            color: #808080;
          }
        }
        .allOKmsg {
          display: flex;
          font-size: 29px;
          line-height: 29px;
          color: #808080;
          align-items: center;
          margin-top: 20px;
          img {
            height: 29px;
            margin: 0 5px;
          }
        }
      }
      .pop-footer {
        width: 100%;
        text-align: center;
        padding-bottom: 40px;
        .pop-bt {
          background-color: #eaa82d;
          color: white;
          font-size: 16px;
          padding: 8px 16px;
          border-radius: 4px;
          cursor: pointer;
        }
      }
    }
  }
  .countdown {
    position: fixed;
    top: 120px;
    right: 5px;
  }
}
</style>

