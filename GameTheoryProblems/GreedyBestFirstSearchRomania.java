package GameTheoryProblems;
import java.util.*;

public class GreedyBestFirstSearchRomania {

    static class City {
        String name;
        double h; // Heuristic cost to goal
        City parent;
        double g; // Cost from start to this city

        City(String name) {
            this.name = name;
            this.h = Double.MAX_VALUE;
            this.g = 0.0;
            this.parent = null;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            City city = (City) obj;
            return Objects.equals(name, city.name);
        }
        
        @Override
        public String toString() {
            return name;
        }
    }

    private static final Map<String, List<Map.Entry<City, Double>>> graph = new HashMap<>();
    private static final Map<String, Double> heuristic = new HashMap<>();

    static {
        graph.put("Bucharest", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Ploiesti"), 60.0),
                new AbstractMap.SimpleEntry<>(new City("Iasi"), 150.0),
                new AbstractMap.SimpleEntry<>(new City("Brasov"), 80.0)));
        graph.put("Cluj-Napoca", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Timisoara"), 100.0),
                new AbstractMap.SimpleEntry<>(new City("Brasov"), 120.0)));
        graph.put("Timisoara", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Cluj-Napoca"), 100.0),
                new AbstractMap.SimpleEntry<>(new City("Arad"), 140.0)));
        graph.put("Iasi", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Bucharest"), 150.0)));
        graph.put("Brasov", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Bucharest"), 80.0),
                new AbstractMap.SimpleEntry<>(new City("Cluj-Napoca"), 120.0),
                new AbstractMap.SimpleEntry<>(new City("Sibiu"), 60.0)));
        graph.put("Constanta", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Bucharest"), 100.0)));
        graph.put("Sibiu", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Brasov"), 60.0),
                new AbstractMap.SimpleEntry<>(new City("Arad"), 140.0)));
        graph.put("Craiova", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Ploiesti"), 110.0),
                new AbstractMap.SimpleEntry<>(new City("Arad"), 130.0)));
        graph.put("Ploiesti", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Bucharest"), 60.0),
                new AbstractMap.SimpleEntry<>(new City("Craiova"), 110.0)));
        graph.put("Arad", Arrays.asList(
                new AbstractMap.SimpleEntry<>(new City("Timisoara"), 140.0),
                new AbstractMap.SimpleEntry<>(new City("Sibiu"), 140.0),
                new AbstractMap.SimpleEntry<>(new City("Craiova"), 130.0)));

        // Heuristic values (unchanged)
        heuristic.put("Bucharest", 0.0);
        heuristic.put("Cluj-Napoca", 50.0);
        heuristic.put("Timisoara", 40.0);
        heuristic.put("Iasi", 30.0);
        heuristic.put("Brasov", 20.0);
        heuristic.put("Constanta", 10.0);
        heuristic.put("Sibiu", 25.0);
        heuristic.put("Craiova", 35.0);
        heuristic.put("Ploiesti", 15.0);
        heuristic.put("Arad", 45.0);
    }

    public static List<City> greedyBestFirstSearch(City start, City goal) {
        PriorityQueue<City> openList = new PriorityQueue<>(Comparator.comparingDouble(c -> c.h + c.g));
        Set<City> closedList = new HashSet<>();
    
        start.h = heuristic.get(start.name);
        openList.add(start);
    
        while (!openList.isEmpty()) {
            City current = openList.poll();
            System.out.println("Expanding City: " + current);
    
            if (current.equals(goal)) {
                return reconstructPath(current);
            }
    
            //marking as explored
            closedList.add(current);
    
            for (Map.Entry<City, Double> entry : graph.getOrDefault(current.name, Collections.emptyList())) {
                City neighbor = entry.getKey();
                double weight = entry.getValue();
    
                if (!closedList.contains(neighbor)) {
                    neighbor.h = heuristic.get(neighbor.name);
                    neighbor.g = current.g + weight;
                    neighbor.parent = current;
                    openList.add(neighbor);
                    System.out.println("Added to Open List: " + neighbor + " with cost: " + neighbor.g + " and heuristic: " + neighbor.h);
                }
            }
        }
        System.out.println("No path found.");
        return Collections.emptyList(); // No path found
    }
    

    private static List<City> reconstructPath(City goal) {
        List<City> path = new ArrayList<>();
        City current = goal;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        City start = new City("Arad");
        City goal = new City("Bucharest");
        List<City> path = greedyBestFirstSearch(start, goal);

        System.out.println("Path found:");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i == path.size() - 1)
                break;
            System.out.print(" -> ");
        }
    }
}
