<template>
  <!-- 选择题目 -->
  <div class="question pd-20">
    <el-input
      v-model="name"
      placeholder="请输入题目名称"
      class="mr-15"/>
    <el-input
      v-model="score"
      placeholder="请输入分值"
      class="mr-15"/>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-table
      ref="multipleTable"
      :data="tableList"
      border
      stripe
      tooltip-effect="dark"
      class="table mt-15"
      @selection-change="selectQuestion">
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="name"
        label="题目内容"/>
      <el-table-column
        prop="typeName"
        label="题型"/>
      <el-table-column
        prop="score"
        label="分值"/>
      <el-table-column
        prop="answer"
        label="正确答案"/>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        min-width="100"/>
      <el-table-column
        prop="spendTime"
        label="学习时长"/>
      <el-table-column
        prop="memo"
        label="备注"/>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'Question',
  data() {
    return {
      tableList: [{
        id: '1',
        name: '课程名称',
        typeName: '单选题',
        score: 80,
        answer: 'B',
        createdTime: '2019-01-01',
        spendTime: 80,
        memo: '备注'
      }],
      name: '',
      score: '',
      selectQuestionList: []
    }
  },
  created() {},
  methods: {
    // 搜索
    search() {},
    // 选择题目
    selectQuestion(val) {
      console.log(val)
      this.selectQuestionList = val
    }
  }
}
</script>
