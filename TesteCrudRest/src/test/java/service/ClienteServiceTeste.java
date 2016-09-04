package service;

import static org.mockito.Mockito.*;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import model.Cliente;
import utilitarios.DAOGenerico;

public class ClienteServiceTeste {

	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	DAOGenerico dao;
	
	@Test
	public void VerificarAdicionarCliente()
	{
		ClienteService clienteService = new ClienteService(dao);
		Cliente c1 = new Cliente();
		c1.setNome("José");
		c1.setIdade(11);
		
		Response resultado = clienteService.cadastrar(c1);
		Assert.assertEquals("cadastro realizado.", resultado.getEntity());
		verify(dao).inserir(c1);
	}
	
	@Test
	public void VerificarAlterarCliente()
	{
		ClienteService clienteService = new ClienteService(dao);
		Cliente c1 = new Cliente();
		c1.setNome("Roberto");
		c1.setIdade(30);
		
		clienteService.cadastrar(c1);
		c1.setNome("Augusto Pereira");
		Response novoResultado = clienteService.alterar(c1);
		Assert.assertEquals("cadastro alterado.", novoResultado.getEntity());
		verify(dao).salvar(c1);
		
	}
	
	@Test
	public void VerificarExcluirCliente() throws Exception
	{
		ClienteService clienteService = new ClienteService(dao);
		Cliente c1 = new Cliente();
		c1.setNome("Roberto");
		c1.setIdade(30);
		
		
		when(dao.recupera(Cliente.class, 1L)).thenReturn(c1);
		Response novoResultado = clienteService.excluir(1L);
		Assert.assertEquals("cadastro excluído.", novoResultado.getEntity());
		verify(dao).excluir(c1);
		
	}
	
	
}
