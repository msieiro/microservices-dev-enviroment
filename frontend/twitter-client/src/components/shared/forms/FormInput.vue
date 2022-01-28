<template>
  <div class="group">
    <input :type="type" :required="required" />
    <span class="highlight"></span>
    <span class="bar"></span>
    <label>{{ label }}</label>
  </div>
</template>

<script>
export default {
  name: 'FormInput',
  props: {
    type: {
      type: String,
      required: true,
    },
    label: {
      type: String,
      required: true,
    },
    required: {
      type: String,
      required: true,
    },
  },
};
</script>

<style lang="scss" scoped>
.group {
  position: relative;
  input {
    background: none;
    color: #c6c6c6;
    font-size: 18px;
    padding: 32px 20px 5px 5px;
    display: block;
    border-radius: 4px;
    border: 1px solid #c6c6c6;
    width: calc(95%);

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
    &:focus ~ .bar:before {
      width: 320px;
    }
    &[type='password'] {
      letter-spacing: 0.3em;
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
    transition: 300ms ease all;
  }
}
</style>
