//
//  17968.cpp
//  icpc_prac_22
//
//  Created by 최예헌 on 2022/10/10.
//
#include <iostream>
#include <vector>
#include <set>
using namespace std;
#define MAX 9876543210
typedef pair<int,int> pii;

int main() {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    int dp[1002];
    
    cin>>n;
    dp[0] = dp[1] = 1;
    for (int i=2;i<=n;i++){
        set<int> s;
        for (int k=1; k*2 <= i; k++){
            s.insert(2*dp[i-k]-dp[i-2*k]);
        }
        for (int k=1;k<=1005;k++){
            if (s.find(k) == s.end()){
                dp[i]=k;
                break;
            }
        }
    }
    cout<<dp[n];
}

