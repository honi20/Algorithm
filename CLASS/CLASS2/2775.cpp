#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int t,n,k;
    cin>>t;
    while (t--){
        int dp[18][18]={0,};
        cin>>k>>n;
        
        dp[0][0]=0;
        for (int i=1;i<=n;i++)
            dp[0][i]=dp[0][i-1]+i;
//        cout<<dp[0][1]<<" "<<dp[0][2]<<" "<<dp[0][3];
        
        for (int i=1;i<=k;i++){
            for (int j=1;j<=n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        
        cout<<dp[k][n]-dp[k][n-1]<<"\n";
    }
}
