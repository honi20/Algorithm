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

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n, x;
    int dp[2][3][2];    // [0] : max , [1] : min
    
    cin>>n;
    for (int i=0; i<n; i++){
        if (i == 0){
            for (int j=0; j<3; j++){
                cin>>x;
                dp[0][j][0] = dp[0][j][1] = x;
            }
        }
        
        else {
            for (int j=0; j<3; j++){
                cin>>x;
                
                if (j == 0){
                    dp[1][j][0] = max(dp[0][j+1][0] + x, dp[0][j][0] + x);
                    dp[1][j][1] = min(dp[0][j+1][1] + x, dp[0][j][1] + x);
                }
                else if (j == 1){
                    dp[1][j][0] = max(dp[0][j][0] + x, max(dp[0][j-1][0] + x, dp[0][j+1][0] + x));
                    dp[1][j][1] = min(dp[0][j][1] + x, min(dp[0][j-1][1] + x, dp[0][j+1][1] + x));
                }
                else if (j == 2){
                    dp[1][j][0] = max(dp[0][j-1][0] + x, dp[0][j][0] + x);
                    dp[1][j][1] = min(dp[0][j-1][1] + x, dp[0][j][1] + x);
                }
            }
            
            for (int j=0; j<3; j++){
                dp[0][j][0] = dp[1][j][0];
                dp[0][j][1] = dp[1][j][1];
            }
        }
    }
    
    int resultMax = 0;
    int resultMin = INF;
    
    for (int j=0; j<3; j++){
        resultMax = max(resultMax, dp[0][j][0]);
        resultMin = min(resultMin, dp[0][j][1]);
    }
    
    cout<<resultMax<<" "<<resultMin;
}

/*
 숫자 : 0~9
 
 */
