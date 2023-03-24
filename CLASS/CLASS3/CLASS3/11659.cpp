#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,m;
    int a,b;
    int arr[100002];
    int dp[100002];
    
    arr[0]=dp[0]=0;
    cin>>n>>m;
    for (int i=1;i<=n;i++){
        cin>>arr[i];
        dp[i]=dp[i-1]+arr[i];
    }
    while (m--){
        cin>>a>>b;
        cout<<dp[b]-dp[a-1]<<"\n";
    }
}
