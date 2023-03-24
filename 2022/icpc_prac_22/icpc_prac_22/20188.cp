//
//  20188.cpp
//  icpc_prac_22
//
//  Created by 최예헌 on 2022/10/10.
//
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
using namespace std;
typedef long long ll;

vector <int> v[300003];
int dp[300003];
int n;

void solve(int node, int par){
    if (node == 1)
        dp[node] = 0;
    else
        dp[node] = dp[par]+1;
    
    for (int i=0;i<v[node].size();i++){
        if (v[node][i] == par)
            continue;
        
        solve(v[node][i], node);
    }
    return;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int a,b;
    cin>>n;
    v[1].push_back(0);
    for (int i=0;i<n-1;i++){
        cin>>a>>b;
        v[a].push_back(b);
        v[b].push_back(a);
    }
    
    for (int i=0;i<=n;i++){
        dp[i] = -1;
    }
    dp[0] = 0;
    solve(1,0);
    
    for (int i=1;i<=n;i++){
        cout<<i<<" : "<<dp[i]<<"\n";
    }
}
