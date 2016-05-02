This application will look for new created orders from April 1, 2016 and into the future.

It will continuously run, sleeping in betewen calls, until you exit (via Ctrl+C).

Output will be written to a CSV file, but the delimiter is actually a pipe "|", not a comma.

To run this application:
1) Unpack this entire archive
2) Open a command prompt (cmd.exe)
3) Change Directories to where GetSuperStoreOrders.exe is located
4) Execute at your prompt:
	GetSuperStoreOrders.exe <Magento Base URL> <admin username> <admin password> <output file location> <number of minutes to sleep>

Example:
	GetSuperStoreOrders.exe http://www.example.com/magento admin password123 c:\order.csv 60
What this is saying is to run ever 60 minutes, looking for orders located at www.example.com/magento and write the content to c:\order.csv

The Magento Base URL, admin username, and admin password are REQUIRED

If you do not provide an output file location or a value for the amount of time to sleep, output will be written to: C:\OrdersJSON.csv every minute.

Final disclaimer....I have put zero error handling in here.  If it crashes, I'm sorry, try it again.  If it crashes again, let me know and I may be able ot figure out what's going on.
