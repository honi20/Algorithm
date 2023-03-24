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

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    ll n, a, b, c;
    vector <ll> arr;
    ll result = 0;
    
    cin>>n;
    result = n;
    
    for (ll i=0; i<n; i++){
        cin>>a;
        arr.push_back(a);
        
        
    }
    cin>>b>>c;
    
    for (ll i=0; i<arr.size(); i++){
        // 총감독관
        arr[i] -= b;
        if (arr[i] <= 0)
            continue;
        
        // 부감독관
        result += (arr[i]/c);
        if (arr[i] % c != 0){
            result++;
        }
    }
    
    cout<<result;
}
