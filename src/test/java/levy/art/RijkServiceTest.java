package levy.art;

import com.andrewoid.ApiKey;
import levy.art.json.ArtObject;
import levy.art.json.RijksCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RijkServiceTest {

    ApiKey apiKey = new ApiKey();
    String keyString = apiKey.get();
    @Test
    void getCollectionByPage() {
        // Given
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByPage(keyString, 1).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.getArtObjects());
        assertTrue(response.getArtObjects().length > 0);
        ArtObject firstArtObject = response.getArtObjects()[0];
    }


    @Test
    void getCollectionByQuery() {
        // Given
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByQuery(keyString, "flowers", 4).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.getArtObjects());
        ArtObject firstArtObject = response.getArtObjects()[0];
        assertNotNull(firstArtObject.getWebImage());
        assertNotNull(firstArtObject.getWebImage().getUrl());


    }

    @Test
    void getCollectionByArtist() {
        // Given
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByArtist(keyString, "Johannes Vermeer", 1).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.getArtObjects());
        assertNotNull(response.getArtObjects()[0]);
    }
}

