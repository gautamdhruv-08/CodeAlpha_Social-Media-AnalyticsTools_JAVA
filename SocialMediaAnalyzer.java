import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import edu.stanford.nlp.simple.*;

import java.util.List;

public class SocialMediaAnalyzer {

    public static void main(String[] args) {
        // Set up Twitter API credentials
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("YOUR_TWITTER_API_KEY")
                .setOAuthConsumerSecret("YOUR_TWITTER_API_SECRET")
                .setOAuthAccessToken("YOUR_TWITTER_ACCESS_TOKEN")
                .setOAuthAccessTokenSecret("YOUR_TWITTER_ACCESS_TOKEN_SECRET");

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        // Search for tweets with a specific hashtag
        String hashtag = "#Java";
        Query query = new Query(hashtag);
        query.setCount(100); // Number of tweets to fetch
        try {
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();

            // Analyze and visualize data
            for (Status tweet : tweets) {
                System.out.println("Tweet: " + tweet.getText());
                System.out.println("User: " + tweet.getUser().getScreenName());
                System.out.println("Retweets: " + tweet.getRetweetCount());
                System.out.println("Favorites: " + tweet.getFavoriteCount());

                // Perform sentiment analysis
                String text = tweet.getText();
                Document doc = new Document(text);
                System.out.println("Sentiment: " + doc.sentences().get(0).sentiment());
                System.out.println("-------------------------------------");
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }
}
