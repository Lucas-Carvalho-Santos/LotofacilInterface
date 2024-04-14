import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class LotofacilInterface extends JFrame {
    // Atributos
    private JPanel painel = new JPanel();
    private JButton jButtonAposta1 = new JButton("Apostar de 1 a 100");
    private JButton jButtonAposta2 = new JButton("Apostar de A até Z");
    private JButton jButtonAposta3 = new JButton("Apostar entre par ou ímpar");

    // Construtor
    Random random = new Random();
    int numero_sorteado = -1;

    public LotofacilInterface() {
        // Configurações do frame
        this.setTitle("Lotofácil");
        this.setSize(400, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centralizar janela

        // Configurações do painel
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
        painel.setBackground(new Color(255, 255, 255));

        // Definindo tamanhos padrão para os botões
        Dimension buttonSize = new Dimension(160, 30);
        jButtonAposta1.setPreferredSize(buttonSize);
        jButtonAposta2.setPreferredSize(buttonSize);
        jButtonAposta3.setPreferredSize(buttonSize);

        // Adiciona listeners aos botões
        jButtonAposta1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apostarNumero();
            }
        });

        jButtonAposta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apostarLetra();
            }
        });

        jButtonAposta3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apostarParImpar();
            }
        });

        // Adiciona componentes ao painel
        painel.add(jButtonAposta1);
        painel.add(jButtonAposta2);
        painel.add(jButtonAposta3);

        this.getContentPane().add(painel);

        // Exibe janela
        this.setVisible(true);
    }

    // Método para apostar em um número de 0 a 100
    private void apostarNumero() {
        try {
            String aposta = exibirModalPergunta("Faça sua aposta!","Digite um número de 1 a 100:");
            numero_sorteado = random.nextInt(101);
            int numero = Integer.parseInt(aposta);
            if (numero <= 0 || numero > 100) {
                JOptionPane.showMessageDialog(null, "Aposta Inválida");
            } else if (numero == numero_sorteado) {
                JOptionPane.showMessageDialog(null, "Parabéns! Você ganhou R$1000 reais.");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi dessa vez. O número sorteado foi: " + numero_sorteado);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Entrada Inválida. Digite um número válido.");
        }
    }

    // Método para apostar em uma letra de A a Z
    private void apostarLetra() {
        String aposta = exibirModalPergunta("Faça sua aposta!","Digite uma letra de A a Z:");
        char letra_premiada = 'L';
        if (aposta != null && aposta.length() == 1 && Character.isLetter(aposta.charAt(0))) {
            char letra_aposta = Character.toUpperCase(aposta.charAt(0));

            if (letra_aposta == letra_premiada) {
                JOptionPane.showMessageDialog(null, "Parabéns! Você ganhou R$500 reais.");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi dessa vez. A letra premiada era: " + letra_premiada);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Aposta Inválida.");
        }
    }

    private String exibirModalPergunta(String titulo, String pergunta)
    {
        final ImageIcon icon = new ImageIcon("src/images/coin.png");
        Image image2 = icon.getImage().getScaledInstance(70,70,0);
        var retorno = JOptionPane.showInputDialog(null,pergunta,titulo, JOptionPane.PLAIN_MESSAGE, new ImageIcon(image2),  null, "");
        return retorno != null ? retorno.toString() : "";
    }

    // Método para apostar em número par ou ímpar
    private void apostarParImpar() {
        try {
            String aposta = exibirModalPergunta("Faça sua aposta!","Digite um número Par ou Impar:");
            int par_impar = Integer.parseInt(aposta);

            if (par_impar % 2 == 0) {
                JOptionPane.showMessageDialog(null, "Parabéns! Você ganhou R$100 reais.");
            } else {
                JOptionPane.showMessageDialog(null, "Que pena! O número digitado é ímpar e a premiação foi para números pares.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Entrada Inválida. Digite um número válido.");
        }
    }

    public static void main(String[] args) {
        new LotofacilInterface();
    }
}