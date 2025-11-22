package ObjetoDeManipulacao;

import java.util.Map;
import java.util.Objects;

public class Tarefa implements Comparable<Tarefa> {

    private String titulo;
    private String categoria; //
    private String prioridade ; // Alto: urgente com prazos | Médio nem tão urgente nem tão menos importante | Baixo: baixa urgencia

    public Tarefa(String titulo, String categoria, String prioridade) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridade = prioridade;
    }

    public Tarefa(String titulo, String categoria) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridade = "Baixa";
    }


    public String getTitulo(){ return titulo;}
    public String getCategoria(){ return categoria;}
    public String getPrioridade(){ return prioridade;}
    public void setPrioridade(String prio){ this.prioridade = prio;}

    @Override
    public String toString() {
        return "Titulo da Tarefa= " + titulo + " | Categoria= " + categoria +" | Prioridade= " + prioridade;
    }

    @Override
    public int compareTo(Tarefa o) {
        Map<String, Integer> prioridade = Map.of(
                "Alta", 1,
                "Média", 2,
                "Baixa", 3
        );

     /*   if ((this.prioridade.equalsIgnoreCase("Baixa") ||
                this.prioridade.equalsIgnoreCase("Média"))
                && o.prioridade.equalsIgnoreCase("Alta")) {
            o.
        }*/
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(titulo, tarefa.titulo) && Objects.equals(categoria, tarefa.categoria) && Objects.equals(prioridade, tarefa.prioridade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, categoria, prioridade);
    }
}
