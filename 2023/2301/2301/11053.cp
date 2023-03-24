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
    
    int n;
    int arr[1002] = {0,};
    int dp[1002] = {0,};
    
    cin>>n;
    for (int i=1; i<=n; i++)
        cin>>arr[i];
    
    int result = 0;
    for (int i=1; i<=n; i++){
        dp[i] = 1;
        for (int j = i-1; j >= 1; j--){
            if (arr[i] > arr[j])
                dp[i] = max(dp[i], dp[j]+1);
        }
        result = max(result, dp[i]);
    }
    cout<<result;
}

