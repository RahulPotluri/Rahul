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

class TrafficRequest {
	// public Vertex source = null;
	// public Vertex destination = null;
	public int trafficDemand = 0; // Traffic demand in GB
	public List<Vertex> path = null;
	public String name = null;
	public int spectrumAssigned = 0;

}

class TrafficRequestComparator implements Comparator<TrafficRequest> {
	public int compare(TrafficRequest t1, TrafficRequest t2) {
		return t2.trafficDemand - t1.trafficDemand;
	}

}

class SubCarrier {
	public double SpectrumMinRange = 50.00;
	public double SpectrumMaxRange = 100.00;
	public double size_carrier = 1.00; // Size is in GB
	public double size_occupied = 0;
	public String name = null;
	public  List<Vertex> path = null;
}

public class MaxSpectrumAllocation {
	// Traffic Request are mentioned here
	public static List<TrafficRequest> FindTrafficDemand(int t) {
		List<TrafficRequest> TrafficDemand = new ArrayList<TrafficRequest>();
		
                for(int i=1;i<=(t*5);)
                {
                TrafficRequest x = new TrafficRequest();
		x.trafficDemand = 1;
		x.path = Dijkstra.FindPath("A", "F");
		x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequest();
		x.trafficDemand = 1;
		x.path = Dijkstra.FindPath("B", "F");
		x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequest();
		x.trafficDemand = 1;
		x.path = Dijkstra.FindPath("C", "F");
		x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequest();
		x.trafficDemand = 1;
		x.path = Dijkstra.FindPath("D", "F");
		x.name = "SP" + i++;
		TrafficDemand.add(x);

		x = new TrafficRequest();
		x.trafficDemand = 1;
		x.path = Dijkstra.FindPath("E", "F");
		x.name = "SP" + i++;
		TrafficDemand.add(x);
                
               
                }
                
                

		return TrafficDemand;
	}

	public static int MaxReuseSpectrumAllocation(int t) {
		List<TrafficRequest> TrafficDemand = FindTrafficDemand(t);
		List<TrafficRequest> TrafficDemandSorted = new ArrayList<TrafficRequest>();

		// Sorting the Demands

		Collections.sort(TrafficDemand, new TrafficRequestComparator());

		for (TrafficRequest tr : TrafficDemand) {
			System.out.println("Path: " + tr.path);
			System.out.println("Traffic Demand:" + tr.trafficDemand);
		}

		// Assigning Sub Carriers

		SubCarrier a = new SubCarrier();
		List<SubCarrier> subcarrier_new = new ArrayList<SubCarrier>();
		for (TrafficRequest tr : TrafficDemand) {
			if (tr.spectrumAssigned == 0) {
				a = new SubCarrier();
				// If spectrum path is the first one
				if (subcarrier_new.size() == 0) {
					subcarrier_new = Assigning_spectrum(a, tr, subcarrier_new);
					tr.spectrumAssigned = 1;
				}

				else {
					int Same_Route = 0;
					
					List<Vertex> traffic_path = new ArrayList<Vertex>();
					String traffic_path_string="";
					for (Vertex v : tr.path) {
						traffic_path_string=traffic_path_string + v.name;
					}
					int i = 0;
					
					for (SubCarrier sc : subcarrier_new) {
						String subCarrier_paths = "";	
						List<Vertex> paths_subCarrier = sc.path;
						for(Vertex v : paths_subCarrier)
						{
							subCarrier_paths = subCarrier_paths + v.name;
						}
						
						i = compare(subCarrier_paths, traffic_path_string);
						if(i>=1)
						{
							Same_Route = 1;
						}
					}
								
					if (Same_Route == 1) {
						a = new SubCarrier();
						a.name = "GC";
						a.path = tr.path;
						a.size_occupied = a.size_carrier;
						subcarrier_new.add(a);
						a=new SubCarrier();
						subcarrier_new = Assigning_spectrum(a, tr,
								subcarrier_new);
						tr.spectrumAssigned = 1;

					} else {
						subcarrier_new = Assigning_spectrum(a, tr,
								subcarrier_new);
						tr.spectrumAssigned = 1;
					}
				}
			}
		}
		for (SubCarrier s : subcarrier_new) {
			System.out.println(s.name);
		}
		List<SubCarrier> subCarrier_Number = new ArrayList<SubCarrier>();
               int index =0; 
                SubCarrier sb= new SubCarrier();
                sb = subcarrier_new.get(index);
                subCarrier_Number.add(sb);
                for(SubCarrier s : subcarrier_new)
                {
                if(subCarrier_Number.get(index).name != s.name )
                {
                    subCarrier_Number.add(s);
                    if(index < subCarrier_Number.size())
                    {index++;}
                }
                }
                System.out.print("Size of " + subCarrier_Number.size());
		return (subCarrier_Number.size());

	}

	public static List<SubCarrier> Assigning_spectrum(SubCarrier a,
			TrafficRequest tr, List<SubCarrier> subcarrier_new) {
		
		
		
		double traffic_Request_Remainingtobe_Sent = tr.trafficDemand;
		while (traffic_Request_Remainingtobe_Sent > 0) {
			if (traffic_Request_Remainingtobe_Sent > 0
					&& traffic_Request_Remainingtobe_Sent <= a.size_carrier) {
				a.name = tr.name;
				a.size_occupied = traffic_Request_Remainingtobe_Sent;
				a.path = new ArrayList<Vertex>(tr.path);
				subcarrier_new.add(a);
				traffic_Request_Remainingtobe_Sent = 0;
			} else {
				while (traffic_Request_Remainingtobe_Sent > a.size_carrier) {
					a = new SubCarrier();
					a.name = tr.name;
					a.path = new ArrayList<Vertex>(tr.path);
					a.size_occupied = a.size_carrier;
					traffic_Request_Remainingtobe_Sent = traffic_Request_Remainingtobe_Sent
							- a.size_carrier;
					subcarrier_new.add(a);

				}
			}

		}
		
		
		return subcarrier_new;
	}

	public static int compare(String searchMe, String findMe) {
		int count = 0;
		for (int i = 0; i <= findMe.length() - 2; i++) {
			for (int j = i + 2; j <= findMe.length(); j++) {
				String newFindMe = null;
				if (j == findMe.length()) {
					newFindMe = findMe.substring(i);
				} else {
					newFindMe = findMe.substring(i, j);
				}
				//System.out.println(i + " " + j + " " + " " + newFindMe);
				if (searchMe.contains(newFindMe)) {
					count = count + 1;
					//System.out.println(searchMe.contains(newFindMe) + " "
					//		+ newFindMe);
				}
			}
		}
		return count;
	}
}
