package TesteExecutaveis;

import Exceptions.TarefaInvalidException;
import Gerenciador.GerenciadorDeTarefas;
import ObjetoDeManipulacao.Tarefa;

public class ExecuteTest {
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
            ger.add(tarefa4);

            ger.exibirLista();

/*      } catch (TarefaInvalidException e) {
//            System.out.println("ERRO: "+ e.getMessage());
      */} catch (Exception e) {
            System.out.print("ERRO GERAL: ");
            e.printStackTrace();
        }
    }
}
