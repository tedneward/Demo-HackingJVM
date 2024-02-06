import com.sun.jdi.VirtualMachine;
import com.sun.jdi.Bootstrap;
import com.sun.jdi.connect.*;
import java.util.Map;

public class TraceHello {
  public static void main(String... args) throws Exception {
    String mainArgs = "Hello";

    // Find the CommandLineLauncher connector and use it
    LaunchingConnector connector = null;
    for (Connector c : Bootstrap.virtualMachineManager().allConnectors()) {
        if (c.name().equals("com.sun.jdi.CommandLineLaunch")) {
            connector = (LaunchingConnector)c;
        }
    }

    // Set some JDI arguments?
    Map arguments = connector.defaultArguments();

    Connector.Argument mainArg = (Connector.Argument)arguments.get("main");
    mainArg.setValue(mainArgs);

    Connector.Argument optionArg = (Connector.Argument)arguments.get("options");
    optionArg.setValue("-classic");
    
    // Create the VM!
    VirtualMachine vm = connector.launch(arguments);

    vm.setDebugTraceMode(0);

    EventThread eventThread = 
      new EventThread(vm, new String[] {"java.*", "javax.*", "sun.*", "com.sun.*"}, new java.io.PrintWriter(System.out));
    eventThread.setEventRequests(true);
    eventThread.start();

    Process process = vm.process();
    Thread errThread = new StreamRedirectThread("error reader",
                                         process.getErrorStream(),
                                         System.err);
    Thread outThread = new StreamRedirectThread("output reader",
                                         process.getInputStream(),
                                         System.out);
    errThread.start();
    outThread.start();
    vm.resume();

    // Shutdown begins when event thread terminates
    try {
        eventThread.join();
        errThread.join(); // Make sure output is forwarded
        outThread.join(); // before we exit
    } catch (InterruptedException exc) {
        // we don't interrupt
    }
  }
}