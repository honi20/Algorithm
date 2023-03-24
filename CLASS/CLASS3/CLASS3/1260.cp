#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int n,m,k;
int x,y;
vector <int> v[1002];
bool visited[1002];
int cnt = 0;

void dfs(int n){
    visited[n]=true;
    cout<<n<<" ";
    for (int i=0; i<v[n].size(); i++){
        if (!visited[v[n][i]]){
            dfs(v[n][i]);
        }
    }
}

void bfs(int n){
    queue<int> q;
    q.push(n);
    
    while (!q.empty()){
        int now = q.front();
        q.pop();
        if (visited[now])
            continue;
        
        visited[now] = true;
        cnt++;
        cout<<now<<" ";
        
        
        for (int i=0;i<v[now].size();i++){
            if (!visited[v[now][i]]){
                q.push(v[now][i]);
            }
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
   
    cin>>n>>m>>k;
    for (int i=0;i<m;i++){
        cin>>x>>y;
        v[x].push_back(y);
        v[y].push_back(x);
    }
    
    for (int i=0;i<=n;i++){
        sort(v[i].begin(), v[i].end());
    }
    
    dfs(k);
    cout<<"\n";
    for (int i=0;i<=n;i++)
        visited[i]=false;
    bfs(k);
}

