package schaek.teamcity.trac;

import jetbrains.buildServer.issueTracker.AbstractIssueProviderFactory;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.IssueProvider;

import org.jetbrains.annotations.NotNull;

public class TracIssueProviderFactory extends AbstractIssueProviderFactory {
    
    @SuppressWarnings("deprecation")
    public TracIssueProviderFactory(@NotNull IssueFetcher fetcher) {
        super(fetcher, "trac");
    }

    @Override
    public String getDisplayName() {
        return "Trac";
    }

    @NotNull
    public IssueProvider createProvider() {
        return new TracIssueProvider(myFetcher);
    }
}