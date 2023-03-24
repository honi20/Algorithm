#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <map>
#include <set>
using namespace std;
typedef long long ll;
#define MAX 1000000001

ll sieve[1005];
void init(){
    sieve[1]=1;
    for (ll i=2; i<1005; i++){
        if (sieve[i])       //0이 아니면 합성수임
            continue;
        for (ll j=i*i; j<1005; j+=i)    //시간 복잡도 보장
        //for (ll j=i+i; j<101010; j+=i)  //응용문제에서 주로 사용
            sieve[j]=1;
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,x;
    int cnt=0;
    cin>>n;
    init();
    while (n--){
        cin>>x;
        if (sieve[x]==0)
            cnt++;
    }
    cout<<cnt;
}
