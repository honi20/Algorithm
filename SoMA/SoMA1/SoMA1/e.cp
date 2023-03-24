#include <iostream>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;

int n, m;

void solve(int idx, int cnt, vector <int> v){
    if (cnt == m){
        for (int i=0; i<v.size(); i++)
            cout<<v[i]<<" ";
        cout<<"\n";
        return;
    }
    
    for (int i=idx; i<=n; i++){
        vector <int> vv = v;
        vv.push_back(i);
        solve(i, cnt+1, vv);
    }
    return;
}


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=1; i<=n; i++){
        vector <int> v;
        v.push_back(i);
        solve(i, 1, v);
    }
    
    
    return 0;
}
