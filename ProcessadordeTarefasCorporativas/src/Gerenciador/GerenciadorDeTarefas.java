package Gerenciador;
import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;

import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorDeTarefas {

    private List<Tarefa> listaDeTarefas;
    private Set<String> categoriasUnicas;
    private Queue<Tarefa> tarefasPorUrgencia;

    public GerenciadorDeTarefas(){
        this.listaDeTarefas = new ArrayList<>();
        this.categoriasUnicas = new HashSet<>();
        this.tarefasPorUrgencia = new PriorityQueue<>();
    }

    public void add(Tarefa tarefa) throws ErrorGeralExeception {
        if (tarefa == null){
            throw new ErrorGeralExeception("Tarefa passada como referência é nula!");
        }

        this.listaDeTarefas.add(tarefa);

        if (!categoriasUnicas.contains(tarefa.getCategoria())){
            this.categoriasUnicas.add(tarefa.getCategoria());
        }

        ordenarPorCategoria();
        ordenarPorUrgencia();
    }

    public void remove(Tarefa tarefa) throws TarefaInvalidException, ErrorGeralExeception{
        if (tarefa == null ) {
            throw new ErrorGeralExeception("Tarefa de referência nula!");
        }

        if (!listaDeTarefas.contains(tarefa)) {
            throw new TarefaInvalidException("Tarefa de referência não existe dentro da lista!");
        }

        this.listaDeTarefas.remove(tarefa);

        ordenarPorCategoria();
        ordenarPorUrgencia();
    }

    public void remove(String nomeTarefa) throws TarefaInvalidException{
        if (nomeTarefa.isBlank()){
            throw new TarefaInvalidException("Referência de Tarefa inválida!");
        }

        boolean removeu = this.listaDeTarefas.removeIf(t -> t.getTitulo().equalsIgnoreCase(nomeTarefa));
        ordenarPorCategoria();
        ordenarPorUrgencia();

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

        this.listaDeTarefas.stream()
                .sorted()
                .forEach(System.out::println);
    }

    private void ordenarPorUrgencia(){
        this.tarefasPorUrgencia = this.listaDeTarefas.stream()
                                        .sorted()
                                        .collect(Collectors.toCollection(LinkedList::new));
    }

    private void ordenarPorCategoria() {
       this.categoriasUnicas = this.listaDeTarefas.stream()
                                .map(Tarefa::getCategoria)
                                .collect(Collectors.toSet());
    }
}
