/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

/**
 *
 * @author Rahul
 */

import java.util.*;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.Graph;

import javax.swing.text.html.ListView;
class LoadBPSR
{
   int loadonFibre=0; 
   String FibreSource = null;
   String FibreDestination = null;
}

class routingPath
{
int loadonPath;
List<GraphPath> path = null;
}

class LoadComparatorBPSR implements Comparator<LoadBPSR> {
	public int compare(LoadBPSR t1, LoadBPSR t2) {
		return t1.loadonFibre - t2.loadonFibre;
	}

}
public class LowerBoundBPSR {
    
    List<LoadBPSR> load = new ArrayList<>();
  static int  max_Spectrum =0;
 BPSR bpsr_obj = new BPSR();
  public int FindLowerBound(int t)
    {
        List<TrafficRequestBPSR> trafficDemand = bpsr_obj.FindTrafficDemandBPSR(1) ;
       
    
    return 1;
    }
  
  public List<LoadBPSR> FindLoad()
  {
  List<TrafficRequestBPSR> trafficDemand = bpsr_obj.FindTrafficDemandBPSR(1) ;
      LoadBPSR a =new LoadBPSR();
      a.FibreSource = "A";
      load.add(a);
      a =new LoadBPSR();
      a.FibreSource = "B";
      load.add(a);
      a =new LoadBPSR();
      a.FibreSource = "C";
      load.add(a);
      a =new LoadBPSR();
      a.FibreSource = "D";
      load.add(a);
      a =new LoadBPSR();
      a.FibreSource = "E";
      load.add(a);
      a =new LoadBPSR();
      a.FibreSource = "F";
      load.add(a);
      
      for(TrafficRequestBPSR tr : trafficDemand)
      {
      for(GraphPath x : tr.path)
      {
      Graph v = x.getGraph();
      List<DefaultWeightedEdge> ed= x.getEdgeList();
      for(DefaultWeightedEdge e:ed)
      {
      String source = v.getEdgeSource(e).toString();
      String target= v.getEdgeTarget(e).toString();
      for(LoadBPSR lb:load)
        {
            if(lb.FibreSource.equals(source) && lb.FibreDestination==null)
            {
            lb.FibreDestination = target;
            ++lb.loadonFibre;
            }
            else if(lb.FibreSource.equals(source) && lb.FibreDestination==(target))
            {
            ++lb.loadonFibre;
            }
       }
      }
      }
      }
      Collections.sort(load,new LoadComparatorBPSR());
      for(LoadBPSR l:load)
      {
       System.out.println("Load BPSR is "+ l.loadonFibre + " " +  l.FibreSource + " "+ l.FibreDestination  );
      }
      
    return load;
  }
    
  
    public void pathselection()
    {
      List<LoadBPSR> load = FindLoad();
      List<GraphPath> pathGraphA= BPSR.GraphBPSR("A", 1);
      List<routingPath> pathRoute;
      for(GraphPath p : pathGraphA)
      {
      Graph g = p.getGraph();
      List<DefaultWeightedEdge> ed = p.getEdgeList();
      int loadonPath = 0; 
      for(DefaultWeightedEdge e:ed)
      {
      String source = g.getEdgeSource(e).toString();
      String target= g.getEdgeTarget(e).toString();
      for(LoadBPSR sp:load)
      {
          if(source.equals(sp.FibreSource) && target.equals(sp.FibreDestination))
          {
          loadonPath = loadonPath + sp.loadonFibre;
          
          }
      }
      }
      String a = "";
      }
    }
}
