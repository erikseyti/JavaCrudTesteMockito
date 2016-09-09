package service;

import javax.ws.rs.core.Response;

import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import model.Produto;
import utilitarios.DAOGenerico;

public class ProdutoServiceTeste {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
	DAOGenerico dao;
	
	public void verificarAdicionarProduto()
	{
		ProdutoService servicoProduto = new ProdutoService(dao);
		Produto produto1 = new Produto();
		produto1.setDescricao("Novo, 5 anos de Garantia");
		produto1.setValor(500.60);
		//Response resultado = servicoProduto.inserir(produto1, token);
		
	}
	
	
}
