package br.com.clinica.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import br.com.clinica.controller.PacienteController;
import br.com.clinica.model.Paciente;

public class PacienteView extends JFrame {
    private static final long serialVersionUID = 1L;
    private final JTextField txtNome = new JTextField();
    private final JTextField txtCpf = new JTextField();
    private final JTextField txtTelefone = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nome", "CPF", "Telefone", "E-mail"}, 0) {
        private static final long serialVersionUID = 1L;
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTable tabela = new JTable(modelo);
    private final PacienteController controller;

    public PacienteView(PacienteController controller) {
        this.controller = controller;
        setTitle("Clínica Médica - Cadastro de Pacientes");
        setSize(850, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        criarInterface();
        atualizarTabela();
    }

    private void criarInterface() {
        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.setBorder(BorderFactory.createTitledBorder("Dados do paciente"));
        formulario.add(new JLabel("Nome:")); formulario.add(txtNome);
        formulario.add(new JLabel("CPF:")); formulario.add(txtCpf);
        formulario.add(new JLabel("Telefone:")); formulario.add(txtTelefone);
        formulario.add(new JLabel("E-mail:")); formulario.add(txtEmail);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnLimpar = new JButton("Limpar");
        JPanel botoes = new JPanel();
        botoes.add(btnSalvar); botoes.add(btnAtualizar);
        botoes.add(btnExcluir); botoes.add(btnLimpar);

        JPanel superior = new JPanel(new BorderLayout(5, 5));
        superior.add(formulario, BorderLayout.CENTER);
        superior.add(botoes, BorderLayout.SOUTH);
        add(superior, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnExcluir.addActionListener(e -> excluir());
        btnLimpar.addActionListener(e -> limpar());
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) preencherFormulario();
        });
    }

    private void salvar() {
        try {
            controller.cadastrar(txtNome.getText(), txtCpf.getText(),
                    txtTelefone.getText(), txtEmail.getText());
            atualizarTabela(); limpar();
            mostrarMensagem("Paciente cadastrado com sucesso!");
        } catch (IllegalArgumentException ex) { mostrarMensagem(ex.getMessage()); }
    }

    private int idSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) return -1;
        return (int) modelo.getValueAt(tabela.convertRowIndexToModel(linha), 0);
    }

    private void atualizar() {
        int id = idSelecionado();
        if (id < 0) { mostrarMensagem("Selecione um paciente."); return; }
        try {
            controller.atualizar(id, txtNome.getText(), txtCpf.getText(),
                    txtTelefone.getText(), txtEmail.getText());
            atualizarTabela(); limpar();
            mostrarMensagem("Paciente atualizado!");
        } catch (IllegalArgumentException ex) { mostrarMensagem(ex.getMessage()); }
    }

    private void excluir() {
        int id = idSelecionado();
        if (id < 0) { mostrarMensagem("Selecione um paciente."); return; }
        int resposta = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir este paciente?", "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);
        if (resposta != JOptionPane.YES_OPTION) return;
        try {
            controller.excluir(id);
            atualizarTabela(); limpar();
            mostrarMensagem("Paciente excluído!");
        } catch (IllegalArgumentException ex) { mostrarMensagem(ex.getMessage()); }
    }

    private void preencherFormulario() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) return;
        int linhaModelo = tabela.convertRowIndexToModel(linha);
        txtNome.setText(modelo.getValueAt(linhaModelo, 1).toString());
        txtCpf.setText(modelo.getValueAt(linhaModelo, 2).toString());
        txtTelefone.setText(modelo.getValueAt(linhaModelo, 3).toString());
        txtEmail.setText(modelo.getValueAt(linhaModelo, 4).toString());
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Paciente p : controller.listar()) {
            modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getCpf(),
                    p.getTelefone(), p.getEmail()});
        }
    }

    private void limpar() {
        tabela.clearSelection();
        txtNome.setText(""); txtCpf.setText("");
        txtTelefone.setText(""); txtEmail.setText("");
        txtNome.requestFocusInWindow();
    }

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}
