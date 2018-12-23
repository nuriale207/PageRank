import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
public class ListaWebs {
	private ArrayList<Web> lista;
	private static ListaWebs miListaWebs=new ListaWebs();

	
	private ListaWebs() {
		this.lista= new ArrayList<Web>();
	}
	
	public void cargarLista(String nomF, String nomEnlaces) {
		//Pre:el nombre de los ficheros debe ser válido y estar seguido por la extensión .txt. Los nombres de las webs se 
				//	  almacenan en una lista donde en cada linea hay una solo nombre de la web seguido por su índice.
				//    En el caso de los enlaces en cada linea se almacena el número del índice de la web seguido por una
				//	flecha que indica una serie de indices separados por espacios.
				//Post:carga las webs contenidas en el fichero nomF en la lista de webs y los enlaces de cada web en el atributo
				//	   listaEnlaces de cada web.
		
		try {
		      Scanner entrada = new Scanner(new FileReader(nomF));
		      Scanner entrada2= new Scanner(new FileReader(nomEnlaces));
		    
		      while (entrada.hasNext()&& entrada2.hasNext()) {
			    	 			         
			         String[] datosWeb=entrada.nextLine().split("\\s+" );			         
			         int inEnlaces= Integer.parseInt(datosWeb[1]);
			         Web webAct= new Web(datosWeb[0],inEnlaces);  
			        
			         
			         //System.out.println(datosWeb[0]);
//			         System.out.println(datosWeb[1]);
   
			         ArrayList<Integer> listaEn= new ArrayList<Integer>();
			         String[] enlace2=entrada2.nextLine().split("\\s+-->\\s+");
         
			         if(enlace2.length>1) {
			        	 String[] enlaceSep=enlace2[1].split("\\s");
				         Integer i=0;
				         while(i<enlaceSep.length) {
				        	 Integer enlace=new Integer(Integer.parseInt(enlaceSep[i]));			        	
				        	 listaEn.add(enlace);
//				        	 System.out.println(enlace.getNombre());
				        	 i=i+1;  	 
				         }	
			        	 
			         }
			        		        
			         webAct.anadirEnlaces(listaEn);			         
			         this.lista.add(webAct);
		      }
		      entrada.close();
		      entrada2.close();
		   }
		   catch(IOException e) {e.printStackTrace();{
		   
		
	}
		   }	
	
	}
	
	public static ListaWebs getListaWebs() {
		return miListaWebs;
	}
	
	public Iterator<Web> getIterador() {
		//Pre:
		//Post:devuelve el iterador de la web.
		return this.lista.iterator();
	}
	
	private Web obtenerElemento(int posicion) {
		//Pre:el diccionario contiene al menos posicion elementos.
		//Post: devuelve la web que está en posicion.
		
		return this.lista.get(posicion); 
	}
	public void anadirWeb(Web pWeb) {
		//Pre:
		//Post: añade la web pWeb a la lista de webs.
		
			this.lista.add(pWeb);
		
		
		
	}
	private void anadirWebEn(Web pWeb, int pIndice) {
		//Pre: la lista contiene pIndice o más elementos.
		//Post: añade la web pWeb en la posición pIndice.
		this.lista.add(pIndice, pWeb);
		
	
	}
	public void imprimir() {
		//Pre:
		//Post:imprime el nombre de las webs en el orden que están almacenadas.
		Iterator <Web> itr= getIterador();
		Web webAct=  new Web("wew",8); 
		while(itr.hasNext()) {
			webAct=itr.next();			
			System.out.println(webAct.getNombre());}
	}
	
	public String id2String(int pId) {
		//Pre: 
		//Post: en caso de que el pId sea válido (menor o igual que el número de elementos de la lista), devuelve el 
		//nombre de la web en esa posición. En caso contrario imprime un mensaje de error.
		if(pId>this.getLength()) {	
			System.out.println("Error:el índice no es válido");
			return "Error:el índice no es válido";
		}
		else {
			return this.lista.get(pId).getNombre();
		}
		
			
		
	}
	
