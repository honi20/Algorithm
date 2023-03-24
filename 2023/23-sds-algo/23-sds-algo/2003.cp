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

ll n, m, x;
ll sumarr[10002] = {0,};

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=1; i<=n; i++){
        cin>>x;
        sumarr[i] = sumarr[i-1] + x;
    }
    
    
    int s = 0, e = 1;
    int result = 0;
    while (true){

        if (s > e || e > n || s > n)
            break;

        if (sumarr[e] - sumarr[s] == m){
            s++;
            result++;
        }

        else if (sumarr[e] - sumarr[s] < m)
            e++;

        else if (sumarr[e] - sumarr[s] > m)
            s++;
    }
    cout<<result;
}

