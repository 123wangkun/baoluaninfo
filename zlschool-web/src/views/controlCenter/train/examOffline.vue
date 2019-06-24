<template>
  <!-- 线下考试选择 -->
  <div class="exam-offline-select pd-20">
    <el-input
      v-model="name"
      placeholder="请输入考试名称"
      class="mr-15"/>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-table
      ref="multipleTable"
      :data="tableList"
      border
      stripe
      tooltip-effect="dark"
      class="table mt-15"
      @selection-change="selectCourse">
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="name"
        label="考试名称"/>
      <el-table-column
        prop="address"
        label="考试地址"/>
      <el-table-column
        prop="studyTime"
        label="开始时间"
        min-width="100"/>
      <el-table-column
        prop="totalTime"
        label="考试时长"/>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        min-width="100"/>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'ExamOffline',
  data() {
    return {
      tableList: [{
        id: '1',
        name: '考试名称',
        address: '地址',
        studyTime: '2019-01-01',
        totalTime: 80,
        createdTime: '2019-01-01'
      }],
      name: '',
      selectExamList: []
    }
  },
  created() {},
  methods: {
    // 搜索
    search() {},
    // 选择考试
    selectCourse(val) {
      console.log(val)
      this.selectExamList = val
    }
  }
}
</script>
