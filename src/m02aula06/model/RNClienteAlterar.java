package m02aula06.model;

import m02aula06.basica.Cliente;
import m02aula06.repository.ConexaoException;
import m02aula06.repository.DAOCliente;
import m02aula06.repository.RepositoryException;

public class RNClienteAlterar {

	public Cliente atualizar(Cliente cliente) throws Exception {
		altera(cliente);
		return consulta(cliente);
	}
	
	private void altera(Cliente cliente) throws Exception {
		DAOCliente dao = new DAOCliente();
		
		try {
			dao.alterar(cliente);
			
		} catch(ConexaoException e) {
			throw new Exception("Erro Conex�o ClienteAlterar " + e.getMessage());
		} catch(RepositoryException e) {
			throw new Exception("Erro Reposit�rio ClienteAlterar " + e.getMessage());
		}
	}
	
	private Cliente consulta(Cliente cliente) throws Exception{
		DAOCliente dao = new DAOCliente();
		
		try {
			Cliente aux = dao.consultar(cliente);
			
			if(aux == null) {
				throw new Exception("Dados n�o encontrados");
			}
			
			return aux;
		} catch(ConexaoException e) {
			throw new Exception("Erro Conex�o ClienteAlterar 2 " + e.getMessage());
		} catch(RepositoryException e) {
			throw new Exception("Erro Reposit�rio ClienteAlterar 2 " + e.getMessage());
		}
	}
}
