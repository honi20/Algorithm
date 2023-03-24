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

int dp[32][32] = {0,};

void init(){
    dp[0][0] = dp[1][0] = dp[1][1] = 1;
    for (int i=2; i<32; i++){
        for (int j=0; j<=i; j++){
            if (i == j || j == 0){
                dp[i][j] = 1;
                continue;
            }
            dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]);
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int t, n, m;
    cin>>t;
    init();
    while (t--){
        cin>>n>>m;
        cout<<dp[m][n]<<"\n";
    }
}

