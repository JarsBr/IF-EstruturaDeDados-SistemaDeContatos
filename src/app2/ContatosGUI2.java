package app2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class ContatosGUI {
    private JFrame frame;
    private Contato[] todos_contatos;
    private int contador_contatos;
    private JTextArea contatosTextArea;

    public ContatosGUI() {
        frame = new JFrame("Gerenciador de Contatos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nomeLabel = new JLabel("Nome:");
        JTextField nomeField = new JTextField();
        JLabel telefoneLabel = new JLabel("Telefone:");
        JTextField telefoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel grupoLabel = new JLabel("Grupo:");
        JTextField grupoField = new JTextField();

        panel.add(nomeLabel);
        panel.add(nomeField);
        panel.add(telefoneLabel);
        panel.add(telefoneField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(grupoLabel);
        panel.add(grupoField);

        JButton adicionarButton = new JButton("Adicionar");
        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String telefone = telefoneField.getText();
                String email = emailField.getText();
                String grupo = grupoField.getText();

                Contato contato = new Contato(nome, telefone, email, grupo);

                adicionarContatoLista(contato);
                imprimirContatosLista();

                nomeField.setText("");
                telefoneField.setText("");
                emailField.setText("");
                grupoField.setText("");
            }
        });

        JButton ordenarButton = new JButton("Ordenar");
        ordenarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String opcao = JOptionPane.showInputDialog(frame, "Ordenar por (nome, telefone, email, grupo):");
                String direcao = JOptionPane.showInputDialog(frame, "Direção (1 para crescente, 2 para decrescente):");

                boolean crescente = direcao.equals("1");
                ordenarContatos(opcao, crescente);
            }
        });

        todos_contatos = new Contato[20];
        contador_contatos = 0;

        contatosTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(contatosTextArea);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(adicionarButton, BorderLayout.WEST);
        frame.add(ordenarButton, BorderLayout.EAST);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    public void adicionarContatoLista(Contato contato) {
        if (contador_contatos < 20) {
            todos_contatos[contador_contatos] = contato;
            contador_contatos++;
        } else {
            JOptionPane.showMessageDialog(frame, "A lista de contatos está cheia.");
        }
    }

    public void imprimirContatosLista() {
        contatosTextArea.setText("");
        for (int i = 0; i < contador_contatos; i++) {
            if (todos_contatos[i] != null) {
                contatosTextArea.append(todos_contatos[i].toString() + "\n");
            }
        }
    }


    public void ordenarContatos(String opcao, boolean crescente) {
        int tamanho = contador_contatos;
        Contato[] contatos = Arrays.copyOf(todos_contatos, tamanho);

        if (opcao.equals("nome")) {
            ordenarContatosPorCampo(contatos, crescente, "nome");
        } else if (opcao.equals("telefone")) {
            ordenarContatosPorCampo(contatos, crescente, "telefone");
        } else if (opcao.equals("email")) {
            ordenarContatosPorCampo(contatos, crescente, "email");
        } else if (opcao.equals("grupo")) {
            ordenarContatosPorCampo(contatos, crescente, "grupo");
        }

        // Copiar os contatos ordenados de volta para todos_contatos
        System.arraycopy(contatos, 0, todos_contatos, 0, tamanho);

        imprimirContatosLista();
    }


    private void ordenarContatosPorCampo(Contato[] contatos, boolean crescente, String campo) {
        for (int i = 0; i < contatos.length - 1; i++) {
            for (int j = 0; j < contatos.length - i - 1; j++) {
                if (compararStringsIgnoreCase(getCampo(contatos[j], campo), getCampo(contatos[j + 1], campo)) > 0 && crescente) {
                    Contato temp = contatos[j];
                    contatos[j] = contatos[j + 1];
                    contatos[j + 1] = temp;
                } else if (compararStringsIgnoreCase(getCampo(contatos[j], campo), getCampo(contatos[j + 1], campo)) < 0 && !crescente) {
                    Contato temp = contatos[j];
                    contatos[j] = contatos[j + 1];
                    contatos[j + 1] = temp;
                }
            }
        }
    }

    private String getCampo(Contato contato, String campo) {
        if (contato == null) {
            return "";
        }

        if (campo.equals("nome")) {
            return contato.getNome();
        } else if (campo.equals("telefone")) {
            return contato.getTelefone();
        } else if (campo.equals("email")) {
            return contato.getEmail();
        } else if (campo.equals("grupo")) {
            return contato.getGrupo();
        }

        return "";
    }


    private int compararStringsIgnoreCase(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());

        for (int i = 0; i < minLength; i++) {
            char char1 = Character.toLowerCase(str1.charAt(i));
            char char2 = Character.toLowerCase(str2.charAt(i));

            if (char1 != char2) {
                return char1 - char2;
            }
        }

        return str1.length() - str2.length();
    }
}
