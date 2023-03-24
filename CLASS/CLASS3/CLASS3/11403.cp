#include <iostream>
#include <vector>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, x;
    vector <int> v[102];
    int dp[102][102]={0,};
    
    cin>>n;
    for (int i=1;i<=n;i++){
        for (int j=1;j<=n;j++){
            cin>>x;
            if (x == 1)
                v[i].push_back(j);
        }
    }
    
    for (int i=1;i<=n;i++){
        dfs(i);
    }
    
    
    for (int i=1;i<=n;i++){
        for (int j=1;j<=n;j++)
            cout<<dp[i][j]<<" ";
        cout<<"\n";
    }
}

