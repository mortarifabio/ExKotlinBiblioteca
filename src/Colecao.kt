class Colecao (
    val codigo: String,
    val nome: String,
    val dataCriacao: String
) {
    var status = 'D'
    val livros: MutableMap<String, Livro> = mutableMapOf()

    fun addLivro(livro: Livro) {
        if(!livros.containsKey(livro.codigo)){
            livros.put(livro.codigo, livro)
        }else{
            println("Já existe um livro cadastrado com esse código.")
        }
    }

    fun consultarLivro(entrada: String): Map<String, Livro> {
        return livros.filter { it.key == entrada || it.value.titulo == entrada }
    }

}