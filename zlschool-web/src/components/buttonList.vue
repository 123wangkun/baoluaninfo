<template>
  <div class="ButtonList">
    <div class="title">{{ title }}</div>
    <div class="list-box clearfix">
      <div v-for="(item,index) in list" :key="index" :class="value===item.value?'curr':''" class="list-bt" @click="setVal(item)">{{ item.name }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ButtonList',
  props: {
    list: {
      type: Array,
      required: true
    },
    value: {
      type: String,
      required: true
    },
    title: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      defaultImg: 'this.src="' + require('../assets/img/noImg.png') + '"'
    }
  },
  methods: {
    setVal(item) {
      this.$emit('input', item.value)
    }
  }
}
</script>
<style lang="scss" scoped>
@import '../assets/css/common.scss';
.ButtonList {
  width: 100%;
  display: flex;
  margin-bottom: 20px;
  align-items: center;
  .title {
    margin-right: 20px;
  }
  .list-box {
    flex: 1;
    .list-bt {
      float: left;
      padding: 2px 10px;
      border-radius: 10px;
      color: #606266;
      margin-right: 10px;
      cursor: pointer;
    }
    .curr {
      color: #409eff;
    }
  }
}
</style>

