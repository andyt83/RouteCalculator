package distance;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class distance {
		
	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner sc = new Scanner(System.in);
		Scanner s = new Scanner(new File("txcities.txt"));
		int[][] tokens = new int [15][15];
		for(int i = 0; i < 15; i++)
		{
			String[] tokens1 = s.nextLine().split(" ");
			tokens[i][0] = Integer.parseInt(tokens1[0]);
			tokens[i][1] = Integer.parseInt(tokens1[1]);
			tokens[i][2] = Integer.parseInt(tokens1[2]);
			tokens[i][3] = Integer.parseInt(tokens1[3]);
			tokens[i][4] = Integer.parseInt(tokens1[4]);
			tokens[i][5] = Integer.parseInt(tokens1[5]);
			tokens[i][6] = Integer.parseInt(tokens1[6]);
			tokens[i][7] = Integer.parseInt(tokens1[7]);
			tokens[i][8] = Integer.parseInt(tokens1[8]);
			tokens[i][9] = Integer.parseInt(tokens1[9]);
			tokens[i][10] = Integer.parseInt(tokens1[10]);
			tokens[i][11] = Integer.parseInt(tokens1[11]);
			tokens[i][12] = Integer.parseInt(tokens1[12]);
			tokens[i][13] = Integer.parseInt(tokens1[13]);
			tokens[i][14] = Integer.parseInt(tokens1[14]);
			
			
		}
		ArrayList<String> cities = new ArrayList();
		cities.add("Houston");
		cities.add("San Antonio");
		cities.add("Dallas");
		cities.add("Austin");
		cities.add("Fort Worth");
		cities.add("El Paso");
		cities.add("Arlington");
		cities.add("Corpus Christi");
		cities.add("Plano");
		cities.add("Laredo");
		cities.add("Lubbock");
		cities.add("Garland");
		cities.add("Irving");
		cities.add("Amarillo");
		cities.add("Grand Prairie");
		
		System.out.println("Starting city?");
		String city = sc.nextLine();
		double[][] times = new double[15][15];
		for(int j = 0; j < 15; j++)
		{
		for(int k = 0; k < 15; k++)
		{
			double d = tokens[j][k];
			times[j][k] = d/1.1546;
		}
		}
		double[][] cost = new double[15][15];
		for(int m = 0; m < 15; m++)
		{
		for(int k = 0; k < 15; k++)
		{
			double d = tokens[m][k];
			cost[m][k] = d*1.26 + 232.71;
		}
		
		}
		System.out.println("Max time?");
		double maximum = sc.nextInt();
		int number = 0;
		for(int n = 0; n < cities.size(); n++)
		{
			if(city.contentEquals(cities.get(n)))
			number = n;
		}
		System.out.println(topRoute(cities, cost, times, number, maximum));
		//System.out.println(times[14][1]);
	}
	
	
	public static ArrayList<String> topRoute(ArrayList<String> citys, double[][] cos, double[][] tim, int cit, double maxtime)
	{
		Route r1 = new Route(null, 0, 0);
		Route ttr = new Route(null, 0, 0);
		//ArrayList<String> mainList1 = new ArrayList<String>();
		for(int z = 0; z < cos[0].length; z++)
		{
			ArrayList<String> mainList = new ArrayList<String>();
			Route r2 = new Route(mainList, 0, 0);
			Route tr = new Route(mainList, 0, 0);

			if(cit != z)
			{
				if(tim[cit][z]*2 + 30 < maxtime)
				{
				r2.addStop(citys.get(z));
				r2.addTime(tim[cit][z]);
				r2.addTime(30);
				
				for(int y = 0; y < cos[0].length; y++)
				{
					if(cit != y && z != y)
					{
						if(tim[y][z] + 30 + tim[cit][y] + r2.getTime() < maxtime)
						{
							
							Route r3 = r2;	
							r3.addStop(citys.get(y));
							r3.addTime(tim[y][z]);
							r3.addTime(30);
							
						for(int x = 0; x < cos[0].length; x++)
						{
							if(cit!= x && z!= x && y != x)
							{
								if(tim[x][y] + 30 + tim[cit][x] + r3.getTime() < maxtime)
								{
									Route r4 = r3;	
									r4.addStop(citys.get(x));
									r4.addTime(tim[x][y]);
									r4.addTime(30);
									r4.addTime(tim[cit][x]);
									r4.addCost(cos[cit][z] + cos[z][y] + cos[y][x] + cos[x][cit]);
									if(tr.getCost() < r4.getCost())
									tr = r4;
								}
							}
								
						}
						if(r3.getStops() == 2)
						{
							r3.addTime(tim[cit][y]);
							r3.addCost(cos[cit][z] + cos[z][y] + cos[y][cit]);
							if(tr.getCost() < r3.getCost())
							tr = r3;	
						}
						}
					}
				}
				if(r2.getStops() == 1)
				{
					r2.addTime(tim[cit][z]);
					r2.addCost(cos[cit][z] + cos[z][cit]);
					if(tr.getCost() < r2.getCost())
					tr = r2;	
				}
				}
			}
			if(tr.getCost() > ttr.getCost())
			ttr = tr;
		}
		ttr.getStopList().add(String.valueOf(ttr.getTime()));
		ttr.getStopList().add(String.valueOf(ttr.getCost()));

		ttr.getStopList().add(String.valueOf(ttr.getStops()));

		return ttr.getStopList();
	}
	}

