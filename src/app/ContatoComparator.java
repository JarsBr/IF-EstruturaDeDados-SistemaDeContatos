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
                resultado = c1.getNome().toLowerCase().compareTo(c2.getNome().toLowerCase());
                System.out.println(c1.getNome() + c2.getNome());
                break;
            case "telefone":
                resultado = c1.getTelefone().toLowerCase().compareTo(c2.getTelefone().toLowerCase());
                break;
            case "email":
                resultado = c1.getEmail().toLowerCase().compareTo(c2.getEmail().toLowerCase());
                break;
            case "grupo":
                resultado = c1.getGrupo().toLowerCase().compareTo(c2.getGrupo().toLowerCase());
                break;
        }
        return crescente ? resultado : -resultado;
    }
}
