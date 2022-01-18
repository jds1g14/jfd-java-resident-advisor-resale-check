# jfd-java-resident-advisor-resale-check v1.0.0

# Getting Started
    Skip Steps 1 & 2 if you've taken the jar directly off of github
    1. Generate executable jar using 'mvn clean package'
    2. cd to the location in which the jar is located using command prompt
    3. input command:
       java -jar *jar_file_name*.jar "resident-advisor-event-url" "refresh_period_mins"  "Ticket_Name"
    
    Example Command:
       java -jar jfd-java-resident-advisor-resale-check-1.0.0.jar "https://ra.co/events/1479289" "5" "Entry before 12am Release 2"
       
    The above command will check the webpage once every 5 mins for at least one "Entry before 12am Release 2" ticket. 
    A system popup will be generated indicating that the ticket is available

# FOR YOUR INFORMATION

    1. This app will eventually stop working once Resident Advisor refactor their website and is working as of 17-jan-21.
       If you're facing any issues let me know and I'll make some updates.  

    2. If running on macos the Java/TrayIcon notification may not appear due to insufficien permissions. To fix on 
       MacOS 12/Monterey: 
       
       System Preferences -> Notifications & Focus -> Notifications - > Java -> Allow Notifications
    
# Version History

    v1.0.0 Initial Refactor


