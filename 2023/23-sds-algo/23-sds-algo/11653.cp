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

void f(ll n){
    vector <ll> v;
    for (ll i=2; i*i<=n; i++){
        if (n%i)
            continue;
        while ((n%i)==0){
            v.push_back(i);
            n/=i;
        }
    }
    if (n!=1)
        v.push_back(n);
    for (auto nxt:v)
        cout<<nxt<<"\n";
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    cin>>n;
    f(n);
}
