//
//  1929.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
using namespace std;
typedef long long ll;

ll sieve[1000002];

void init(){
    for (ll i=2; i<1000002; i++){
        if (sieve[i])       //0이 아니면 합성수임
            continue;
        
        for (ll j=i*i; j<1000002; j+=i)    //시간 복잡도 보장
        //for (ll j=i+i; j<101010; j+=i)  //응용문제에서 주로 사용
            sieve[j]=1;
    }
}

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int a,b;
    cin>>a>>b;
    init();
    sieve[1]=1;
    for (int i=a;i<=b;i++){
        if (sieve[i] == 0)
            cout<<i<<"\n";
    }
}
