package TesteExecutaveis.Main;

import Exceptions.TarefaInvalidException;
import Gerenciador.GerenciadorDeTarefas;
import ObjetoDeManipulacao.Tarefa;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestePrincipal {
    public static void main(String[] args) {

        String endereco_do_arquivo = "arquitetura.txt";

        try {
            GerenciadorDeTarefas ger = new GerenciadorDeTarefas(endereco_do_arquivo);

            Tarefa tarefa1 = new Tarefa("Beber Água", "Dia", "Baixa");
            Tarefa tarefa2 = new Tarefa("Fazer Compra", "Dia", "Baixa");
            Tarefa tarefa3 = new Tarefa("Lavar o carro", "Tarde", "Média");
            Tarefa tarefa4 = new Tarefa("Lavar a moto", "Tarde", "Média");
            Tarefa tarefa5 = new Tarefa("Sair", "Noturna", "Alta");
            Tarefa tarefa6 = new Tarefa("Comprar a janta", "Noturna", "Alta");

            ger.add(tarefa1);
            ger.add(tarefa3);
            ger.add(tarefa2);
            ger.add(tarefa4);
            ger.add(tarefa6);
            ger.add(tarefa5);

            ger.exibirTarefas();

            ger.getTarefas_Urgentes();

            System.out.println("\n");
            Map<String, List<Tarefa>> subdivisao_das_categorias = ger.agrupamento();

            subdivisao_das_categorias.forEach((categoria,tarefas) -> {
                System.out.println(categoria.toUpperCase());
                tarefas.forEach(System.out::println);
                System.out.println();
            });

        } catch (IOException e) {
            System.out.println("ERRO NA MANIPULAÇÃO DE ARQUIVOS .TXT: "+e.getMessage());
        } catch (TarefaInvalidException e) {
            System.out.println("ERRO COM NA MANIPULAÇÃO DE TAREFAS: "+e.getMessage());
        }
    }
}
