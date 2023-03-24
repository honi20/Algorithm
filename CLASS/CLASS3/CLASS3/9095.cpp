#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t,n;
    int dp[12];
    
    cin>>t;
    dp[0]=0; dp[1]=1; dp[2]=2; dp[3]=4;
    for (int i=4;i<12;i++)
        dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
    
    while (t--){
        cin>>n;
        cout<<dp[n]<<"\n";
    }
}
