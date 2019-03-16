/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

	/**
	 * Get the time period spanned by tweets.
	 *
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of every
	 *         tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {
		Instant startInstant = tweets.get(0).getTimestamp(), endInstant = tweets.get(1).getTimestamp();
		for (int i = 0; i < tweets.size(); i++) {
			if (tweets.get(i).getTimestamp().compareTo(startInstant) <= 0)
				startInstant = tweets.get(i).getTimestamp();
			if (tweets.get(i).getTimestamp().compareTo(endInstant) >= 0)
				endInstant = tweets.get(i).getTimestamp();
		}
		Timespan times = new Timespan(startInstant, endInstant);
		return times;
		// throw new RuntimeException("not implemented");
	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 *
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets. A
	 *         username-mention is "@" followed by a Twitter username (as defined by
	 *         Tweet.getAuthor()'s spec). The username-mention cannot be immediately
	 *         preceded or followed by any character valid in a Twitter username.
	 *         For this reason, an email address like bitdiddle@mit.edu does NOT
	 *         contain a mention of the username mit. Twitter usernames are
	 *         case-insensitive, and the returned set may include a username at most
	 *         once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {
		Set<String> MentionedUsers = new HashSet<String>();
		int size = tweets.size();
		Pattern pattern = Pattern.compile("(^|[^A-Za-z0-9_-]+)@([A-Za-z0-9_-]+)");// �����������ַ�����������ʽ
		Pattern pattern1 = Pattern.compile("(^|[^A-Za-z0-9_-]+)@([A-Za-z0-9_-]+\\.)");// �����������ַ�����������ʽ
		for (int i = 0; i < size; i++) {
			String textString = tweets.get(i).getText();
			Matcher mentionedUsers = pattern.matcher(textString);
			Matcher mentionedUsers1=pattern1.matcher(textString);
			while (mentionedUsers.find()&&!mentionedUsers1.find()) {
				String nowString = new String(mentionedUsers.group(2).toString().toLowerCase());// ȫ��Сд
				MentionedUsers.add(nowString);// ��ƥ�䵽��mentionedUsers����ӵ�MentionedUsers
			}
		}
		return MentionedUsers;
		// throw new RuntimeException("not implemented");
	}

}
 *
