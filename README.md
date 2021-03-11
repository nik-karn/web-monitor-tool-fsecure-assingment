# web-monitor-tool-fsecure-assingment

This project is an assingment which was created by Nikhil Karn to design a web monitoring tool

This project is a web app develoved in java (1.8) along with SpringBoot Framework.
Design can be refered here: https://github.com/nik-karn/web-monitor-tool-fsecure-assingment/blob/main/Web%20Assingment.png
App is hosted to AWS : http://ec2-13-127-198-9.ap-south-1.compute.amazonaws.com:8080/
API to read the monitored data in JSON format: http://ec2-13-127-198-9.ap-south-1.compute.amazonaws.com:8080/monitor

Feature of the App:
1. Reads a list of web pages (HTTP URLs) and corresponding page content requirements from a configuration file. (https://github.com/nik-karn/web-monitor-tool-fsecure-assingment/blob/main/webmonitioring/src/main/resources/urldata.json)
2. Periodically makes an HTTP request to each page. After each 10 sec, it makes the request to  the configured URL. This frequency can be configured via app.properties file
3. Verifies that the page content received from the server matches the content requirements. In each request it verifies the page content even the status is 200 OK.
4. Measures the time it took for the web server to complete the whole request. 
5. Writes a log file that shows the progress of the periodic checks. Stores the data in JSON format.
6. Implemented a single-page HTTP server interface in the same process that shows (HTML) each monitored web site and their current (last check) status.: http://ec2-13-127-198-9.ap-south-1.compute.amazonaws.com:8080/


# Answer to OPTIONAL questions
Q1. Assuming we wanted to simultaneously monitor the connectivity (and latencies) from multiple geographically distributed locations and collect all the data to a single report that always reflects the current status across all locations, describe how the design would be different.

Ans: The request can be configured in this app to point to the PROXY SERVER which will act like request are getting fetched from the diffrent locations and result can be pushed to the common repo and will displayed
 

Q.2 How would you transfer the data? 
Monitored data can be pushed to both i.e FILE and DATABASE, as per config

Q3. Security considerations
This app and its api is protected by SPRING WEB SECURITY which can be connected to the IAM database or the API and make sures that only Authenticated users are allowed to access this app.
