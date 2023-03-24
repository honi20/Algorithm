#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#include <algorithm>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string key, password;
    int num[102] = {0,};        // 정렬 순서
    char result[102][102];
    
    cin>>key>>password;
    
    vector <pair<char,int>> v;
    for (int i=0; i<key.size(); i++){
        v.push_back({key[i],i});
    }
    
    sort(v.begin(), v.end());
    for (int i=0; i<v.size(); i++)
        num[i] = v[i].second;
    
    ll passize = password.size()/key.size();
    int idx = 0;
    
    for (int i=0; i<password.size(); i++){
        if (i != 0 && i % passize == 0)
            idx++;
        
//        cout<<idx<<" "<<num[idx]<<" "<<i%passize<<" "<<password[i]<<"\n";
        result[i % passize][num[idx]] = password[i];
    }
    
    for (int i=0; i<passize; i++){
        for (int j=0; j<key.size(); j++)
            cout<<result[i][j];
    }
    
    
    
    return 0;
}
