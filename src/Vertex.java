/*ZiJian Chen 215245111
 * XinQi Chen 215639545
 * HanSen Tang 214142707*/
import java.util.Iterator;
import java.util.LinkedList;

public class Vertex {
    public boolean visited;
    public String airport;
    public LinkedList<Edge> edges;

    public Vertex(String name){
        visited=false;
        this.airport=name;
        edges= new LinkedList<>();
    }

    public int addEdge(Edge e){
        edges.addLast(e);
        return e.weight;
    }

    public Edge getMatchedEdegs(String apname,int egweight) throws NoMatchedEdgeException{
        Iterator tmp =edges.iterator();
        Edge tmmp=null;
        while(tmp.hasNext()){// go throw this vertex's list
            Edge x= (Edge)tmp.next();
            if((x.destination.airport).equals(apname) && x.weight==egweight){
                tmmp=x; // if the right edge found, return it.
                return tmmp;
            }
        }
        //else its going to be null, so we throws exception and return null
        if(tmmp==null){throw new NoMatchedEdgeException("No matched edges found");}
        return null;
    }
    public void remove(Edge e){
        edges.remove(e);// remove the edge from the linkedlist
    }

    public String getAirport(){
        return airport;
    }


    // go through the list then print out each of them
    public void incidentEdges(){
        Iterator a = edges.iterator();
        while(a.hasNext()){
             Edge tmp= (Edge)a.next();
             int wtmp= tmp.weight;
             String destmp= tmp.destination.airport;
            System.out.println(airport+" "+destmp+" "+wtmp+" "+"plane");
        }
    }

}
