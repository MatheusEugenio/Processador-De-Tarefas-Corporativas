package Gerenciador;

import ObjetoDeManipulacao.Tarefa;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorDeTarefas {

    private Set<Tarefa> listaDeTarefas = new HashSet<>();

    void add(Tarefa tarefa){
        this.listaDeTarefas.add(tarefa);
    }

    void remove(Tarefa tarefa){
        this.listaDeTarefas.remove(tarefa);
    }

    void remove(String nomeTarefa){
        this.listaDeTarefas.removeIf(t -> t.getTitulo().equalsIgnoreCase(nomeTarefa));
    }

    void exibirLista(){
        this.listaDeTarefas.forEach(System.out::println);
    }

    void acessarTarefa(Tarefa tarefa){

    }

    void acessarTarefa(String nomeTarefa){

    }
}
