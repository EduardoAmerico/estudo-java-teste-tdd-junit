package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

    private String descricao;
    private List<Lance> lances;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<Lance>();
    }

    public void propoe(Lance lance) {
        int total = qntLancesUsuario(lance.getUsuario());

        if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
            lances.add(lance);
        }

    }

    public void dobraLance(Usuario usuario) {
        Lance ultimo = getUltimoLanceDoUsuario(usuario);
        if (ultimo != null) propoe(new Lance(usuario, ultimo.getValor() * 2));
    }

    private Lance getUltimoLanceDoUsuario(Usuario usuario) {
        Lance ultimo = null;
        for (Lance lance : lances) {
            if (lance.getUsuario().equals(usuario)) ultimo = lance;
        }
        return ultimo;
    }

    private boolean podeDarLance(Usuario usuario) {
        return !UltimolanceDado().getUsuario().equals(usuario) && qntLancesUsuario(usuario) < 5;
    }

    private int qntLancesUsuario(Usuario usuario) {
        int total = 0;
        for (Lance l : lances) {
            if (l.getUsuario().equals(usuario)) total++;
        }
        return total;
    }

    private Lance UltimolanceDado() {
        return lances.get(lances.size() - 1);
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }


}
