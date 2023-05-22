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

int n, m;
int s, e;
int arr[2020] = {0,};
bool dp[2020][2020] = {0,};

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=1; i<=n; i++){
        cin>>arr[i];
        dp[i][i] = 1;
    }
    
    for (int i=1; i<n; i++){
        if (arr[i] == arr[i+1])
            dp[i][i+1] = 1;
    }
    
    for (int i=n-1; i>=1; i--){
        for (int j=i+2; j<=n; j++){
            if (arr[i] == arr[j] && dp[i+1][j-1] == 1)
                dp[i][j] = 1;
        }
    }

    cin>>m;
    while (m--){
        cin>>s>>e;
        
        if (dp[s][e] == 1)
            cout<<"1\n";
        else
            cout<<"0\n";
    }
}

