package levy.art.json;

import com.google.gson.annotations.SerializedName;
import levy.art.json.ArtObject;

public class RijksCollection {
    @SerializedName("artObjects")
    public ArtObject[] artObjects;
}
