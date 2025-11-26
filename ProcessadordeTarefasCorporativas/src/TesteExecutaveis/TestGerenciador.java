package TesteExecutaveis;

import Exceptions.TarefaInvalidException;
import Gerenciador.GerenciadorDeTarefas;
import ObjetoDeManipulacao.Tarefa;

public class TestGerenciador {
    public static void main(String[] args) {


        try {
            GerenciadorDeTarefas ger = new GerenciadorDeTarefas("arquivo_test.txt");

            Tarefa tarefa1 = new Tarefa("Beber Água", "Dia", "Baixa");
            Tarefa tarefa2 = new Tarefa("Fazer Compra", "Dia", "Baixa");
            Tarefa tarefa3 = new Tarefa("Lavar o carro", "Tarde", "Média");
            Tarefa tarefa4 = new Tarefa("Cortar o Cabelo", "Tarde", "Média");
            Tarefa tarefa5 = new Tarefa("Sair com a nega", "Noturna", "Alta");
            Tarefa tarefa6 = new Tarefa("Estudar para prova", "Noturna", "Alta");

            ger.add(tarefa1);
            ger.add(tarefa2);
            ger.add(tarefa3);
            ger.add(tarefa4);
            ger.add(tarefa5);
            ger.add(tarefa6);

            ger.exibirLista();

            ger.add(tarefa5);
            ger.add(tarefa4);
            ger.add(tarefa4);

            ger.exibirLista();

            ger.remove(tarefa1);
            ger.remove(tarefa2);

            ger.exibirLista();

            ger.add(tarefa1);

            ger.exibirLista();

            //ger.remove("Fazer Compra");
            //ger.remove(" ");
            //ger.add(null);
            Tarefa tarefa7 = new Tarefa(" ", "", "  ");
            ger.add(tarefa7);

            ger.exibirLista();

        } catch (TarefaInvalidException e){
            System.out.println("ERRO DE TAREFA: "+ e.getMessage());
        }
        catch (Exception e) {
            System.out.print("ERRO GERAL: "+e.getMessage());
        }
    }
}
