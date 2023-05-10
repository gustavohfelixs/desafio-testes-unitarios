package br.ce.wcaquino.servicos;


import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LocacaoServiceTest {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup(){
        service = new LocacaoService();
    }

    @Test
    public void testeLocacao() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void testLocacao_filmeSemEstoque() throws Exception{
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 0, 4.0));

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException{
        //cenario
        List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));

        //acao
        try {
            service.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    @Test
    public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
        //cenario
        Usuario usuario = new Usuario("Usuario 1");

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //acao
        service.alugarFilme(usuario, null);
    }

    @Test
    public void deveDarDescontoTresFilmes() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList( new Filme("Filme 1", 1, 5.0),
                                            new Filme("Filme 2", 1, 5.0),
                                            new Filme("Filme 3", 1, 5.0));

        service.alugarFilme(usuario, filmes);

        assertThat(service.getValorTotal(), is (13.75));
    }

    @Test
    public void deveDarDescontoQuatroFilmes() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList( new Filme("Filme 1", 1, 5.0),
                                            new Filme("Filme 2", 1, 5.0),
                                            new Filme("Filme 3", 1, 5.0),
                                            new Filme("Filme 4", 1, 5.0));

        service.alugarFilme(usuario, filmes);

        assertThat(service.getValorTotal(), is (16.25));
    }

    @Test
    public void deveDarDescontoCincooFilmes() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList( new Filme("Filme 1", 1, 5.0),
                new Filme("Filme 2", 1, 5.0),
                new Filme("Filme 3", 1, 5.0),
                new Filme("Filme 3", 1, 5.0),
                new Filme("Filme 3", 1, 5.0),
                new Filme("Filme 4", 1, 5.0));

        service.alugarFilme(usuario, filmes);

        assertThat(service.getValorTotal(), is (17.50));
    }
    @Test
    public void deveDarDescontoSeisFilmes() throws FilmeSemEstoqueException, LocadoraException {
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = Arrays.asList( new Filme("Filme 1", 1, 5.0),
                                            new Filme("Filme 2", 1, 5.0),
                                            new Filme("Filme 3", 1, 5.0),
                                            new Filme("Filme 3", 1, 5.0),
                                            new Filme("Filme 3", 1, 5.0),
                                            new Filme("Filme 3", 1, 5.0));

        service.alugarFilme(usuario, filmes);

        assertThat(service.getValorTotal(), is (17.50));
    }
}
