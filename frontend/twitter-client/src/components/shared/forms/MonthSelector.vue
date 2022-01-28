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
      <option
        v-for="(month, index) in months"
        :key="index"
        :value="month.value"
      >
        {{ month.name }}
      </option>
    </select>
    <label :for="name">{{ name }}</label>
  </div>
</template>

<script>
export default {
  name: 'MonthSelector',
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
  },
  data() {
    return {
      months: [
        {
          name: 'Enero',
          value: '01',
        },
        {
          name: 'Febrero',
          value: '02',
        },
        {
          name: 'Marzo',
          value: '03',
        },
        {
          name: 'Abril',
          value: '04',
        },
        {
          name: 'Mayo',
          value: '05',
        },
        {
          name: 'Junio',
          value: '06',
        },
        {
          name: 'Julio',
          value: '07',
        },
        {
          name: 'Agosto',
          value: '08',
        },
        {
          name: 'Septiembre',
          value: '09',
        },
        {
          name: 'Octubre',
          value: '10',
        },
        {
          name: 'Noviembre',
          value: '11',
        },
        {
          name: 'Diciembre',
          value: '12',
        },
      ],
    };
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
