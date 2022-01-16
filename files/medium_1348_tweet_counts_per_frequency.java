import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class TweetCounts {

    private HashMap<String, List<Integer>> tweetRecordsHash;

    public TweetCounts() {
        tweetRecordsHash = new HashMap<String, List<Integer>>();
    }

    public void recordTweet(String tweetName, int time) {

        List<Integer> tweets = tweetRecordsHash.get(tweetName);
        if (tweets == null) {
            tweets = new ArrayList<Integer>();
        }
        tweets.add(time);
        Collections.sort(tweets);

        tweetRecordsHash.put(tweetName, tweets);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> tweets = tweetRecordsHash.get(tweetName);

        List<Integer> recordCountPerPeriod = new ArrayList<Integer>();

        int freqToTime = freqToTime(freq);
        int periodStartTime = startTime;
        int periodEndTime = periodStartTime + freqToTime;

        int i = 0;
        while (periodStartTime <= endTime) {

            if (periodEndTime > endTime) {
                periodEndTime = endTime;
            }

            int periodCount = 0;
            while (i < tweets.size()) {
                int tweetTime = tweets.get(i);

                if (tweetTime < periodStartTime) {
                    i++;
                    continue;
                }

                if (tweetTime > periodEndTime) {
                    break;
                }
                periodCount++;
                i++;

            }

            recordCountPerPeriod.add(periodCount);

            periodStartTime = periodEndTime + 1;
            periodEndTime = periodStartTime + freqToTime;

        }
        return recordCountPerPeriod;
    }

    private int freqToTime(String freq) {
        if (freq.equals("minute")) {
            return 59;
        } else if (freq.equals("hour")) {
            return 3599;
        } else if (freq.equals("day")) {
            return 86399;
        } else {
            // System.out.println("illegal freq parameter");
            return -1;
        }
    }
}