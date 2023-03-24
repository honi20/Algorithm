#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10007
#define INF 987654321

int n, k;
ll dp[1002][1002] = {0,};

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>k;
    
    dp[0][0] = dp[1][0] = dp[1][1] = 1;
    for (int i=2; i<=n; i++){
        for (int j=0; j<=i; j++){
            if (i == j || j == 0){
                dp[i][j] = 1;
                continue;
            }
            dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % MAX;
        }
    }
    cout<<dp[n][k];
}

