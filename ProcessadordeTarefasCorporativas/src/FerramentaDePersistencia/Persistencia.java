package FerramentaDePersistencia;

import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class Persistencia<T>{

    private String caminho;

    public Persistencia(String caminho){
        this.caminho = caminho;
    }

    public void salvar(List<T> tarefas) throws TarefaInvalidException,IOException {

        if (tarefas.isEmpty()){
            throw new TarefaInvalidException("Lista vazia, nada para salvar!");
        }

        tarefas.sort(null);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.caminho))){
            writer.newLine();

            for (T tarefa : tarefas) {
                writer.write(tarefa.toString() +" -> "+ LocalDate.now());
                writer.newLine();
            }
            writer.flush();
        }

    }

    public void carregar(List<Tarefa> lista_principal, Queue<Tarefa> lista_de_prioridade) throws IOException {
        File file = new  File(this.caminho);
        if (!file.exists()){
            if (file.createNewFile()) {
                return;
            }
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(this.caminho))){
            String linha;
            while((linha = reader.readLine()) != null){
                if (linha.trim().isEmpty()){
                    continue;
                }

                try{
                    Optional<Tarefa> tarefa_Optional = parseTarefa(linha);
                    tarefa_Optional.ifPresent(tarefa_nova -> {
                        lista_principal.add(tarefa_nova);
                        lista_de_prioridade.add(tarefa_nova);
                    });
                }catch (TarefaInvalidException e){
                    System.out.println("Erro ao passar a Tarefa para a lista!");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar as Tarefas!");
        }
    }

    private Optional<Tarefa> parseTarefa(String linha) throws TarefaInvalidException,ErrorGeralExeception{
        if (linha == null || linha.isBlank()){
            return Optional.empty();
        }

        int indice_do_separador = linha.indexOf("->");

        if (indice_do_separador != -1){
            linha = linha.substring(0,indice_do_separador);
        }

        String[] linha_quebrada = linha.split("\\|");

        if (linha_quebrada.length < 3){
            throw new ErrorGeralExeception("Linha do arquivo mal formatada: "  + linha);
        }

        String titulo_da_tarefa_quebrada = linha_quebrada[0].trim();
        String categoria_da_tarefa_quebrada = linha_quebrada[1].trim();
        String prioridade_da_tarefa_quebrada = linha_quebrada[2].trim();

        if (!titulo_da_tarefa_quebrada.isEmpty() && !categoria_da_tarefa_quebrada.isEmpty() && !prioridade_da_tarefa_quebrada.isEmpty()) {
            return Optional.of(new Tarefa(titulo_da_tarefa_quebrada, categoria_da_tarefa_quebrada, prioridade_da_tarefa_quebrada));
        }

        return Optional.empty();
    }

}
