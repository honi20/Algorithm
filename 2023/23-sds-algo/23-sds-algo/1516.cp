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
#define INF 9876543210

ll n;
vector <ll> v[505];
ll cnt[505] = {0,};
ll buildtime[505] = {0,};

void solve(){
    queue <ll> q;
    
    for (ll i=1; i<=n; i++){
        if (cnt[i] == 0)
            q.push(i);
    }
    
    while (!q.empty()){
        ll nownode = q.front();
        q.pop();
        
        cnt[nownode] = INF;
        
        for (ll i=0; i<v[nownode].size(); i++){
            ll nextnode = v[nownode][i];
            cnt[nextnode]--;
            
            ll maxval = 0;
            if (cnt[nextnode] == 0){
                for (ll j=1; j<=n; j++){
                    if (j == nextnode)
                        continue;
                    
                    bool flag = false;
                    for (ll k=0; k<v[j].size(); k++){
                        if (v[j][k] == nextnode){
                            flag = true;
                            break;
                        }
                    }
                    if (flag)
                        maxval = max(maxval, buildtime[j]);
                }
                
                buildtime[nextnode] += maxval;
//                cout<<nownode<<" "<<nextnode<<" "<<maxval<<"*\n";
                q.push(nextnode);
            }
        }
    }
    
    for (ll i=1; i<=n; i++)
        cout<<buildtime[i]<<"\n";
    
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll prebuild;
    cin>>n;
    
    for (ll i=1; i<=n; i++){
        cin>>buildtime[i];
        
        while (cin>>prebuild){
            if (prebuild == -1)
                break;
            
            cnt[i]++;
            v[prebuild].push_back(i);
        }
    }
    solve();
}

