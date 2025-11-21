package TesteExecutaveis;

import Exceptions.TarefaInvalidException;
import Gerenciador.GerenciadorDeTarefas;
import ObjetoDeManipulacao.Tarefa;

public class ExecuteTest {
    public static void main(String[] args) {
        GerenciadorDeTarefas  ger = new GerenciadorDeTarefas();

        try {
            Tarefa tarefa = null;
            ger.add(tarefa);

/*      } catch (TarefaInvalidException e) {
//            System.out.println("ERRO: "+ e.getMessage());
      */} catch (Exception e) {
            System.out.print("ERRO GERAL: ");
            e.printStackTrace();
        }
    }
}
