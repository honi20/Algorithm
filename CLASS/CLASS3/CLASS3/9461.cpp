#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t,n;
    long long dp[102];
    
    cin>>t;
    dp[0]=0; dp[1]=dp[2]=dp[3]=1; dp[4]=dp[5]=2;
    for (int i=6;i<102;i++)
        dp[i]=dp[i-1]+dp[i-5];
    
    while(t--){
        cin>>n;
        cout<<dp[n]<<"\n";
    }
}
