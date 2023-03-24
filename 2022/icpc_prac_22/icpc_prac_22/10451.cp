#include <iostream>
#include <vector>
#include <cstring>
using namespace std;
typedef long long ll;

vector<int> v[1002];
bool visited[1002];
bool check = false;
int result = 0;

void dfs(int node, int parent){
    visited[node] = true;
    
    for (int i=0; i<v[node].size(); i++){
        if (v[node][i] == parent)
            continue;
        
        if (visited[v[node][i]])
            check = true;
        
        else
            dfs(v[node][i], node);
    }
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    int t, n, x;
    cin>>t;
    while (t--){
        result = 0;
        cin>>n;
        
        for (int i=1; i<=n; i++){
            visited[i] = false;
            v[i].clear();
        }
        
        for (int i=1; i<=n; i++){
            cin>>x;
            v[i].push_back(x);
        }
        
        for (int i=1; i<=n; i++){
            check = false;
            if (!visited[i])
                dfs(i, 0);
            
            if (v[i].size() == 1 && i < v[i][0] && v[v[i][0]][0] == i)
                check = true;
            
            if (check){
                result++;
            }
        }
        cout<<result<<"\n";
    }
}
