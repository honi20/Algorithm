#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <cctype>
#include <stack>
#include <map>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios_base::sync_with_stdio(false);
    
    ll n,m,x;
    map <ll,ll> map;
    vector <ll> v;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x;
        if (map.find(x) == map.end()){
            map[x]=1;
        }
        else
            map[x]++;
    }
    cin>>m;
    for (int i=0;i<m;i++){
        cin>>x;
        v.push_back(x);
    }
    
    for (int i=0;i<v.size();i++){
        if (map.find(v[i])==map.end())
            cout<<"0 ";
        else
            cout<<map[v[i]]<<" ";
    }
}
