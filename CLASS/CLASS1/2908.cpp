#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int a,b;
    int a1=0,b1=0;
    cin>>a>>b;
    
    for (int i=0;i<3;i++){
        a1=a1*10+a%10;
        a/=10;
    }
    for (int i=0;i<3;i++){
        b1=b1*10+b%10;
        b/=10;
    }
    
    if (a1>b1)
        cout<<a1;
    else
        cout<<b1;
}
