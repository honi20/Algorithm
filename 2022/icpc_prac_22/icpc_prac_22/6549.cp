#include <iostream>
#include <vector>
#include <cstring>
#include <algorithm>
#include <queue>
#include <tuple>
#include <set>
using namespace std;
typedef long long ll;
typedef tuple<int,int,int> tii;
#define MAX 1000002
#define INF 987654321

ll n, x, result = 0;
ll arr[MAX] = {0,};
ll hei[100002] = {0,};

ll init(int node, int s, int e){
    if (s == e)
        return arr[node] = s;
    
    int mid = (s+e)/2;
    ll a = init(2*node, s, mid);
    ll b = init(2*node+1, mid+1, e);
    
    if (hei[a] < hei[b])
        return a;
    else
        return b;
}

int query(int node, int s, int e, int l, int r){
    if (e < l || r < s)
        return INF;
    
    if (l <= s && e <= r)
        return arr[node];
    
    int mid = (s+e)/2;
    int a = query(2*node, s, mid, l, r);
    int b = query(2*node+1, mid+1, e, l, r);
    
    if (a == INF)
        return b;
    else if (b == INF)
        return a;
    else{
        if (hei[a] < hei[b])
            return a;
        else
            return b;
    }
}

void solve(ll l, ll r){
    if (l > r)
        return;
    
    ll idx = query(1, 0, n-1, l, r);
    result = max(result, hei[idx]*(r-l+1));
    
    solve(l, idx-1);
    solve(idx+1, r);
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    while (1){
        result = 0;
        
        cin>>n;
        if (n == 0)
            break;
        
        for (int i=0; i<n; i++)
            cin>>hei[i];
        
        init(1, 0, n-1);
        cout<<"*";
        solve(0, n-1);
        cout<<result<<"\n";
    }
    
}

