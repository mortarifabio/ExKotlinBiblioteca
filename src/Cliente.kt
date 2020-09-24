class Cliente (
    val nome: String,
    val rg: String
) {
    val livrosAlugados: MutableMap<String, Livro> = mutableMapOf()
    val livrosComprados: MutableMap<String, Livro> = mutableMapOf()
}