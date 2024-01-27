import javax.swing.*;
import java.awt.*;

public class WelcomePage {

    public WelcomePage() {
        JFrame welcomeFrame = new JFrame("Welcome to Surah Al-Kahf");

        JTextArea translationTextArea = new JTextArea();
        translationTextArea.setEditable(false);
        translationTextArea.setLineWrap(true);
        translationTextArea.setWrapStyleWord(true);

        String surahKahfTranslation = getSurahKahfTranslation();

        translationTextArea.setText(surahKahfTranslation);

        JScrollPane scrollPane = new JScrollPane(translationTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        welcomeFrame.add(scrollPane);

        welcomeFrame.setSize(600, 400);
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setLocationRelativeTo(null); // Center the frame
        welcomeFrame.setVisible(true);
    }

    private String getSurahKahfTranslation() {
        return "1. All praise is due to Allah, who has sent down to His Servant the Book, and has not placed any crookedness in it,\n" +
                "2. an unerringly straight Book, to warn of severe punishment from Him, and to give good news to the believers who do righteous deeds that they will have a generous reward,\n" +
                "3. where they will remain forever,\n" +
                "4. and to warn those who say, \"Allah has taken to Himself a son,\"\n" +
                "5. having no knowledge of it, nor do their forefathers. A grievous word it is that comes out of their mouths. They speak not except a lie.\n" +
                "6. So perhaps, you would kill yourself with grief, sorrowing after them, if they believe not in this presentation.\n" +
                "7. Indeed, We have made all that is on the earth as an ornament for it, so that We may test them, who among them has the best deeds.\n" +
                "8. And surely, We shall turn all that is on it into a barren plain.\n" +
                "9. Do you think that the People of the Cave and the Inscription were of Our signs, marvelous?\n" +
                "10. When the youths sought refuge in the Cave, they said, \"Our Lord! Grant us mercy from Yourself, and bestow upon us right guidance in our affair.\"";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WelcomePage());
    }
}
