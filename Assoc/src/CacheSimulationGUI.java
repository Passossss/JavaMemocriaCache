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
    private JTextField tamanhoConjuntoField; // Novo campo para o tamanho do conjunto
    private JButton simularButton;
    private JTextArea resultadosArea;

    public CacheSimulationGUI() {
        setTitle("Simulação de Cache");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        JLabel headerLabel = new JLabel("Mapeamento Associativo por Conjunto em Java", JLabel.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 16));
        headerLabel.setForeground(Color.black);

        JLabel alunosLabel = new JLabel("Alunos: Gustavo Passos, Luis Otavio", JLabel.CENTER);
        alunosLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        alunosLabel.setForeground(Color.DARK_GRAY);

        JLabel tamanhoCacheLabel = new JLabel("Tamanho da Cache:");
        tamanhoCacheField = new JTextField(10);

        JLabel tamanhoConjuntoLabel = new JLabel("Tamanho do Conjunto:");
        tamanhoConjuntoField = new JTextField(10);

        JLabel posicoesMemoriaLabel = new JLabel("Posições de Memória (separadas por vírgula):");
        posicoesMemoriaField = new JTextField(20);

        JPanel tamanhoPanel = new JPanel();
        tamanhoPanel.setBackground(Color.LIGHT_GRAY);
        tamanhoPanel.add(tamanhoCacheLabel);
        tamanhoPanel.add(tamanhoCacheField);

        JPanel tamanhoConjuntoPanel = new JPanel();
        tamanhoConjuntoPanel.setBackground(Color.LIGHT_GRAY);
        tamanhoConjuntoPanel.add(tamanhoConjuntoLabel);
        tamanhoConjuntoPanel.add(tamanhoConjuntoField);

        JPanel posicoesPanel = new JPanel();
        posicoesPanel.setBackground(Color.LIGHT_GRAY);
        posicoesPanel.add(posicoesMemoriaLabel);
        posicoesPanel.add(posicoesMemoriaField);

        simularButton = new JButton("Simular");
        simularButton.setBackground(Color.GRAY);
        simularButton.setForeground(Color.WHITE);
        simularButton.addActionListener(this);

        resultadosArea = new JTextArea(15, 30);
        resultadosArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadosArea);

        mainPanel.add(headerLabel);
        mainPanel.add(alunosLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(tamanhoPanel);
        mainPanel.add(tamanhoConjuntoPanel);
        mainPanel.add(posicoesPanel);
        mainPanel.add(simularButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(scrollPane);

        add(mainPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simularButton) {
            String tamanhoCacheStr = tamanhoCacheField.getText();
            String tamanhoConjuntoStr = tamanhoConjuntoField.getText(); // Obtém o tamanho do conjunto
            String posicoesMemoriaStr = posicoesMemoriaField.getText();
            try {
                int tamanhoCache = Integer.parseInt(tamanhoCacheStr);
                int tamanhoConjunto = Integer.parseInt(tamanhoConjuntoStr); // Converte para int
                List<Integer> posicoesMemoriaAcessar = Arrays.stream(posicoesMemoriaStr.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
                simularCache(tamanhoCache, tamanhoConjunto, posicoesMemoriaAcessar); // Chama o método com o tamanho do conjunto
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite números válidos para o tamanho da cache, tamanho do conjunto e posições de memória.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void simularCache(int tamanhoCache, int tamanhoConjunto, List<Integer> posicoesMemoriaAcessar) {
        CacheSimulationAssociativoConjunto cacheSim = new CacheSimulationAssociativoConjunto(tamanhoCache, tamanhoConjunto);
        cacheSim.setResultadosArea(resultadosArea);
        cacheSim.mapeamentoAssociativoConjunto(posicoesMemoriaAcessar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CacheSimulationGUI());
    }
}
