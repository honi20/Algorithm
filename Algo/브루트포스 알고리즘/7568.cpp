#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    int x,y;
    vector <pair<int,int>> v;
    
    cin>>n;
    for (int i=0;i<n;i++){
        cin>>x>>y;
        v.push_back({x,y});
    }
    
    for (int i=0;i<n;i++){
        int cnt=0;
        for (int j=0;j<n;j++){
            if (i==j)
                continue;
            
            if (v[i].first<v[j].first && v[i].second<v[j].second)
                cnt++;
        }
        cout<<cnt+1;
        if (i!=(n-1))
            cout<<" ";
    }
}
