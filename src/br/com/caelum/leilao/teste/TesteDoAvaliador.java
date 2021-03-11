package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TesteDoAvaliador {

    private Avaliador leiloeiro;
    private Usuario jose;
    private Usuario maria;
    private Usuario joao;

    @BeforeEach
    private void criaAvaliador(){
        this.leiloeiro = new Avaliador();
        //cenario
        this.joao = new Usuario("João");
        this.maria = new Usuario("Maria");
        this.jose = new Usuario("José");
    }

    @Test
    public void naoDeveAvaliarLailaoSemLances(){
        try{
            Leilao leilao = new CriadorDeLeilao().para("PS4").constroi();
            leiloeiro.avalia(leilao);
            Assertions.fail();
        }catch (RuntimeException e){

        }

    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        //cenario
        //exemplo de Test Data Builder -
        Leilao leilao = new CriadorDeLeilao().para("ps5")
                .lance(joao, 250.00)
                .lance(jose, 300.0)
                .lance(maria, 400.0)
                .constroi();
        //acao
        leiloeiro.avalia(leilao);

        //validacao

        double maiorEsperado = 400.00;
        double menorEsperado = 250.00;
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }


    @Test
    public void deveEntenderLilaoComApenasUmlance() {
        //cenario

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 1000.00));
        //acao
        leiloeiro.avalia(leilao);

        //validacao

        double maiorEsperado = 1000.00;
        double menorEsperado = 1000.00;
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
    }
    @Test
    public void deveEncontrarOs3MaioresLances() {
        //cenario

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 100.00));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.00));
        leilao.propoe(new Lance(maria, 400.0));
        //acao
        leiloeiro.avalia(leilao);

        //validacao


        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor());
        assertEquals(300.0, maiores.get(1).getValor());
        assertEquals(200.0, maiores.get(2).getValor());
    }
    @Test
    public void deveEncontrarOs3MaioresLancesOrdemAleatoria() {
        //cenario

        Leilao leilao = new Leilao("PS5");

        leilao.propoe(new Lance(joao, 200.00));
        leilao.propoe(new Lance(maria, 450.0));
        leilao.propoe(new Lance(joao, 120.00));
        leilao.propoe(new Lance(maria, 700.0));
        leilao.propoe(new Lance(joao, 630.0));
        leilao.propoe(new Lance(maria, 230.0));
        //acao
        leiloeiro.avalia(leilao);

        //validacao


        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(700.0, maiores.get(0).getValor());
        assertEquals(630.0, maiores.get(1).getValor());
        assertEquals(450.0, maiores.get(2).getValor());

        assertEquals(700.00, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(120.00, leiloeiro.getMenorLance(), 0.00001);
    }
}
