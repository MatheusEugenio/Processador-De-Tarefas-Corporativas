package FerramentaPersistencia;

import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;

import java.io.*;
import java.util.List;
import java.util.function.Function;

public class Persistencia<T>{

    private String caminho;

    public Persistencia(String caminho){
        this.caminho = caminho;
    }

    public void salvar(List<T> tarefas) throws TarefaInvalidException,IOException {

        if (tarefas.isEmpty()){
            throw new TarefaInvalidException("Lista vazia, nada para salvar!");
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.caminho, true))){
            for (T tarefa : tarefas) {
                writer.write(tarefa.toString());
                writer.newLine();
            }
            writer.flush();
        }

    }

    public void carregar(List<Tarefa> lista){
        try(BufferedReader reader = new BufferedReader(new FileReader(this.caminho))){

            String linha;
            while(!(linha = reader.readLine()).isEmpty()){
                    Tarefa tarefa_Nova = parseTarefa(linha);
                    lista.add(tarefa_Nova);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TarefaInvalidException e) {
            throw new RuntimeException(e);
        }
    }

    public void carregar(Function<String, T> conversor, List<Tarefa> lista){



    }

    private Tarefa parseTarefa(String linha) throws TarefaInvalidException,ErrorGeralExeception{
        if (linha == null || linha.isBlank()){
            return null;
        }

        String[] linha_quebrada = linha.split("\\|");

        if (linha_quebrada.length < 3){
            throw new ErrorGeralExeception("Linha do arquivo mal formatada: "  + linha);
        }

        String titulo_da_tarefa_quebrada = linha_quebrada[0].trim();
        String categoria_da_tarefa_quebrada = linha_quebrada[1].trim();
        String prioridade_da_tarefa_quebrada = linha_quebrada[2].trim();

        if (!titulo_da_tarefa_quebrada.isEmpty() && !categoria_da_tarefa_quebrada.isEmpty() && !prioridade_da_tarefa_quebrada.isEmpty()) {
            return new Tarefa(titulo_da_tarefa_quebrada, categoria_da_tarefa_quebrada, prioridade_da_tarefa_quebrada);
        }

        throw new TarefaInvalidException("Erro ao criar e retornar, algum atributo da Tarefa falhou!");
    }

}
