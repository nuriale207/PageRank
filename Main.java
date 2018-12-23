import java.util.ArrayList;
import java.util.Random;
import java.security.SecureRandom;
import java.math.BigInteger;
public class Main {
	public static void main (String [ ] args) {
		
//		ListaWebs.getListaWebs().resetear();
//		ListaWebs.getListaWebs().cargarLista("smallindex.txt", "smallpld-arcs-1-N.txt");
//		Graph prueba=new Graph();
//		prueba.crearGrafo(ListaWebs.getListaWebs());
//		prueba.print();
//		
//		System.out.println(prueba.estanConectados("01gtyjrz.com", "0103media.co.uk"));
//		System.out.println(prueba.hayCamino("01gtyjrz.com", "0103media.co.uk"));
//		System.out.println(prueba.estanConectados("013pk.com", "0086k.com"));
//		System.out.println(prueba.hayCamino("013pk.com", "0086k.com"));
//		System.out.println(prueba.estanConectados("0-3ani.ro", "005tourdial.com"));
//		System.out.println(prueba.hayCamino("0-3ani.ro", "005tourdial.com"));
//		
//		ListaWebs.getListaWebs().resetear();
//		ListaWebs.getListaWebs().cargarLista("prueba.txt", "pruebaEnlaces.txt");
//		
//		Graph prueba2=new Graph();
//		prueba2.crearGrafo(ListaWebs.getListaWebs());
//		prueba2.print();
//		System.out.println(prueba2.estanConectados("0-3ani.ro", "0086k.com"));
		
		Web w0,w1,w2,w3,w4,w5;
		Graph g1,g2;
		w0=new Web("youtube.com",0);
		w1=new Web("gmail.com",1);
		w2=new Web("ebay.com",2);
		w3=new Web("amazon.com",3);
		w4=new Web("booking.com",4);
		ArrayList<Integer> enlW1=new ArrayList<Integer>();
		enlW1.add(3);
		enlW1.add(4);
		w0.anadirEnlaces(enlW1);
		
		ArrayList<Integer> enlW2=new ArrayList<Integer>();
		enlW2.add(2);
		w1.anadirEnlaces(enlW2);
		
		ArrayList<Integer> enlW3=new ArrayList<Integer>();
		enlW3.add(3);
		w2.anadirEnlaces(enlW3);
		
		ArrayList<Integer> enlW4=new ArrayList<Integer>();
		enlW4.add(4);
		w3.anadirEnlaces(enlW4);
		
		ArrayList<Integer> enlW5=new ArrayList<Integer>();
		enlW5.add(4);
		enlW5.add(3);
		w4.anadirEnlaces(enlW5);
		
		ListaWebs.getListaWebs().resetear();
		ListaWebs.getListaWebs().anadirWeb(w0);
		ListaWebs.getListaWebs().anadirWeb(w1);
		ListaWebs.getListaWebs().anadirWeb(w2);
		ListaWebs.getListaWebs().anadirWeb(w3);
		ListaWebs.getListaWebs().anadirWeb(w4);
		
		g1=new Graph();
		g1.crearGrafo(ListaWebs.getListaWebs());
		System.out.println(g1.pageRankWebs);
		
		System.out.println(g1.hayCamino("amazon.com", "amazon.com"));
		System.out.println(g1.hayCamino("booking.com", "booking.com"));
		
		System.out.println(g1.pageRank());
		g1.imprimir(g1.buscar("a"));
		g1.imprimir(g1.buscar("com"));
		g1.imprimir(g1.buscar("instagram"));
		
		g1.imprimir(g1.buscar("instagram", "twitter"));
		g1.imprimir(g1.buscar("amazon", "zalando"));
		g1.imprimir(g1.buscar("y", "s"));
		g1.imprimir(g1.buscar("a", "o"));
		
		g2=new Graph();
		ListaWebs.getListaWebs().resetear();
		ListaWebs.getListaWebs().cargarLista("smallindex.txt", "smallpld-arcs-1-N.txt");
		g2.crearGrafo(ListaWebs.getListaWebs());
		System.out.println(g2.pageRank());
		
		g2.imprimir(g2.buscar("a"));
		g2.imprimir(g2.buscar("world", "sea"));
		g2.imprimir(g2.buscar("a","web"));
		

		 
		
		Graph g3=new Graph();
		g3=new Graph();
		ListaWebs.getListaWebs().resetear();
		ListaWebs.getListaWebs().cargarLista("index.txt", "pld-arcs-1-N.txt");
		g3.crearGrafo(ListaWebs.getListaWebs());
    	int cont=1;
    	
    
//    	
//    	while(cont<=1000){
//    		SecureRandom random = new SecureRandom();
//   		 	String text = new BigInteger(16, random).toString(32);//se crea un string aleatorio de 3 caracteres
//   		 	
//
//   		 	g3.buscar(text);
//			cont++;
//		}
    	cont=1;
    	while(cont<=1000){
    		SecureRandom random = new SecureRandom();
   		 	String text = new BigInteger(16, random).toString(32);//se crea un string aleatorio de 3 caracteres
   		 	String text2 = new BigInteger(16, random).toString(32);//se crea un string aleatorio de 3 caracteres
   		 	System.out.println(text);
   		 	System.out.println(text2);

   		 	g3.buscar(text,text2);
			cont++;
		}

		//g3.imprimir(g3.buscar("instagram"));
		
		//g3.imprimir(g3.buscar("world", "sea"));
		
		
	}
}
