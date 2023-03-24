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
int arr[1030][1030] = {0,};
int sum[1030][1030] = {0,};
int a, b, c, d;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=1; i<=n; i++){
        for (int j=1; j<=n; j++){
            cin>>arr[i][j];
            sum[i][j] = sum[i][j-1] + arr[i][j];
        }
    }
    
    while (m--){
        cin>>a>>b>>c>>d;
        int result = 0;
        for (int i=a; i<=c; i++){
            result += (sum[i][d] - sum[i][b-1]);
        }
        cout<<result<<"\n";
    }
    
    
}

