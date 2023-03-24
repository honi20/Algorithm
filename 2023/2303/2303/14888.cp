#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 9876543210

ll n, x;
vector <ll> arr;
ll maxval = -1*INF, minval = INF;

void solve(ll idx, ll total, ll a, ll b, ll c, ll d){
//    cout<<idx<<" "<<total<<"\n";
    if (idx == n){
        maxval = max(maxval, total);
        minval = min(minval, total);
        return;
    }
    
    // +
    if (a > 0)
        solve(idx+1, total+arr[idx], a-1, b, c, d);
    
    // -
    if (b > 0)
        solve(idx+1, total-arr[idx], a, b-1, c, d);
    
    // *
    if (c > 0)
        solve(idx+1, total*arr[idx], a, b, c-1, d);
    
    // /
    if (d > 0){
        if (total > 0)
            solve(idx+1, total/arr[idx], a, b, c, d-1);
        else
            solve(idx+1, (abs(total)/arr[idx])*(-1), a, b, c, d-1);
    }
    
    
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll a, b, c, d;
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>x;
        arr.push_back(x);
    }
    cin>>a>>b>>c>>d;
    
    solve(1, arr[0], a, b, c, d);
    cout<<maxval<<"\n"<<minval;
}

