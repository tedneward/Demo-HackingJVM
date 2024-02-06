import java.io.*;
import java.util.*;
import java.net.*;

public class App
{
	public static void main(String[] args)
		throws Exception
	{
		// Load code from classes subdir and instantiate one
		URLClassLoader ucl = new URLClassLoader(new URL[] { new java.io.File("./classes").toURL() });

		/*		
		Enumeration<URL> enums = ucl.findResources("*.class");
		while (enums.hasMoreElements())
		{
			System.out.println(enums.nextElement());
		} // Works but doesn't help
		*/
				
		File classesDir = new File("./classes");
		String[] contents = classesDir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".class");
			}
		});
		for (String s : contents)
		{
			System.out.println(s);
			String classname = s.substring(0, s.indexOf(".class"));
			System.out.println(classname);
			Class c = Class.forName(classname, true, ucl);
			c.newInstance();
		}
	}
}
