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

ll n, m;
ll arr[1000002] = {0,};

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
   
    ll maxval = 0;

    cin>>n>>m;
    for (int i=0; i<n; i++){
        cin>>arr[i];
        maxval = max(maxval, arr[i]);
    }
    
    ll s = 0, e = maxval;
    ll result = 0;
    while (s <= e){
        ll mid = (s + e)/2;
        ll sum = 0;
        
        for (int i=0; i<n; i++){
            if (arr[i] > mid)
                sum += (arr[i] - mid);
        }
        
        if (sum >= m){
            result = mid;
            s = mid+1;
        }
        else
            e = mid-1;
    }
    cout<<result;
}

