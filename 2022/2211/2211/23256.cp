#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 1000000007
#define INF 987654321

ll t, n;
ll dp[1000002][2];

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>t;
    
    dp[1][0] = 7;
    dp[1][1] = 3;
//    dp[2][0] = 33;
//    dp[2][1] = 13;
    
    for (int i=2; i<1000001; i++){
        dp[i][0] = ((6 * dp[i-1][0])%MAX - (3 * dp[i-1][1])%MAX)%MAX;
        dp[i][1] = ((dp[i-1][0] - dp[i-1][1]) * 3 + 1)%MAX;
    }
                        
    while (t--){
        cin>>n;
        cout<<dp[n][0]<<"\n";
    }
    
}

