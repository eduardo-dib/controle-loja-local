import axios from 'axios'


const API_URL = import.meta.env.VITE_API_URL


export async function listarVendas() {
    const response = await axios.get(`${API_URL}/vendas`)
    return response
}
