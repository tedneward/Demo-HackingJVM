import java.io.*;

public class App
{
	public static void main(String[] args)
		throws Exception
	{
		FileOutputStream fos = new FileOutputStream("michaeldata.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person michael = new Person("Michael", "Case", 32, "123-45-6789");
		
		System.out.println(michael);
		
		oos.writeObject(michael);
		
		oos.close();
		fos.close();
		
		FileInputStream fis = new FileInputStream("michaeldata.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Person michael2 = (Person)ois.readObject();
		
		ois.close();
		fis.close();
		
		System.out.println(michael2);
	}
}