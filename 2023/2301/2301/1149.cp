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

int n;
int dp[1002][3];

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int a, b, c;
    
    cin>>n;
    cin>>a>>b>>c;
    dp[0][0] = a; dp[0][1] = b; dp[0][2] = c;
    
    for (int i=1; i<n; i++){
        cin>>a>>b>>c;
        dp[i][0] = min(dp[i-1][1]+a, dp[i-1][2]+a);
        dp[i][1] = min(dp[i-1][0]+b, dp[i-1][2]+b);
        dp[i][2] = min(dp[i-1][0]+c, dp[i-1][1]+c);
    }
    cout<<min(dp[n-1][0], min(dp[n-1][1], dp[n-1][2]));
}

