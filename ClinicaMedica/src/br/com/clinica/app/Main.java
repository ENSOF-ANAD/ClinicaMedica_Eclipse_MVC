package br.com.clinica.app;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import br.com.clinica.controller.PacienteController;
import br.com.clinica.repository.PacienteRepository;
import br.com.clinica.view.PacienteView;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Mantém o visual padrão do Swing.
            }
            PacienteRepository repository = new PacienteRepository();
            PacienteController controller = new PacienteController(repository);
            new PacienteView(controller).setVisible(true);
        });
    }
}
