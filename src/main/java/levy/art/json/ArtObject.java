package levy.art.json;


public class ArtObject {
    private String title;
    private String principalOrFirstMaker;
    private WebImage webImage;

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return principalOrFirstMaker;
    }

    public String getImageUrl() {
        return webImage != null ? webImage.getUrl() : null;
    }

    public WebImage getWebImage() {
        return webImage;
    }

    public static class WebImage {
        private String url;

        public String getUrl() {
            return url;
        }
    }
}
