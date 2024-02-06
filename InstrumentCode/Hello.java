import java.lang.reflect.*;

public class Hello {
  public static void test() {
    System.out.println("Hello.test");
  }

  public static void main(String[] args)
    throws Exception 
  {
    System.out.println("Howdy");
    
    test();
    
    Class Hello_class = Hello.class;
    Method newmain =
      Hello_class.getDeclaredMethod("newtest",
                                    new Class[] { });
    newmain.invoke(args);
  }
}