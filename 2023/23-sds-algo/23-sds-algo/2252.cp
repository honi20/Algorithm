#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <queue>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 987654321

int n, m;
vector <int> v[32002];
int cnt[32002] = {0,};

void solve(){
    vector <int> result;
    queue <int> q;
    
    for (int i=1; i<=n; i++){
        if (cnt[i] == 0)
            q.push(i);
    }
    
    while (!q.empty()){
        int nownode = q.front();
        q.pop();
        
        result.push_back(nownode);
        cnt[nownode] = INF;
        for (int i=0; i<v[nownode].size(); i++){
            cnt[v[nownode][i]]--;
            if (cnt[v[nownode][i]] == 0){
                q.push(v[nownode][i]);
            }
        }
    }
    
    for (int i=0; i<result.size(); i++)
        cout<<result[i]<<" ";
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int a, b;
    
    cin>>n>>m;
    for (int i=0; i<m; i++){
        cin>>a>>b;
        cnt[b]++;
        v[a].push_back(b);
    }
    solve();
}

