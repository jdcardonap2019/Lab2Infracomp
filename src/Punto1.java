import java.util.concurrent.ThreadLocalRandom;
public class Punto1 extends Thread{
	//Intervalo de números amplio
	private final static int INT_MAX= 105345;
	//Dimensiones cuadradas
	private final static int DIM=3;
	//Matriz
	private static int[][] matriz=new int[DIM][DIM];
	//Mayor global
	private static int mayor=-1;
	//Mayor local
	private int mayorFila=-1;
	//ID Thread
	private int idThread;
	//Fila a registrar
	private int fila;
	
	//Constructor
	public Punto1(int pIdThread, int pFila){
		this.idThread=pIdThread;
		this.fila=pFila;
	}
	
	//Generar la matriz con números aleatorios
	public static void crearMatriz(){
		for(int i=0;i<DIM;i++)
		{
			for(int j=0;j<DIM;j++)
			{
				matriz[i][j]=ThreadLocalRandom.current().nextInt(0, INT_MAX);
			}
		}
		//Imprimir la matriz
		System.out.println("Matriz: ");
		System.out.println("============");
		imprimirMatriz();
	}
	
	//Imprimir matriz en la consola
	private static void imprimirMatriz()
	{
		for(int i=0;i<DIM;i++)
		{
			for(int j=0;j<DIM;j++)
			{
				System.out.println(matriz[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	@Override
	public void run(){
		for(int j=0;j<DIM;j++)
		{
			if(this.mayorFila<matriz[this.fila][j])
			{
				this.mayorFila=matriz[this.fila][j];
			}
		}
		if(this.mayorFila>mayor)
		{
			try{
				Thread.sleep(250);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			mayor=this.mayorFila;
			String warn = String.format(
					"======== Nuevo maximo encontrado ========= \n" +
					"ID Thread: %d - Maximo local actual: %d - Maximo global: %d \n" +
					"\n",
					this.idThread,
					mayor,
					this.mayorFila
				);
			System.out.println(warn);	
		}
		//Resultados
		String msg=String.format("ID Thread: %d - Maximo Local: %d - Maximo Global: %d", 
				this.idThread,
				this.mayorFila,
				mayor);
		System.out.println(msg);
	}
	
	//Main
	public static void main(String[] args)
	{
		System.out.println("Búsqueda concurrente por una matriz");
		
		//Iniciar la matriz
		Punto1.crearMatriz();
		System.out.println();
		System.out.println("Iniciando la busqueda por la matriz \n");
		
		//Iniciar busqueda
		Punto1[] bThreads=new Punto1[DIM];
		for(int i=0;i<DIM;i++)
		{
			bThreads[i]=new Punto1(i,i);
			bThreads[i].start();
		}
	}
}