	private Web buscarEn(int inicio, int fin,String pNombre, ListaWebs lista) {
		//Pre: los parámetros inicio y fin equivalen a posiciones reales de la lista.
		//Post: devuelve la web cuyo nombre es pNombre. En caso de que no esté imprime por pantalla un mensaje
		//	    y devuelve una web cuyo nombre es noExiste
		int i=inicio;
		int f=fin;
		int centro=0;
		boolean salir=false;

		Web webCen=new Web("noExiste",-1);
		while(i<=f && !salir) {	
			centro=((i+f)/2);
			webCen= lista.obtenerElemento(centro);	
			String nWeb=webCen.getNombre();
			
			if(nWeb.compareTo(pNombre)>0) {
		
				f=centro-1;
				
			}
			else if(nWeb.compareTo(pNombre)<0) {

				i=centro+1;	
			}
			else if(nWeb.equals(pNombre)) {
				//Web web=new Web(webCen);				
				//indice=web.getIndice();
				salir=true;
	
			}
		}
		if(!salir) {
			System.out.println("El elemento no ha aparecido");
			webCen=new Web("noExiste",-1);
		}
		return webCen;
	}
	
	public int string2Id(String pNombre) {
		//Pre:
		//Post: devuelve el índice de la web cuyo nombre es pNombre. En caso de que la lista no contenga
		//		la web que se busca devuelve -1.
		ListaWebs lista= webOrdenada();
		int inicio=0;
		int fin=lista.getLength()-1;	
		Web web= buscarEn(inicio,fin,pNombre, lista);
		return web.getIndice();
	
	}
	public int getLength() {
		//Pre:
		//Post: devuelve el numero de elementos de la lista.
		return this.lista.size();
		
	}

	
	public ListaWebs webOrdenada() {
		//Pre:
		/*Post: devuelve una ListaWebs cuyos elementos están ordenados, esta lista solo se utiliza internamente.
		 * Es un método público para poder realizar las pruebas.
		 */
		Iterator<Web> itr=getIterador();
		Web webAct=null;
		ListaWebs listaAux= new ListaWebs();
		while(itr.hasNext()) {
			webAct=itr.next();
			listaAux.anadirWeb(webAct);			
		}
		mergeSort(listaAux,0,(listaAux.getLength()-1));
		
		return listaAux;
		
	}
	
	private  void mergeSort(ListaWebs lista,int inicio, int fin) {
		//Pre:la lista de webs contiene al menos un elemento
		//Post: realiza llamadas recursivas para ordenar el diccionario
		if(fin-inicio>0) {
		mergeSort(lista, inicio, (inicio+fin)/2);
		mergeSort(lista, (((inicio+fin)/2)+1),fin);
		webOrdenada(lista,inicio,(inicio+fin)/2,fin);
		}
		
	}
	
	
	
	
	private void webOrdenada( ListaWebs lista, int inicio, int centro,int fin){
		//Pre:la lista contiene al menos un elemento y las posiciones inicio,centro y fin son válidas.
				//Post: elimina el contenido de la lista de webs.
		ListaWebs listOrd=new ListaWebs();
		
		//listOrd=lista;
		int izq=inicio;
		int der=centro+1;
		int indice= 0;
		
		while(izq<=centro && der<= fin) {
			//System.out.println(lista.get(izq).compareTo(lista.get(der)));     
			if(lista.obtenerElemento(izq).getNombre().compareTo(lista.obtenerElemento(der).getNombre())<=0) {	
				listOrd.anadirWebEn(lista.obtenerElemento(izq), indice);
				indice++;
				izq++;
			}
			else {
				listOrd.anadirWebEn(lista.obtenerElemento(der),indice  );
				indice++;
				der++;
				
			}
		}
		
		if(izq>centro) {
			while(der<=fin) {
				listOrd.anadirWebEn(lista.obtenerElemento(der),indice);
				indice++;
				der++;
				
			}
		}
		
		else {
			while(izq<=centro) {
				listOrd.anadirWebEn(lista.obtenerElemento(izq),indice);
				indice++;
				izq++;
				
			}
		}
		for(int j=inicio; j<=fin; j++) {
			lista.sustituir(j, listOrd.obtenerElemento(j-inicio));
		}
	
		
	}
	private void sustituir(int pInd, Web pWeb) {
		//Pre: la web contiene al menos pInd elementos.
		//Post: elimina el contenido de la posicion pInd y guarda en esa posicion pWeb.
		this.lista.set(pInd, pWeb);
	}
	public void resetear() {
		//Pre:
		//Post: elimina el contenido de la lista de webs.
		this.lista= new ArrayList<Web>();
	}
	
