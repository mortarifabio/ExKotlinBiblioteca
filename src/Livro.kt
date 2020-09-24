class Livro (
    val codigo: String,
    val titulo: String,
    val autor: String,
    val anoLancamento: Int,
    var precoVenda: Double,
    var precoAluguel: Double,
) {
    var status = 'D'
}