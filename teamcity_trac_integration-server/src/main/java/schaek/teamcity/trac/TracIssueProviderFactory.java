package schaek.teamcity.trac;

import jetbrains.buildServer.issueTracker.AbstractIssueProviderFactory;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.issueTracker.IssueProvider;

import org.jetbrains.annotations.NotNull;

public class TracIssueProviderFactory extends AbstractIssueProviderFactory {
    public TracIssueProviderFactory(@NotNull IssueFetcher fetcher) {
        // Type name usually starts with uppercase character because it is displayed in UI, but not necessarily.
        super(fetcher, "trac","Trac");
    }

    @NotNull
    public IssueProvider createProvider() {
        return new TracIssueProvider(myFetcher);
    }
}