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

import com.sun.jersey.core.util.Base64;

import model.Cliente;
import model.Produto;
import utilitarios.DAOGenerico;

public class ProdutoServiceTeste {

	
	
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	DAOGenerico dao;
	
	@Test
	public void verificarAdicionarProduto()
	{
		ProdutoService servicoProduto = new ProdutoService(dao);
		Produto produto1 = new Produto();
		produto1.setDescricao("Novo, 5 anos de Garantia");
		produto1.setValor(500.60);
		Response resultado = servicoProduto.inserir(produto1, "123");
		Assert.assertEquals("cadastro realizado.", resultado.getEntity());
		verify(dao).inserir(produto1);
		
	}
	
	@Test
	public void verificarAlterarProduto()
	{
		ProdutoService servicoProduto = new ProdutoService(dao);
		Produto produto1 = new Produto();
		produto1.setDescricao("Usado apenas de noite");
		produto1.setValor(320.20);
		servicoProduto.inserir(produto1, "123");
		produto1.setDescricao("Novo");
		Response novoResultado = servicoProduto.alterar(produto1);
		Assert.assertEquals("cadastro realizado.", novoResultado.getEntity());
		verify(dao).salvar(produto1);
	}
	
	@Test
	public void verificarExcluirProduto() throws Exception
	{
		ProdutoService servicoProduto = new ProdutoService(dao);
		Produto produto1 = new Produto();
		produto1.setDescricao("Novo");
		produto1.setValor(560.6);
		when(dao.recupera(Produto.class,1L)).thenReturn(produto1);
		Response novaResposta = servicoProduto.excluir(1L);
		Assert.assertEquals("removido com sucesso", novaResposta.getEntity());
		verify(dao).excluir(produto1);
	}
	
	@Test
	public void verificarBuscarIDProduto()
	{
		ProdutoService servicoProduto = new ProdutoService(dao);
		Produto produto1 = new Produto();
		when(dao.recupera(Produto.class,1L)).thenReturn(produto1);
		String token = new String(Base64.encode("jose:123"));
		Response retornoProduto = servicoProduto.buscarId(1L,token);
		Assert.assertEquals(produto1, retornoProduto.getEntity());
	}
	
	@Test
	public void verificarListarProduto()
	{
		ProdutoService servicoProduto = new ProdutoService(dao);
		ArrayList<Produto> retornoNumeroProduto = new ArrayList<Produto>();
		retornoNumeroProduto.add(new Produto());
		retornoNumeroProduto.add(new Produto());
		retornoNumeroProduto.add(new Produto());
		
		when(dao.lista(Produto.class)).thenReturn(retornoNumeroProduto);
		Response tamanhoListaProduto = servicoProduto.listarTodos("123");
		Assert.assertEquals(retornoNumeroProduto, tamanhoListaProduto.getEntity());

		
	}
	
	
}
