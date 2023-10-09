import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    // cReating GUI components and variables.
    private JPanel buttonPanel;
    private JPanel statsPanel;
    private JTextArea resultTextArea;
    private CustomTextField playerWinsField;
    private CustomTextField computerWinsField;
    private CustomTextField tiesField;
    private int playerWins;
    private int computerWins;
    private int ties;

    public RockPaperScissorsFrame() {
        // creating JFrame settings.
        setTitle("Rock Paper Scissors Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating the GUI components.
        buttonPanel = new JPanel();
        statsPanel = new JPanel();
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setEditable(false);

        // Game option buttons with their associated images.
        JButton rockButton = new JButton("Rock", new ImageIcon("rock.jpeg"));
        JButton paperButton = new JButton("Paper", new ImageIcon("paper.jpeg"));
        JButton scissorsButton = new JButton("Scissors", new ImageIcon("scissors.jpeg"));
        JButton quitButton = new JButton("Quit");

        rockButton.addActionListener(e -> playGame("Rock"));
        paperButton.addActionListener(e -> playGame("Paper"));
        scissorsButton.addActionListener(e -> playGame("Scissors"));
        quitButton.addActionListener(e -> System.exit(0));

        // Adding buttons to the button panel.
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        buttonPanel.add(quitButton);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Game Options"));

        // Initialize the custom text fields for the game stats.
        playerWinsField = new CustomTextField();
        computerWinsField = new CustomTextField();
        tiesField = new CustomTextField();

        // Configuring the stats panel layout and components.
        statsPanel.setLayout(new GridLayout(3, 2));
        statsPanel.add(new JLabel("Player Wins:"));
        statsPanel.add(playerWinsField);
        statsPanel.add(new JLabel("Computer Wins:"));
        statsPanel.add(computerWinsField);
        statsPanel.add(new JLabel("Ties:"));
        statsPanel.add(tiesField);
        //Creating the border to name the panel this is the way that i found when researching
        statsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));

        // Add the main panels to the frame.
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
        add(statsPanel, BorderLayout.SOUTH);
    }

    // This method handles the game logic.
    private void playGame(String playerChoice) {
        // Array to represent the three possible game choices.
        String[] options = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int computerChoiceIndex = random.nextInt(3);
        String computerChoice = options[computerChoiceIndex];

        // Determine the result of the game round.
        String result = determineWinner(playerChoice, computerChoice);

        // Display the result in the text area.
        resultTextArea.append(result + "\n");

        // Update the game stats based on the result.
        if (result.contains("Player wins")) {
            playerWins++;
        } else if (result.contains("Computer wins")) {
            computerWins++;
        } else {
            ties++;
        }

        // Update the statistics display.
        playerWinsField.updateText(playerWins);
        computerWinsField.updateText(computerWins);
        tiesField.updateText(ties);
    }

    // Method to determine the winner based on the player's and computer's choices.
    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if (
                (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            return "Player wins! " + playerChoice + " beats " + computerChoice;
        } else {
            return "Computer wins! " + computerChoice + " beats " + playerChoice;
        }
    }

    // Custom JTextField class to allow easy updating of the displayed statistics.
    private class CustomTextField extends JTextField {
        public CustomTextField() {
            super(10);
            this.setEditable(false);
            this.setText("0");
        }

        // Method to set the text based on an integer value.
        public void updateText(int value) {
            this.setText(Integer.toString(value));
        }
    }
}
