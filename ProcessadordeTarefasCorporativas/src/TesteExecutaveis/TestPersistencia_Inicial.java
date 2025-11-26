package TesteExecutaveis;

import Gerenciador.GerenciadorDeTarefas;
import ObjetoDeManipulacao.Tarefa;

public class TestPersistencia_Inicial {
    public static void main(String[] args) {

        try {
            GerenciadorDeTarefas ger = new GerenciadorDeTarefas("arquivo_persiste.txt");

            Tarefa tarefa1 = new Tarefa("Beber Água", "Dia", "Baixa");
            Tarefa tarefa2 = new Tarefa("Fazer Compra", "Dia", "Baixa");
            Tarefa tarefa3 = new Tarefa("Lavar o carro", "Tarde", "Média");
            Tarefa tarefa4 = new Tarefa("Lavar a moto", "Tarde", "Média");
            Tarefa tarefa5 = new Tarefa("Sair", "Noturna", "Alta");
            Tarefa tarefa6 = new Tarefa("Comprar a janta", "Noturna", "Alta");

            ger.exibirLista();

            ger.add(tarefa1);
            ger.add(tarefa2);
            ger.add(tarefa3);
            ger.add(tarefa4);
            ger.add(tarefa5);
            ger.add(tarefa6);

            ger.exibirLista();

            ger.remove(tarefa1);
            ger.remove(tarefa3);

            ger.exibirLista();
            /*

             */


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
