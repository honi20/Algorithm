#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;
#define MAX 1000000001

ll gcd(ll x, ll y){         // 최대공약수
    if (!y)
        return x;
    return gcd(y, x % y);
}

// 최소공배수 = x * y / gcd(x,y)

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int a,b;
    cin>>a>>b;
    cout<<gcd(a,b)<<"\n"<<a*b/gcd(a,b);
    
}
