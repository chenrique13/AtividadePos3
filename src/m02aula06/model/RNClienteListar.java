package m02aula06.model;

import java.util.ArrayList;
import java.util.List;

import m02aula06.basica.Cliente;
import m02aula06.repository.ConexaoException;
import m02aula06.repository.DAOCliente;
import m02aula06.repository.RepositoryException;

public class RNClienteListar {

	public List<Cliente> listar() throws Exception {
		DAOCliente dao = new DAOCliente();
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try {
			lista = (ArrayList<Cliente>)dao.listarTudo();
		} catch (ConexaoException e) {
			throw new Exception(
					"Estamos com dificuldades. Tente novamente mais tarde (daqui há 12horas)<br/>" + e.getMessage());
		} catch (RepositoryException e) {
			throw new Exception("O programador fez kk. Nem adianta tentar de novo.<br/>" + e.getMessage());
		}
		return lista;
	}
}
