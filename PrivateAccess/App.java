import java.lang.reflect.*;

public class App {
  public static void main(String... args)
    throws Exception
  {
    // {{## BEGIN using ##}}
    Point p = new Point(12,12);
    System.out.println(p);
    // {{## END using ##}}

    // {{## BEGIN accessing ##}}
    Class pointClass = p.getClass();
    Field pointX = pointClass.getDeclaredField("x");
    Field pointY = pointClass.getDeclaredField("y");
    
    pointX.setAccessible(true);
    System.out.println(pointX.get(p));
    // {{## END accessing ##}}

    // {{## BEGIN modifying ##}}
    pointX.set(p, 6);
    System.out.println(p);
    // {{## END modifying ##}}

    // {{## BEGIN invoking ##}}
    Method pointInit = 
      pointClass.getDeclaredMethod("init", int.class, int.class);
    pointInit.setAccessible(true);
    pointInit.invoke(p, 24, 24);
    System.out.println(p);
    // {{## END invoking ##}}
  }
}