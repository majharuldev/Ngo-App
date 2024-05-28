package majharul.soft.pkss.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import majharul.soft.pkss.api.Api;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
   private static final String baseUrl = "https://majharulsoft.000webhostapp.com/";
   //  private static final String baseUrl = "https://jsonplaceholder.typicode.com/";
    private static RetrofitClient instance;
    private Retrofit retrofit;

//    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
//    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();


    private RetrofitClient() {
        Gson gson = new Gson();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static RetrofitClient getInstance() {

        if (instance == null) {
            instance = new RetrofitClient();
        }

        return instance;
    }

    public Api getApi() {

        return retrofit.create(Api.class);
    }

}
