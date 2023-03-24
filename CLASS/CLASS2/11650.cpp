#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <map>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,x,y;
    vector <pair<int,int>> v;
    
    cin>>n;
    while (n--){
        cin>>x>>y;
        v.push_back({x,y});
    }
    sort(v.begin(),v.end());
    for (int i=0;i<v.size();i++)
        cout<<v[i].first<<" "<<v[i].second<<"\n";
}
