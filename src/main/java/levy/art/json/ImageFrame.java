package levy.art.json;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageFrame extends JFrame {
    public ImageFrame(String imageUrl) {
        setTitle("Image Viewer");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);

        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(
                    new ImageIcon(url).getImage().getScaledInstance(800, -1, Image.SCALE_SMOOTH)
            );
            JLabel label = new JLabel(icon);
            JScrollPane scrollPane = new JScrollPane(label);
            add(scrollPane, BorderLayout.CENTER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
