#include <iostream>
#include <vector>
#include <cmath>
#include <cstring>
using namespace std;
typedef long long ll;

int n, m;
ll x;
vector <ll> v;
ll maxval = 0;
ll result = 0;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0; i<n; i++){
        cin>>x;
        v.push_back(x);
        maxval = max(maxval, x);
    }
    
    ll s = 1;
    ll e = maxval;
    while (s <= e){
        ll mid = (s+e)/2;
        ll total = 0;
        for (int i=0; i<v.size(); i++){
            total += (v[i] / mid);
        }
        
        if (total >= m){
            result = mid;
            s = mid+1;
        }
        else
            e = mid-1;
    }
    
    cout<<result;
    
    return 0;
}
