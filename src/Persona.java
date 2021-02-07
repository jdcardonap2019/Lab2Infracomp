import java.util.concurrent.ThreadLocalRandom;

public class Persona extends Thread{
	
	private static int direccion=0;
	private int id=0;
	private static Pasarela pasarela=new Pasarela();
	public Persona(int num, int id)
	{
		direccion=num;
		this.id=id;
	}
	public void run()
	{
		pasarela.entrar(direccion,id);
		try{
			pasarela.caminar(id);
		}catch(Exception e)
		{
			e.getMessage();
		}
		pasarela.salir(direccion,id);
	}
	public static void main(String[] args)
	{
		Persona[] modelos=new Persona[20];
		for(int i=0;i<10;i++)
		{
			modelos[i]=new Persona(direccion,i);
		}
		for(int i=0;i<10;i++)
		{
			modelos[i].start();
		}
	}
}
