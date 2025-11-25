package Gerenciador;
import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;

import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorDeTarefas {

    private List<Tarefa> listaDeTarefas;
    private Set<String> categoriasUnicas;
    private Queue<Tarefa> tarefasPorPrioridade;

    public GerenciadorDeTarefas(){
        this.listaDeTarefas = new ArrayList<>();
        this.categoriasUnicas = new HashSet<>();
        this.tarefasPorPrioridade = new PriorityQueue<>();
    }

    public void add(Tarefa tarefa) throws ErrorGeralExeception {
        if (tarefa == null){
            throw new ErrorGeralExeception("Tarefa passada como referência é nula!");
        }

        this.listaDeTarefas.add(tarefa);
        this.categoriasUnicas.add(tarefa.getCategoria());
        this.tarefasPorPrioridade.add(tarefa);
    }

    public void remove(Tarefa tarefa) throws TarefaInvalidException, ErrorGeralExeception{
        if (tarefa == null ) {
            throw new ErrorGeralExeception("Tarefa de referência nula!");
        }

        if (!listaDeTarefas.contains(tarefa)) {
            throw new TarefaInvalidException("Tarefa de referência não existe dentro da lista!");
        }

        this.listaDeTarefas.remove(tarefa);
        this.tarefasPorPrioridade.remove(tarefa);
        atualizacao_da_Lista_de_Categoria();
    }

    public void remove(String nomeTarefa) throws TarefaInvalidException{
        if (nomeTarefa.isBlank()){
            throw new TarefaInvalidException("Referência de Tarefa inválida!");
        }

        Tarefa tarefa_alvo = this.listaDeTarefas.stream()
                .filter(t -> t.getTitulo().equalsIgnoreCase(nomeTarefa))
                .findFirst()
                .orElseThrow(null);

        if (tarefa_alvo != null) {
            this.listaDeTarefas.remove(tarefa_alvo);
            this.tarefasPorPrioridade.remove(tarefa_alvo);
            atualizacao_da_Lista_de_Categoria();
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

        this.tarefasPorPrioridade.forEach(System.out::println);
    }

    private void atualizacao_da_Lista_de_Categoria() {
       this.categoriasUnicas = this.listaDeTarefas.stream()
                                .map(Tarefa::getCategoria)
                                .collect(Collectors.toSet());
    }
}
