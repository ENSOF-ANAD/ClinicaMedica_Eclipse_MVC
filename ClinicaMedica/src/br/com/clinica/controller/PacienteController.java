package br.com.clinica.controller;

import java.util.List;
import br.com.clinica.model.Paciente;
import br.com.clinica.repository.PacienteRepository;

public class PacienteController {
    private final PacienteRepository repository;

    public PacienteController(PacienteRepository repository) {
        this.repository = repository;
    }

    private void validar(String nome, String cpf) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("O nome é obrigatório.");
        if (cpf == null || cpf.isBlank())
            throw new IllegalArgumentException("O CPF é obrigatório.");
    }

    public void cadastrar(String nome, String cpf, String telefone, String email) {
        validar(nome, cpf);
        repository.salvar(new Paciente(repository.gerarId(), nome.trim(), cpf.trim(),
                telefone == null ? "" : telefone.trim(),
                email == null ? "" : email.trim()));
    }

    public void atualizar(int id, String nome, String cpf, String telefone, String email) {
        validar(nome, cpf);
        Paciente paciente = repository.buscarPorId(id);
        if (paciente == null) throw new IllegalArgumentException("Paciente não encontrado.");
        paciente.setNome(nome.trim());
        paciente.setCpf(cpf.trim());
        paciente.setTelefone(telefone == null ? "" : telefone.trim());
        paciente.setEmail(email == null ? "" : email.trim());
    }

    public void excluir(int id) {
        if (!repository.excluir(id))
            throw new IllegalArgumentException("Paciente não encontrado.");
    }

    public List<Paciente> listar() { return repository.listar(); }
}
