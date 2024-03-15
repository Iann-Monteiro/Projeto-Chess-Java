/*
 Implemente um exercício que simule as operações de:
a. Cadastro de Clientes (Inserir, Buscar e Deletar);
b. Cadastro de Produtos (Inserir, Buscar e Deletar);
c. Estoque;
d. Venda;
e. Relatório de Clientes Cadastrados;
f. Relatório de Total de Vendas.
2. Cada grupo deverá realizar para um segmento especifico.
Observação: Enviar o nome do grupo e segmento no grupo do WhatsApp. Lembrando que o
trabalho poderá ser realizado em grupo de no máximo duas pessoas.

*/

import 'dart:io';

List<Map<String, dynamic>> listaDeClientes = [{'Nome': 'Ana','Idade':'20',"Cpf":'4235142314'},{'Nome': 'Wilian','Idade':'18','Cpf':'3423533232'}];
List<Map<String, dynamic>> listaDeProdutos = [{'Nome':'Garrafa','preco':'10.00','Quantidade':'2'},{'Nome':'Geladeira','preço':'2500.00','Quantidade':'2'}];
List<Map<String, dynamic>> listaDeVendas = [{'Nome':'Garrafa','PrecoTotal':'20.00', 'Quantidade':'2'}];

void main(List<String> args) {
  MenuPricipal();
}

MenuPricipal(){
  int? resposta;
  while(resposta != 7) {
    print("Digite a opção desejada:");
    print('1-Clientes');
    print('2-Produtos');
    print('3-Estoque');
    print('4-Venda');
    print('5-Controle de clientes');
    print('6-controle de Vendas');
    print('7-Sair');
    resposta = int.parse(stdin.readLineSync()!);

    switch (resposta) {
      case 1:
        sistemadeClientes();
        break;
      case 2:
        sistemadeProdutos();
        break;
      case 3:
        controleDeEstoque();
        break;
      case 4:
        registrarVenda();
        break;
      case 5:
        relatorioClientesCadastrados();
        break;
      case 6:
        relatorioTotalVendas();
        break;
      case 7:
        print("Sistema de cadastros cancelado!");
        break;
      default:
        print("Opção inválida! Tente novamente!");
        return MenuPricipal();
    }
  }
}

void sistemadeClientes(){
  print('BEM VINDO AO SISTEMA DE CADASTRO DE CLIENTES!!!');

  while(true){
    print('Insira a opção desejada: \n1- Inserir \n2- Buscar \n3- Deletar  \n4- Sair');
    int? resposta = int.parse(stdin.readLineSync()!);
    if (resposta == 1) {
      inserirCliente();
    }else if (resposta == 2){
      print('Digite o nome do cliente:');
      String? nomeCliente = stdin.readLineSync();
      buscarCliente(nomeCliente);
    }else if(resposta == 3){
      print('Nome do cliente que será deletado:');
      String? nome = stdin.readLineSync();
      if(nome!=null){
        deletar(listaDeClientes,nome);
      }
      print(listaDeClientes);
    }else if(resposta == 4){
      print("Sistema de cadastro cancelado");
      break;
    }
  }
}

void inserirCliente() {
  Map<String, dynamic> cadastro = {};

  print('--------------------------');

  print('Digite o nome do Cliente: ');
  cadastro['Nome'] = stdin.readLineSync();

  print('Digite a idade do Cliente: ');
  cadastro['Idade'] = stdin.readLineSync();

  print('Digite a cidade do Cliente: ');
  cadastro['Cidade'] = stdin.readLineSync();

  print('Digite o estado do Cliente: ');
  cadastro['Estado'] = stdin.readLineSync();

  print('Digite o Cpf do Cliente: ');
  cadastro['Cpf'] = stdin.readLineSync();

  listaDeClientes.add(cadastro);
  relatorioClientesCadastrados();
}

void deletar(List<Map<String, dynamic>> lista, String nome) {
  lista.removeWhere((element) => element['Nome'] == nome);
}

