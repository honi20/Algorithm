#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int n, t, p;
vector <pair<int,int>> arr;
int result = 0;

void solve(int idx, int finishDay, int cost){
//    cout<<idx<<" "<<finishDay<<" "<<cost<<"\n";
    if (idx == n){
        result = max(result, cost);
        return;
    }
    
    // 상담함
    if (idx+1 > finishDay && idx + arr[idx+1].first <= n)
        solve(idx+1, idx+arr[idx+1].first, cost+arr[idx+1].second);
    
    // 상담안함
    solve(idx+1, finishDay, cost);
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    arr.push_back({0,0});
    for (int i=0; i<n; i++){
        cin>>t>>p;
        arr.push_back({t, p});
    }
    
    solve(0, 0, 0);
    cout<<result;
}

