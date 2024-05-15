package levy.art;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RijkServiceFactory {

    public RijkService getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.rijksmuseum.nl/api/en/collection/")
                // Configure Retrofit to use Gson to turn the Json into Objects
                .addConverterFactory(GsonConverterFactory.create())
                // Configure Retrofit to use Rx
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(RijkService.class);
    }

}