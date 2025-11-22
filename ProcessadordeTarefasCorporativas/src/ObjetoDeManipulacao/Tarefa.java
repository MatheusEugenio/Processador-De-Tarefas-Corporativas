package ObjetoDeManipulacao;

import java.util.Map;
import java.util.Objects;

public class Tarefa implements Comparable<Tarefa> {

    private String titulo;
    private String categoria; //
    private String prioridade ; // Alto: urgente com prazos | Médio nem tão urgente nem tão menos importante | Baixo: baixa urgencia
    private static final Map<String, Integer> valor_Prioridade = Map.of(
            "alta", 1,
            "média", 2,
            "media", 2,
            "baixa", 3
    );

    public Tarefa(String titulo, String categoria, String prioridade) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridade = prioridade;
    }

    public Tarefa(String titulo, String categoria) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.prioridade = "baixa";
    }

    public String getTitulo(){ return titulo;}
    public String getCategoria(){ return categoria;}
    public String getPrioridade(){ return prioridade;}
    public void setPrioridade(String prio){ this.prioridade = prio;}

    @Override
    public String toString() {
        return titulo + " | " + categoria +" | " + prioridade;
    }

    @Override
    public int compareTo(Tarefa o) {

        String thisPrioridade = (this.prioridade == null) ? "baixa" : this.prioridade.toLowerCase(); /*VERIFICA SE A PRIORIDADE DA CLASSE É NULL:
                                                                                                   SE SIM{RETORNA A STRING baixa QUE VAI SER USADA COMO CHAVE
                                                                                                   PARA ACESSAR O MAP DE PRIORIDADES}
                                                                                                   SE NÃO{DEIXA A PRIORIDADE TODA MINUSCULA PARA PADRONIZAR} */
        String oPrioridade = (o.prioridade == null) ? "baixa" : o.prioridade.toLowerCase();

        int valueThis = valor_Prioridade.getOrDefault(thisPrioridade, 3); /*ACESSA O MAP ATRAVÉS DE "valor_Prioridade" PARA RETORNA O VALOR QUE
                                                                                        ESTÁ ATRELADO A CHAVE, SE NÃO ACHAR, RETORNA 3 -> VALOR BAIXO*/
        int valueO = valor_Prioridade.getOrDefault(oPrioridade, 3);

        int comparacao = Integer.compare(valueThis, valueO); /*COMPARA OS VALORES:
                                                               SE O 1° É MAIOR QUE O 2°: RETORNA 1;
                                                               SE O 2° É MAIOR QUE O 1°: RETORNA -1;
                                                               SE OS DOIS FOREM IGUAIS: RETORNA 0;*/

        if (comparacao != 0){
            return comparacao;
        }else {
            return this.titulo.compareToIgnoreCase(o.titulo); //faz a comparação de ordem alfabética
        }
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
