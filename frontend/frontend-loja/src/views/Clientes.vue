<template>
  <h1>Clientes</h1>
  <p>Bem-vindo ao seu app Vue limpo!</p>
</template>

<script>
import * as ClientesAPI from '../api/Clientes'

export default {
  name: 'ClientesView',

  data() {
    return {
      clientes: [],
      loading: false,
      error: null,
    }
  },

  created() {
    this.listarClientes()
  },

  methods: {
    async listarClientes() {
      this.loading = true
      this.error = null

      try {
        const data = await ClientesAPI.listarClientes()
        this.clientes = data
        console.log(this.clientes)
      } catch (err) {
        this.error = 'Erro ao buscar clientes.'
        console.error(err)
      } finally {
        this.loading = false
      }
    },
  }
}
</script>
