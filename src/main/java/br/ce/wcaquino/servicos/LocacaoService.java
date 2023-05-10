package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

    double valorTotal;

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws LocadoraException, FilmeSemEstoqueException {


        verificaFilmeNull(filmes);
        verificaEstoqueFilmes(filmes);
        verificaUsuarioNull(usuario);
        calcTotValorFilmes(filmes);

        Locacao locacao = new Locacao();
        locacao.setFilme(filmes);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(valorTotal);

        //Entrega no dia seguinte
        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }

    public void verificaUsuarioNull(Usuario usuario) throws LocadoraException {
        if (usuario == null) {
            throw new LocadoraException("Usuario vazio");
        }
    }

    public void verificaFilmeNull(List<Filme> filmes) throws LocadoraException {
        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Filme vazio");
        }
    }

    public void verificaEstoqueFilmes(List<Filme> filmes) throws FilmeSemEstoqueException {
        for (Filme filme : filmes) {
            if (filme.getEstoque() == 0) {
                throw new FilmeSemEstoqueException();
            }
        }
    }

    public Double calcTotValorFilmes(List<Filme> filmes) {
        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            double valorFilme = filme.getPrecoLocacao();
            switch (i) {
                case 2: valorFilme = valorFilme * 0.75; break;
                case 3: valorFilme = valorFilme * 0.50; break;
                case 4: valorFilme = valorFilme * 0.25; break;
                case 5: valorFilme = 0d; break;
            }
            valorTotal += valorFilme;
        }
        return valorTotal;

    }
}

