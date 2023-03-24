#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    int dp[1002];
    
    dp[0]=0; dp[1]=1; dp[2]=3;
    cin>>n;
    for (int i=3;i<=n;i++){
        dp[i] = (dp[i-2]*2)%10007;
        dp[i] += (dp[i-1]%10007);
        dp[i] %= 10007;
    }
    cout<<dp[n];
}
