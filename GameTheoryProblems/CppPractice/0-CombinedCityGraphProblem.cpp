#include<iostream>
#include<vector>
#include<list>
#include<string>
#include<map>
#include<queue>
using namespace std;

typedef struct node{
    string city;
    int distance;
    node(int _value, string _city) : distance(_value), city(_city){}
}*pnode;

void addEdge(vector<list<pnode>>& graph, int u, pnode newCity){
    graph[u].push_back(newCity);
}

void BFS(int vertices, auto& graph, auto& cityIds, int start, int goal){
    vector<bool> visited(vertices,false);
    queue<node> nodeQueue; 
    nodeQueue.push()

    while()

}

int main(){
    // City conventions:
        // 0 -> A
        // 1 -> B
        // 2 -> C
        // 3 -> D
        // 4 -> E
        // 5 -> F
    
    int vertices = 5;
    vector<list<pnode>> graph(vertices);

    map<string,int> city_id = {
        {"A",0},  {"B", 1}, {"C", 2}, {"D", 3}, {"E", 4}, {"F", 5}
    };
    

    // intializing cities
    addEdge(graph, 0, new node(16,"B"));
    addEdge(graph, 0, new node(34,"F"));

    addEdge(graph, 1, new node(16,"A"));
    addEdge(graph, 1, new node(21,"C"));
    addEdge(graph, 1, new node(9,"D"));

    addEdge(graph, 2, new node(21,"B"));
    addEdge(graph, 2, new node(7,"F"));
    addEdge(graph, 2, new node(12,"D"));

    addEdge(graph, 3, new node(9,"B"));
    addEdge(graph, 3, new node(12,"C"));
    addEdge(graph, 3, new node(15,"E"));

    addEdge(graph, 4, new node(16,"F"));
    addEdge(graph, 4, new node(15,"D"));
    
    addEdge(graph, 5, new node(34,"A"));
    addEdge(graph, 5, new node(16,"E"));
    addEdge(graph, 5, new node(7,"C"));


    // Breadth First Search
    BFS(graph);

}