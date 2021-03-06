package schaek.teamcity.trac;

import java.util.regex.Matcher;

import jetbrains.buildServer.issueTracker.AbstractIssueProvider;
import jetbrains.buildServer.issueTracker.IssueFetcher;

import org.jetbrains.annotations.NotNull;

public class TracIssueProvider extends AbstractIssueProvider {
    public TracIssueProvider(@NotNull IssueFetcher fetcher) {
        super("trac", fetcher);
    }
    // Means that issues are in format "PREFIX-123', like in Jira or YouTrack.
    // The prefix is configured via properties, regexp is invisible for users.
    @Override
    protected boolean useIdPrefix() {
        return false;
    }
    @Override
    protected String extractId(String match) {
        Matcher matcher = myPattern.matcher(match);
        matcher.find();
        return matcher.group(1);
    }
}