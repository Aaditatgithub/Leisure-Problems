#include<iostream>
#include<list>
#include<unordered_map>
#include<string>
#include<queue>
#include<functional>
#include<limits>
#include<algorithm>
using namespace std;

struct city{
    int distance;
    string name;
    int fcost;

    city(int dist, string n, int _fcost = 0) : distance(dist), name(n), fcost(_fcost) {}

    // operator overloading for finding greater instance
    bool operator>(const city& other) const {
        return fcost > other.fcost ;
    } 
};


//astar
void heuristicSearch(auto& cities, const string& start, const string& destination, unordered_map<string,int>& heuristics){
    priority_queue<city, vector<city>, greater<city>> pq;
    unordered_map<string, int> gCost;
    unordered_map<string, string> parent;

    for(const auto& city: cities){
        gCost[city.first] = numeric_limits<int>::max();
    } gCost[start] = 0;

    pq.push(city(0,start,heuristics[start]));
    parent[start] = "";

    while(!pq.empty()){
        city curr = pq.top(); pq.pop();

        // dest reached
        if(curr.name == destination){
            vector<string> path;
            for(string at = curr.name; !at.empty(); at = parent[at]){
                path.push_back(at);
            } 
            reverse(path.begin(), path.end());
            cout << "Path: ";
            for(const string& citi: path)
                cout << citi << " -> ";
            cout << "Reached.";
            return;
        }

        // better path to neighbour
        for(city& neighbour: cities[curr.name]){
            int newGCost = neighbour.distance + gCost[curr.name];
            if(newGCost < gCost[neighbour.name]) {   // better path
                gCost[neighbour.name] = newGCost;

                // string tempname = neighbour.name;  // temp non-const copy
                parent[neighbour.name] = curr.name;

                int fcost = newGCost + heuristics[neighbour.name];
                pq.push(city(neighbour.distance, neighbour.name, fcost));
            }
        }
    }
    cout << "No Path to Destination Found." << endl;
}

int main(){
    unordered_map<string, list<city>> cities;

    // Heuristic values (h-cost)
    unordered_map<string, int> heuristic = {
        {"A", 10}, {"B", 7}, {"C", 7}, {"D", 4}, {"E", 4}, {"F", 0}
    };

    // City connections and distances (g-costs)
    cities["A"] = {city(4, "B"), city(5, "C")};
    cities["B"] = {city(4, "A"), city(11, "C"), city(9, "D"), city(7, "E")};
    cities["C"] = {city(11, "B"), city(5, "A"), city(3, "E")};
    cities["D"] = {city(9, "B"), city(2, "F"), city(13, "E")};
    cities["E"] = {city(3, "C"), city(7, "B"), city(13, "D"), city(6, "F")};
    cities["F"] = {city(2, "D"), city(6, "E")};

    // Find path from A to F
    heuristicSearch(cities, "A", "F", heuristic);

    return 0;
}