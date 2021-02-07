
public class Pasarela {
	private static int contadorIzquierdaDerecha=0;
	private static int contadorDerechaIzquierda=0;
	
	public synchronized void entrar(int direccion,int id)
	{
	//Direccion en 0 es de derecha a izquierda y 1 de izquierda a derecha
		if(contadorIzquierdaDerecha!=0 && direccion==0)
		{
			try{
				wait();
			}catch(Exception e){}
		}else if(contadorDerechaIzquierda!=0 && direccion==1)
		{
			try{
			wait();
			}catch(Exception e){}
		}
		else if(contadorIzquierdaDerecha==0 && direccion==0)
		{
			System.out.println("Persona con id "+id+" entrando en direccion "+direccion+".");
			contadorDerechaIzquierda++;
		}
		else if(contadorDerechaIzquierda==0 && direccion==1)
		{
			System.out.println("Persona con id "+id+" entrando en direccion "+direccion+".");
			contadorIzquierdaDerecha++;
		}
	}
	public void salir(int direccion,int id)
	{
		if(direccion==0)
		{
			System.out.println("Persona con id "+id+" saliendo de la direccion "+direccion+".");
			contadorDerechaIzquierda--;
		}else if(direccion==1)
		{
			System.out.println("Persona con id "+id+" saliendo de la direccion "+direccion+".");
			contadorIzquierdaDerecha--;
		}
		
	}
	public synchronized void caminar(int id) throws Exception
	{
		try{
			System.out.println("Persona con id "+id+" caminando.");
			Thread.sleep(200);
		}catch(Exception e){e.getMessage();}
	}
}
