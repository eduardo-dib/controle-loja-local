<script setup>
import { RouterLink, RouterView } from 'vue-router'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
</script>

<template>
  <div id="app">
    
    <Header />

    
    <main>
      <RouterView />
    </main>

    >
    <Footer />
  </div>
</template>

<style>
main {
  min-height: 80vh; /* só pra empurrar o footer mais pra baixo */
  padding: 1rem;
}
</style>
