package Gerenciador;
import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import FerramentaDePersistencia.Persistencia;
import ObjetoDeManipulacao.Tarefa;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorDeTarefas {

    private List<Tarefa> listaDeTarefas;
    private Set<String> set_de_Categorias;
    private Queue<Tarefa> tarefasPorPrioridade;
    private Persistencia<Tarefa> persistencia;

    public GerenciadorDeTarefas(String caminho) throws IOException {
        this.listaDeTarefas = new ArrayList<>();
        this.set_de_Categorias = new HashSet<>();
        this.tarefasPorPrioridade = new PriorityQueue<>();

        this.persistencia = new Persistencia<>(caminho);
        persistencia.carregar(this.listaDeTarefas);
    }

    public void add(Tarefa tarefa) throws ErrorGeralExeception, IOException, TarefaInvalidException {
        if (tarefa == null){
            throw new ErrorGeralExeception("Tarefa passada como referência é nula!");
        }

        if (tarefa.getTitulo().isBlank() && tarefa.getCategoria().isBlank() && tarefa.getPrioridade().isBlank()){
            throw new ErrorGeralExeception("Tarefa passada como referência é totalmente vazia!");
        }

        this.listaDeTarefas.add(tarefa);
        this.set_de_Categorias.add(tarefa.getCategoria());
        adicionar_tarefa_sem_repeticao_e_ordena_por_prioridade(tarefa);

        persistencia.salvar(this.listaDeTarefas);
    }

    public void remove(Tarefa tarefa) throws TarefaInvalidException, ErrorGeralExeception, IOException {
        if (tarefa == null ) {
            throw new ErrorGeralExeception("Tarefa de referência nula!");
        }

        if (!listaDeTarefas.contains(tarefa)) {
            throw new TarefaInvalidException("Tarefa de referência não existe dentro da lista!");
        }

        this.listaDeTarefas.remove(tarefa);
        this.tarefasPorPrioridade.remove(tarefa);
        atualizacao_da_Lista_de_Categoria();

        persistencia.salvar(this.listaDeTarefas);

        System.out.println("\nTarefa: \""+tarefa.getTitulo()+"\" removida com sucesso!");
    }

    public void remove(String nomeTarefa) throws TarefaInvalidException, IOException {
        if (nomeTarefa.isBlank()){
            throw new TarefaInvalidException("Referência de Tarefa inválida!");
        }

        boolean existe_na_lista = this.listaDeTarefas.stream()
                .map(Tarefa::getTitulo)
                .anyMatch(t -> t.equalsIgnoreCase(nomeTarefa));

        if (!existe_na_lista) {
            throw new TarefaInvalidException("Tarefa de referência não existe dentro da lista!");
        }

        // resgata a tarefa que vai ser removida
        Tarefa tarefa_alvo = this.listaDeTarefas.stream()
                .filter(t -> t.getTitulo().equalsIgnoreCase(nomeTarefa))
                .findFirst()
                .orElseThrow(null);

        if (tarefa_alvo != null) {
            this.listaDeTarefas.remove(tarefa_alvo);
            this.tarefasPorPrioridade.remove(tarefa_alvo);
            atualizacao_da_Lista_de_Categoria();

            persistencia.salvar(this.listaDeTarefas);

            System.out.println("\nTarefa: \""+nomeTarefa+ "\" removida com sucesso!");
        }else {
            throw new TarefaInvalidException("Referência de Tarefa inválida!");
        }
    }

    public void exibirLista(){
        if (this.listaDeTarefas.isEmpty()) {
            System.out.println("\nLista de Tarefas vazia!");
            return;
        }

        System.out.println("\n");
        System.out.println("=== Lista de Tarefas ===");
        this.tarefasPorPrioridade.stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("========================");
    }

    private void atualizacao_da_Lista_de_Categoria() {
       this.set_de_Categorias = this.listaDeTarefas.stream()
                                .map(Tarefa::getCategoria)
                                .collect(Collectors.toSet());
    }

    private void adicionar_tarefa_sem_repeticao_e_ordena_por_prioridade(Tarefa tarefa) {
        if (tarefa != null && !this.tarefasPorPrioridade.contains(tarefa)) {
            this.tarefasPorPrioridade.add(tarefa);
            System.out.println("\nTarefa não existente adicionada!");
            return;
        }
        System.out.println("\nTarefa já existente!");
    }
}
