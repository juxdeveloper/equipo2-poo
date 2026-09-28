class Sistema {
	String nombreHospital;
	int noMedicos;
	int noEnfermeros;
	int noPacientes;

	ArrayList<Medico> m = new ArrayList<>();
	ArrayList<Enfermero> e = new ArrayList<>();
	ArrayList<Paciente> p = new ArrayList<>();


	Sistema(String nombreHospital){
		this.nombreHospital = nombreHospital;
		System.out.println("[add] Sistema / Hospital \"" + nombreHospital + "\"");
	}

	public void registroMedico(Medico medico){
		m.add(medico);
		noMedicos++;
	}	
	
	public void registroEnfermero(Enfermero enfermero){
		e.add(enfermero);
		noEnfermeros++;
	}

	public void registroPaciente(Paciente paciente){
		p.add(paciente);
		noPacientes++;
	}

	boolean asignarPaciente(Paciente paciente, Medico medico){
		if(medico.noPacientes >= 10)
			return true;
		//...
		return false;
	}
	// registroMedico, registroEnfermero, registroPaciente, asignarPaciente
}
