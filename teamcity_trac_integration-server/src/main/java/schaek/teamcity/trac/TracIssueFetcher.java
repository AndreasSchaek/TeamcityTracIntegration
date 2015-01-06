package schaek.teamcity.trac;


import java.io.InputStream;

import jetbrains.buildServer.issueTracker.AbstractIssueFetcher;
import jetbrains.buildServer.issueTracker.IssueData;
import jetbrains.buildServer.util.cache.EhCacheUtil;

import org.apache.commons.httpclient.Credentials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TracIssueFetcher extends AbstractIssueFetcher {

    public TracIssueFetcher(EhCacheUtil cacheUtil) {
        super(cacheUtil);
    }

    public IssueData getIssue(@NotNull String host, @NotNull String id, @Nullable final Credentials credentials) throws Exception {
        final String url = getUrl(host, id);
        return getFromCacheOrFetch(url, new FetchFunction() {
            @NotNull
            public IssueData fetch() throws Exception {
                InputStream body = fetchHttpFile(url, credentials);
                IssueData result = null;
                if (body != null) {
                    result = parseXml(body, url);
                }
                if (result == null) {
                    throw new RuntimeException("Failed to fetch issue from \"" + url + "\"");
                }
                return result;
            }
        });
    }
    
    public String getUrl(String host, String id) {
        System.out.println(host);
        System.out.println(id);
        return null;
    }
    private IssueData parseXml(InputStream body, String url) {
        return null;
    }
}
