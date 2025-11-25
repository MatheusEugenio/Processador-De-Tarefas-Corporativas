package TesteExecutaveis;

import FerramentaPersistencia.Persistencia;

public class TestPersistencia_Inicial {
    public static void main(String[] args) {


        try {

            System.out.println("Salvo com sucesso!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
