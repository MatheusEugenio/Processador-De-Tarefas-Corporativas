package Gerenciador;
import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;

import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorDeTarefas {

    private List<Tarefa> listaDeTarefas;
    private Set<String> set_de_Categorias;
    private Queue<Tarefa> tarefasPorPrioridade;

    public GerenciadorDeTarefas(){
        this.listaDeTarefas = new ArrayList<>();
        this.set_de_Categorias = new HashSet<>();
        this.tarefasPorPrioridade = new PriorityQueue<>();
    }

    public void add(Tarefa tarefa) throws ErrorGeralExeception {
        if (tarefa == null){
            throw new ErrorGeralExeception("Tarefa passada como referência é nula!");
        }

        this.listaDeTarefas.add(tarefa);
        this.set_de_Categorias.add(tarefa.getCategoria());
        this.tarefasPorPrioridade.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso!");
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
        System.out.println("Tarefa removida com sucesso!");
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
            System.out.println("\nTarefa Lista vazia!");
            return;
        }

        System.out.println("\n");
        this.tarefasPorPrioridade.forEach(System.out::println);
    }

    private void atualizacao_da_Lista_de_Categoria() {
       this.set_de_Categorias = this.listaDeTarefas.stream()
                                .map(Tarefa::getCategoria)
                                .collect(Collectors.toSet());
    }
}
