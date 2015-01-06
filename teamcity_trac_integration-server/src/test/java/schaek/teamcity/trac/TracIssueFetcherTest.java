package schaek.teamcity.trac;

import jetbrains.buildServer.issueTracker.IssueData;
import jetbrains.buildServer.issueTracker.IssueFetcher;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.ServerPaths;
import jetbrains.buildServer.util.EventDispatcher;
import jetbrains.buildServer.util.cache.EhCacheUtil;
import jetbrains.buildServer.util.cache.ResetCacheRegisterImpl;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TracIssueFetcherTest {
    
    private String host="http://192.168.168.26:8000";
    private String id="TEST-1";
    private EhCacheUtil cacheUtil;
    
    //@BeforeClass
    public void init(){
        ServerPaths serverPaths = new ServerPaths(System.getProperty("java.io.tmpdir"));
        EventDispatcher<BuildServerListener> dispatcher = EventDispatcher.create(BuildServerListener.class);
        cacheUtil = new EhCacheUtil(serverPaths, dispatcher, new ResetCacheRegisterImpl());
    }
    //@Test
    public void testFetch() throws Exception{
        IssueFetcher tracIssueFetcher = new TracIssueFetcher(cacheUtil);
        
        IssueData issueData = tracIssueFetcher.getIssue(host, id, null);
        Assert.assertNotNull(issueData);
    }
}
