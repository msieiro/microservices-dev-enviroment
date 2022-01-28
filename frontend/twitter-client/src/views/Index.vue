<template>
  <div class="index-grid-container">
    <div class="index-main">
      <div>
        <Logo height="40" />
        <h1 class="index-title">Lo que está pasando ahora</h1>
        <h2 class="index-subtitle">Únete a Twitter hoy mismo.</h2>
      </div>
      <div>
        <a class="button" href="#flow">Regístrate</a>
        <h2 class="index-subtitle">¿Ya tienes una cuenta?</h2>
        <button class="button">Iniciar sesión</button>
      </div>
    </div>
    <div class="index-aside">
      <Logo height="280" />
    </div>
    <IndexFooter />
    <RegisterModal id="flow" />
  </div>
</template>

<script>
import Logo from '../components/shared/Logo.vue';
import IndexFooter from '../components/index/IndexFooter.vue';
import RegisterModal from '../components/register/Register.vue';

export default {
  name: 'Index',
  components: {
    Logo,
    IndexFooter,
    RegisterModal,
  },
};
</script>

<style lang="scss">
#flow:target {
  opacity: 1;
  pointer-events: auto;
}
.index-grid-container {
  display: grid;
  grid-template-areas:
    'main main main'
    'aside aside aside'
    'footer footer footer';
  grid-template-columns: auto auto auto;
  grid-template-rows: auto auto auto;
  background-color: black;
  height: 100vh;
  color: white !important;
  .index-main {
    grid-area: main;
    display: flex;
    flex-direction: column;
    margin: 2.5em;
  }
  .index-title {
    letter-spacing: -0.8px;
    overflow-wrap: break-word;
    font-size: 3.2em;
    font-weight: 700;
  }
  .index-subtitle {
    letter-spacing: -0.8px;
    overflow-wrap: break-word;
    font-size: 2em;
  }
  .index-aside {
    grid-area: aside;
    background: url('../assets/background.png') no-repeat center;
    display: flex;
    justify-content: center;
    align-items: center;
  }
}
</style>
