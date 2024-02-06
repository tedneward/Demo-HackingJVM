import java.lang.reflect.*;
import java.net.*;

public class Shim {
	public static void main(String[] args)
		throws Exception
	{
		URLClassLoader ucl = new URLClassLoader(
			new URL[] { new URL("http://localhost:8080/App.jar")});
		Class appClass = Class.forName("App", true, ucl);
		Method m = appClass.getDeclaredMethod("main", 
			new Class[] { args.getClass() });
		System.out.println(m);
		m.invoke(null, new Object[] { args });
	}
}
