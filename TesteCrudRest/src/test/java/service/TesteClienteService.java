package service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

public class TesteClienteService {
	
	@Mock
	DAOGenerico dao;
	
	 @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	 
	 @Test
	 public void testaListarClientesMaiores()
	 {
		 ClienteService clienteService = new ClienteService(dao);
		 List<Cliente> resultadoDAO = new ArrayList<Cliente>();
		 Cliente clienteMaior = new Cliente();
		 clienteMaior.setId(1l);
		 clienteMaior.setNome("John");
		 clienteMaior.setIdade(21);
		 resultadoDAO.add(clienteMaior);
		 Cliente clienteMenor = new Cliente();
		 clienteMenor.setId(2l);
		 clienteMenor.setNome("Carl");
		 clienteMenor.setIdade(11);
		 resultadoDAO.add(clienteMenor);
		 when(dao.lista(Cliente.class)).thenReturn(resultadoDAO);
		 List<Cliente> resultado = clienteService.listarMaiores();
		 Assert.assertEquals(1, resultado.size());
		 resultado.get(0);
		 Cliente clienteRetornado = resultado.get(0);
		 Assert.assertTrue("O Cliente Retornado deveria ser Maior de Idade",clienteRetornado.getIdade()>=18);
	 }
	 
	 @Test
	 public void testaBuscarCliente()
	 {
		 ClienteService clienteService = new ClienteService(dao);
		 Cliente cliente1 = new  Cliente();
		 when(dao.recupera(Cliente.class, 1l)).thenReturn(cliente1);
		 when(dao.recupera(Cliente.class, 2l)).thenReturn(null);
		 
		 Cliente clienteRetornado = clienteService.buscar(1l);
		 Assert.assertEquals(cliente1, clienteRetornado);
	 }
	
	@Test
	public void testaListaClientes() {
		ClienteService clienteService = new ClienteService(dao);
		ArrayList<Cliente> retornoFixo = new ArrayList<Cliente>();
		retornoFixo.add(new Cliente());
		retornoFixo.add(new Cliente());
		retornoFixo.add(new Cliente());
		
		when(dao.lista(Cliente.class)).thenReturn(retornoFixo);
		
		List<Cliente> resultado = clienteService.listar();
		
		//A operacao retornou uma lista com 3 clientes?
		Assert.assertEquals("Deveria retornar 3 clientes", 3, resultado.size());
		
		//O metodo lista da classe DAOGenerico foi executado?
		verify(dao).lista(Cliente.class);
	}
	
	@Test
	public void testaIncluirCliente() {
		ClienteService clienteService = new ClienteService(dao);
		Cliente cliente = new Cliente();
		cliente.setId(1l);
		cliente.setNome("John");
		cliente.setIdade(20);
		
		Response resultado = clienteService.cadastrar(cliente);
		
		//a operacao foi executada com sucesso?
		Assert.assertEquals("cadastro realizado.", resultado.getEntity());
		
		//o metodo inserir da classe DAOGenerico foi executado com o parametro cliente?
		verify(dao).inserir(cliente);
	}
	
	//TODO testar os outros métodos da classe ClienteService

	@Test
	public void calcularSoma()
	{
		int a = 2;
		int b = 3;
		int c =a+b;
		Assert.assertEquals(5,c);
	}
	
}
