package levy.art;

import com.andrewoid.ApiKey;
import levy.art.json.RijksCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijkServiceTest {

    @Test
    void getArtObjectsByPage() {
        // Given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByPage(keyString, "1").blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertNotNull(response.artObjects[0]);
    }


    @Test
    void getArtObjectsByQuery() {
        // Given
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByQuery(keyString, "flowers", 4).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertNotNull(response.artObjects[0]);
    }

    @Test
    void getArtObjectsByArtist() {
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

