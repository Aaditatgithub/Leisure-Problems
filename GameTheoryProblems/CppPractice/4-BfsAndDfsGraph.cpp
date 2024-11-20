#include<iostream>
#include<unordered_map>
#include<list>
#include<queue>
#include<unordered_set>
#include<stack>
using namespace std;


void bfs(unordered_map<string, list<string>>& cities, const string& startCity){
    unordered_set<string> visited;
    queue<string> cityQueue;
    cityQueue.push(startCity);

    while(!cityQueue.empty()){
        string curr = cityQueue.front();
        cityQueue.pop();

        if(visited.find(curr) != visited.end())
            continue;
        
        visited.insert(curr);
        cout << "visited: " << curr << endl;

        for(string& city: cities[curr]){
            
                cityQueue.push(city);
        }
    }
}

void dfs(auto& cities, const string& start){
    unordered_set<string> visited;
    stack<string> cityStack;
    cityStack.push(start);

    while(!cityStack.empty()){
        string curr_city = cityStack.top();
        cityStack.pop();

        if(visited.find(curr_city) == visited.end()){
            visited.insert(curr_city);
            cout << "visited: " << curr_city << endl;

            for(const string& neighbour: cities[curr_city]){
                if(visited.find(neighbour) == visited.end())
                    cityStack.push(neighbour);
            }
        }
    }
    
}

bool depthLimitedDFS(auto& cities, const string& curr_city, const string& dest, 
                            const int& max_depth, unordered_set<string>& visited){
    visited.insert(curr_city);
    cout << "visited: " << curr_city << endl;

    if(curr_city == dest)
        return true;

    if(max_depth == 0)
        return false;
    
    for(const string& neighbour: cities[curr_city]){
        if(visited.find(neighbour) == visited.end()){
            if(depthLimitedDFS(cities, neighbour, dest, max_depth - 1, visited))
                return true;
        }
    }
    return false;                           
}

bool iddfs(auto& cities, const string& start, const string& dest, int max_depth){
    for(int depth = 1; depth <= max_depth; depth++){
        unordered_set<string> visited;
        if(depthLimitedDFS(cities, start, dest, max_depth, visited)){
            cout << "Found " << dest << " at depth " << depth << endl; 
            return true;
        }
    }
    return false;
}

int main(){
    
    unordered_map<string, list<string>> cities;

    // city graph
    cities["mumbai"].push_back("delhi");
    cities["mumbai"].push_back("bangalore");

    cities["delhi"].push_back("mumbai");
    cities["delhi"].push_back("kolkata");
    
    cities["bangalore"].push_back("chennai");
    cities["bangalore"].push_back("mumbai");

    cities["chennai"].push_back("bangalore");
    cities["chennai"].push_back("kolkata");

    cities["kolkata"].push_back("chennai");
    cities["kolkata"].push_back("delhi");

    bfs(cities, "mumbai");

    cout << "DFS----------------------" << endl;
    dfs(cities, "mumbai");

    cout << "IDDFS---------------------" << endl;
    iddfs(cities,"mumbai","kolkata",3);
}