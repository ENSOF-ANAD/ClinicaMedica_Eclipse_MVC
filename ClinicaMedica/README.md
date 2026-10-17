# Clínica Médica — Java Swing MVC

Projeto acadêmico em Java Swing seguindo o padrão MVC, com cadastro de pacientes em uma interface desktop simples e sem dependências externas.

## Objetivo

Este projeto foi desenvolvido para demonstrar os conceitos básicos de:
- Java Swing para interface gráfica
- Arquitetura MVC (Model-View-Controller)
- CRUD de pacientes
- Organização de classes por pacote

## Requisitos

- JDK 17 ou superior (recomendado 21)
- Eclipse IDE para Java

## Como abrir no Eclipse

1. Descompacte o ZIP do projeto.
2. No Eclipse, acesse: **File > Import > General > Existing Projects into Workspace**.
3. Em **Select root directory**, selecione a pasta `ClinicaMedica` descompactada.
4. Marque o projeto e clique em **Finish**.
5. Abra o arquivo `src/br/com/clinica/app/Main.java`.
6. Execute com **Run As > Java Application**.

## Funcionalidades

- Cadastrar paciente
- Listar pacientes
- Selecionar paciente na tabela
- Atualizar dados do paciente
- Excluir paciente
- Validar campos mínimos (nome e CPF)

## Estrutura do projeto

```text
ClinicaMedica/
├─ src/
│  └─ br/com/clinica/
│     ├─ app/
│     │  └─ Main.java
│     ├─ controller/
│     │  └─ PacienteController.java
│     ├─ model/
│     │  └─ Paciente.java
│     ├─ repository/
│     │  └─ PacienteRepository.java
│     └─ view/
│        └─ PacienteView.java
└─ README.md
```

## Organização em MVC

### Model
A camada de modelo representa os dados do sistema.

- `Paciente.java`: contém os atributos do paciente (`id`, `nome`, `cpf`, `telefone`, `email`) e seus getters/setters.

### Repository
A camada de persistência em memória do projeto.

- `PacienteRepository.java`: guarda os pacientes em uma `List` e oferece métodos para:
  - salvar
  - listar
  - buscar por id
  - excluir
  - gerar próximo identificador

### Controller
A camada que conecta a interface com o modelo e o repositório.

- `PacienteController.java`: valida entradas e executa as operações de cadastro, atualização e exclusão.

### View
A camada que mostra a interface gráfica ao usuário.

- `PacienteView.java`: cria a janela Swing com formulário de cadastro, tabela de pacientes e botões de ação.

### App
Arquivo de inicialização do projeto.

- `Main.java`: cria o repositório, o controlador e abre a tela principal.

## Fluxo de funcionamento

1. O usuário preenche os campos na tela.
2. A `PacienteView` chama o método correspondente no `PacienteController`.
3. O controlador valida os dados e chama o `PacienteRepository`.
4. O repositório atualiza a lista em memória.
5. A view atualiza a tabela para mostrar os dados.

## Observações importantes

- Os dados são mantidos em memória e são perdidos ao fechar o aplicativo.
- A versão atual é simples e didática, sem persistência em banco de dados.
- Não há autenticação, validação completa de CPF, proteção LGPD ou persistência externa nesta etapa.
- Use somente dados fictícios nesta versão.

## Possíveis melhorias futuras

- Persistência em banco de dados (MySQL, PostgreSQL ou SQLite)
- Validação completa de CPF
- Login/autenticação
- Controle de usuários e permissões
- Melhor tratamento de erros e mensagens
- Testes automatizados
- Separação de camadas mais robusta

## Licença

Este projeto é destinado a fins acadêmicos e de estudo.

---

Desenvolvido para fins de aprendizado de Java Swing e arquitetura MVC.
