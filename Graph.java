import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	
      HashMap<String, Integer> th;
      String[] keys;
      ArrayList<Integer>[] adjList;
      HashMap<String, Double> pageRankWebs;
	
	public void crearGrafo(ListaWebs lista){
		// Post: crea el grafo desde la lista de webs
		//       Los nodos son nombres de webs
		
		
            // Paso 1: llenar â€œthâ€�
            // COMPLETAR CÃ“DIGO
		th=new HashMap<String,Integer>();
		keys=new String[lista.getLength()];
		Iterator<Web> itr= lista.getIterador();
		adjList=new ArrayList[lista.getLength()];
		int indice=0;
		while(itr.hasNext()){
			Web webAct=itr.next();
			th.put(webAct.getNombre(),webAct.getIndice());
			adjList[indice]=webAct.getEnlaces();
			keys[indice]=webAct.getNombre();
			indice++;
			
		}
		
		this.pageRankWebs=this.pageRank();
		

		
		
	}
	
	public void print(){
	   for (int i = 0; i < adjList.length; i++){
		System.out.print("Element: " + i + " " + keys[i] + " --> ");
		for (int k: adjList[i])  System.out.print(keys[k] + " ### ");
		
		System.out.println();
	   }
	}
	
//	public boolean estanConectados(String a1, String a2){
//		/*
//			Pre: ambas webs se encuentran en la lista de webs, al igual que las webs con las que están conectadas
//			Post: devuelve true si hay un camino entre el string a1 y el string a2.
//			Casos de prueba:
//				*No hay camino:
//					-Ninguna web tiene enlaces salientes
//					-No hay camino desde la web a1 a la a2.
//				*Hay camino
//		*/
//		Queue<Integer> porExaminar = new LinkedList<Integer>();
//		
//		int pos1 = th.get(a1);
//		int pos2 = th.get(a2);
//		boolean enc = false;
//		boolean[] examinados = new boolean[th.size()];
//		
//		porExaminar.add(pos1);
//		examinados[pos1]=true;
//		
//		while(!enc && !porExaminar.isEmpty()){
//			int act=porExaminar.remove();
//			if (act==pos2){enc=true;}
//			
//			else{
//				Iterator<Integer> itr=adjList[act].iterator();
//				while(itr.hasNext()){
//					Integer enlaceAct=itr.next();
//					
//					if(examinados[enlaceAct]==false){
//						porExaminar.add(enlaceAct);
//						examinados[enlaceAct]=true;
//					}
//					
//				}
//				
//			}
//			
//		}
//                 // COMPLETAR CÃ“DIGO
//		
//		return enc;
//
//	}
	public boolean estanConectados(String a1, String a2){

		Queue<Integer> porExaminar = new LinkedList<Integer>();

		

		int pos1 = th.get(a1);

		int pos2 = th.get(a2);

		boolean enc = false;

		boolean[] examinados = new boolean[th.size()];

		

		 // COMPLETAR CÓDIGO

		

		//Inicializar todos los elementos de la lista de examinados a false

		for(int i=0; i<examinados.length; i++){

			examinados[i]=false;

		}

		

		porExaminar.add(pos1);

		

		while (!enc && !porExaminar.isEmpty()) {

			pos1=porExaminar.remove();

			ArrayList<Integer>enlaces = adjList[pos1];

			examinados[pos1]=true;

			

			if (enlaces.contains(pos2)) enc = true; 

			else {

				Iterator<Integer> itr=enlaces.iterator();

				while(itr.hasNext()){

					Integer enl=itr.next();

					if (!examinados[enl]) porExaminar.add(enl);

				}

			}

		}

		return enc;



	}


	public ArrayList<String> hayCamino(String a1, String a2){
		Queue<Integer> porExaminar = new LinkedList<Integer>();
		ArrayList<String> camino=new ArrayList<String>();
		
		int[] bp=new int[adjList.length];
		
		int pos1 = th.get(a1);
		int pos2 = th.get(a2);
		boolean enc = false;
		boolean[] examinados = new boolean[th.size()];
		bp[pos1]=-1;
		porExaminar.add(pos1);
		int act=-1;
		examinados[pos1]=true;
		boolean iguales=false;
		if(pos1==pos2){
			iguales=true;
		}
		

			while(!enc && !porExaminar.isEmpty()){
				act=porExaminar.poll();
				if (act==pos2 && !iguales){enc=true;}
				
				else{
					Iterator<Integer> itr=adjList[act].iterator();
					while(itr.hasNext()){
						Integer enlaceAct=itr.next();
						if(iguales && enlaceAct==pos1){
							bp[enlaceAct]=act;
							enc=true;
						}
						if(examinados[enlaceAct]==false){
							porExaminar.add(enlaceAct);examinados[enlaceAct]=true;
							bp[enlaceAct]=act;}
						
					}
			}
		
			}
		
		if(enc){
			ArrayList<String> camino1=new ArrayList<String>();
			
			
			int caminoAct=bp[pos2];
			camino1.add(a2);
				if(iguales){
					do{
						camino1.add(keys[caminoAct]);
						caminoAct=bp[caminoAct];
						}while(caminoAct!=bp[pos2]);
					}
				else{
					while(caminoAct!=-1){
						camino1.add(keys[caminoAct]);
						caminoAct=bp[caminoAct];
				
					} 
				}
				
			
			int indice=camino1.size()-1;
			while(indice>=0){
				camino.add(camino1.get(indice));
				indice--;
			}
			
		}	
		return camino;
		
	}
	
	HashMap<String, Double> pageRank(){
	// Post: el resultado es el valor del algoritmo PageRank para cada web 
	//de la lista de webs
		
		/*
		 * PRi<-1/n
		 * diff<-9999
		 * while diff>e
		 * 		PRnuevo<-calcularNuevo(PR)
		 * 		diff<-resta(PR,PRnuevo))
		 * 		PR<-PRNuevo
		 * 
		 */
		
		HashMap<String, Double> pageRank=new HashMap<String,Double>();
		HashMap<String, Double> pageRank_aux;
		ArrayList<Integer>[] adjListEntrantes=new ArrayList[adjList.length];
		
		
		int indice=0;
		while(indice<adjList.length){
			adjListEntrantes[indice]=new ArrayList<Integer>();
			indice++;
		}
		indice=0;
		while(indice<adjList.length){
			ArrayList<Integer> listaEnlaces=new ArrayList<Integer>();
			listaEnlaces=adjList[indice];
			Iterator<Integer> itr=listaEnlaces.iterator();
			while(itr.hasNext()){
				int actual=itr.next();
				adjListEntrantes[actual].add(indice);
			}
			indice++;
		}
		
		
		
		
		
		
		double valorInicial=1.0/keys.length;
		
		int i2=0;
		
		while(i2<keys.length){
			pageRank.put(keys[i2], valorInicial);
			
			i2++;
		}
		double difTotal=2.5;
		double d= 0.85;
		pageRank_aux=pageRank;
		double diferencia=0.0001;
		
		while(difTotal>diferencia){
			pageRank=new HashMap<String,Double>();
			i2=0;
			difTotal=0;
			while(i2<keys.length){
				double prAct=0;
				ArrayList<Integer> nodosEntrantes=adjListEntrantes[i2];
				Iterator<Integer> itr2=nodosEntrantes.iterator();
				while(itr2.hasNext()){
					int actual=itr2.next();
					prAct=prAct+ pageRank_aux.get(keys[actual])/adjList[actual].size();
					
				}
				prAct=((1-d)/keys.length)+(d*prAct);
				difTotal=difTotal+Math.abs(pageRank_aux.get(keys[i2])-prAct);
				pageRank.put(keys[i2], prAct);
				i2++;
				
			}
			pageRank_aux=pageRank;
			
		}
		return pageRank;
	
	}
	ArrayList<Par> buscar(String palabraClave){
		ArrayList<String> listaWebs=ListaWebs.getListaWebs().word2Webs(palabraClave);//????
		
		ArrayList<Par> listaPRWeb=new ArrayList<Par>();
		//HashMap<String, Double> pageRank=this.pageRank();
		
		int indice=0;
		while(indice<=listaWebs.size()-1){
			Par par=new Par();
			par.web=listaWebs.get(indice);
			par.pagerank=pageRankWebs.get(listaWebs.get(indice));
			listaPRWeb.add(par);
			indice++;
			}
		
		mergeSort(listaPRWeb,0,(listaPRWeb.size()-1));
		
		return listaPRWeb;
	}
	
	ArrayList<Par> buscar(String palabraClave, String palabraClave2){
		ArrayList<Par> listaWebsP1=this.buscar(palabraClave);
		ArrayList<Par> listaWebsP2=this.buscar(palabraClave2);
		HashMap<String, Double> elementosEnLista=new HashMap<String,Double>();
		
		int indice=0;
		while(indice<listaWebsP1.size()){
			Par par=listaWebsP1.get(indice);
			String web=par.web;
			Double rank=par.pagerank;
			elementosEnLista.put(web, rank);
			indice++;	
		}
		
		indice=0;
		while(indice<listaWebsP2.size()){
			Par par=listaWebsP2.get(indice);
			String web=par.web;
			if(elementosEnLista.containsKey(web)==false){
				listaWebsP1.add(par);
			}
			indice++;
		}
		mergeSort(listaWebsP1,0,(listaWebsP1.size()-1));
		return listaWebsP1;
	
	}
	
	public void imprimir (ArrayList<Par> lista){
		Iterator<Par> itr=lista.iterator();
		while(itr.hasNext()){
			Par par=itr.next();
			par.imprimir();
			System.out.println();
		}
	}
	private  void mergeSort(ArrayList<Par> lista,int inicio, int fin) {
		//Pre:la lista de webs contiene al menos un elemento
		//Post: realiza llamadas recursivas para ordenar el diccionario
		if(fin-inicio>0) {
		mergeSort(lista, inicio, (inicio+fin)/2);
		mergeSort(lista, (((inicio+fin)/2)+1),fin);
		listaParOrdenada(lista,inicio,(inicio+fin)/2,fin);
		}
		
	}
	
	private void listaParOrdenada( ArrayList<Par> lista, int inicio, int centro,int fin){
		//Pre:la lista contiene al menos un elemento y las posiciones inicio,centro y fin son válidas.
				//Post: elimina el contenido de la lista de webs.
		ArrayList<Par> listOrd=new ArrayList<Par>();
		
		//listOrd=lista;
		int izq=inicio;
		int der=centro+1;
		int indice= 0;
		
		while(izq<=centro && der<= fin) {
			//System.out.println(lista.get(izq).compareTo(lista.get(der)));     
			if(lista.get(izq).pagerank.compareTo(lista.get(der).pagerank)>=0) {	
				listOrd.add(indice,lista.get(izq));
				indice++;
				izq++;
			}
			else {
				listOrd.add(indice,lista.get(der));
				indice++;
				der++;
				
			}
		}
		
		if(izq>centro) {
			while(der<=fin) {
				listOrd.add(indice,lista.get(der));
				indice++;
				der++;
				
			}
		}
		
		else {
			while(izq<=centro) {
				listOrd.add(indice,lista.get(izq));
				indice++;
				izq++;
				
			}
		}
		for(int j=inicio; j<=fin; j++) {
			lista.set(j, listOrd.get(j-inicio));
		}
	
		
	}
	
	
}
