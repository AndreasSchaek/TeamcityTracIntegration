# Trac Plugin for Teamcity
## Introduction
This project creates a [TeamCity](www.jetbrains.com/teamcity/) Plugin to communicate with [Trac](http://trac.edgewall.org/) issue tracker.

## Quick-Use
If you just need the zip to activate the plugin take this zip: * target/teamcity_trac_integration.zip`

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

## Donations welcome
[PayPal](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=52MGDJ6KQ5S2Q&lc=DE&item_name=Andreas%20Schaek&no_note=0&cn=Message%20to%20the%20developer%3a&no_shipping=1&currency_code=EUR&bn=PP%2dDonationsBF%3abtn_donate_SM%2egif%3aNonHosted)