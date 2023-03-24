#include <iostream>
#include <vector>
using namespace std;

int n, m,a,b;
vector <int> v[102];
bool visited[102];
int result = 0;

void dfs(int n){
    result++;
    visited[n]=true;
    
    for (int i=0;i<v[n].size();i++){
        if (visited[v[n][i]])
            continue;
        dfs(v[n][i]);
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0;i<m;i++){
        cin>>a>>b;
        v[a].push_back(b);
        v[b].push_back(a);
    }
    dfs(1);
    cout<<result-1;
}
