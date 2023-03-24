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

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,x,y;
    vector <pair <pair<int,int>,int>> v;        //x,y,n
    int arr[52];
    
    cin>>n;
    for (int i=1;i<=n;i++){
        cin>>x>>y;
        v.push_back({{x,y},i});
    }
    sort(v.begin(),v.end(),greater<>());
    
    for (int i=0;i<v.size();i++){
        int cnt=0;
        for (int j=0;j<v.size();j++){
            if (v[j].first.first > v[i].first.first && v[j].first.second > v[i].first.second)
                cnt++;
        }
        arr[v[i].second]=cnt+1;
    }
    for (int i=1;i<=n;i++)
        cout<<arr[i]<<" ";
}
