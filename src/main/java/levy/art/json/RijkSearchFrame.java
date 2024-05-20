package levy.art.json;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

import com.andrewoid.ApiKey;
import hu.akarnokd.rxjava3.swing.SwingSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import levy.art.RijkService;
import levy.art.RijkServiceFactory;
import levy.art.json.RijksCollection;
import levy.art.json.ArtObject;

public class RijkSearchFrame extends JFrame {
    private JTextField searchField;
    private JButton prevButton, nextButton;
    private JPanel imagesPanel;
    private int currentPage = 1;
    private RijkService rijkService;
    private Disposable disposable;
    private String apiKey;

    public RijkSearchFrame(RijkService rijkService) {
        this.rijkService = rijkService;

        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();



        setTitle("Rijks");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        searchField = new JTextField(20);
        prevButton = new JButton("Previous Page");
        nextButton = new JButton("Next Page");
        topPanel.add(searchField);
        topPanel.add(prevButton);
        topPanel.add(nextButton);
        add(topPanel, BorderLayout.NORTH);

        imagesPanel = new JPanel(new GridLayout(2, 5, 10, 10));
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
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = rijkService.getCollectionByQuery(keyString, searchField.getText(), currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(SwingSchedulers.edt())
                .subscribe(
                        this::handleResponse,
                        Throwable::printStackTrace);
    }

    private void handleResponse(RijksCollection rijksCollection) {
        imagesPanel.removeAll();
        ArtObject[] artObjects = rijksCollection.getArtObjects();
        for (ArtObject art : artObjects) {
            try {
                URL url = new URL(art.getImageUrl());
                ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                JLabel label = new JLabel(icon);
                label.setToolTipText(art.getTitle() + " by " + art.getArtist());
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        new ImageFrame(art.getImageUrl()).setVisible(true);
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
