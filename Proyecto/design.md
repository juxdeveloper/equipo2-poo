

| Clase | Atributos | Métodos / Comportamientos |
| :--- | :--- | :--- |
| **Sistema** | `nombreHospital`, `noMedicos`, `noEnfermeros`, `noPacientes` | `registroMedico()`, `registroEnfermero()`, `registroPaciente()`, `asignarPaciente()` *(sobrecargado)* |
| **Médico** | `nombre`, `cedula`, `especialidad`, `noPacientes` | `registroEnSistema()`, `solicitarPaciente()`, `darConsulta()`, `darTratamiento()`, `verListaPacientes()` |
| **Enfermero** | `nombre`, `cedula`, `especialidad`, `noPacientes` | `registroEnSistema()`, `verListaPacientes()`, `darTratamiento()` |
| **Paciente** | `nombre`, `especialidadAtencion` | `registroEnSistema()`, `solicitarConsulta()`, `verTratamiento()` |
## Restricciones
Pacientes se asignan a especialidad donde requieren atención -> Al crear un paciente o ate
