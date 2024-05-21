package levy.art.json;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import levy.art.RijkService;
import levy.art.RijkServiceFactory;


public class RijkSearchFrame extends JFrame {
    private JTextField searchField;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel imagesPanel;
    private int currentPage = 1;
    private RijkService rijkService;
    private Disposable disposable;
    private String apiKey;

    public RijkSearchFrame(RijkService rijkService) {
        this.rijkService = rijkService;

        ApiKey apiKey = new ApiKey();
        this.apiKey = apiKey.get();

        setTitle("Rijks");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        searchField = new JTextField(20);
        prevButton = new JButton("Previous Page");
        nextButton = new JButton("Next Page");
        JPanel topPanel = new JPanel();
        topPanel.add(prevButton);
        topPanel.add(searchField);
        topPanel.add(nextButton);
        add(topPanel, BorderLayout.NORTH);

        imagesPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        add(new JScrollPane(imagesPanel), BorderLayout.CENTER);

        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                searchArt();
            }
        });

        nextButton.addActionListener(e -> {
            currentPage++;
            searchArt();
        });

        searchField.addActionListener(e -> {
            currentPage = 1;
            searchArt();
        });

        searchArt();
    }

    private void searchArt() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        if (searchField.getText().trim().isEmpty()) {

            disposable = rijkService.getCollectionByPage(keyString, currentPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(SwingSchedulers.edt())
                    .subscribe(
                            this::handleResponse,
                            Throwable::printStackTrace);
        } else {

            disposable = rijkService.getCollectionByQuery(keyString, searchField.getText(), currentPage)
                    .subscribeOn(Schedulers.io())
                    .observeOn(SwingSchedulers.edt())
                    .subscribe(
                            this::handleResponse,
                            Throwable::printStackTrace);
        }
    }

    private void handleResponse(RijksCollection rijksCollection) {
        imagesPanel.removeAll();
        ArtObject[] artObjects = rijksCollection.getArtObjects();
        for (ArtObject art : artObjects) {
            try {
                URL url = new URL(art.getImageUrl());
                ImageIcon originalIcon = new ImageIcon(url);

                Image scaledImage = originalIcon.getImage().getScaledInstance(200, -1, Image.SCALE_SMOOTH);

                JLabel label = new JLabel();
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                label.setIcon(imageIcon);
                label.setToolTipText(art.getTitle() + " by " + art.getArtist());

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Yay you clicked me");
                        new ImageFrame(art.getTitle(), art.getArtist(), art.getImageUrl()).setVisible(true);
                    }
                });

                imagesPanel.add(label);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        imagesPanel.revalidate();
        imagesPanel.repaint();
    }

    public static void main(String[] args) {
        RijkService rijkService = new RijkServiceFactory().getService();
        new RijkSearchFrame(rijkService).setVisible(true);
    }
}
