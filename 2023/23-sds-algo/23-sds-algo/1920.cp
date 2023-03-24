#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <set>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n, m, x;
    set<ll> s;
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>x;
        s.insert(x);
    }
    cin>>m;
    while (m--){
        cin>>x;
        if (s.find(x) == s.end())
            cout<<"0\n";
        else
            cout<<"1\n";
    }
}

