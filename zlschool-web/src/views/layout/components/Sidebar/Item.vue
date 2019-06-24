<template>
  <div>
    <svg-icon v-if="icon" :icon-class="icon" />
    <span v-if="icon && title" slot="title1"> {{ title }}</span>
    <span v-else-if="!icon && title" slot="title" style="padding-left: 25px;">
      {{ title }}
    </span>
  </div>
</template>

<script>
export default {
  name: 'MenuItem',
  props: {
    icon: {
      type: String,
      default: ''
    },
    title: {
      type: String,
      default: ''
    }
  }
}
</script>
<style lang="scss" scoped>
.sidebar-item {
}
</style>

