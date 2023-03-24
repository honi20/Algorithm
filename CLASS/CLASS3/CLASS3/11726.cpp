#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int dp[1002];
    int n;
    
    dp[0]=0; dp[1]=1; dp[2]=2;
    
    cin>>n;
    if (n<3){
        cout<<dp[n];
        return 0;
    }
    
    for (int i=3;i<=n;i++){
        dp[i] = (dp[i-1]+dp[i-2])%10007;
    }
    cout<<dp[n];
}
