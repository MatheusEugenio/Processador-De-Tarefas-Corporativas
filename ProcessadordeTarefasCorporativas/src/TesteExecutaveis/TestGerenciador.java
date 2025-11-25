package TesteExecutaveis;

import Gerenciador.GerenciadorDeTarefas;
import ObjetoDeManipulacao.Tarefa;

public class TestGerenciador {
    public static void main(String[] args) {
        GerenciadorDeTarefas  ger = new GerenciadorDeTarefas();

        try {
            Tarefa tarefa1 = new Tarefa("Beber Água", "Dia", "Baixa");
            Tarefa tarefa2 = new Tarefa("Fazer Compra", "Dia", "Baixa");
            Tarefa tarefa3 = new Tarefa("Lavar o carro", "Tarde", "Média");
            Tarefa tarefa4 = new Tarefa("Sair com a nega", "Noturna", "Alta");

            ger.add(tarefa1);
            ger.add(tarefa2);
            ger.add(tarefa3);

            ger.exibirLista();

            ger.remove(tarefa1);

            ger.exibirLista();
        } catch (Exception e) {
            System.out.print("ERRO GERAL: "+e.getMessage());
        }
    }
}
