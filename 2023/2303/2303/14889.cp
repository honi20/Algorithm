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

int n, x;
int dp[22][22] = {0,};
bool visited[22] = {false,};
int result = INF;

void solve(int idx, int cnt){
    if (cnt == n/2){
        vector <int> team1, team2;
        int sum1 = 0, sum2 = 0;
        
        for (int i=0; i<n; i++){
            if (visited[i])
                team1.push_back(i);
            else
                team2.push_back(i);
        }
        
        for (int i=0; i<team1.size(); i++){
            for (int j=0; j<team1.size(); j++)
                sum1 += dp[team1[i]][team1[j]];
        }
        
        for (int i=0; i<team2.size(); i++){
            for (int j=0; j<team2.size(); j++)
                sum2 += dp[team2[i]][team2[j]];
        }
        
        result = min(result, abs(sum1-sum2));
        
        return;
    }
    
    if (idx == n-1){
        return;
    }
    
    visited[idx+1] = true;
    solve(idx+1, cnt+1);
    visited[idx+1] = false;
    solve(idx+1, cnt);
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0; i<n; i++){
        for (int j=0; j<n; j++){
            cin>>dp[i][j];
        }
    }
    
    visited[0] = true;
    solve(0, 1);
    visited[0] = false;
    solve(0, 0);
    cout<<result;
}
