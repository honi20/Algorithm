#include <iostream>
#include <vector>
#include <queue>
using namespace std;
typedef long long ll;

int n, m, q;
vector <int> v[1002];
int dijk[1002] = {0, };

int solve(int start, int end){
    priority_queue<pair<int,int>, vector<pair<int,int>>, greater<>> pq;     // dist, node
    pq.push({0,start});
    
    while (!pq.empty()){
        int nowdist = pq.top().first;
        int nownode = pq.top().second;
        pq.pop();
        
        if (nownode == end)
            return nowdist;
        
        for (int i=0; i<v[nownode].size(); i++){
            int nextnode = v[nownode][i];
            
            if (dijk[nextnode] == -1 || nowdist + 1 < dijk[nextnode]){
                dijk[nextnode] = nowdist + 1;
                pq.push({dijk[nextnode], nextnode});
            }
        }
    }
    return -1;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int x, y;
    cin>>n>>m;
    for (int i=0; i<m; i++){
        cin>>x>>y;
        v[x].push_back(y);
        v[y].push_back(x);
    }
    
    for (int i=0; i<=n; i++)
        dijk[i] = -1;
    dijk[1] = 0;
    
    cin>>q;
    for (int i=0; i<q; i++){
        cin>>x>>y;
        v[x].push_back(y);
        v[y].push_back(x);
        
        cout<<"0 ";
        for (int j=2; j<=n; j++)
            cout<<solve(1, j)<<" ";
        cout<<"\n";
    }
}
 
