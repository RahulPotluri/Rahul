/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;
import java.util.List;

import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import java.util.*;
import javax.swing.text.html.ListView;

/**
 *
 * @author Rahul
 */
class TrafficRequestBPSR {
	// public Vertex source = null;
	// public Vertex destination = null;
	public int trafficDemand = 0; // Traffic demand in GB
	public List<GraphPath> path = null;
	public String name = null;
	public int spectrumAssigned = 0;

}

class TrafficRequestBPSRComparator implements Comparator<TrafficRequestBPSR> {
	public int compare(TrafficRequestBPSR t1, TrafficRequestBPSR t2) {
		return t2.trafficDemand - t1.trafficDemand;
	}

}

public class BPSR {

    public static List<GraphPath> GraphBPSR(String startVertex, int type)
    {
       SimpleWeightedGraph<String,DefaultWeightedEdge> g= new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
       String a = "A";
       String b = "B";
       String c = "C";
       String d = "D";
       String e = "E";
       String f = "F";
      
       g.addVertex(a);
       g.addVertex(b);g.addVertex(c);g.addVertex(d);g.addVertex(e);g.addVertex(f);
       DefaultWeightedEdge e1= g.addEdge(a, b);
       g.setEdgeWeight(e1, 12);
       e1=g.addEdge(a, e);
       g.setEdgeWeight(e1,8);
       e1=g.addEdge(a, d);
       g.setEdgeWeight(e1,5);
       e1=g.addEdge(e, d);
       g.setEdgeWeight(e1,4);
       e1=g.addEdge(d, f);
       g.setEdgeWeight(e1,3);
       e1=g.addEdge(d, c);
       g.setEdgeWeight(e1,8);
       e1=g.addEdge(c, f);
       g.setEdgeWeight(e1,2);
       e1=g.addEdge(b, c);
       g.setEdgeWeight(e1,6);
  if(type == 1)
       {
      if(startVertex.equals("A"))
      {
       KShortestPaths x1= new KShortestPaths(g, a, 5);
       List<GraphPath> gp1 = x1.getPaths(f);
       return gp1;
      }
      else if(startVertex.equals("B"))
      {
      KShortestPaths x2= new KShortestPaths(g, b, 5);
      List<GraphPath> gp2 = x2.getPaths(f);
      return gp2;
      }
      else if(startVertex.equals("C"))
      {
      KShortestPaths x3= new KShortestPaths(g, c, 5);
      List<GraphPath> gp3 = x3.getPaths(f);
      return  gp3;
      }
      else if(startVertex.equals("D"))
      {
      KShortestPaths x4= new KShortestPaths(g, d, 5);
      List<GraphPath> gp4 = x4.getPaths(f);
      return gp4;
      }
      else
      {
      KShortestPaths x5= new KShortestPaths(g, e, 5);
      List<GraphPath> gp5 = x5.getPaths(f);
      return gp5;
      }
      
       }
       
  else
      {
        List<GraphPath> gp1 = new ArrayList<>();
        if(startVertex.equals("A"))
        {
        DijkstraShortestPath x1 = new DijkstraShortestPath(g, a, f);
        gp1.add(x1.getPath());
        return gp1;
        }
        else if(startVertex.equals("B"))
        {
        DijkstraShortestPath x1 = new DijkstraShortestPath(g, b, f);
        gp1.add(x1.getPath());
        return gp1;
        }
        else if(startVertex.equals("C"))
        {
        DijkstraShortestPath x1 = new DijkstraShortestPath(g, c, f);
        gp1.add(x1.getPath());
        return gp1;
        }
        else if(startVertex.equals("D"))
        {
        DijkstraShortestPath x1 = new DijkstraShortestPath(g, d, f);
        gp1.add(x1.getPath());
        return gp1;
        }
        else
        {    
        DijkstraShortestPath x1 = new DijkstraShortestPath(g, e, f);
        gp1.add(x1.getPath());
        return gp1;
        }
     }
    }
 
    public List<TrafficRequestBPSR> FindTrafficDemandBPSR(int t)
    {
        List<TrafficRequestBPSR> TrafficDemand = new ArrayList<TrafficRequestBPSR>();
		
                for(int i=1;i<=(t*5);)
                {
                TrafficRequestBPSR x = new TrafficRequestBPSR();
		x.trafficDemand = 1;
		x.path = GraphBPSR("A",0);
                x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequestBPSR();
		x.trafficDemand = 1;
		x.path = GraphBPSR("B",0);
		x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequestBPSR();
		x.trafficDemand = 1;
		x.path = GraphBPSR("C",0);
		x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequestBPSR();
		x.trafficDemand = 1;
		x.path = GraphBPSR("D",0);
		x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequestBPSR();
		x.trafficDemand = 1;
		x.path = GraphBPSR("E",0);
		x.name = "SP" + i++;
		TrafficDemand.add(x);
                }
                return TrafficDemand;
    }
    
    
}
