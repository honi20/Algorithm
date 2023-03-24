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
vector <int> v;
int vsize = 0;
bool visited[10002];

void solve(int idx, int cnt, vector <int> arr){
    arr.push_back(v[idx]);
    if (cnt == m){
        for (int i=0; i<arr.size(); i++)
            cout<<arr[i]<<" ";
        cout<<"\n";
        return;
    }
    for (int i=0; i<vsize; i++){
        if (!visited[i]){
            visited[i] = true;
            solve(i, cnt+1, arr);
            visited[i] = false;
        }
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
 
    cin>>n>>m;
    for (int i=1; i<=n; i++){
        cin>>x;
        v.push_back(x);
    }
    sort(v.begin(), v.end());
    v.erase(unique(v.begin(), v.end()), v.end());
    vsize = v.size();
    
    vector<int> arr;
    for (int i=0; i<vsize; i++){
        visited[i] = true;
        solve(i, 1, arr);
        visited[i] = false;
    }
}

