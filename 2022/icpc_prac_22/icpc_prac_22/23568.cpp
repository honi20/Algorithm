//
//  23568.cpp
//  icpc_prac_22
//
//  Created by 최예헌 on 2022/10/10.
//
#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <map>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n, now;
    ll pos,dist;
    string dir;
    map<ll, ll> m;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>pos>>dir>>dist;
        if (dir == "L"){
            m[pos] = pos-dist;
        }
        else
            m[pos] = pos+dist;
    }
    cin>>now;
    
    while (true){
        if (m.find(now) == m.end()){
            cout<<now;
            break;
        }
        
        now = m[now];
    }
    return 0;
}
