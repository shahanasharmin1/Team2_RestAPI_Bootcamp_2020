package tweet;

import base.TwitterAPIClient;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends TwitterAPIClient {
    // http://developer.twitter.com/en/docs/tweets/timelines
    // Tweet Client class that consists of all the different API's of the Twitter tweet
    // POST: Retrieve and engage with tweets....

    //https://api.twitter.com/1.1/statuses/update.json
    private final String CREATE_TWEET_ENDPOINT="/statuses/update.json";
    //https://api.twitter.com/1.1/statuses/destroy/:id.json
    private final String DELETE_TWEET_ENDPOINT="/statuses/destroy.json";
    // https://api.twitter.com/1.1/statuses/user_timeline.json
    private final String GET_USER_TWEET_ENDPOINT="/statuses/user_timeline.json";
    //
    protected final static String API_KEY ="EDc0iI6EzadQzy9a6HOQy8BKN";
    protected final static String API_SECTRET_KEY ="iKDvyYlQOk1HPaladjvGiVbVEOvrwE33JqbtCbcZah4TcFHft3";
    protected final static String ACCESS_TOKEN ="1182730200691425283-1Ueu1yOEN36x1Ubr4DdXbG3urhlvCS";
    protected final static String ACCESS_TOKEN_SECRET= "EBuemQ0NWeYIcfsdzwa3ASysFnNbabnCmB9Cpr3XV8rXy";
    protected final static String BASE_URI ="https://api.twitter.com/1.1";
    protected final static String TWEET_ENDPOINT ="/statuses/update.json";
    protected final static String SEARCH_ENDPOINT ="/users/search.json";


    // GET All tweet information
    public ValidatableResponse getUserTimelineTweets(){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUri+this.GET_USER_TWEET_ENDPOINT)
                .then();
    }
    // Create a tweet from users twitter
    public ValidatableResponse createTweet(String tweet){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status",tweet)
                .when().post(this.baseUri+this.CREATE_TWEET_ENDPOINT)
                .then();
    }
    // Delete a tweet from users twitter
    public ValidatableResponse deleteTweet(Long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",tweetId)
                .when().post(this.baseUri+this.DELETE_TWEET_ENDPOINT)
                .then();
    }
    public Response sentTweet(String status){
        return given().auth().oauth(API_KEY,API_SECTRET_KEY,ACCESS_TOKEN,ACCESS_TOKEN_SECRET)
                .param("status",status).when().post(BASE_URI + TWEET_ENDPOINT).then()
                .statusCode(200).extract().response();
    }
    public Response searchProfile(String q){
        return given().auth().oauth(API_KEY,API_SECTRET_KEY,ACCESS_TOKEN,ACCESS_TOKEN_SECRET)
                .param("q",q).when().get(BASE_URI + SEARCH_ENDPOINT).then()
                .statusCode(200).extract().response();
    }
}