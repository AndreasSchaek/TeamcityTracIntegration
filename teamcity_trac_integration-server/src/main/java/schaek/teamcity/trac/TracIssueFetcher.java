package schaek.teamcity.trac;


import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jetbrains.buildServer.issueTracker.AbstractIssueFetcher;
import jetbrains.buildServer.issueTracker.IssueData;
import jetbrains.buildServer.util.cache.EhCacheUtil;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TracIssueFetcher extends AbstractIssueFetcher {
    private static final Pattern PATTERN_ISSUE_TITLE      = Pattern.compile("<span *class *= *\"summary\">(.*?)</span>");
    private static final Pattern PATTERN_ISSUE_STATE      = Pattern.compile("<span *class *= *\"trac-status\">\\n *<a href=.*?>(\\w*)</a>");
    private static final Pattern PATTERN_ISSUE_RESOLUTION = Pattern.compile("<span *class *= *\"trac-resolution\">\\n.* *\\(<a href=.*?>(\\w*)</a>");
    
    public TracIssueFetcher(EhCacheUtil cacheUtil) {
        super(cacheUtil);
    }

    public IssueData getIssue(@NotNull String host, final @NotNull String id, @Nullable final Credentials credentials) throws Exception {
        final String url = getUrl(host, id);
        return getFromCacheOrFetch(url, new FetchFunction() {
            @NotNull
            public IssueData fetch() throws Exception {
                InputStream issueInputStream = fetchHttpFile(url, credentials);
                IssueData result = null;
                if (issueInputStream != null) {
                    result = parseInputStream(id, url, issueInputStream);
                }
                if (result == null) {
                    throw new RuntimeException("Failed to fetch issue from \"" + url + "\"");
                }
                return result;
            }
        });
    }
    
    public String getUrl(String host, String id) {
        try {
            URL url = new URL(new URL(host),"ticket/"+Integer.parseInt(id));
            return url.toString();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private IssueData parseInputStream(String id, String url, InputStream inputStream) {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer);
            String htmlContent = writer.toString();
            String summary  = null;
            String state    = null;
            String issueURL = null;
            String resolution = null;
            Matcher matcher;
            
            matcher = PATTERN_ISSUE_TITLE.matcher(htmlContent);
            if(matcher.find()){
                summary = matcher.group(1);
            }
            matcher = PATTERN_ISSUE_STATE.matcher(htmlContent);
            if(matcher.find()){
                state = matcher.group(1);
            }
            matcher = PATTERN_ISSUE_RESOLUTION.matcher(htmlContent);
            if(matcher.find()){
                resolution = matcher.group(1);
            }
            IssueData issueData;
            if(resolution != null){
                issueData = new IssueData(id, summary, state, url, true);                
            }
            else{
                issueData = new IssueData(id, summary, state, url, false);
            }
            
            return issueData;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
