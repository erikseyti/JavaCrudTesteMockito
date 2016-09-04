package service;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


import model.Cliente;
import utilitarios.DAOGenerico;

public class DaoGenericoTeste {
	
	DAOGenerico dao = new DAOGenerico();
	

	
	@Test
	public void VerificarCliente() throws Exception
	{
		
		Cliente c1 = new Cliente();
		c1.setNome("José");
		c1.setIdade(11);
		
		dao.inserir(c1);
		
		Cliente c2 =(Cliente) dao.recupera(Cliente.class, 1L);
		Assert.assertEquals("José", c2.getNome());
		//verify(dao).inserir(c1);
		c1.setNome("Pedro");
		c1.setIdade(20);
		dao.salvar(c1);
		Cliente c3 = (Cliente) dao.recupera(Cliente.class,1L);
		Assert.assertEquals("Pedro", c3.getNome());
		
		Cliente c4 = new Cliente();
		c4.setNome("Augusto");
		c4.setIdade(18);
		dao.inserir(c4);
		
		Cliente c5 = new Cliente();
		c5.setNome("Pesana");
		c5.setIdade(17);
		dao.inserir(c5);
		
		Assert.assertEquals(3, dao.lista(Cliente.class).size());
		dao.excluir(c5);
		Assert.assertEquals(2,dao.lista(Cliente.class).size());
		
	}
	
//	@Test
//	public void VerificarAlterarCliente()
//	{
//		Cliente c1 = new Cliente();
//		c1.setNome("José");
//		c1.setIdade(11);
//		
//		dao.salvar(c1);
//		verify(dao).salvar(c1);		
//	}
//	
//	@Test
//	public void ExcluirCliente() throws Exception
//	{
//		Cliente c1 = new Cliente();
//		c1.setNome("José");
//		c1.setIdade(11);
//		
//		dao.inserir(c1);
//		dao.excluir(c1);
//		verify(dao).excluir(c1);
//		
//	}
//	
//	@Test
//	public void ListaClientes()
//	{
//		
//		Cliente c1 = new Cliente();
//		c1.setNome("José");
//		c1.setIdade(11);
//		dao.inserir(c1);
//		dao.inserir(c1);
//		verify(dao).inserir(c1);
//		Assert.assertEquals(2, dao.lista(Cliente.class).size());
//		
//	}
	

}
