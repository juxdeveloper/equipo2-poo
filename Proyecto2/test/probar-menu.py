import subprocess
from pathlib import Path
base=str(Path(__file__).resolve().parents[1])
def run(steps):
    r=subprocess.run(['java','-cp',base+'/test','hospital.Main'],input='\n'.join(steps)+'\n',text=True,capture_output=True,timeout=10)
    assert r.returncode==0 and not r.stderr,r.stderr
    return r.stdout
steps=['D','','10','','6','','7','Reposo e hidratación','','14','','16','1','1','2','','15','1','','17','1','2','','14','','P','3','','6','','7','Control cardiológico','','14','','H','1','','P','1','','14','','0']
s=run(steps)
for t in ['Reposo e hidratación','Control cardiológico','Vespertino','Enfermero vespertino 1','Autorizado:','7. Neurología']:
    assert t in s,t

steps=['1','Hospital Manual','','2','23','Traumatología','','3','Dra. Pérez','300001','23','','4','1','Enf. Ruiz','400001','23','1','','4','2','Jefa Soto','400002','23','2','','5','Vega Solís, Ana','23','','6','','7','Rehabilitación','','14','','1','Hospital Dos','','H','1','','13','','0']
s=run(steps)
assert 'Traumatología | EN_TRATAMIENTO' in s
assert 'Hospital activo: Hospital Dos' in s
assert 'Paciente activo: Sin seleccionar' in s

steps=['1','0','','D','','3','Cancelado','0','','4','2','Cancelado','0','','5','Cancelado','0','','2','0','','9','','0']
s=run(steps)
assert 'Médicos: 3 | Enfermeros: 9 | Pacientes: 6' in s
print('Flujos interactivos correctos: demo, ID 23, turnos, traslado, hospitales independientes y cancelación sin registros parciales.')
