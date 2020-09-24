class Biblioteca (
    val nome: String,
    val dataCriacao: String
) {
    val livros: MutableMap<String, Livro> = mutableMapOf()
    val colecoes: MutableMap<String, Colecao> = mutableMapOf()
    var statusMap: Map<Char, String> = mapOf(
        'D' to "Disponível",
        'A' to "Alugado",
        'V' to "Vendido"
    )

    fun addLivro(livro: Livro) {
        if(!livros.containsKey(livro.codigo)){
            livros.put(livro.codigo, livro)
            println("Livro '${livro.titulo}' adicionado à biblioteca.")
        }else{
            println("Já existe um livro com o código '${livro.codigo}'.")
        }
    }

    fun addColecao(colecao: Colecao) {
        if(!colecoes.containsKey(colecao.codigo)){
            colecoes.put(colecao.codigo, colecao)
            colecao.livros.forEach { t, u ->
                addLivro(u)
            }
            println("Coleção '${colecao.nome}' adicionada à biblioteca.")
        }else{
            println("Já existe uma coleção com o código '${colecao.codigo}'.")
        }
    }

    fun consultarLivros(entrada: String = "") {
        var resultado: Map<String, Livro>
        if(entrada == ""){
            resultado = livros
        }else {
            resultado = livros.filter { it.key == entrada || it.value.titulo == entrada }
        }
        if(resultado.size > 0) {
            println("LIVROS ENCONTRADOS:")
            resultado.forEach { t, u ->
                println("$t - ${u.titulo} - Status: ${statusMap.getValue(u.status)}")
            }
        }else{
            println("Livro não encontrado.")
        }

    }

    fun consultarColecoes(entrada: String = "") {
        var resultado: Map<String, Colecao>
        if(entrada == ""){
            resultado = colecoes
        }else {
            resultado = colecoes.filter { it.key == entrada || it.value.nome == entrada }
        }
        if(resultado.size > 0) {
            println("COLEÇÕES ENCONTRADOS:")
            resultado.forEach { t, u ->
                println("$t - ${u.nome} - Status: ${statusMap.getValue(u.status)}")
            }
        }else{
            println("Coleção não encontrada.")
        }
    }

    fun alugarLivro(codigo: String, funcionario: Funcionario, cliente: Cliente){
        val livro = livros.getValue(codigo)
        livro?.let {
            if(livro.status == 'D'){
                livro.status = 'A'
                funcionario.livrosAlugados.put(livro.codigo, livro)
                cliente.livrosAlugados.put(livro.codigo, livro)
                println("Livro '${livro.titulo}' alugado por ${funcionario.nome} para ${cliente.nome}")
            }else{
                println("O livro '${livro.titulo}' não está disponível para locação.")
            }
        } ?: println("O livro não existe.")
    }

    fun alugarColecao(codigo: String, funcionario: Funcionario, cliente: Cliente){
        val colecao = colecoes.getValue(codigo)
        colecao?.let {
            if(colecao.status == 'D'){
                colecao.status = 'A'
                colecao.livros.forEach { t, u ->
                    alugarLivro(t, funcionario, cliente)
                }
                println("Coleção '${colecao.nome}' alugada por ${funcionario.nome} para ${cliente.nome}")
            }else{
                println("A coleção '${colecao.nome}' não está disponível para locação.")
            }
        } ?: println("A coleção não existe.")
    }

    fun venderLivro(codigo: String, funcionario: Funcionario, cliente: Cliente) {
        val livro = livros[codigo]
        livro?.let {
            if(livro.status == 'D'){
                livro.status = 'V'
                funcionario.livrosVendidos.put(livro.codigo, livro)
                cliente.livrosComprados.put(livro.codigo, livro)
                println("Livro '${livro.titulo}' vendido por ${funcionario.nome} para ${cliente.nome}")
            }else{
                println("O livro '${livro.titulo}' não está disponível para venda.")
            }
        } ?: println("O livro não existe.")
    }

    fun venderColecao(codigo: String, funcionario: Funcionario, cliente: Cliente){
        val colecao = colecoes[codigo]
        colecao?.let {
            if(colecao.status == 'D'){
                colecao.status = 'A'
                colecao.livros.forEach { t, u ->
                    venderLivro(t, funcionario, cliente)
                }
                println("Coleção '${colecao.nome}' vendida por ${funcionario.nome} para ${cliente.nome}")
            }else{
                println("A coleção '${colecao.nome}' não está disponível para venda.")
            }
        } ?: println("A coleção não existe.")
    }

    fun verificarEstoque(){
        val disponiveis = livros.filter { it.value.status == 'D' }
        val alugados = livros.filter { it.value.status == 'A' }
        val vendidos = livros.filter { it.value.status == 'V' }
        var vendaTotal = 0.0
        vendidos.forEach { t, u ->
            vendaTotal += u.precoVenda
        }
        println("RELATÓRIO DE VENDAS:")
        println("Livros disponíveis: ${disponiveis.size}")
        println("Livros alugados: ${alugados.size}")
        println("Livros vendidos: ${vendidos.size}")
        println("Valor total das vendas: R$$vendaTotal")
    }

}
