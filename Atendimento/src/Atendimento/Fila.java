package Atendimento;

import java.util.ArrayList;

public class Fila {
	private ArrayList<String> prioridade;
	private ArrayList<String> normal;
	private int alternador = 0;
	int quantP, quantN;

	public Fila() {
		prioridade = new ArrayList<String>();
		normal = new ArrayList<String>();
		quantP = 0;
		quantN = 0;
	}

	public boolean isEmpty(){
		return sizeGeral() == 0;
	}
	
	public int sizeGeral(){
		return quantP + quantN;
	}

	public void inserirNormal (String paciente) {
		normal.add(paciente);
		quantN++;
	}
	
	public void inserirPrioridade (String paciente) {
		prioridade.add(paciente);
		quantP++;
	}
	
	private void organizaFila (ArrayList<String> fila) {
		ArrayList<String> aux = new ArrayList<String>();
		if (fila.equals(prioridade)) {
			for (int i = 1; i < prioridade.size(); i++) {
				aux.add(prioridade.get(i));
			}
			prioridade = aux;
		} else {
			for (int i = 1; i < normal.size(); i++) {
				aux.add(normal.get(i));
			}
			normal = aux;
		}
	}
	
	public String atenderCliente() {
		String pacienteAtendido = "";
		if (quantP != 0 && alternador == 0) {
			pacienteAtendido = prioridade.get(0);
			organizaFila(prioridade);
			alternador = 1;
			quantP--;
		} else {
			pacienteAtendido = normal.get(0);
			organizaFila(normal);
			alternador = 0;
			quantN--;
		}
		
		return pacienteAtendido;
	}
	
	public String toString() {
		String str = "";
		int ordem = 0, p = 0, n = 0;
		int alterna = alternador;
		
		for (int i = 0; i < (prioridade.size() + normal.size()); i++) {
			ordem++;
			if (n >= normal.size()) {
				str += ordem + " - " + prioridade.get(p++) + "\n";
				continue;
			}
			
			if (p >= prioridade.size()) {
				str += ordem + " - " + normal.get(n++) + "\n";
				continue;
			}
			
			if(alterna == 0) {
				str += ordem + " - " + prioridade.get(p++) + "\n";
				alterna = 1;
			} else {
				str += ordem + " - " + normal.get(n++) + "\n";
				alterna = 0;
			}
		}
		return str;
	}
}