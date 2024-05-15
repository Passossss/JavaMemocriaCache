import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class CacheSimulationGUI extends JFrame implements ActionListener {
    private JTextField tamanhoCacheField;
    private JButton simularButton;

    public CacheSimulationGUI() {
        setTitle("Simulação de Cache");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel tamanhoCacheLabel = new JLabel("Tamanho da Cache:");
        tamanhoCacheField = new JTextField(10);

        JPanel tamanhoPanel = new JPanel();
        tamanhoPanel.add(tamanhoCacheLabel);
        tamanhoPanel.add(tamanhoCacheField);

        simularButton = new JButton("Simular");
        simularButton.addActionListener(this);

        mainPanel.add(tamanhoPanel);
        mainPanel.add(simularButton);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simularButton) {
            String tamanhoCacheStr = tamanhoCacheField.getText();
            try {
                int tamanhoCache = Integer.parseInt(tamanhoCacheStr);
                simularCache(tamanhoCache);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite um número válido para o tamanho da cache.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void simularCache(int tamanhoCache) {
        List<Integer> posicoesMemoriaAcessar = Arrays.asList(33, 3, 11, 5);
        CacheSimulation cacheSim = new CacheSimulation(tamanhoCache);
        cacheSim.mapeamentoDireto(posicoesMemoriaAcessar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CacheSimulationGUI());
    }
}
