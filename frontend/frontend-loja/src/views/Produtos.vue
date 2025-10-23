<template>
  <h1>Produtos</h1>
  <p>Bem-vindo ao seu app Vue limpo!</p>
</template>

<script>
import * as ProdutosAPI from '../api/Produtos'

export default {
  name: 'ProdutosView',

  data() {
    return {
      produtos: [],
      loading: false,
      error: null,
    }
  },

  created() {
    this.listarProdutos()
  },

  methods: {
    async listarProdutos() {
      this.loading = true
      this.error = null

      try {
        const data = await ProdutosAPI.listarProdutos()
        this.produtos = data
        console.log(this.clientes)
      } catch (err) {
        this.error = 'Erro ao buscar produtos.'
        console.error(err)
      } finally {
        this.loading = false
      }
    },
  }
}
</script>
