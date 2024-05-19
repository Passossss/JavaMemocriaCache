import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CacheSimulationGUI extends JFrame implements ActionListener {
    private JTextField tamanhoCacheField;
    private JTextField posicoesMemoriaField;
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

        JLabel posicoesMemoriaLabel = new JLabel("Posições de Memória (separadas por vírgula):");
        posicoesMemoriaField = new JTextField(20);

        JPanel tamanhoPanel = new JPanel();
        tamanhoPanel.add(tamanhoCacheLabel);
        tamanhoPanel.add(tamanhoCacheField);

        JPanel posicoesPanel = new JPanel();
        posicoesPanel.add(posicoesMemoriaLabel);
        posicoesPanel.add(posicoesMemoriaField);

        simularButton = new JButton("Simular");
        simularButton.addActionListener(this);

        mainPanel.add(tamanhoPanel);
        mainPanel.add(posicoesPanel);
        mainPanel.add(simularButton);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simularButton) {
            String tamanhoCacheStr = tamanhoCacheField.getText();
            String posicoesMemoriaStr = posicoesMemoriaField.getText();
            try {
                int tamanhoCache = Integer.parseInt(tamanhoCacheStr);
            List<Integer> posicoesMemoriaAcessar = Arrays.stream(posicoesMemoriaStr.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            simularCache(tamanhoCache, posicoesMemoriaAcessar);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite números válidos para o tamanho da cache e posições de memória.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        }
    }

    private void simularCache(int tamanhoCache, List<Integer> posicoesMemoriaAcessar) {
        CacheSimulation cacheSim = new CacheSimulation(tamanhoCache);
        cacheSim.mapeamentoDireto(posicoesMemoriaAcessar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CacheSimulationGUI());
    }
}