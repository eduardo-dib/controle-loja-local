<template>
  <h1>Produtos</h1>
  <p>Bem-vindo ao seu app Vue limpo!</p>
</template>

<script>
import * as VendasAPI from '../api/Vendas'

export default {
  name: 'VendasView',

  data() {
    return {
      vendas: [],
      loading: false,
      error: null,
    }
  },

  created() {
    this.listarVendas()
  },

  methods: {
    async listarVendas() {
      this.loading = true
      this.error = null

      try {
        const data = await VendasAPI.listarVendas()
        this.vendas = data
        console.log(this.clientes)
      } catch (err) {
        this.error = 'Erro ao buscar vendas.'
        console.error(err)
      } finally {
        this.loading = false
      }
    },
  }
}
</script>
