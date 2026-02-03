package br.edu.ifba.inf008.app.interfaces;

import java.util.List;

public interface IDataAccess<T> {
    List<T> listarTodos();
}