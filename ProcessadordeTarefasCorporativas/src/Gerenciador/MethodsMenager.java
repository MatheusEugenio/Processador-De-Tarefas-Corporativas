package Gerenciador;

import Exceptions.ErrorGeralExeception;
import Exceptions.TarefaInvalidException;
import ObjetoDeManipulacao.Tarefa;

import java.io.IOException;

public interface MethodsMenager {
    void add(Tarefa tarefa) throws ErrorGeralExeception, IOException, TarefaInvalidException;
    void remove(Tarefa tarefa) throws TarefaInvalidException, ErrorGeralExeception, IOException;
    void remove(String nomeTarefa) throws TarefaInvalidException, IOException;
    void exibirTarefas();
}
