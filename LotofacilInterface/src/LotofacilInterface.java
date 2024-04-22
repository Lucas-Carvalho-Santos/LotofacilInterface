import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class LotofacilInterface extends JFrame{
    // Atributos e interfaces

    private JPanel painel = new JPanel(); // Estanciando um novo painel principal
    private JButton JbutonAposta1 = new JButton("Aposta de 0 a 100"); // botões do painel
    private JButton JbutonAposta2 = new JButton("Aposta de A a Z"); // botões do painel
    private JButton JbutonAposta3 = new JButton("Aposta Par e Impar"); // botões do painel

    Random random = new Random(); // para gerar um número aleátorio
    int num_sorteado = -1; // para a escolha da modalidade

    // configurando a interface
    public LotofacilInterface(){
        this.setTitle("Loto fácil"); // definindo o titulo do painel
        this.setSize(400, 200); // tamanho do painel
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // centraliza o painel
        this.setResizable(false); // não permite ao usuário redimensionar o painl

        // configurando o painel
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 10,20));
        painel.setBackground(new Color(125, 158, 255)); // definindo a cor

        // configurando o botões
        Dimension buttonSize = new Dimension(160, 30); // cria um butão padrão
        JbutonAposta1.setPreferredSize(buttonSize); // copia as dimensões do butão padrão
        JbutonAposta2.setPreferredSize(buttonSize); // copia as dimensões do butão padrão
        JbutonAposta3.setPreferredSize(buttonSize); // copia as dimensões do butão padrão

        // comportamento de escuta do botões
        JbutonAposta1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apostarNumero();
            }
        });

        JbutonAposta2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apostarLetra();
            }
        });

        JbutonAposta3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apostarParImpar();
            }
        });

        // adiciona buitões ao painel principal
        painel.add(JbutonAposta1);
        painel.add(JbutonAposta2);
        painel.add(JbutonAposta3);

        this.getContentPane().add(painel);

        // exibindo a janela
        this.setVisible(true);
    }

    // Método para exibir caixa de diálogo para perguntar um número
    private String exibirModalPergunta(String titulo, String mensagem) {
        return JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    // criando os metodos
    private void apostarNumero() {
        try {
            String aposta = exibirModalPergunta("Faça sua aposta!","Digite um número de 1 a 100:");
            num_sorteado = random.nextInt(101);
            int numero = Integer.parseInt(aposta);
            if (numero <= 0 || numero > 100) {
                JOptionPane.showMessageDialog(null, "Aposta Inválida");
            } else if (numero == num_sorteado) {
                JOptionPane.showMessageDialog(null, "Parabéns! Você ganhou R$1000 reais.");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi dessa vez. O número sorteado foi: " + num_sorteado);
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
