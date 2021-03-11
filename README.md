# web-monitor-tool-fsecure-assingment

This project is an assingment which was created by Nikhil Karn to design a web monitoring tool

This project is a web app develoved in java (1.8) along with SpringBoot Framework.
Design can be refered here: https://github.com/nik-karn/web-monitor-tool-fsecure-assingment/blob/main/Web%20Assingment.png

Feature of the App:
1. Reads a list of web pages (HTTP URLs) and corresponding page content requirements from a configuration file. (https://github.com/nik-karn/web-monitor-tool-fsecure-assingment/blob/main/webmonitioring/src/main/resources/urldata.json)
2. Periodically makes an HTTP request to each page. After each 10 sec, it makes the request to  the configured URL. This frequency can be configured via app.properties file
3. Verifies that the page content received from the server matches the content requirements. In each request it verifies the page content even the status is 200 OK.
4. Measures the time it took for the web server to complete the whole request. 
5. Writes a log file that shows the progress of the periodic checks. Stores the data in JSON format.
6. Implemented a single-page HTTP server interface in the same process that shows (HTML) each monitored web site and their current (last check) status.: http://ec2-13-127-198-9.ap-south-1.compute.amazonaws.com:8080/