	public ArrayList<String> enlacesSalientes(String s){
		//Pre: la web cuyo nombre es s está en la lista de webs.
		//Post: devuelve una lista que contiene los nombres de las webs a los que tiene salida la web s.
		ListaWebs lista= webOrdenada();
		Web web=buscarEn(0,getLength(),s,lista);
		ArrayList<Integer> enlaces=web.getEnlaces();
		ArrayList<String> websEnlazadas=new ArrayList<String>();
		Iterator<Integer> itr= enlaces.iterator();
		while(itr.hasNext()) {
			Integer idAct=itr.next();
			websEnlazadas.add(id2String(idAct));
		}
		return websEnlazadas;
	}
	public ArrayList<String> word2Webs(String s){
		//Pre: 
		//Post: devuelve el nombre de las webs que contienen la palabra s. En caso de que ninguna web tenga la palabra imprime un mensaje de error.
		Iterator<Web> itr= getIterador();
		ArrayList<String> lista= new ArrayList<String>();
		boolean esta=false;
		while(itr.hasNext()){
			Web webAct=itr.next();
			if(webAct.getNombre().contains(s)){
				lista.add(webAct.getNombre());
				esta=true;
			}
		}
		if(!esta){
			System.out.println("Ninguna web contiene esa palabra");
		}
		return lista;
		
	}
	
//	public ArrayList<String> web2Words(String w){
////		Pre: la web indicada está en la lista de webs
////		Post: devuelve las palabras de 3 o más letras que están en el nombre de la web.
////			 en caso de no haber ninguna devuelve una lista vacía.
//		
//		ArrayList<String> lista= new ArrayList<String>();
//		int primero=0;//este indice marca el principio de la secuencia de caracteres que se va a comparar con el diccionario
//		int ultimo=3;//este indice marca el final de la secuencia de caracteres que se va a comparar con el diccionario
//		int numLetras=3;//este indice marca el numero de caracteres que tiene el string que se va a comparar con el diccionario
//		Diccionario.getDiccionario().diccionarioOrdenado();
//		while(numLetras<=w.length()-1){
//			while(ultimo<w.length()-1 && primero<w.length()-1){
//				
//				if(Diccionario.getDiccionario().esta(w.substring(primero, ultimo))){
//					lista.add(w.substring(primero, ultimo));
//					
//				}
//				primero=primero+1;
//				ultimo=ultimo+1;
//			}
//			numLetras++;
//			primero=0;
//			ultimo=numLetras;
//		}
//		return lista;
//
//
//	}
	
	public void guardarWebs(String pFicheroWebs, String pFicheroEnlaces) {
		
		/*
		 * Pre: Los nombres de los ficheros van seguidos de .txt.
		 * Post: Guarda la lista de webs en el fichero cuyo nombre es pFicheroWebs y la lista de enlaces
		 * en el fichero pFicheroEnlaces. En caso de que el fichero con el nombre ya exista escribe por
		 * pantalla un mensaje de error.
		 */
		File ficheroWebs=new File(pFicheroWebs);
		File ficheroEnlaces=new File(pFicheroEnlaces);
		if(ficheroWebs.exists()){
			System.out.println("El fichero de webs ya existe con ese nombre");
			return;
		}
		if(ficheroEnlaces.exists()){
			System.out.println("El fichero de enlaces ya existe con ese nombre");
			return;
		}
		FileWriter webs = null;
		FileWriter enlaces=null;
        PrintWriter pw = null;
        PrintWriter pe=null;
        try
        {
            webs = new FileWriter(pFicheroWebs);
            enlaces=new FileWriter(pFicheroEnlaces);
            pw = new PrintWriter(webs);
            pe=new PrintWriter(enlaces);

            Iterator<Web>itr=getIterador();
			while(itr.hasNext()){
				Web webAct=itr.next();
				ArrayList<Integer> listAct=webAct.getEnlaces();
				
				pw.println(webAct.getNombre()+" "+webAct.getIndice());
				pe.print(webAct.getIndice()+"-->");
				Iterator<Integer> itr2=listAct.iterator();
				while(itr2.hasNext()){
					Integer enlaceAct= itr2.next();
					pe.print(enlaceAct+" ");
		
				}
				pe.println("");
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != webs)
              webs.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
	
}

