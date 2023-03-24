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

ll fib[100];

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n;
    cin>>n;
    
    fib[0] = 0; fib[1] = 1;
    if (n < 2)
        cout<<fib[n];
    
    else{
        for (int i=2; i<=n; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        cout<<fib[n];
    }
}
