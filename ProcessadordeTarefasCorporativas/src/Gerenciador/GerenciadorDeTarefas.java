package Gerenciador;

import Exceptions.TarefaInvalidException;
import FerramentaPersistencia.Persistencia;
import ObjetoDeManipulacao.Tarefa;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorDeTarefas {

    private Set<Tarefa> listaDeTarefas = new HashSet<>();
    private Persistencia<Tarefa> persistencia;

    public GerenciadorDeTarefas(){
        this.persistencia = new Persistencia<>();
    }

    public void add(Tarefa tarefa) throws NullPointerException{
        if (tarefa == null){
            throw new NullPointerException("Tarefa passada como referência é nula!");
        }

        this.listaDeTarefas.add(tarefa);
    }

    public void remove(Tarefa tarefa) throws TarefaInvalidException, NullPointerException{
        if (tarefa == null ) {
            throw new NullPointerException("Tarefa de referência nula!");
        }

        if (!listaDeTarefas.contains(tarefa)) {
            throw new TarefaInvalidException("Tarefa de referência não existe dentro da lista!");
        }

        this.listaDeTarefas.remove(tarefa);
    }

    public void remove(String nomeTarefa) throws TarefaInvalidException{
        if (nomeTarefa.isBlank()){
            throw new TarefaInvalidException("Referência de Tarefa inválida!");
        }

        boolean removeu = this.listaDeTarefas.removeIf(t -> t.getTitulo().equalsIgnoreCase(nomeTarefa));

        if (removeu) {
            System.out.println("Tarefa: \""+nomeTarefa+ "\" removida com sucesso!");
        }else {
            throw new TarefaInvalidException("Referência de Tarefa inválida!");
        }
    }

    public void exibirLista(){
        if (this.listaDeTarefas.isEmpty()) {
            System.out.println("Tarefa Lista vazia!");
            return;
        }

        this.listaDeTarefas.forEach(System.out::println);
    }

    public void acessarTarefa(Tarefa tarefa)throws TarefaInvalidException{ //lançar exception personalizada

    }

    public void acessarTarefa(String nomeTarefa) throws TarefaInvalidException{ //lançar exception personalizada

    }
}
