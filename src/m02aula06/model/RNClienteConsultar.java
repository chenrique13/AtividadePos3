package m02aula06.model;

import m02aula06.basica.Cliente;
import m02aula06.repository.ConexaoException;
import m02aula06.repository.DAOCliente;
import m02aula06.repository.RepositoryException;

public class RNClienteConsultar {
	public Cliente consultar(Cliente cliente) throws Exception{
		valida(cliente);
		Cliente aux = consulta(cliente);
		
		return aux;
	}
	
	private void valida(Cliente cliente) throws Exception{
		if((cliente.getCpf() == null) || (cliente.getCpf().isEmpty())) {
			throw new Exception("CPF Inválido");
		}
	}
	
	private Cliente consulta(Cliente cliente) throws Exception{
		DAOCliente dao = new DAOCliente();
		
		try {
			Cliente aux = dao.consultar(cliente);
			
			if(aux == null) {
				throw new Exception("Nenhum dado encontrado ao consultar ");
			}
			
			return aux;
		} catch(ConexaoException e) {
			throw new Exception("Erro Conexão ao Consultar " + e.getMessage());
		} catch(RepositoryException e) {
			throw new Exception("Erro Repositório ao Consultar " + e.getMessage());
		}
	}
}