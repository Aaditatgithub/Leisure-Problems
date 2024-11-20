#include<iostream>
#include<vector>
#include<queue>
using namespace std;

typedef struct Node{
    int val;
    struct Node* left;
    struct Node* right;

    Node(int a) : val(a), left(nullptr), right(nullptr) {}
}*PNode;

void BFS(PNode root){
   
   queue<PNode> nodeQueue;
   nodeQueue.push(root);

    while(!nodeQueue.empty()){
        PNode curr = nodeQueue.front();
        if(curr->left)
            nodeQueue.push(curr->left);
        if(curr->right)
            nodeQueue.push(curr->right);

        cout << curr->val << " ";
        nodeQueue.pop();  
    }
}

void DFS(PNode root){
    // pretty straightforward
        //inorder //preorder // postorder
    
}

int main(){

    PNode root = new Node(10);
    root->left = new Node(20);
    root->right = new Node(30);
    root->left->left = new Node(40);
    root->left->right = new Node(50);
    root->right->left = new Node(60);
    root->right->right = new Node(70);

    BFS(root);
}