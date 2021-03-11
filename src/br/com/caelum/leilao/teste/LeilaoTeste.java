package br.com.caelum.leilao.teste;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeilaoTeste {

    @Test
    public void deveReceberUmLance(){
        Leilao leilao = new Leilao("MAC");
        assertEquals(0, leilao.getLances().size());
        leilao.propoe(new Lance(new Usuario("Steve"), 2000));
        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.00, leilao.getLances().get(0).getValor(), 0.00001);

    }

    @Test
    public void deveReceberVariosLances(){
        Leilao leilao = new Leilao("MAC");
        assertEquals(0, leilao.getLances().size());
        leilao.propoe(new Lance(new Usuario("Steve"), 2000));
        leilao.propoe(new Lance(new Usuario("Steve2"), 3000));
        assertEquals(2, leilao.getLances().size());
        assertEquals(2000.00, leilao.getLances().get(0).getValor(), 0.00001);
        assertEquals(3000.00, leilao.getLances().get(1).getValor(), 0.00001);

    }
    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario(){
        Leilao leilao = new Leilao("MAC");
        Usuario steveJobs = new Usuario("steveJobs");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(steveJobs, 3000));

        assertEquals(1, leilao.getLances().size());
        assertEquals(2000.00, leilao.getLances().get(0).getValor(), 0.00001);

    }
    @Test
    public void naoDeveExecutarmaisDeCincoLancesDeUmMesmoUsuario(){
        Leilao leilao = new Leilao("MAC");
        Usuario steveJobs = new Usuario("steveJobs");
        Usuario billGates = new Usuario("billGates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));

        leilao.propoe(new Lance(steveJobs, 4000));
        leilao.propoe(new Lance(billGates, 5000));

        leilao.propoe(new Lance(steveJobs, 6000));
        leilao.propoe(new Lance(billGates, 7000));

        leilao.propoe(new Lance(steveJobs, 8000));
        leilao.propoe(new Lance(billGates, 9000));

        leilao.propoe(new Lance(steveJobs, 10000));
        leilao.propoe(new Lance(billGates, 11000));

        //deve ser ignorado
        leilao.propoe(new Lance(steveJobs, 12000));

        assertEquals(10, leilao.getLances().size());
        assertEquals(11000.00, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
    }
    @Test
    public void deveDobrarOUltimoLanceDado() {
        Leilao leilao = new Leilao("MAC");
        Usuario steveJobs = new Usuario("steveJobs");
        Usuario billGates = new Usuario("billGates");

        leilao.propoe(new Lance(steveJobs, 2000));
        leilao.propoe(new Lance(billGates, 3000));
        leilao.dobraLance(steveJobs);

        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
    }

    @Test
    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
        Leilao leilao = new Leilao("MAC");
        Usuario steveJobs = new Usuario("steveJobs");

        leilao.dobraLance(steveJobs);

        assertEquals(0, leilao.getLances().size());
    }

}
