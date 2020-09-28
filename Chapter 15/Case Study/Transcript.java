// Case Study
// File: Transcript.java
// Keep the chat room transcript

public class Transcript{
   private String str;
	
   public Transcript(){
      str = "";
   }
	
   public void add (String more){
      str = str + more +"\n";
   }
	
   public String toString(){
      return str;
   }
}