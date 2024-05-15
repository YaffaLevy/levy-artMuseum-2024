package levy.art;

import levy.art.json.RijksCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RijkServiceTest {

    @Test
    void getArtObjectsByPage() {
        // Given
        int pageNumber = 1;
        int resultsPerPage = 10;
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByPage(apiKey, pageNumber, resultsPerPage).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertTrue(response.artObjects.length > 0);
    }

    @Test
    void getArtObjectsByQuery() {
        // Given
        String query = "Van Gogh";
        int pageNumber = 1;
        int resultsPerPage = 10;
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByQuery(apiKey, query, pageNumber, resultsPerPage).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertTrue(response.artObjects.length > 0);
    }

    @Test
    void getArtObjectsByArtist() {
        // Given
        String artist = "Rembrandt";
        int pageNumber = 1;
        int resultsPerPage = 10;
        RijkService service = new RijkServiceFactory().getService();

        // When
        RijksCollection response = service.getCollectionByArtist(apiKey, artist, pageNumber, resultsPerPage).blockingGet();

        // Then
        assertNotNull(response);
        assertNotNull(response.artObjects);
        assertTrue(response.artObjects.length > 0);
    }
}

