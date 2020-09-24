class Funcionario (
    val nome: String,
    val rg: String
) {
    val livrosAlugados: MutableMap<String, Livro> = mutableMapOf()
    val livrosVendidos: MutableMap<String, Livro> = mutableMapOf()
}
