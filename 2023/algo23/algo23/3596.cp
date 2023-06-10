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

int dp[2002] = {0,};
bool visited[102];

void init(){
    for (int i=0; i<102; i++)
        visited[i] = false;
}

void solve(){
    dp[1] = dp[2] = dp[3] = 1;
    
    int tmp = 0;
    for (int i=4; i<2002; i++){
        init();
        for (int j=1; j<=(i+1 >> 1); j++){
            if (j - 3 > 0)
                tmp = dp[i-j-2] ^ dp[j-3];
            else
                tmp = dp[i-j-2] ^ 0;
            visited[tmp] = true;
        }
        
        for (int j=0; j<102; j++){
            if (!visited[j]){
                dp[i] = j;
                break;
            }
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    cin>>n;
    solve();
    if (dp[n] != 0)
        cout<<"1";
    else
        cout<<"2";
    return 0;
}

