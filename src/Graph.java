/*ZiJian Chen 215245111
* XinQi Chen 215639545
* HanSen Tang 214142707*/
import java.util.*;

public class Graph {
    protected HashMap<String,Vertex> Vlist= new HashMap<>();
    int MAX_WEIGHT=99999;

    /* addV, to add a new vertex to the graph, create a new Vertex object first then add it to the hashmap*/
    private void addV(String name){
        Vertex a= new Vertex(name);
        Vlist.put(name,a);
        System.out.println("Vertex added");

    }

    /*removeV, first find the correct vertex, then find all its adjacent Vertices and remove
    * all the airline that comes back to the about-to-be-removed Vertex, when its done, we come back to this vertex
    * and remove it*/
    private void removeV(String name) throws NoMatchedEdgeException, NoMatchedVertexException {
        Vertex tmp=Vlist.get(name);
        LinkedList tmmp=(LinkedList) tmp.edges.clone();// usd clone here, because we do not want to change the original data structure;
        Iterator itr = tmmp.iterator();
        Edge o=null;
        while(itr.hasNext()){
           o = (Edge)itr.next();
            removeE(o.destination.airport,name,o.weight); // call the removeE to remove the edge between two Vertices;
        }
        Vlist.remove(name);// finally remove this Vertex
        System.out.println("Vertex removed");
    }

    // for addE, is both Vertices exist, simply add airline for each one towards each other
    // if one does not exist, create this new Vertex, then add the Edge
    private void addE(String a, String b, int c ){
        Vertex first;
        Vertex second;
        if(Vlist.containsKey(a)){ //to see  if the airport is stored already
             first= Vlist.get(a);
        }else{
            addV(a); // if a does not exist yet, then add a new Vertex
             first= Vlist.get(a);
        }
        if(Vlist.containsKey(b)){ // same procedure here for b just as a
             second= Vlist.get(b);
        }else{
            addV(b);
             second= Vlist.get(b);
        }
        Edge f= new Edge(c,second);// then add edge towards the other one
        Edge e= new Edge(c,first);
        first.addEdge(f);
        second.addEdge(e);
        System.out.println("Edge added");
    }

    /* to remvoe an edge, we find each vertex and remove the edge*/
    private void removeE(String a, String b, int c) throws NoMatchedEdgeException, NoMatchedVertexException{
        Vertex tmp1= Vlist.get(a);
        if(tmp1==null){throw new NoMatchedVertexException("Airport not in the graph");}// check if the input if valid
        Vertex tmp2= Vlist.get(b);
        if(tmp2==null){throw new NoMatchedVertexException("Airport not in the graph");}
        tmp2.remove(tmp2.getMatchedEdegs(a,c)); //call the method, get the matched edge, then remove it.
        tmp1.remove(tmp1.getMatchedEdegs(b,c));
        System.out.println("Edge removed");
    }

    private void getincidentEdges(String a){
        Vertex tmp = Vlist.get(a);
        tmp.incidentEdges();// call this Vertex's method
    }
    private void getAllIncidentEdges(){
        for(Vertex v : Vlist.values()){
            v.incidentEdges();
        }
        // print incidentEdges for each one of the vertex that is stored in hashmap
    }

    protected Stack routes= new Stack();
    String patha="";
    // use recursive
    private int  getshortestRoute(String a, String b){
        Edge cur = null;
        int x;
        int curWeight=MAX_WEIGHT;
        Vertex tmp= Vlist.get(a);
        tmp.visited=true;// to make sure it's next vertex will not to backward in the graph
        Iterator itr = (tmp.edges).iterator();

        while (itr.hasNext()) {// for each edge that belong to this current Vertex
            cur = (Edge) itr.next();
            if (cur.destination.airport.equals(b) && cur.weight < curWeight) {// if it can reach the destination in one airline, and it's she shortest
                curWeight = cur.weight; // save it
            } else if (!cur.destination.visited && (x = cur.weight + getshortestRoute(cur.destination.airport, b)) < curWeight) {
                /*else, pass this problem to its next vertex, and each of them will return their shortest route to the destination
                * and the current vertex will add the weight to each one of them then save the shortest one*/
                curWeight = x;
                }
            }
        tmp.visited=false;// cancel the mark, so other node can go through this Vertex.
        //return the current weight(shortest) back to the upper level
        return curWeight;
    }


    public static void main(String[] args) throws NoMatchedEdgeException, NoMatchedVertexException, WrongInputException {
        Graph a= new Graph();
        a.addE("HNL","LAX",2555);
        a.addE("LAX","SFO",337);
        a.addE("LAX","ORD",1743);
        a.addE("LAX","DFW",1233);
        a.addE("SFO","ORD",1843);
        a.addE("DFW","ORD",802);
        a.addE("ORD","PVD",849);
        a.addE("DFW","LGA",1387);
        a.addE("DFW","MIA",1120);
        a.addE("LGA","MIA",1099);
        a.addE("LGA","PVD",142);
        a.addE("PVD","MIA",1205);
        Scanner s = new Scanner(System.in);
        System.out.println("PLease enter your input");
        while(true){
            String input[] = s.nextLine().split(" ");
            String tmp1="";
            String tmp2="";
            String tmp3="";
            int tmp4=0;
            if(input.length>0){ tmp1=input[0];}
            if(input.length>1){ tmp2=input[1];}
            if(input.length>2){ tmp3=input[2];}
            if(input.length>3){ tmp4=Integer.parseInt(input[3]);}

            if(tmp1.equals("+") && !tmp2.equals("") && !tmp3.equals("") && tmp4!=0){
                a.addE(tmp2,tmp3,tmp4);
            }else if(tmp1.equals("-") && !tmp2.equals("")){
                if (tmp3.equals("") && tmp4==0){
                    a.removeV(tmp2);
                }else{
                    a.removeE(tmp2,tmp3,tmp4);
                }
            }else if(tmp1.equals("?")){
                if (tmp2.equals("")){
                    a.getAllIncidentEdges();
                }else if(tmp3.equals("")){
                    a.getincidentEdges(tmp2);
                }else{
                    System.out.println("The total weight from "+ tmp2+" to " +tmp3+" is " +a.getshortestRoute(tmp2,tmp3));
                    System.out.println(a.routes);
//                    System.out.println(a.routes.get(a.getshortestRoute(tmp2,tmp3)));
//                    a.printRoute(tmp2,tmp3,a.getshortestRoute(tmp2,tmp3));
                }
            }else if(tmp1.equals("QUIT")){
                System.out.println("Program ending...");
                break;
            }else{
                throw new WrongInputException();
            }
        }
        System.out.println("Program ended");
    }
}
