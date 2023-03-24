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

bool cmp(tuple<int,string,int> a,tuple<int,string,int> b){
    if (get<0>(a) == get<0>(b))
        return get<2>(a) < get<2>(b);
    return get<0>(a) < get<0>(b);
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,x;
    string y;
    vector <tuple<int,string,int>> v;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x>>y;
        v.push_back({x,y,i});
    }
    sort(v.begin(),v.end(),cmp);
    for (int i=0;i<v.size();i++){
        cout<<get<0>(v[i])<<" "<<get<1>(v[i])<<"\n";
    }
}
