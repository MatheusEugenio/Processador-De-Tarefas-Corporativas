package FerramentaPersistencia;

import Exceptions.TarefaInvalidException;

import java.io.*;
import java.util.List;
import java.util.function.Function;

public class Persistencia<T>{

    public void save(List<T> dados, String caminho) throws IOException {
        carregar();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))){

            int i = 0;
            while (!dados.isEmpty()) {
                writer.write(dados.get(i).toString());
                writer.newLine();
                i++;
            }

            writer.flush();

            System.out.println("Salvo com sucesso!");
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public void carregar(Function<String, T> conversor){

    }

    public void carregar(){
        //percorrer oque tem no arquivo e joga na lista, assim atualiazando a lista sempre que rodar o programa


    }

}
