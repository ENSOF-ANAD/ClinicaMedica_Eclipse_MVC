package br.com.clinica.repository;

import java.util.ArrayList;
import java.util.List;
import br.com.clinica.model.Paciente;

public class PacienteRepository {
    private final List<Paciente> pacientes = new ArrayList<>();
    private int proximoId = 1;

    public void salvar(Paciente paciente) { pacientes.add(paciente); }
    public int gerarId() { return proximoId++; }
    public List<Paciente> listar() { return new ArrayList<>(pacientes); }
    public boolean excluir(int id) { return pacientes.removeIf(p -> p.getId() == id); }
    public Paciente buscarPorId(int id) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId() == id) return paciente;
        }
        return null;
    }
}
