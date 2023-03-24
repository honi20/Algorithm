//
//  18111.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n,m,b;
    ll arr[505][505];
    ll ht = 0;
    ll result_time=987654321, result_ht=0;
    
    cin>>n>>m>>b;
    for (int i=0;i<n;i++){
        for (int j=0;j<m;j++){
            cin>>arr[i][j];
            ht = max(ht, arr[i][j]);
        }
    }
    // 최소 시간 -> 최대 높이
    while (ht>=0){
        ll cnt = b;
        ll sum = 0;
        
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (arr[i][j] > ht){
                    cnt+=(arr[i][j]-ht);
                    sum+=((arr[i][j]-ht)*2);
                }
            }
        }
        
        for (int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                if (arr[i][j] < ht){
                    cnt-=(ht-arr[i][j]);
                    sum+=((ht-arr[i][j]));
                }
            }
        }
        
        if (cnt >= 0){
            if (result_time > sum){
                result_time = sum;
                result_ht = ht;
            }
        }
        
        ht--;
    }
    cout<<result_time<<" "<<result_ht;
}
