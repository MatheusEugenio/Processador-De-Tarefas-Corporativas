package Gerenciador;

import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorDeTarefas {

    private Set<Tarefa> listaDeTarefas = new HashSet<>();

    void add(Tarefa tarefa) throws TarefaInvalidException{ //lançar exception personalizada

        try {
            this.listaDeTarefas.add(tarefa);
        }catch (Exception e){
            throw new TarefaInvalidException("A tarefa passada como parâmetro é inválida");
        }
    }

    void remove(Tarefa tarefa){ //lançar exception personalizada
        this.listaDeTarefas.remove(tarefa);
    }

    void remove(String nomeTarefa){
        this.listaDeTarefas.removeIf(t -> t.getTitulo().equalsIgnoreCase(nomeTarefa));
    }

    void exibirLista(){
        this.listaDeTarefas.forEach(System.out::println);
    }

    void acessarTarefa(Tarefa tarefa){ //lançar exception personalizada

    }

    void acessarTarefa(String nomeTarefa){ //lançar exception personalizada

    }
}
