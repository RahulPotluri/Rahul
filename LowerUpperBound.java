/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulation;

/**
 *
 * @author csc400adm
 */
import java.util.*;

import javax.swing.text.html.ListView;
class Load
{
   int loadonFibre=0; 
   String FibreSource = null;
   String FibreDestination = null;
}

class LoadComparator implements Comparator<Load> {
	public int compare(Load t1, Load t2) {
		return t1.loadonFibre - t2.loadonFibre;
	}

}

public class LowerUpperBound {
    
    List<Load> load = new ArrayList<>();
  static int  max_Spectrum =0;
    public int FindLowerBound(int t)
    {
        List<TrafficRequest> trafficDemand = MaxSpectrumAllocation.FindTrafficDemand(Simulation_Frame.i);
        Vertex[] vertices = Dijkstra.vertex;
        
       
      for(int i=0; i <vertices.length;i++)
      {
      Load a =new Load();
      a.FibreSource = vertices[i].name;
      load.add(a);
      }
      
      for(TrafficRequest tr : trafficDemand)
      {
         for(Vertex x : tr.path)
            {
            for(Load l : load)
            {
            if(l.FibreSource.equals(x.name))
                {
                    int next_index =  tr.path.indexOf(x) + 1;       
                    if(next_index < tr.path.size())
                    {
                    l.loadonFibre = ++l.loadonFibre;
                    l.FibreDestination = tr.path.get(next_index).name;
                    }
                }
            }
          }
      }
      
      int max_Load = 0;
      
      // Sorting the Demands
	Collections.sort(load, new LoadComparator());
        for(Load l : load)
        {
          max_Load = l.loadonFibre;
          System.out.println("Load is "+ l.loadonFibre + " " +  l.FibreSource + " "+ l.FibreDestination  );
        }
        max_Spectrum = max_Load;
    //LB = Sigma(t) + GC * (I -1) ;  
    // GC = 1
      
    int lowerBound = t + 1 *(max_Load -1);
    
    return lowerBound;
    }
    
    
    public static Double FindUpperBound()
    {
    
    //Finding MaxLENGTH    
    List<TrafficRequest> trafficDemand = MaxSpectrumAllocation.FindTrafficDemand(Simulation_Frame.i);
    double[] PathLength = new double[trafficDemand.size()];
    double maxLength = 0;
    int i=0;
    for(TrafficRequest tr : trafficDemand)
    {
    String start = tr.path.get(0).name;
    String end = tr.path.get(tr.path.size()-1).name;
    PathLength[i] = Dijkstra.FindPath(start,end).get(tr.path.size()-1).minDistance;
    i++;
    }
    Arrays.sort(PathLength);
    maxLength = PathLength[PathLength.length - 1];
    
    //R can be found from lower bound
    // UB = ((R-1)*(M+1))*(X+GC)-GC
    // x= 1 Uniform Traffic, GC =1
    
    Double UpperBound = ((max_Spectrum - 1) * maxLength + 1) * (1+1) - 1;
    
    
    return UpperBound;
    }
    
    
}
