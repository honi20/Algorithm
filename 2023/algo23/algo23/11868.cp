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
    
    ll n, p;
    ll result = 0;
    
    cin>>n;
    
    while (n--){
        cin>>p;
        result ^= p;
    }
    
    if (result == 0)
        cout<<"cubelover";
    else
        cout<<"koosaga";
    
    return 0;
}

