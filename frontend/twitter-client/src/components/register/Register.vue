<template>
  <div class="register-modal">
    <div class="register-modal-contenido">
      <header class="modal-header">
        <a href="#" class="register-close-modal">X</a>
        <logo height="30" />
      </header>
      <div class="register-modal-form">
        <h2>Crea tu cuenta</h2>
        <form>
          <form-input type="text" required="true" label="Name" class="mt-5" />
          <form-input type="email" required="true" label="Email" class="mt-5" />
          <h3 class="mt-5">Fecha de nacimiento</h3>
          <span>
            Esta información no será pública. Confirma tu propia edad, incluso
            si esta cuenta es para una empresa, una mascota u otra cosa.
          </span>
          <div class="register-date-selectors mt-3">
            <month-selector name="Mes" />
            <month-selector name="Dia" />
            <month-selector name="Año" />
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import Logo from '../shared/Logo.vue';
import FormInput from '../shared/forms/FormInput.vue';
import MonthSelector from '../shared/forms/MonthSelector.vue';
export default {
  name: 'Register',
  components: { Logo, FormInput, MonthSelector },
};
</script>

<style lang="scss">
.register-modal {
  background-color: rgba(0, 0, 0, 0.8);
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  opacity: 0;
  pointer-events: none;
  transition: all 1s;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  .register-modal-contenido {
    background-color: black;
    color: white;
    border-radius: 16px;
    min-height: 400px;
    min-width: 500px;
    max-width: 500px;
    max-height: 90vh;
    overflow: hidden;
    padding: 10px 20px;
    position: relative;
    .modal-header {
      display: flex;
      align-items: center;
    }
    .register-close-modal {
      color: white;
      text-decoration: none;
      flex-basis: 47%;
    }
    .register-modal-form {
      margin: calc(3%);
      .register-date-selectors {
        display: flex;
      }
    }
    .register-date-selectors {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: center;
    }
  }
}
</style>
