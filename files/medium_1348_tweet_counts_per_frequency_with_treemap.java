import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;


class TweetCounts {

    private HashMap<String, TreeMap<Integer,Integer>> tweetRecordsHash;

    public TweetCounts() {
        tweetRecordsHash = new HashMap<String, TreeMap<Integer, Integer>>();
    }

    public void recordTweet(String tweetName, int time) {

        TreeMap<Integer, Integer> tweets = tweetRecordsHash.get(tweetName);

        if (tweets == null) {
            tweets = new TreeMap<Integer, Integer>();
            tweets.put(time,1);
            tweetRecordsHash.put(tweetName, tweets);
        }
        else {
            tweets.put(time, tweets.getOrDefault(time, 0) + 1);
        }
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        TreeMap<Integer, Integer> tweets = tweetRecordsHash.get(tweetName);
         int[] tweetsArr = tweets.keySet().stream().mapToInt(Integer::intValue).toArray(); //여기
        //int[] tweetsArr = tweets.keySet().parallelStream().mapToInt(Integer::intValue).toArray(); // 여기
        //Set<Integer> tempset = tweets.keySet();
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
            while (i < tweetsArr.length) {

                int tweetTime = tweetsArr[i];

                if (tweetTime < periodStartTime) {
                    i++;
                    continue;
                }

                if (tweetTime > periodEndTime) {
                    break;
                }

                periodCount = periodCount + tweets.get(tweetTime);
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