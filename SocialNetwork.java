
package twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set
public class SocialNetwork {
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		Map<String, Set<String>> FollowsGraph = new HashMap<String, Set<String>>();
		Set<String> authorStrings = new HashSet<String>();
		for (int i = 0; i < tweets.size(); i++) {
			authorStrings.add(tweets.get(i).getAuthor().toLowerCase());
		}
		for (String name : authorStrings) {
			Set<String> MentionedUsers = Extract.getMentionedUsers(Filter.writtenBy(tweets, name));
			FollowsGraph.put(name, MentionedUsers);
		}
		return FollowsGraph;
	}
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {
		List<String> influencersString = new ArrayList<String>();
		Map<String, Integer> influnencersMap = new HashMap<String, Integer>();
		Set<String> authorStrings = followsGraph.keySet();
		for (String author : authorStrings) {
			for (String user : followsGraph.get(author)) {
				if (!influnencersMap.containsKey(user))
					influnencersMap.put(user, 1);
				else
					influnencersMap.put(user, influnencersMap.get(user));
			}
		}
		Set<String> userStrings = influnencersMap.keySet();
		for (String user : userStrings) {
			int i = 0;
			while (i < influencersString.size()
					&& influnencersMap.get(user) < influnencersMap.get(influencersString.get(i)))
				i++;
			influencersString.add(i, user);
		}
		return influencersString;
	}

}
