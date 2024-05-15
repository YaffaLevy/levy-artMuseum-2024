package levy.art;

import io.reactivex.rxjava3.core.Single;
import levy.art.json.RijksCollection;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijkService {
    @GET("/api/en/collection")
    Single<RijksCollection> getCollectionByPage(
            @Query("key") String apiKey,
            @Query("p") int pageNumber,
            @Query("ps") int resultsPerPage
    );

    @GET("/api/en/collection")
    Single<RijksCollection> getCollectionByQuery(
            @Query("key") String apiKey,
            @Query("q") String query,
            @Query("p") int pageNumber,
            @Query("ps") int resultsPerPage
    );

    @GET("/api/en/collection")
    Single<RijksCollection> getCollectionByArtist(
            @Query("key") String apiKey,
            @Query("involvedMaker") String artist,
            @Query("p") int pageNumber,
            @Query("ps") int resultsPerPage
    );
}