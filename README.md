#Testability Chart
Current Version: 0.17

##Overview
This application is the java based implementation using jfreechart for graph.

Please see http://jawspeak.com/category/testability/ for more info.

You can easily run the application in the Sample folder by running the jar. A sample log of the git log --numstat of this project is used. Also the properties are read from the common properties file.

##Launching
You have create a common.properties file in the same directory as the jar file. You can optionally set the directory
to run from by setting the system variable PROJECT_FOLDER. Example: -DPROJECT_FOLDER=../samples/qt/

##Manditory Arguments
At minimum you have to set:
<table border="1">
<tr><td>Option</td><td>Explanation</td><td>Examples</td></tr>
<tr><td>log.file</td><td>the name of the file that contains the results of running "git log --numstat"</td><td>out.txt</td></tr>
<tr><td>types</td><td>what type of source code is your project using?</td><td>java, cpp, c</td></tr>
</table>

##Optional Arguments
You can optionally also set:
<table border="1">
<tr><td>Option</td><td>Explanation</td><td>Examples</td></tr>
<tr><td>base.url</td><td>for commits where on the Internet can you link too</td><td>http://qt.gitorious.org/qt/qt/commit/</td></tr>
<tr><td>project.name</td><td>The name of the project that can show up on the chart</td><td>QT</td></tr>
<tr><td>debug</td><td>Enable or disable debugging, disabled by default</td><td>TRUE, FALSE</td></tr>
</table>

##Examples
- QT
--A very very large project with thousands of commits and many different developers.
![QT](https://raw.github.com/mike011/TestabilityChart/master/samples/qt/screenshot.png)

- Testability Chart
--This project that has very high coverage (95%+)
![Testability Chart](https://raw.github.com/mike011/TestabilityChart/master/samples/TestabilityChart/screenshot.png)
