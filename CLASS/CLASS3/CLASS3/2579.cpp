#include <iostream>
#include <vector>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    int arr[303];
    int dp[303];
    
    cin>>n;
    arr[0]=0;
    for (int i=1;i<=n;i++){
        cin>>arr[i];
    }
    dp[0]=0;
    dp[1]=arr[1];
    dp[2]=max(dp[1]+arr[2], dp[0]+arr[2]);
    
    for (int i=3;i<=n;i++){
        dp[i]=max(dp[i-3]+arr[i-1]+arr[i], dp[i-2]+arr[i]);
    }
    cout<<dp[n];
}
