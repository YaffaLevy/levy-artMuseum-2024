package levy.art;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageFrame extends JFrame {
    public ImageFrame(String title, String artist, String imageUrl) {
        setTitle(title + " by " + artist);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);

        try {
            URL url = new URL(imageUrl);
            Image image = ImageIO.read(url);
            Image scaledImage = image.getScaledInstance(200, -1, Image.SCALE_DEFAULT);
            ImageIcon icon = new ImageIcon(scaledImage);

            JLabel label = new JLabel(icon);
            JScrollPane scrollPane = new JScrollPane(label);
            add(scrollPane, BorderLayout.CENTER);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}



