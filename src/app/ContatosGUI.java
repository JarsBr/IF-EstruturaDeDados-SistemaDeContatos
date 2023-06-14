package app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

class ContatosGUI {
    private JFrame frame;
    private DefaultListModel<Contato> listModel;

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
               imprimirContatosLista(todos_contatos);
                
                //listModel.addElement(contato);

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
                
                
                ArrayList<Contato> contatos = Collections.list(listModel.elements());
                Collections.sort(contatos, new ContatoComparator(opcao, crescente));
                listModel.clear();
                for (Contato contato : contatos) {
                    listModel.addElement(contato);
                }
            }
        });

        listModel = new DefaultListModel<>();
        JList<Contato> listaContatos = new JList<>(listModel);
        
        //Fazewr for para adiocnar elemento na lista contatos

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(listaContatos), BorderLayout.CENTER);
        frame.add(adicionarButton, BorderLayout.WEST);
        frame.add(ordenarButton, BorderLayout.EAST);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
    Contato[] todos_contatos = new Contato[20];
    public void adicionarContatoLista(Contato contato) {
         for(int i = 0; i < 20; i++) {
        	if(todos_contatos[i] == null) {
        		todos_contatos[i] = contato;
        		break;
        	}
         }
         
		
	}
    public Contato[] imprimirContatosLista(Contato[] contatos) {
    	int cont = 0;
		for(Contato p: contatos) {
			if(p != null) {
			cont++;
			System.out.println(p.toString());
			}
		}
		Contato[] todos_contatos_novos = new Contato[cont];
		for(int i = 0; i < cont; i++) {
			todos_contatos_novos[i] = contatos[i];
		}
		return todos_contatos_novos;
	}
}

