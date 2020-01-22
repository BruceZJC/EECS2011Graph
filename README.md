# EECS2011Graph
Shortest distance on a graph
Find the shortest distance on an graph structure, in this case with airports being the nodes and distances between each pair of airports being the edges
This algorithm uses recursion to keep comparing for the shortest path

Part1
Create generic data structure(s) for storing graphs using the adjacency list structure.The  vertices will
store objects of generic typen V and the edges store objects of generic type E.The entries should be
location-aware.The data structure must support adding and removing of given vertex or edge.For quick
reference the vertices should also be stored in a hash table keyed by vertex’s object V.

Part2
Create a program that will find the shortest connection travel time between airports. It will accept the
following commands:
+ YYZJFK120plane
to add a connection from airport YYZ to airport JFK that takes 120 minutes using a plane
- YYZ
to remove an airport from the database
- YYZ JFK 120 plane
to remove a connection from airport YYZ to airport JFK that takes 120 minutes using plane
? YYZ
to list all connections from YYZ (lines in the format YYZ JFK 120 plane)
? YYZ LAX
to find the quickest route from YYZ to LAX, it shout print the total duration and then list the individual connections for this option (lines in the format YYZ JFK 120 plane)
?
to list all connections in memory (lines in the format YYZ JFK 120 plane)
QUIT
to end the program
You must use the data structure developed in part 1.
