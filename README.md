# Java-Requests (NOT STABLE)
A Simple Java Request Package To Simplify Java API Integrations. Included Is An Example Application Which Provides Use Case Examples. To Install Simply Place The Package Files Into Your Project And Import Them.

## Request()
To begin using the request you need to create a new Request variable and designate the target API Url. Below is an example using the Timely Systems API.
`Request request = new Request("http://ins.api.timelydevs.com");`

To make a simple request, the HTTP request is also the method name. For example, 
`request.post( < URI > ,  <Parameters>);`
`request.get( < URI > ,  <Parameters>);`

The URI is the endpoint of the API being requested. The URI would most likely be something like "users/read". The parameters could be something like "?id=1" or '{"id" : "1"}' depending on the HTTP Method.

### Please Note that the request object will need to be parsed. The responses will most likely be in JSON and will need a JSON package to do anything with the response. 

## APILogin()
The APILogin File Is the example file. To run this file, load it into an IDE or editor and click build/run. You will need credentials to use it. The credentials are 
user: test@timelydevs.com 
pass: Password123!

### ** The application only allows you to make an API user and to login and set the company Name or change the email. 

## User()
The User File Is the Examples User Class. The User class is used to store information regarding the logged in user. The object (User class) helps to make local changes and push them live / get changes from the API and make them local.

### ** The file is not used in the requests. Therefore, it is not documented except for the internal documentation. 

## Contributors		
* Brent Dalling, Timely Systems

## External Packages
*  [Java Simple JSON](https://github.com/fangyidong/json-simple)
