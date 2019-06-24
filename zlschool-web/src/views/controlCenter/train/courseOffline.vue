<template>
  <!-- 线下课程选择 -->
  <div class="course-offline-select pd-20">
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
        label="课程名称"/>
      <el-table-column
        prop="typeName"
        label="课程类型"/>
      <el-table-column
        prop="studyTime"
        label="开始时间"
        min-width="100"/>
      <el-table-column
        prop="totalTime"
        label="课程时长"/>
      <el-table-column
        prop="createdTime"
        label="创建时间"
        min-width="100"/>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'CourseOffline',
  data() {
    return {
      tableList: [{
        id: '1',
        name: '课程名称',
        typeName: '类型1',
        studyTime: '2019-01-01',
        totalTime: 80,
        createdTime: '2019-01-01'
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
      selectCourseList: []
    }
  },
  created() {},
  methods: {
    // 搜索
    search() {},
    // 选择课程
    selectCourse(val) {
      console.log(val)
      this.selectCourseList = val
    }
  }
}
</script>
