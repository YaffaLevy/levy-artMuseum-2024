package levy.art.json;

import com.google.gson.annotations.SerializedName;

public class RijksCollection {
    @SerializedName("artObjects")
    private ArtObject[] artObjects;

    public ArtObject[] getArtObjects() {
        return artObjects;
    }

    public void setArtObjects(ArtObject[] artObjects) {
        this.artObjects = artObjects;
    }
}
