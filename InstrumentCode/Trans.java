import java.security.*;
import java.lang.instrument.*;

import javassist.*;

// {{## BEGIN premain ##}}
public class Trans
  implements ClassFileTransformer
{
  public static void premain(String agentArgs, Instrumentation inst)
  {
    inst.addTransformer(new Trans());
  }
  // {{## END premain ##}}

  // {{## BEGIN transform ##}}
  public byte[] transform(ClassLoader loader, String className,
                          Class<?> classBeingRedefined,
                          ProtectionDomain protectionDomain,
                          byte[] classfileBuffer)
  {
  // {{## END transform ##}}
      try
      {
          System.out.println("Transforming " + className);
        
          // {{## BEGIN javassist ##}}
          if (className.equals("Hello"))
          {
              ClassPool cp = ClassPool.getDefault();
              cp.insertClassPath(new ByteArrayClassPath(className, classfileBuffer));
              CtClass cc = cp.get(className);
    
              CtMethod test = cc.getDeclaredMethod("test");
            
              CtMethod newtest = CtNewMethod.copy(test,cc,null);
              newtest.setName("newtest");
    
              cc.addMethod(newtest);
            
              return cc.toBytecode();
          }
          // {{## END javassist ##}}
      }
      catch (Exception x)
      {
          x.printStackTrace();
      }

      return classfileBuffer;
  }
}


















