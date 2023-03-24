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

ll solve(ll a, ll b, ll c){
    if (b == 1)
        return a%c;
    
    ll temp = solve(a, b/2, c)%c;
    if (b % 2 == 0)
        return temp * temp % c;
    else
        return temp * temp % c * a % c;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll a, b, c;
    cin>>a>>b>>c;
    cout<<solve(a, b, c);
}


