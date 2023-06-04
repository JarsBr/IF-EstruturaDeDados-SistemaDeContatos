package app;

import java.util.Comparator;

class ContatoComparator implements Comparator<Contato> {
    private String opcao;
    private boolean crescente;

    public ContatoComparator(String opcao, boolean crescente) {
        this.opcao = opcao;
        this.crescente = crescente;
    }

    public int compare(Contato c1, Contato c2) {
        int resultado = 0;
        switch (opcao) {
            case "nome":
                resultado = c1.getNome().compareTo(c2.getNome());
                break;
            case "telefone":
                resultado = c1.getTelefone().compareTo(c2.getTelefone());
                break;
            case "email":
                resultado = c1.getEmail().compareTo(c2.getEmail());
                break;
            case "grupo":
                resultado = c1.getGrupo().compareTo(c2.getGrupo());
                break;
        }
        return crescente ? resultado : -resultado;
    }
}
