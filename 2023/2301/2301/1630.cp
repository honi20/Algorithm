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

ll num[1000001] = {0,};

void solve(ll n){
    for (ll i=2; i<=n; i++){
        if (num[i])
            continue;
        for (ll j=i*i; j<=n; j+=i)
            num[j]=1;
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n;
    cin>>n;
    solve(n);
    
    ll result = 1;
    for (int i=2; i<=n; i++){
        if (num[i] == 0){
            int tmp = 1;
            int tmpHat = log(n)/log(i);
            
            while (tmpHat--){
                tmp *= i;
                tmp %= INF;
            }
            
            result *= tmp;
            result %= INF;
        }
    }
    cout<<result;
    
    
}

