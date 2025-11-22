package Gerenciador;

import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;

import java.util.*;

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
    }

    public void remove(Tarefa tarefa) throws TarefaInvalidException, ErrorGeralExeception{
        if (tarefa == null ) {
            throw new ErrorGeralExeception("Tarefa de referência nula!");
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

    public Tarefa acessarTarefa(Tarefa tarefa)throws ErrorGeralExeception,TarefaInvalidException{
        if (tarefa == null ) {
            throw new ErrorGeralExeception("Tarefa de referência nula!");
        }

        if (!listaDeTarefas.contains(tarefa)) {
            throw new TarefaInvalidException("Impossível de acessar, a tarefa de referência não existe dentro da lista!");
        }else {
            return tarefa;
        }
    }

    public Tarefa acessarTarefa(String nomeTarefa) throws TarefaInvalidException{
        if (nomeTarefa.isBlank()){
            throw new TarefaInvalidException("Referência de Tarefa inválida!");
        }

        return this.listaDeTarefas.stream()
                .filter(t -> t.getTitulo().equalsIgnoreCase(nomeTarefa))
                .findFirst()
                .orElseThrow(() -> new TarefaInvalidException("Tarefa '" + nomeTarefa + "' não encontrada."));
    }
}
