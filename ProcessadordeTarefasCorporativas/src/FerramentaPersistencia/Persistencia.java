package FerramentaPersistencia;

import Exceptions.TarefaInvalidException;

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

    public void carregar(){
        //percorrer oque tem no arquivo e joga na lista, assim atualiazando a lista sempre que rodar o programa
        File file = new File(this.caminho);
        if (file.exists()) {

        }

        try(BufferedReader reader = new BufferedReader(new FileReader(this.caminho))){

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void carregar(Function<String, T> conversor){



    }

    /*private Tarefa parseTarefa(){

        return ;//new Tarefa();

    }
     */

}
