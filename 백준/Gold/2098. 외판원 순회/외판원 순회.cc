#include <iostream>
#include <cstring>
using namespace std;

int n;
int w[20][20];
int dp[(1<<20)][20];

int solve(int visited,int now){
    
    if (visited == (1<<n)-1){
        if (w[now][0]==0){
            return 987654321;
        }
        else
            return w[now][0];
    }
    
    if (dp[visited][now]!= -1)
        return dp[visited][now];
    
    dp[visited][now]=987654321;
    for (int i=1;i<n;i++){
        if ((1<<i)&visited || w[now][i]==0)
            continue;
        
        dp[visited][now]=min(dp[visited][now],solve(visited|(1<<i),i)+w[now][i]);
    }
    return dp[visited][now];
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0;i<n;i++){
        for (int j=0;j<n;j++){
            cin>>w[i][j];
        }
    }
    memset(dp,-1,sizeof(dp));
    cout<<solve(1,0);
}