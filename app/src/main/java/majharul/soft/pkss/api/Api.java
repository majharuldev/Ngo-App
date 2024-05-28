package majharul.soft.pkss.api;

import java.util.List;

import majharul.soft.pkss.model.InvesterHelp;
import majharul.soft.pkss.model.LoginHelper;
import majharul.soft.pkss.model.Member;
import majharul.soft.pkss.model.MemberHelper;
import majharul.soft.pkss.model.PrimiyamAdayHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("conecction.php")
    Call<ResponseBody> CreateUser(

            @Field("name") String name,
            @Field("phone") String phone,
            @Field("password") String password

    );

    @FormUrlEncoded
    @POST("member.php")
    Call<ResponseBody> newMember(
            @Field("id") String id,
            @Field("team") String team,
            @Field("member") String member,
            @Field("name") String name,
            @Field("father") String father,
            @Field("village") String village,
            @Field("phone") String phone,
            @Field("category") String category,
            @Field("userphone") String userphone,
            @Field("paddress") String paddress,
            @Field("nid") String nid,
            @Field("savings") String savings);


    @FormUrlEncoded
    @POST("cdabedon.php")
    Call<ResponseBody> add(
            @Field("name") String name,
            @Field("team") String team,
            @Field("village") String village,
            @Field("father") String father,
            @Field("phone") String phone,
            @Field("category") String category,
            @Field("member") String member,
            @Field("userphone") String userphone,
            @Field("savings") String savings,
            @Field("status") String status);


    @FormUrlEncoded
    @POST("login.php")
    Call<LoginHelper> loginUser(

            @Field("phone") String phone,
            @Field("password") String password

    );


    @GET("view_post.php")
    Call<List<MemberHelper>> getMember(
            @Query("userphone") String userphone

    );


    @GET("cdaday.php")
    Call<List<MemberHelper>> getlist(
            @Query("category") String category

    );


    @GET("primiyam_aday.php")
    Call<List<PrimiyamAdayHelper>> getmember(

            @Query("userphone") String userphone

    );


    @FormUrlEncoded
    @POST("invester.php")
    Call<ResponseBody> CreateInvest(

            @Field("name") String name,
            @Field("village") String village,
            @Field("father") String father,
            @Field("license") String license,
            @Field("phone") String phone

    );

    @GET("/binyogkari_list.php")
    Call<List<InvesterHelp>> getInvester();


    @FormUrlEncoded
    @POST("primyamabedon.php")
    Call<ResponseBody> create(

            @Field("team") String team,
            @Field("name") String name,
            @Field("father") String father,
            @Field("village") String village,
            @Field("phone") String phone,
            @Field("amount") String amount,
            @Field("date") String date,
            @Field("member") String member,
            @Field("category") String category,
            @Field("munafa_lon") String munafa_lon,
            @Field("primiyam_poriman") String primiyam_poriman,
            @Field("primyam_number") String primyam_number,
            @Field("userphone") String userphone,
            @Field("status") String status


    );


    @FormUrlEncoded
    @POST("saving.php")
    Call<ResponseBody> saving(

            @Field("id") String id,
            @Field("savings") String savings

    );


    @FormUrlEncoded
    @POST("primyinput.php")
    Call<ResponseBody> primiyam(

            @Field("id") String id,
            @Field("munafa_lon") String munafa_lon,
            @Field("primyam_number") String primyam_number


    );


    @FormUrlEncoded
    @POST("bibidhkhorc.php")
    Call<ResponseBody> primiyam(

            @Field("category") String category,
            @Field("amount") String amount,
            @Field("remarks") String remarks,
            @Field("userphone") String userphone);


    @FormUrlEncoded
    @POST("admission.php")
    Call<ResponseBody> vortiFee(

            @Field("memberId") String memberId,
            @Field("book") String book,
            @Field("element") String element,
            @Field("balance") String balance,
            @Field("share") String share

    );


    @FormUrlEncoded
    @POST("bankhisab.php")
    Call<ResponseBody> bankhisab(

            @Field("account_no") String account_no,
            @Field("account_name") String account_name,
            @Field("bank_name") String bank_name,
            @Field("balance") String balance);


}
