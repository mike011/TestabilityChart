# Testability Chart # 
Current Version: 0.17

## Overview ##
This application is the java based implementation using jfreechart for graph.

Please see <a href="http://jawspeak.com/category/testability/">JAW Speak</a> for more info.

You can easily run the application in the Sample folder by running the jar. A sample log of the git log --numstat of this project is used. Also the properties are read from the common properties file.

## Launching ##
You have create a common.properties file in the same directory as the jar file. You can optionally set the directory
to run from by setting the system variable PROJECT_FOLDER. Example: -DPROJECT_FOLDER=../samples/qt/

## Manditory Arguments ##
At minimum you have to set:
<table border="1">
<tr><td>Option</td><td>Explanation</td><td>Examples</td></tr>
<tr><td>log.file</td><td>the name of the file that contains the results of running "git log --numstat"</td><td>out.txt</td></tr>
<tr><td>types</td><td>what type of source code is your project using?</td><td>java, cpp, c</td></tr>
</table>

## Optional Arguments ##
You can optionally also set:
<table border="1">
<tr><td>Option</td><td>Explanation</td><td>Examples</td></tr>
<tr><td>base.url</td><td>for commits where on the Internet can you link too</td><td>http://qt.gitorious.org/qt/qt/commit/</td></tr>
<tr><td>project.name</td><td>The name of the project that can show up on the chart</td><td>QT</td></tr>
<tr><td>debug</td><td>Enable or disable debugging, disabled by default</td><td>TRUE, FALSE</td></tr>
</table>

## Samples ##
To launch a sample from the project folder execute: java -jar ..\TestabilityChart.jar. This uses the common.properties
and log.txt from the projects folder.
<b>QT</b>
<UL>
<LI>A very very large project with thousands of commits and many different developers.</LI>
<LI>Located in <a href="https://github.com/mike011/TestabilityChart/tree/master/samples/qt">samples/qt<a></LI>
</UL>
  
![QT](https://raw.github.com/mike011/TestabilityChart/master/samples/qt/screenshot.png)

<b>Testability Chart</b>
<UL>
<LI>This is this git project and has a very unit test high coverage (95%+)</LI>
<LI>Located in <a href="https://github.com/mike011/TestabilityChart/tree/master/samples/TestabilityChart">samples/TestabilityChart<a></LI>
</UL>
  
![Testability Chart](https://raw.github.com/mike011/TestabilityChart/master/samples/TestabilityChart/screenshot.png)

## History ##
<B>Version 0.1</B> 
<UL>
<LI>Percents on verticals</LI>
<LI>Dates on bottom</LI>
<LI>Bubbles to show size of change</LI>
<LI>When you click on bubble the commit is shown.</LI>
<LI>Size is determined by looking at the git log --numstat values for only java files</LI>
<LI>90% code coverage</LI>
</UL>
<BR>

<B>Version 0.15</B>
<UL>
<LI>Specify by passing into program type of source code</LI>
<LI>Specify by configuration file program type</LI>
<LI>Sample with log file and jar.</LI>
<LI>HTTP Link to change description</LI>
</UL>
<BR>

<B>Version 0.17 - Be able to handle a larger project</B><BR>
QT has been added as a sample project to show case the handling of large projects. QT has 
thousands of commits with hundreds of different developers.
<UL>
<LI>Fix URL path for different projects</LI>
<LI>Change title for current project.</LI>
<LI>Add in Version name in title.</LI>
<LI>Be able to set properties file by passing in system variable.</LI>
<LI>For unit tests only statically import.</LI>
<LI>Grab screen shots for projects and have them show up in GIT hub as examples. </LI>
<LI>Remove passing in arguments and base only on property file. </LI>
<LI>Legend becomes ridiculously large for large projects and dominates the screen. Maybe a smaller
scrollable window is needed? No possible with jFreechart instead don't show the table for commits greater then 10.</LI>
<LI>Chart and Panel are fixed in size, they should be dynamic</LI>
</UL>
<BR>
