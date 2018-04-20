import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AIOne {
    static List<Node> queue;
    static City[] cities;
            
    public static void main(String[] args) {
        cities = new City[20];
        cities[0] = new City(CityName.Oradea, 
                new Distance[]{
                    new Distance(CityName.Zerind, 71),
                    new Distance(CityName.Sibiu, 151)
                });
        
        cities[1] = new City(CityName.Zerind, 
                new Distance[]{
                    new Distance(CityName.Oradea, 71),
                    new Distance(CityName.Arad, 75)
                });
        
        cities[2] = new City(CityName.Arad, 
                new Distance[]{
                    new Distance(CityName.Zerind, 75),
                    new Distance(CityName.Tincsoara, 118),
                    new Distance(CityName.Sibiu, 140)
                });
        
        cities[3] = new City(CityName.Tincsoara, 
                new Distance[]{
                    new Distance(CityName.Arad, 118),
                    new Distance(CityName.Lugoj, 111)
                });
        
        cities[4] = new City(CityName.Lugoj, 
                new Distance[]{
                    new Distance(CityName.Tincsoara, 111),
                    new Distance(CityName.Mehadia, 70)
                });
        
        cities[5] = new City(CityName.Mehadia, 
                new Distance[]{
                    new Distance(CityName.Lugoj, 70),
                    new Distance(CityName.Dobreta, 75)
                });
        
        cities[6] = new City(CityName.Dobreta, 
                new Distance[]{
                    new Distance(CityName.Mehadia, 75),
                    new Distance(CityName.Craiwa, 120)
                });
        
        cities[7] = new City(CityName.Sibiu, 
                new Distance[]{
                    new Distance(CityName.Oradea, 151),
                    new Distance(CityName.Arad, 140),
                    new Distance(CityName.Figaras, 99),
                    new Distance(CityName.RimnicuVilcea, 80)
                });
        
        cities[8] = new City(CityName.Eforie, 
                new Distance[]{
                    new Distance(CityName.Hirsova, 86)
                });
        
        cities[9] = new City(CityName.RimnicuVilcea, 
                new Distance[]{
                    new Distance(CityName.Sibiu, 80),
                    new Distance(CityName.Pitesti, 97),
                    new Distance(CityName.Craiwa, 146)
                });
        
        cities[10] = new City(CityName.Craiwa, 
                new Distance[]{
                    new Distance(CityName.RimnicuVilcea, 146),
                    new Distance(CityName.Pitesti, 138),
                    new Distance(CityName.Dobreta, 120)
                });
        
        cities[11] = new City(CityName.Figaras, 
                new Distance[]{
                    new Distance(CityName.Sibiu, 99),
                    new Distance(CityName.Bucharest, 211)
                });
        
        cities[12] = new City(CityName.Pitesti, 
                new Distance[]{
                    new Distance(CityName.RimnicuVilcea, 97),
                    new Distance(CityName.Craiwa, 138),
                    new Distance(CityName.Bucharest, 101)
                });
        
        cities[13] = new City(CityName.Xeamt, 
                new Distance[]{
                    new Distance(CityName.Iasi, 87)
                });
        
        cities[14] = new City(CityName.Iasi, 
                new Distance[]{
                    new Distance(CityName.Xeamt, 87),
                    new Distance(CityName.Vadui, 92)
                });
        
        cities[15] = new City(CityName.Vadui, 
                new Distance[]{
                    new Distance(CityName.Iasi, 92),
                    new Distance(CityName.Urziceni, 142)
                });
        
        cities[16] = new City(CityName.Urziceni, 
                new Distance[]{
                    new Distance(CityName.Vadui, 142),
                    new Distance(CityName.Bucharest, 85),
                    new Distance(CityName.Hirsova, 98)
                });
        
        cities[17] = new City(CityName.Bucharest, 
                new Distance[]{
                    new Distance(CityName.Figaras, 211),
                    new Distance(CityName.Pitesti, 101),
                    new Distance(CityName.Urziceni, 85),
                    new Distance(CityName.Girgiu, 90)
                });
        
        cities[18] = new City(CityName.Girgiu, 
                new Distance[]{
                    new Distance(CityName.Bucharest, 90)
                });
        
        cities[19] = new City(CityName.Hirsova, 
                new Distance[]{
                    new Distance(CityName.Urziceni, 98),
                    new Distance(CityName.Eforie, 86)
                });
        
        Scanner scanner = new Scanner(System.in);
        int inpOrigin, inpDest;
        CityName origin, dest;
        
        System.out.println("Enter origin city and destination city number according to below list!");
        for(int i=0; cities.length>i; i++) System.out.println(i+1 + ": " + cities[i].cityName);
        System.out.println("");
        
        System.out.print("Origin City: ");
        inpOrigin = inputInt(1, 20);
        origin = cities[inpOrigin-1].cityName;
        
        System.out.print("Destination City: ");
        inpDest = inputInt(1, 20);
        dest = cities[inpDest-1].cityName;
        
        calcShortestDistance(origin, dest);
    }
    
    public static void calcShortestDistance(CityName origin, CityName dest){
        System.out.println("\nShortest path from " + origin + " to " + dest + ":");
        Node node = new Node(null, origin, 0, 0);
        queue = new ArrayList();
        queue.add(node);
        
        while(queue.size()>0){
            //System.out.println("1");
            sortQueue(queue);
            Node parent = queue.get(0);
            
            if(parent.cityName == dest){
                Stack stack = new Stack();
                Node p = parent;
                
                while(p!= null){
                    stack.push(p.cityName);
                    p=p.parent;
                }
                
                while(!stack.empty()){
                    System.out.print(stack.pop());
                    if(!stack.empty()) System.out.print("->");
                }
                
                System.out.println("\nDestination: " + parent.cost + "KM");
                return;
            }
            
            Distance[] distances = null;
            for(int i=0; 20>i; i++){
                if(cities[i].cityName == parent.cityName) distances = cities[i].distances;
            }

            for(int i=0; distances.length>i; i++){
                queue.add(new Node(parent, distances[i].destCity, parent.cost + distances[i].distance, parent.depth+1));
            }
            
            queue.remove(0);
        }
    }
    
    public static void sortQueue(List<Node> queue){
        for(int i=0; queue.size()>i; i++){
            int choicenIndex = i;
            for(int j=i+1; queue.size()>j; j++){
                if(queue.get(choicenIndex).cost>queue.get(j).cost) choicenIndex=j;
            }
            
            if(choicenIndex!=i){
                Node temp = queue.get(i);
                queue.set(i, queue.get(choicenIndex));
                queue.set(choicenIndex, temp);
            }
        }
    }
    
    public static int inputInt(int min, int max){
        Scanner scanner = new Scanner(System.in);
        int result;
        
        while(true){
            String input = scanner.next();
            try{result = Integer.valueOf(input);}
            catch(NumberFormatException ex){
                System.out.print("Wrong input! Try Again: ");
                continue;
            }
            
            if(result<min || result>max){
                System.out.print("Wrong input! Try Again: ");
                continue;
            }
            
            return result;
        }
    }
}
