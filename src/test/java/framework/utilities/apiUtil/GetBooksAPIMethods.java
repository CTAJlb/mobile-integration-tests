package framework.utilities.apiUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetBooksAPIMethods {
    @GET("lyrasis/loans")
    Call<APIPageXMLModel> getBooks(@Header("Authorization") String authorization);
}
