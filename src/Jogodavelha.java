import javax.swing.*;
import java.util.Random;

public class Jogodavelha extends JFrame {
    Random random = new Random();


    private void reiniciarjogo() {
        jogadas = 0;
        venceu = false;
        xo = false;
        for (int i = 0; i < 9; i++) {
            bt[i].setText("");
            click[i] = false;
        }
    }

    private void jogadaMaquina() {
        if (jogadas >= 9) {
            return;
        }
        int posicao = random.nextInt(9);

        while (click[posicao]) {
            posicao = random.nextInt(9);
        }

        click[posicao] = true;
        mudar(bt[posicao]);
    }


    int jogadas = 0;
    boolean venceu = false;
    JButton[] bt = new JButton[9];
    boolean xo = false;
    boolean[] click = new boolean[9];
    Jogador jogador1;
    Jogador jogador2;
    JLabel jogadores;


    public Jogodavelha() {


        String sexo1 = JOptionPane.showInputDialog(
                "Escolha o sexo do jogador 1:\n\n"
                        + "Menino 1\n\n" +
                        "Menina 2\n\n"
        );
        String nome1 = "";
        if (sexo1.equals("1")) {
            nome1 = JOptionPane.showInputDialog(
                    "Escolha o seu personagem:\n\n"
                            + "1- Matheus\n\n" +
                            "2 - Chico bento\n" +
                            "3 - Chico"
            );
            if (nome1.equals("1")) {
                nome1 = "Matheus";
            } else if (nome1.equals("2")) {
                nome1 = "Chico Bento";
            } else if (nome1.equals("3")) {
                nome1 = "Chico";
            }
        } else if (sexo1.equals("2")) {
            nome1 = JOptionPane.showInputDialog(
                    "Escolha o seu personagem:\n\n"
                            + "1 - Julia \n"
                            + "2 - Larissa \n"
                            + "3 - Isabela "
            );


        }
        jogador1 = new Jogador(nome1, "O");


        String sexo2 = JOptionPane.showInputDialog(
                "Escolha o sexo do jogador 2:\n\n"
                        + "Menino 1\n\n" +
                        "Menina 2\n\n"
        );
        String nome2 = "";
        if (sexo2.equals("1")) {
            nome2 = JOptionPane.showInputDialog(
                    "Escolha o seu personagem:\n\n"
                            + "1- Matheus \n\n" +
                            "2 - Chico bento \n" +
                            "3 - Chico"
            );
            if (nome2.equals("1")) {
                nome2 = "Matheus";
            } else if (nome2.equals("2")) {
                nome2 = "Chico Bento";
            } else if (nome2.equals("3")) {
                nome2 = "Chico";
            }
        } else if (sexo2.equals("2")) {
            nome2 = JOptionPane.showInputDialog(
                    "Escolha o seu personagem:\n\n"
                            + "1 - Julia \n"
                            + "2 - Larissa \n"
                            + "3 - Isabela "
            );
            if (nome2.equals("1")) {
                nome2 = " Julia ";
            } else if (nome2.equals("2")) {
                nome2 = "Larissa";
            } else if (nome2.equals("3")) {
                nome2 = "Isabela";
            }

        }
        jogador1 = new Jogador(nome1, "O");
        jogador2 = new Jogador(nome2, "X");
        JOptionPane.showMessageDialog(
                null,
                "Jogador 1:" + jogador1.nome + "\n" +
                        "Jogador 2:" + jogador2.nome
        );
        setTitle("Jogo da velha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(250, 100, 700, 500);


        jogadores = new JLabel(
                jogador1.nome + " (" + jogador1.simbolo + ") vs " +
                        jogador2.nome + " (" + jogador2.simbolo + " ) "
        );
        add(jogadores);
        jogadores.setBounds(350, 20, 300, 30);
        setVisible(true);

        int cont = 0;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                bt[cont] = new JButton();
                add(bt[cont]);

                bt[cont].setBounds(
                        100 * i,
                        100 * j,
                        95,
                        95
                );


                cont++;
            }
        }

        for (int i = 0; i < 9; i++) {
            click[i] = false;
        }
        for (int i = 0; i < 9; i++) {
            int posicao = i;
            bt[posicao].addActionListener(new java.awt.event.ActionListener() {
                //lumiere
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {

                    if (click[posicao] == false) {
                        click[posicao] = true;
                        mudar(bt[posicao]);

                        jogadas++;

                        verificarVitoria();
                        if (venceu){
                            int resposta = JOptionPane.showConfirmDialog(null, "Deseja reiniciar o jogo?", "Reiniciar", JOptionPane.YES_NO_OPTION);
                            if (resposta == JOptionPane.YES_OPTION) {

                            }
                            if (resposta == JOptionPane.YES_NO_OPTION ){
                                reiniciarjogo();
                            }
                            return;
                        }

                        if (venceu == false && jogadas < 9) {
                            jogadaMaquina();
                            jogadas++;
                            verificarVitoria();
                        }

                        if (venceu == false && jogadas == 9) {
                            JOptionPane.showMessageDialog(null, " Resultado Empate !!");
                        }

                    }
                }
            });

        }
    }

    public void mudar(JButton btn) {

        if (xo) {

            btn.setText(jogador1.simbolo);
            xo = false;

        } else {

            btn.setText(jogador2.simbolo);
            xo = true;
        }

    }

    public void verificarVitoria() {


        if (bt[0].getText().equals(bt[1].getText())
                && bt[1].getText().equals(bt[2].getText())
                && !bt[0].getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Temos um vencedor !");
            venceu = true;
        }
        if (bt[3].getText().equals(bt[4].getText())
                && bt[4].getText().equals(bt[5].getText())
                && !bt[3].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, " Temos um vencedor !");
            venceu = true;
        }
        if (bt[6].getText().equals(bt[7].getText())
                && bt[7].getText().equals(bt[8].getText())
                && !bt[6].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Temos um vencedor !!");
            venceu = true;
        }
        if (bt[0].getText().equals(bt[3].getText())
                && bt[3].getText().equals(bt[6].getText())
                && !bt[0].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Temos um vencedor !!");
            venceu = true;
        }
        if (bt[0].getText().equals(bt[3].getText())
                && bt[3].getText().equals(bt[6].getText())
                && !bt[0].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Temos um vencedor !!");
            venceu = true;
        }
        if (bt[1].getText().equals(bt[4].getText())
                && bt[4].getText().equals(bt[7].getText())
                && !bt[1].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Temos um vencedor !!");
            venceu = true;
        }
        if (bt[2].getText().equals(bt[5].getText())
                && bt[5].getText().equals(bt[8].getText())
                && !bt[2].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Temos um vencedor !!!");
            venceu = true;
        }
        if (bt[0].getText().equals(bt[4].getText())
                && bt[4].getText().equals(bt[8].getText())
                && !bt[0].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Temos um vencedor !!!");
            venceu = true;
        }
        if (bt[2].getText().equals(bt[4].getText())
                && bt[4].getText().equals(bt[6].getText())
                && !bt[2].getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Temos um vencedor !!!!");
            venceu = true;
        }

    }
}
