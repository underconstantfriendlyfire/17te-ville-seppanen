
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AOC15_01 {

    
    public static void main(String[] args) {
   int floor = 0;
   int base = 0;
   int first = 0;
   String txtt = new String();   
        try {
            FileReader file = new FileReader("src/day1.txt");
            BufferedReader br = new BufferedReader(file);
            txtt = br.readLine();
            System.out.println(txtt);
            System.out.println("File found!");
        } catch (FileNotFoundException ex) {
            System.err.println("File not found...");
        } catch (IOException ex) {
            System.err.println("Something happened...");
        }
    
      for(int i = 0; i < txtt.length();i++){
          if(txtt.charAt(i) == '(' ){
          floor++;    
          }
          else{
              floor--;
          }
          if(floor == -1 && first == 0){
              base = i+1;
              first = 1;
          }
          
      }
      System.out.println(floor);
      System.out.println(base);
    }
    
}
