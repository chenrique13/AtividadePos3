package m02aula06.model;

import m02aula06.basica.Cliente;
import m02aula06.repository.ConexaoException;
import m02aula06.repository.DAOCliente;
import m02aula06.repository.RepositoryException;

public class RNClienteApagar {

	public void apagar(Cliente cliente) throws Exception {
		valida(cliente);
		verifica(cliente);
		apaga(cliente);
	}
	
	private void valida(Cliente cliente) throws Exception{
		if( ((cliente.getCpf() == null) || (cliente.getCpf().isEmpty()))
				&&
			((cliente.getEmail() == null) || (cliente.getEmail().isEmpty())) ){
			throw new Exception("Dados inválidos");
		}
	}
	
	private void verifica(Cliente cliente) throws Exception {
		DAOCliente dao = new DAOCliente();
		
		try {
			Cliente aux = dao.consultar(cliente);
			
			if(aux == null) {
				throw new Exception("Nenhum dado encontrado");
			}
		} catch(ConexaoException e) {
			throw new Exception("Erro Conexão ao Verificar" + e.getMessage());
		} catch(RepositoryException e) {
			throw new Exception("Erro Repositório ao Verificar" + e.getMessage());
		}
	}
	
	private void apaga(Cliente cliente) throws Exception {
		DAOCliente dao = new DAOCliente();
		
		try {
			dao.excluir(cliente);
		} catch(ConexaoException e) {
			throw new Exception("Erro Conexão ao Apagar" + e.getMessage());
		} catch(RepositoryException e) {
			throw new Exception("Erro Repositório ao Apagar" + e.getMessage());
		}
	}
}