void sistemadeProdutos(){
  while(true){
    print('BEM VINDO AO SISTEMA DE CADASTRO DE PRODUTOS!!!');
    print('Insira a opção desejada: \n1-Inserir \n2-Buscar \n3-Deletar \n4-Sair');
    int? resposta = int.parse(stdin.readLineSync()!);


    if (resposta == 1) {
      inserirProdutos();
    } else if (resposta == 2) {
      print('Digite o nome do produto:');
      String? nomeproduto = stdin.readLineSync();
      buscarProduto(nomeproduto);
    } else if (resposta == 3) {
      print('Digite o nome do Produto que será deletado:');
      String? nome = stdin.readLineSync();
      if (nome != null) {
        deletar(listaDeProdutos, nome);
      }
      print(listaDeProdutos);
    } else if (resposta == 4) {
      print("Saindo");
      break;
    }
  }
}

void inserirProdutos() {
  Map<String,dynamic>Produtos={};

  print('*** INSERIR---PRODUTOS ***');

  print('Digite o nome do produto:');
  Produtos['Nome'] = stdin.readLineSync();

  print('Digite o preço do produto:');
  Produtos['preco'] = stdin.readLineSync();

  print('Digite a quantidade de produtos:');
  Produtos['Quantidade'] = stdin.readLineSync();

  listaDeProdutos.add(Produtos);
  print(listaDeProdutos);
}

void controleDeEstoque() {
  print('*** Controle de Estoque ***');

  print('Nome do produto para verificar no estoque?');
  String? produto = stdin.readLineSync();

  try{
    print(listaDeProdutos.firstWhere((item) => item['Nome'] == produto));
  }catch(e) {
    print('Produto não encontrado');
  }
}


void registrarVenda() {
  Map<String,dynamic>vendas={};
  print('*** Controle de Vendas ***');
  print('Digite o nome do produto vendido:');
  String? produto = stdin.readLineSync();

  var produtoEncontrado;
  try{
    produtoEncontrado = listaDeProdutos.firstWhere((item) => item['Nome'] == produto);
    String prc = produtoEncontrado['preco'];

    String qtdProd = produtoEncontrado['Quantidade'];
    int quantidadeProduto = int.parse(qtdProd);

    print('Digite a quantidade vendida:');
    String? qtd = stdin.readLineSync();

    if(qtd!=null){
      double precoUnitario = double.parse(prc);
      int quantidade = int.parse(qtd);

      if(quantidade<=quantidadeProduto && quantidade != 0){
        double total = precoUnitario * quantidade;
        print('Total da venda: $total');

        quantidadeProduto -= quantidade;
        produtoEncontrado['Quantidade'] = quantidadeProduto.toString();

        vendas['Nome'] = produtoEncontrado['Nome'];
        vendas['PrecoTotal'] = total.toString();
        vendas['Quantidade'] = qtd;

        listaDeVendas.add(vendas);

        print('Venda registrada com sucesso!');
      }else{
        print("Quantidade insuficiente!!!");
      }
    }else{
      print("Quantidade Inválida!");
    }
  }catch(e) {
    print('Produto não encontrado!');
    sistemadeProdutos();
  }
}

void relatorioClientesCadastrados() {

  print('*** Relatório de Clientes Cadastrados ***');

  listaDeClientes.forEach((cliente) {
    print('Nome: ${cliente['Nome']}, Idade: ${cliente['Idade']}, CPF: ${cliente['Cpf']}');
  }
  );
}

void relatorioTotalVendas() {
  print('*** Relatório de Total de Vendas ***');


  print(listaDeVendas);
}

buscarCliente(String? nomeProcurado) {
  try{
    print(listaDeClientes.firstWhere((obj) => obj['Nome'] == nomeProcurado));
  }catch(e) {
    print('Cliente não encontrado');
  }
}

buscarProduto(String? nomeProduto){
  try{
    print(listaDeProdutos.firstWhere((item) => item['Nome'] == nomeProduto));
  }catch(e) {
    print('Produto não encontrado');
  }
}
