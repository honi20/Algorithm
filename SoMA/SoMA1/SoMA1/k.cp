#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;

int n, m;
int s, e;
int x, y;

vector <int> v[102];
bool visited[102];
bool flag = false;

void solve(int now, int e, int dist){
    visited[now] = true;
    
//    cout<<now<<" ";
    if (now == e){
        cout<<dist;
        flag = true;
        return;
    }
    
    for (int i=0; i<v[now].size(); i++){
        if (!visited[v[now][i]]){
            solve(v[now][i], e, dist+1);
        }
    }
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    cin>>s>>e;
    cin>>m;
    for (int i=0;i<m; i++){
        cin>>x>>y;
        v[x].push_back(y);
        v[y].push_back(x);
    }
    
    solve(s, e, 0);
    if (!flag)
        cout<<"-1";
    return 0;
}
