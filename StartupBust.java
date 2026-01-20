import java.util.ArrayList;

public class StartupBust {
    GameHelper helper = new GameHelper();
    ArrayList<Startup> startups = new ArrayList<Startup>();
    int numOfGuesses = 0;

    private void setUpGame() {
        // Create some startups and add them to the list
        Startup startup1 = new Startup();
        startup1.setName("TechNova");
        startups.add(startup1);

        Startup startup2 = new Startup();
        startup2.setName("InnoWave");
        startups.add(startup2);

        Startup startup3 = new Startup();
        startup3.setName("FutureWorks");
        startups.add(startup3);

        // Print instructions
        System.out.println("Your goal is to take down three startups: TechNova, InnoWave, and FutureWorks.");
        System.out.println("Try to do it in the fewest number of guesses!");

        // Set locations for each startup
        for (Startup startup : startups) {
            ArrayList<String> newLocation = helper.placeStartup(3);
            startup.setLocationCells(newLocation);
        }
    }
    public void startPlaying() {
        while (!startups.isEmpty()) {
            String userGuess = helper.getUserInput("Enter a guess (e.g., A3): ");
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";

        for (Startup startup : startups) {
            result = startup.checkYourself(userGuess);
            if (result.equals("hit")) {
                System.out.println("Hit!");
                break;
            }
            if (result.equals("kill")) {
                startups.remove(startup);
                System.out.println("Kill!");
                break;
            }
        }
        if (result.equals("miss")) {
            System.out.println("You missed.");
        }
    }
    private void finishGame() {
        System.out.println("All startups have been taken down! Your total guesses: " + numOfGuesses);
        if (numOfGuesses <= 18) {
            System.out.println("Great job! You are a startup buster!");
        } else {
            System.out.println("You took too long. Better luck next time!");
        }
    }
    public static void main(String[] args) {
        StartupBust game = new StartupBust();
        game.setUpGame();
        game.startPlaying();
    }
}
