package submenuesycoso;

import java.util.ArrayList;

public class MisMetodos {
	public static double promedio(ArrayList<Alumno> lista){
		int suma = 0;
		for(Alumno n : lista)
			suma += n.notaFinal;
		return suma / (double)(lista.size());
	}
	
	public static ArrayList<Integer> alumnosMasAltos(ArrayList<Alumno> lista){
		int maximo = lista.get(0).notaFinal;
		ArrayList<Integer> listaDeAlumnosConNotaFinalMaxima = new ArrayList<Integer>();
		for(int i = 1; i < lista.size(); ++i){
			if(lista.get(i).notaFinal > maximo) {
				maximo = lista.get(i).notaFinal;
			}
		}
		
		for(Alumno a : lista){
			if(a.notaFinal == maximo){
				listaDeAlumnosConNotaFinalMaxima.add(a.id);
			}
		}
		return listaDeAlumnosConNotaFinalMaxima;
	}
	
	public static ArrayList<Integer> alumnosMasBajos(ArrayList<Alumno> lista){
		int minimo = lista.get(0).notaFinal;
		ArrayList<Integer> listaDeAlumnosConNotaFinalMinima = new ArrayList<Integer>();
		for(int i = 1; i < lista.size(); ++i){
			if(lista.get(i).notaFinal < minimo) {
				minimo = lista.get(i).notaFinal;
			}
		}
		
		for(Alumno a : lista){
			if(a.notaFinal == minimo){
				listaDeAlumnosConNotaFinalMinima.add(a.id);
			}
		}
		return listaDeAlumnosConNotaFinalMinima;
	}
}
