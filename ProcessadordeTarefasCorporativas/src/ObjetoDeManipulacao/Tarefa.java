package ObjetoDeManipulacao;

import java.util.Objects;

public class Tarefa implements Comparable<Tarefa> {

    private int id;
    private String titulo;
    private String categoria; //
    private String prioridade;

    public Tarefa(String titulo, String categoria, String prioridade) {
        this.id = 0;
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridade = prioridade;
    }

    public Tarefa(String titulo, String categoria) {
        this.id = 0;
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridade = "Baixa";
    }

    public int getId(){ return id;}
    public String getTitulo(){ return titulo;}
    public String getCategoria(){ return categoria;}
    public String getPrioridade(){ return prioridade;}
    public void setPrioridade(String prio){ this.prioridade = prio;}

    @Override
    public String toString() {
        return "Tarefa de ID=" + id + " | Titulo= " + titulo + " | Categoria= " + categoria +" | Prioridade= " + prioridade;
    }

    @Override
    public int compareTo(Tarefa o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return id == tarefa.id && Objects.equals(titulo, tarefa.titulo) && Objects.equals(categoria, tarefa.categoria) && Objects.equals(prioridade, tarefa.prioridade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, categoria, prioridade);
    }
}
