#include <iostream>
#include <algorithm>
#include <vector>
#include <cmath>
#include <string>
#include <map>
using namespace std;
typedef long long ll;
typedef pair<int, int> pii;
#define MAX 10002
#define INF 9876543210

int n, x;
map<ll,ll> m;
ll arr[2000002] = {0,};

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    cin>>n;
    for (int i=0; i<n; i++){
        cin>>x;
        
        for (int j=1; j * j <= x; j++){
            if (x % j == 0){
                arr[j]++;
                
                if (j != x/j){
                    arr[x/j]++;
                }
            }
        }
    }
    
    ll result = 0;
    for (int i=1; i<2000001; i++){
        if (arr[i] > 1){
            result = max(result, i*arr[i]);
        }
    }
    cout<<result;
}

