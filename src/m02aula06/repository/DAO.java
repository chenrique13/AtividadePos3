package m02aula06.repository;

import java.util.List;

public interface DAO<T> {
	
	public void inserir(T obj) throws ConexaoException, RepositoryException;
	
	public T consultar(T obj) throws ConexaoException, RepositoryException;
	
	public void excluir(T obj) throws ConexaoException, RepositoryException;
	
	public void alterar(T obj) throws ConexaoException, RepositoryException;
	
	public List<T> listarTudo() throws ConexaoException, RepositoryException;
}