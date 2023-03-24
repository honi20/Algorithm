#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int t, n;
int arr[100002][2];
int dp[100002][2];

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    
    cin>>t;
    while (t--){
        cin>>n;
        
        dp[0][0] = dp[0][1] = 0;
        for (int i=1; i<=n; i++){
            cin>>arr[i][0];
            dp[i][0] = 0;
        }
        for (int i=1; i<=n; i++){
            cin>>arr[i][1];
            dp[i][1] = 0;
        }
        
        dp[1][0] = arr[1][0];
        dp[1][1] = arr[1][1];
        
        for (int i=2; i<=n; i++){
            dp[i][0] = max(dp[i-1][1]+arr[i][0], max(dp[i-2][0]+arr[i][0], dp[i-2][1]+arr[i][0]));
            dp[i][1] = max(dp[i-1][0]+arr[i][1], max(dp[i-2][0]+arr[i][1], dp[i-2][1]+arr[i][1]));
        }
        cout<<max(dp[n][0], dp[n][1])<<"\n";
    }
}

