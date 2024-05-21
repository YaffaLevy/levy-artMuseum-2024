package levy.art.json;


public class ArtObject {
    private String title;
    private String principalOrFirstMaker;
    private ArtImage webImage;

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return principalOrFirstMaker;
    }

    public String getImageUrl() {
        return webImage != null ? webImage.getUrl() : null;
    }

    public ArtImage getWebImage() {
        return webImage;
    }


}
