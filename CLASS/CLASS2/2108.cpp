//
//  2108.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
#include <map>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

bool cmp(pair<int,int> a, pair<int,int> b){
    if (a.first == b.first)
        return a.second < b.second;
    return a.first > b.first;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n,x;
    double sum=0;
    int mmax=-4111, mmin=4111;
    map <int,int> m;
    vector<int> v;
    vector<pair<int,int>> cnt;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x;
        sum+=x;
        v.push_back(x);
        
        if (m.find(x) != m.end())
            m[x]++;
        else
            m[x]=1;
        
        mmax = max(mmax, x);
        mmin = min(mmin, x);
    }
    // 평균
    int temp = round(sum/n);
    if (temp == 0)
        cout<<"0\n";
    else
        cout<<temp<<"\n";
    
    // 중앙값
    sort(v.begin(), v.end());
    cout<<v[n/2]<<"\n";
    
    // 최빈값
    for (auto iter=m.begin(); iter!=m.end(); iter++)
        cnt.push_back({iter->second,iter->first});
    
    sort(cnt.begin(), cnt.end(), cmp);
    if (cnt[0].first == cnt[1].first)
        cout<<cnt[1].second<<"\n";
    else
        cout<<cnt[0].second<<"\n";
    
    // 범위
    cout<<abs(mmax-mmin)<<"\n";
}
