<template>
  <div class="footer">
    <a
      v-for="(item, index) in footerItems"
      :key="index"
      role="link"
      class="footer-item"
      >{{ item }}</a
    >
  </div>
</template>

<script>
export default {
  name: 'IndexFooter',
  data() {
    return {
      footerItems: [
        'Acerca de',
        'Centro de ayuda',
        'Condiciones de Servicio',
        'Política de Privacidad',
        'Política de cookies',
        'Accesibilidad',
        'Información de anuncios',
        'Blog',
        'Estado',
        'Empleos',
        'Recursos para marcas',
        'Publicidad',
        'Marketing',
        'Twitter para empresas',
        'Desarrolladores',
        'Guía',
        'Configuración',
        '© 2021 Twitter, Inc',
      ],
    };
  },
};
</script>

<style lang="scss">
.footer {
  grid-area: footer;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-content: center;
  background: black;
}

.footer-item {
  font-size: 12px;
  padding: 0 0.4em 0 0;
}
</style>
