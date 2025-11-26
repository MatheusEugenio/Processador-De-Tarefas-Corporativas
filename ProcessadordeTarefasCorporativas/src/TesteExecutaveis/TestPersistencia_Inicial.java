package TesteExecutaveis;

import Gerenciador.GerenciadorDeTarefas;
import ObjetoDeManipulacao.Tarefa;

public class TestPersistencia_Inicial {
    public static void main(String[] args) {

        try {
            GerenciadorDeTarefas ger = new GerenciadorDeTarefas("arquivo_test.txt");

            Tarefa tarefa1 = new Tarefa("Beber Água", "Dia", "Baixa");
            Tarefa tarefa2 = new Tarefa("Fazer Compra", "Dia", "Baixa");
            Tarefa tarefa3 = new Tarefa("Lavar o carro", "Tarde", "Média");
            Tarefa tarefa4 = new Tarefa("Sair com a nega", "Noturna", "Alta");

            ger.exibirLista();

            ger.add(tarefa1);
            ger.add(tarefa2);
            ger.add(tarefa3);
            ger.add(tarefa4);

            ger.exibirLista();

            ger.remove(tarefa1);

            String nome_da_Tarefa_a_ser_removida = "Fazer Compra";
            ger.remove(nome_da_Tarefa_a_ser_removida);

            ger.exibirLista();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
