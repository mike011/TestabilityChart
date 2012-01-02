#Testability Chart
Current Version: 0.15

##Overview
This application is the java based implementation using jfreechart for graph.

Please see http://jawspeak.com/category/testability/ for mor einfo.

You can easily run the application in the Sample folder by running the jar. A sample log of the git log --numstat of this project is used. Also the properties are read from the common properties file.

##Launching
You have create a common.properties file in the same directory as the jar file. 

##Manditory Arguments
At minimum you have to set:
- log.file -> the name of the file that contains the results of running "git log --numstat", example: out.txt
- types -> what type of source code is your project using? Examples: java, cpp, c

##Optional Arguments
You can optionally also set:
- base.url -> for commits where on the Internet can you link too, example: http://qt.gitorious.org/qt/qt/commit/
- project.name -> The name of the project that can show up on the chart, example: QT
- debug -> Enable or disable debugging, disabled by default, examples: TRUE, FALSE

##Examples
- QT
--A very very large project with thousands of commits and many different developers.
![QT](https://raw.github.com/mike011/TestabilityChart/master/samples/qt/qt.png)

- Testability Chart
--This project that has very high coverage (95%+)
![Testability Chart](https://raw.github.com/mike011/TestabilityChart/master/samples/TestabilityChart/TestabilityChart.png)
