#include <iostream>
#include <vector>
using namespace std;

int n,m;
int x,y;
vector<int> v[1002];
bool visited[1002];
int result = 0;

void dfs(int n){
    visited[n]=true;
    for (int i=0;i<v[n].size();i++){
        if (!visited[v[n][i]])
            dfs(v[n][i]);
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0;i<m;i++){
        cin>>x>>y;
        v[x].push_back(y);
        v[y].push_back(x);
    }
    for (int i=1;i<=n;i++){
        if (!visited[i]){
            dfs(i);
            result++;
        }
    }
    cout<<result;
}
