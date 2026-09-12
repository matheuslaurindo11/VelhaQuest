import javax.swing.*;

public class Jogodavelha extends JFrame {

    JButton[] bt = new JButton[9];
    boolean xo = false;
    boolean[] click = new boolean[9];
    Jogador jogador1;
    Jogador jogador2;

    public Jogodavelha() {
        jogador1 = new Jogador("Matheus","X");
        jogador2 = new Jogador("Chubby","O");


        setTitle("Jogo da velha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(250, 100, 700, 500);
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
for (int i = 0; i < 9; i++){
    int posicao = i;
    bt[posicao].addActionListener(new java.awt.event.ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {

            if (click[posicao] == false) {

                click[posicao] = true;
                mudar(bt[posicao]);

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
}