#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <queue>
using namespace std;
typedef long long ll;

int n, m;
int t, sy, sx;

int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
int dp[1002][1002] = {0,};

void solve(int y, int x){
    queue <pair<int,int>> q;
    q.push({y, x});
    
    while (!q.empty()){
        int nowy = q.front().first;
        int nowx = q.front().second;
        q.pop();
        
        for (int k=0; k<4; k++){
            int nexty = nowy + dy[k];
            int nextx = nowx + dx[k];
            
            if (nexty < 0 || nextx < 0 || nexty >= n || nextx >= m)
                continue;
            
            if (dp[nexty][nextx] == 0)
                continue;
            
            if (dp[nexty][nextx] == -1 || dp[nowy][nowx] + 1 < dp[nexty][nextx]){
                dp[nexty][nextx] = dp[nowy][nowx] + 1;
                q.push({nexty, nextx});
            }
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0; i<n; i++){
        for (int j=0; j<m; j++){
            cin>>t;
            dp[i][j] = -1;
            if (t == 2){
                sy = i;
                sx = j;
                dp[i][j] = 0;
            }
            else if (t == 0)
                dp[i][j] = 0;
        }
    }
    solve(sy, sx);
    
    for (int i=0; i<n; i++){
        for (int j=0; j<m; j++)
            cout<<dp[i][j]<<" ";
        cout<<"\n";
    }
    
    return 0;
}
