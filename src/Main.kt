fun main() {
    val biblioteca = Biblioteca("Minha Biblioteca", "23/09/2020")
    biblioteca.addLivro(Livro("LIV001", "The Man In The High Castle", "Philip K. Dick", 1962, 130.0, 10.0))
    biblioteca.addLivro(Livro("LIV002", "Zen e a Arte da Manutenção de Motocicletas", "Robert M. Pirsig", 1984, 70.0, 7.0))
    biblioteca.addLivro(Livro("LIV003", "A Revolta de Atlas", "Ayn Rand", 1957, 68.0, 7.0))
    biblioteca.addLivro(Livro("LIV001", "Mindset", "Carol Dweck", 2006, 35.0, 5.0)) // Código duplicado

    val colecao = Colecao("COL001", "Coleção Vagalume", "23/09/2020")
    colecao.addLivro(Livro("LIV003", "A Turma da Rua Quinze", "Marçal Aquino", 1990, 30.0, 5.0))
    colecao.addLivro(Livro("LIV004", "O Rapto do Garoto de Ouro", "Marcos Rey", 1982, 33.0, 5.0))
    colecao.addLivro(Livro("LIV005", "O Mistério do Cinco Estrelas", "Marcos Rey", 1981, 29.0, 5.0))
    colecao.addLivro(Livro("LIV006", "O Escaravelho do Diabo", "Lúcia Machado de Almeida", 1974, 31.0, 5.0))

    biblioteca.addColecao(colecao)

    biblioteca.consultarLivros() // Consulta vazia retorna todos
    biblioteca.consultarLivros("LIV001") // Consulta por código
    biblioteca.consultarLivros("O Escaravelho do Diabo") // Consulta por título
    biblioteca.consultarLivros("Harry Potter") // Consulta sem resultados

    biblioteca.consultarColecoes() // Consulta vazia retorna todos
    biblioteca.consultarColecoes("Coleção Senhor dos Anéis") // Consulta sem resultados

    val funcionario = Funcionario("Eduardo","43432432-2")
    val cliente = Cliente("Cesar","65765765-5")

    biblioteca.alugarLivro("LIV002", funcionario, cliente)
    biblioteca.venderColecao("COL001", funcionario, cliente)

    biblioteca.consultarLivros() // Consulta vazia retorna todos
    biblioteca.verificarEstoque() // Relatório de Estoque
}