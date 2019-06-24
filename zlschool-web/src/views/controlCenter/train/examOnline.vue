<template>
  <!-- 线上考试选择 -->
  <div class="exam-online-select pd-20">
    <el-input
      v-model="name"
      placeholder="请输入课程名称"
      class="mr-15"/>
    <span>类型名称</span>
    <el-select v-model="typeSelect" placeholder="请选择" class="mr-15">
      <el-option
        v-for="item in typeOptions"
        :key="item.value"
        :label="item.label"
        :value="item.value"/>
    </el-select>
    <el-button type="primary" plain class="btn" @click="search">搜索</el-button>
    <el-table
      ref="multipleTable"
      :data="tableList"
      border
      stripe
      tooltip-effect="dark"
      class="table mt-15"
      @selection-change="selectPaper">
      <el-table-column
        type="selection"
        width="55"/>
      <el-table-column
        type="index"
        width="55"
        label="序号"/>
      <el-table-column
        prop="name"
        label="试卷名称"/>
      <el-table-column
        prop="typeName"
        label="试卷类型"/>
      <el-table-column
        prop="totalScore"
        label="试卷总分"/>
      <el-table-column
        prop="single"
        label="单选题数"/>
      <el-table-column
        prop="multiple"
        label="多选题数"/>
      <el-table-column
        prop="judge"
        label="判断题数"/>
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
        typeName: '类型1',
        totalScore: 100,
        single: 80,
        multiple: 80,
        judge: 80
      }],
      name: '',
      typeSelect: '',
      typeOptions: [{
        value: '',
        label: '全部'
      }, {
        value: 0,
        label: '类型1'
      }, {
        value: 1,
        label: '类型2'
      }],
      selectPaperList: []
    }
  },
  created() {},
  methods: {
    // 搜索
    search() {},
    // 选择试卷
    selectPaper(val) {
      console.log(val)
      this.selectPaperList = val
    }
  }
}
</script>
