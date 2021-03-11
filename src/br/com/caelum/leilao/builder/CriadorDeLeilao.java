package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class CriadorDeLeilao {
    private Leilao leilão;

    public CriadorDeLeilao para(String descricao){
        this.leilão = new Leilao(descricao);
        return this;
    }
    public CriadorDeLeilao lance(Usuario usuario, Double valor){
        leilão.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() {
        return leilão;
    }
}
