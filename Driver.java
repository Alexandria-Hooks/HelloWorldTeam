public class Driver {
    private static String level = "1";
    public static void main(String[] args) {
        // Create UI and obtain user chosen level
        new UI(level1 -> {
            level = level1;
            
            // check input
            if (!(level.equals("1") || level.equals("2") || level.equals("3") || level.equals("4"))) {
                System.out.println("How are you going to do cryptography if you can't even read?");
            } else { // Game Loop
                new Game(Integer.parseInt(level), 0, 0);
            }
        });
    }
}

