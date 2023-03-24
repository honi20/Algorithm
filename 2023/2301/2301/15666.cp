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

int n, m, x;
int arrsize;
vector <int> arr;

void solve(int idx, int cnt, vector<int> v){
    v.push_back(arr[idx]);
    if (cnt == m){
        for (int i=0; i<v.size(); i++)
            cout<<v[i]<<" ";
        cout<<"\n";
        return;
    }
    
    for (int i=idx; i<arrsize; i++)
        solve(i, cnt+1, v);
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n>>m;
    for (int i=0; i<n; i++){
        cin>>x;
        arr.push_back(x);
    }
    sort(arr.begin(), arr.end());
    arr.erase(unique(arr.begin(), arr.end()), arr.end());
    arrsize = arr.size();
    
    vector<int> v;
    for (int i=0; i<arrsize; i++)
        solve(i, 1, v);
}

