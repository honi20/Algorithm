//
//  1654.cpp
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
    
    ll k,n,x;
    vector <ll> v;
    ll mmax = 0;
    
    cin>>k>>n;
    for (int i=0;i<k;i++){
        cin>>x;
        mmax = max(mmax, x);
        v.push_back(x);
    }
    
    ll s = 1;
    ll e = mmax;
    ll result=0;
    
    while (s<=e){
        ll mid = (s+e)/2;
        ll sum = 0;
        
        for (int i=0;i<v.size();i++){
            sum+=(v[i]/mid);
            if (sum >= n)
                break;
        }
        
        if (sum >= n){
            result = mid;
            s = mid+1;
        }
        else
            e = mid-1;
    }
    cout<<result;
}
