package service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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
	public void verificarAdicionarCliente()
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
	public void verificarAlterarCliente()
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
	public void verificarExcluirCliente() throws Exception
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
	
	@Test
	 public void testaListarClientesMaiores()
	 {
		 ClienteService clienteService = new ClienteService(dao);
		 List<Cliente> resultadoDAO = new ArrayList<Cliente>();
		 Cliente clienteMaior = new Cliente();
		 clienteMaior.setId(1l);
		 clienteMaior.setNome("Augusto Pedro");
		 clienteMaior.setIdade(23);
		 resultadoDAO.add(clienteMaior);
		 Cliente clienteMenor = new Cliente();
		 clienteMenor.setId(2l);
		 clienteMenor.setNome("Rubens");
		 clienteMenor.setIdade(12);
		 resultadoDAO.add(clienteMenor);
		 when(dao.lista(Cliente.class)).thenReturn(resultadoDAO);
		 List<Cliente> resultado = clienteService.listarMaiores();
		 Assert.assertEquals(1, resultado.size());
		 resultado.get(0);
		 Cliente clienteRetornado = resultado.get(0);
		 Assert.assertTrue("O Cliente Retornado deveria ser Maior de Idade",clienteRetornado.getIdade()>=18);
	 }
	
	@Test
	public void testarListaClienteService(){
		ClienteService servicoCliente = new ClienteService(dao);
		ArrayList<Cliente> retornoNumeroCliente = new ArrayList<Cliente>();
		retornoNumeroCliente.add(new Cliente());
		retornoNumeroCliente.add(new Cliente());
		retornoNumeroCliente.add(new Cliente());
		retornoNumeroCliente.add(new Cliente());
		
		when(dao.lista(Cliente.class)).thenReturn(retornoNumeroCliente);
	}
	
	@Test
	public void testarBuscaClienteService()
	{
		ClienteService servicoCliente = new ClienteService(dao);
		Cliente cliente1 = new Cliente();
		Cliente cliente2 = new Cliente();
		when(dao.recupera(Cliente.class,1L)).thenReturn(cliente1);
		when(dao.recupera(Cliente.class,2L)).thenReturn(cliente2);
		Cliente retornoCliente = servicoCliente.buscar(1L);
		Assert.assertEquals(cliente1, retornoCliente);
		
	}
	
	
	
	
}
