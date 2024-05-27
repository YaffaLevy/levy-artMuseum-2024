package levy.art;

import com.andrewoid.ApiKey;
import io.reactivex.rxjava3.core.Single;
import levy.art.json.RijksCollection;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RijkService {
    ApiKey apiKey = new ApiKey();
    String keyString = apiKey.get();
    @GET("/api/en/collection")
    Single<RijksCollection> getCollectionByPage(
            @Query("key") String apiKey,
            @Query("p") int pageNumber
    );

    @GET("/api/en/collection")
    Single<RijksCollection> getCollectionByQuery(
            @Query("key") String apiKey,
            @Query("q") String query,
            @Query("p") int pageNumber
    );

    @GET("/api/en/collection")
    Single<RijksCollection> getCollectionByArtist(
            @Query("key") String apiKey,
            @Query("involvedMaker") String artist,
            @Query("p") int pageNumber
    );
}