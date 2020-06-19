<template>
    <button @click="addCounter"></button>
</template>

<script>
export default {
  name: 'GlobalComponent',
  props: ['initialCounter'],
  template: '<button @click="addCount">{{ counter }}</button>',
  data: function () {
    return {
      counter: this.initialCount
    }
  },
  methods: {
    addCounter: function () {
      this.counter += 1
    }
  }
}
</script>

<style scoped>

</style>
