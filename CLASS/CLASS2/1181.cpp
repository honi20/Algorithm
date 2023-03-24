#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <set>
using namespace std;
typedef long long ll;
#define MAX 1000000001

bool cmp (pair<int,string> a, pair<int,string> b){
    if (a.first == b.first)
        return a.second<b.second;
    return a.first<b.first;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n;
    string str;
    vector <pair<int,string>> v;
    set<string> s;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>str;
        if (s.find(str) == s.end()){
            v.push_back({str.size(),str});
            s.insert(str);
        }
    }
    sort(v.begin(),v.end(),cmp);
    
    for (int i=0;i<v.size();i++){
        cout<<v[i].second<<"\n";
    }
}
