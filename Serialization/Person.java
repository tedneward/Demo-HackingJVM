import java.io.*;

public class Person implements java.io.Serializable
{
	public Person(String fn, String ln, int a, String ssn)
	{
		this.firstName = fn; this.lastName = ln; this.age = a; this.ssn = ssn;
	}
	
	private void writeObject(ObjectOutputStream stream)
        throws IOException
    {
    	String oldSSN = ssn;
    	ssn = "ENCRYPTED!!" + ssn + "!!ENCRYPTED";
    	stream.defaultWriteObject();
    	ssn = oldSSN;
    }
    
    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException
    {
    	stream.defaultReadObject();
    	// Decrypt ssn here now
    }
	
	public String getFirstName() { return firstName; }
	public String getLastName() { return lastName; }
	public int getAge() { return age; }
	public String getSSN() { return ssn; }
	
	public String toString() 
	{
		return "[Person: firstName=" + firstName + 
			" lastName=" + lastName + 
			" age = " + age + 
			" ssn = " + ssn + "]";
	}
	
	private String firstName;
	private String lastName;
	private int age;
	private String ssn;
}