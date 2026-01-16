import java.util.*;

public class SimpleStartupGame {
  public static void main(String [] args){
    SimpleStartup dot = new SimpleStartup();
    Random random = new Random();
    int numero = random.nextInt(5);
    int [] location = new int[3];
    int i = 0;
    while (i < location.length){
      location[i] = numero;
      numero++;
      i++;
    }
    dot.setLocationCells(location);
    
    int numOfGuesses = 0;
    String resultGame = "miss";
    
    while(!resultGame.equals("kill") ){
    Scanner sc = new Scanner(System.in);
    System.out.print("enter number " );
    int guess = sc.nextInt();
    System.out.println(guess);
    numOfGuesses++;
    resultGame = dot.checkYourself(guess);
    }  
    System.out.println("You took " + numOfGuesses + " guesses");
    
      
  }
}
