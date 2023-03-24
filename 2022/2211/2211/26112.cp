#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <map>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 502
#define INF 987654321

ll n, p, q;
map<string, vector <ll>> m;
string arr1[MAX];
string arr2[MAX];
bool visited[MAX];


// P1 -> P2
ll solve1(){
    ll max1 = 0, max2 = 0;
    ll dp1[MAX][2] = {0,};
    ll dp2[MAX][2] = {0,};
    
    // init
    for (int i=1;i<=n;i++){
        visited[i] = false;
    }
    
    for (ll i=1; i<=p; i++){
        dp1[i][0] = 1;
        dp1[i][1] = m[arr1[i]][0];
    }

    for (ll i=1; i<=q; i++){
        dp2[i][0] = 1;
        dp2[i][1] = m[arr2[i]][0];
    }
    max1 = dp1[1][0];
    max2 = dp2[1][0];
    
    // P1
    for (int i=2; i<=p; i++){
        vector <ll> v = m[arr1[i]];
        
        for (int j=1; j<i; j++){
            
            for (int k=0; k<v.size(); k++){
                if (visited[v[k]])
                    continue;
                
                if (dp1[j][1] < v[k]){
                    if (dp1[j][0]+1 > dp1[i][0]){
                        dp1[i][0] = dp1[j][0] + 1;
                        dp1[i][1] = v[k];
                    }
                    
                    else if (dp1[j][0] + 1 == dp1[i][0] && dp1[i][1] > v[k]){
                        dp1[i][1] = v[k];
                    }
                    break;
                }
            }
        }
        visited[dp1[i][1]] = true;
        max1 = max(max1, dp1[i][0]);
    }
    
    // P2
    for (int i=2; i<=q; i++){
        vector <ll> v = m[arr2[i]];
        
        for (int j=1; j<i; j++){
            for (int k=0; k<v.size(); k++){
                if (visited[v[k]])
                    continue;
                
                if (dp2[j][1] < v[k]){
                    if (dp2[j][0]+1 > dp2[i][0]){
                        dp2[i][0] = dp2[j][0] + 1;
                        dp2[i][1] = v[k];
                    }
                    
                    else if (dp2[j][0] + 1 == dp2[i][0] && dp2[i][1] > v[k]){
                        dp2[i][1] = v[k];
                    }
                    break;
                }
            }
        }
        visited[dp2[i][1]] = true;
        max2 = max(max2, dp2[i][0]);
    }
    return max1+max2;
}

// P2 -> P1
ll solve2(){
    ll max1 = 0, max2 = 0;
    ll dp1[MAX][2] = {0,};
    ll dp2[MAX][2] = {0,};
    
    // init
    for (int i=1;i<=n;i++){
        visited[i] = false;
    }
    
    for (ll i=1; i<=p; i++){
        dp1[i][0] = 1;
        dp1[i][1] = m[arr1[i]][0];
    }

    for (ll i=1; i<=q; i++){
        dp2[i][0] = 1;
        dp2[i][1] = m[arr2[i]][0];
    }
    max1 = dp1[1][0];
    max2 = dp2[1][0];
    
    // P2
    for (int i=2; i<=q; i++){
        vector <ll> v = m[arr2[i]];
        
        for (int j=1; j<i; j++){
            for (int k=0; k<v.size(); k++){
                if (visited[v[k]])
                    continue;
                
                if (dp2[j][1] < v[k]){
                    if (dp2[j][0]+1 > dp2[i][0]){
                        dp2[i][0] = dp2[j][0] + 1;
                        dp2[i][1] = v[k];
                    }
                    
                    else if (dp2[j][0] + 1 == dp2[i][0] && dp2[i][1] > v[k]){
                        dp2[i][1] = v[k];
                    }
                    break;
                }
            }
        }
        visited[dp2[i][1]] = true;
        max2 = max(max2, dp2[i][0]);
    }
    
    // P1
    for (int i=2; i<=p; i++){
        vector <ll> v = m[arr1[i]];
        
        for (int j=1; j<i; j++){
            
            for (int k=0; k<v.size(); k++){
                if (visited[v[k]])
                    continue;
                
                if (dp1[j][1] < v[k]){
                    if (dp1[j][0]+1 > dp1[i][0]){
                        dp1[i][0] = dp1[j][0] + 1;
                        dp1[i][1] = v[k];
                    }
                    
                    else if (dp1[j][0] + 1 == dp1[i][0] && dp1[i][1] > v[k]){
                        dp1[i][1] = v[k];
                    }
                    break;
                }
            }
        }
        visited[dp1[i][1]] = true;
        max1 = max(max1, dp1[i][0]);
    }
    return max1+max2;
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string x;
    
    cin>>n>>p>>q;
    for (ll i=1; i<=n; i++){
        cin>>x;
        m[x].push_back(i);
    }
    
    // P1
    for (ll i=1; i<=p; i++){
        cin>>arr1[i];
    }

    // P2
    for (ll i=1; i<=q; i++){
        cin>>arr2[i];
    }
    cout<<max(solve1(), solve2());
}

