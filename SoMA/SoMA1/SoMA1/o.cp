#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
#define INF 987654321

int n, m;
vector <pair<int,int>> v[1002];
bool visited[1002];

void dfs(int node, int dest, int dist){
    visited[node] = true;
//    cout<<node<<" "<<dest<<" "<<dist<<"\n";
    
    if (node == dest){
        cout<<dist<<"\n";
        return;
    }
    
    for (int i=0; i<v[node].size(); i++){
        if (!visited[v[node][i].first]){
            dfs(v[node][i].first, dest, dist+v[node][i].second);
        }
    }
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int x, y, c;
    
    cin>>n>>m;
    
    for (int i=0; i<n-1; i++){
        cin>>x>>y>>c;
        v[x].push_back({y, c});
        v[y].push_back({x, c});
    }
    
    for (int i=0; i<m; i++){
        for (int i=0; i<=n; i++)
            visited[i] = false;
        
        cin>>x>>y;
        dfs(x, y, 0);
    }
    
    return 0;
}
