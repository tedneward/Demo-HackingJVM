public class Hello {
  static int count = 0;
  static String message = "";
  
  static void displayMessage() {
    count = count + 1;
    message = message + "Hello";
    
    for (int i=0; i<count; i++) {
      System.out.println(message);
      try { Thread.sleep(count * 100); } catch (Exception x) { }
    }
  }
  
  public static void main(String... args) {
    while (count < 5) {
      displayMessage();
    }
  }
}