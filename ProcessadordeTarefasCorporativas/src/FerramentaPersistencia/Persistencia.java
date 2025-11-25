package FerramentaPersistencia;

import java.io.*;
import java.util.List;
import java.util.function.Function;

public class Persistencia<T>{

    private String caminho;

    public Persistencia(String caminho){
        this.caminho = caminho;
    }

    public void salvar(List<T> dados) throws IOException {
        //carregar();

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.caminho))){

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

}
