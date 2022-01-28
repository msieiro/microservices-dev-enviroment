<template>
  <div class="group">
    <select
      :id="name"
      :name="name"
      :required="required"
      :multiple="multiple"
      :disabled="disabled"
    >
      <option selected disabled></option>
      <option v-for="(item, index) in items" :key="index" :value="item.value">
        {{ item.name }}
      </option>
    </select>
    <label :for="name">{{ name }}</label>
  </div>
</template>

<script>
export default {
  name: 'FormSelect',
  props: {
    disabled: {
      type: String,
      required: false,
    },
    multiple: {
      type: String,
      required: false,
    },
    name: {
      type: String,
      required: false,
    },
    required: {
      type: String,
      required: false,
    },
    items: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
.group {
  position: relative;
  select {
    background: black;
    color: #c6c6c6;
    font-size: 18px;
    padding: 32px 20px 5px 5px;
    display: block;
    border-radius: 4px;
    border: 1px solid #c6c6c6;
    cursor: pointer;
    &:focus {
      outline: none;
      border-color: rgb(29, 155, 240);
    }
    &:focus ~ label {
      top: 4px;
      font-size: 12px;
      color: rgb(29, 155, 240);
    }
    &:valid ~ label {
      top: 4px;
      font-size: 12px;
      color: white;
    }
  }

  label {
    color: #c6c6c6;
    font-size: 16px;
    font-weight: normal;
    position: absolute;
    pointer-events: none;
    left: 10px;
    top: 20px;
  }
}
</style>
