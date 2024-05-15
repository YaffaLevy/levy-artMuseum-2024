package levy.art.json;

import com.google.gson.annotations.SerializedName;
import levy.art.json.ArtImage;

public class ArtObject {
    public String title;
    public String longTitle;
    public ArtImage artImage;

    //@SerializedName("principalOrFirstMaker")
    public String principalOrFirstMaker;
}

