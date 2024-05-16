package levy.art;

import com.andrewoid.ApiKey;
import levy.art.json.ArtObject;
import levy.art.json.RijksCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijkServiceTest {

    @Test
    void getCollectionByPage() {
        // Given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByPage(keyString, 1).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertTrue(response.artObjects.length > 0);
        ArtObject firstArtObject = response.artObjects[0];
    }


    @Test
    void getCollectionByQuery() {
        // Given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByQuery(keyString, "flowers", 4).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);

        ArtObject firstArtObject = response.artObjects[0];
        assertNotNull(firstArtObject.webImage);
        assertNotNull(firstArtObject.webImage.url);


    }

    @Test
    void getCollectionByArtist() {
        // Given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByArtist(keyString, "Johannes Vermeer", 1).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertNotNull(response.artObjects[0]);
    }
}

