package app2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;

class Contato {
    private String nome;
    private String telefone;
    private String email;
    private String grupo;

    public Contato(String nome, String telefone, String email, String grupo) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.grupo = grupo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getGrupo() {
        return grupo;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "     Telefone: " + telefone + "     Email: " + email +
        		"     Grupo: " + grupo;
    }

}











