# Trac Plugin for Teamcity
## Introduction
This project creates a [TeamCity](www.jetbrains.com/teamcity/) Plugin to communicate with [Trac](http://trac.edgewall.org/) issue tracker.<BR>
Tested on 8.1.4 and 9.0.1

## Quick-Use
If you just need the zip to activate the plugin take this zip: *target/teamcity_trac_integration.zip* <BR>

## Installation
To install the plugin login with administrator account and select <BR>
*Administration->Plugin List* <BR>
and click <BR>
*Upload plugin zip*.<BR>
The server will tell you that the plugin gets uploaded to *<Teamcity Data Directory>\plugins*.
After the plugin is uploaded you have to restart the server.<BR>
(Check the [Documentation](https://confluence.jetbrains.com/display/TCD9/Installing+Additional+Plugins) for manually installing the plugin)

## Configuration
When you add a new issue tracker connection you have to select _trac_ as the connection type.<BR>
The _server url_ must be the url of the trac project (for example _http://192.168.168.1:8000/EnvironmentTestProject_) <BR>
The issue ID Pattern is a Regular expression (like this _TEST-(\d+)_)<BR>
That would find a commit message _TEST-1_ and link it to _http://192.168.168.1:8000/EnvironmentTestProject/ticket/1_

## Building
You'll need JDK and Maven installed and the corresponding environment settings (`JAVA_HOME=<JDK Location>` and `MAVEN_HOME="MAVEN Location`')

Running `mvn clean package` will automatically download all dependencies and will create the zip file in the `target` folder

## Editing
You can import the project folder into Eclipse.<BR>
For easy testing I added a Testclass (`schaek.teamcity.trac.TracIssueFetcherTest`) so hat you can actually play a little in your own Teamcity/Trac environment. Only uncomment the testng annotations and fill in your data into the private variables

## Workflow
The plugin reads the html text for a specific issue with an InputStream and then parses the text with regular expressions.

## Pitfalls
1. It's important, that the name of the plugin in `teamcity-plugin.xml` equals the type used in `TracIssueProvider` and `TracIssueProviderFactory`.
Otherwise Teamcity doesn't render an input form
2. In Teamcity 8.x _AbstractIssueProviderFactory_ has a two parameter constructor (IssueFetcher fetcher, String type).<BR> 
In Teamcity 9.x this constructor is deprecated and you should use the 3 parameter constructor (IssueFetcher fetcher, String type, String displayName)