public class RockPaperScissorsRunner {
    public static void main(String[] args) {
        // Run the RockPaperScissorsFrame GUI application
        javax.swing.SwingUtilities.invokeLater(() -> {
            RockPaperScissorsFrame frame = new RockPaperScissorsFrame();
            frame.setVisible(true);
        });
    }
}
