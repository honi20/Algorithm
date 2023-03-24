//
//  2805.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
#include <vector>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n,m,x;
    ll mmax = 0;
    vector <ll> v;
    ll result=0;
    
    cin>>n>>m;
    for (int i=0;i<n;i++){
        cin>>x;
        mmax = max(mmax, x);
        v.push_back(x);
    }
    
    ll s = 0;
    ll e = mmax;
    
    while (s<=e){
        ll mid = (s+e)/2;
        
        ll sum=0;
        for (int i=0;i<v.size();i++){
            if (v[i] > mid){
                sum+=(v[i]-mid);
                if (sum >= m)
                    break;
            }
        }
        
        if (sum >= m){
            result = mid;
            s = mid+1;
        }
        
        else
            e = mid-1;
    }
    cout<<result;
}
