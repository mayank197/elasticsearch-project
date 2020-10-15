**Elastic Search Data Source**

We're creating 10000 random resources (from Car entity) and feed them into Elastic Search.

We'll clear index - Its like deleting all cars from table
So if we restart the server, any previous dummy car data will be deleted first. This way, we'll always start afresh with 10000 records

We'll generate 10000 car data using RandomService and put them into a list which will be saved in ES

For deleting, we'll consume ES Rest API using Spring Web Client

Web Client is Spring's way to consume API. In this case, we'll create Web Client to consume ES API
