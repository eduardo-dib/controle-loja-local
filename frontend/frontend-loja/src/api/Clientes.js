import axios from 'axios'


const API_URL = import.meta.env.VITE_API_URL


export async function listarClientes() {
    const response = await axios.get(`${API_URL}/clientes`)
    return response
}
